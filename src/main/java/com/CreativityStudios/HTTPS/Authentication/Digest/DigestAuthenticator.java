package com.CreativityStudios.HTTPS.Authentication.Digest;

import com.CreativityStudios.HTTPS.Authentication.AuthenticationResult;
import com.CreativityStudios.HTTPS.HTTPHeaders.HTTPRequestHeaders;
import com.CreativityStudios.HTTPS.HTTPHeaders.HTTPResponseHeaders;
import com.CreativityStudios.HTTPS.HTTPHeaders.HTTPStatus;
import com.CreativityStudios.HTTPS.Sessions.Cookie.CookieSessionManager;
import com.CreativityStudios.HTTPS.Sessions.Cookie.CookieToken;
import com.CreativityStudios.HTTPS.Sessions.Token;
import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DigestAuthenticator extends Authenticator {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public Result authenticate(HttpExchange exchange) {
        Headers headers = exchange.getRequestHeaders();
        Token token = new CookieToken();
        token.setToken(headers);

        if(CookieSessionManager.isSessionValid(token)) {
            LOGGER.log(Level.INFO, "Authenticated");
            HttpPrincipal newAuthPrincipal = new HttpPrincipal("testing", String.valueOf(exchange.getRequestURI()).substring(1) + "@localhost");

            return new Success(newAuthPrincipal);
        }

        // TODO MAKE SESSIONS WORK
        if(exchange.getRequestHeaders().get(HTTPRequestHeaders.AUTHORIZATION) != null) {
                    String[] authUnsplitValues = exchange.getRequestHeaders().get(HTTPRequestHeaders.AUTHORIZATION).getFirst().split(", ");
                    HashMap<String, String> authParams = new HashMap<>();
                    authUnsplitValues[0] = authUnsplitValues[0].substring(7);
                    String method = exchange.getRequestMethod();


                    for (int i = 0; i < authUnsplitValues.length; i++) {
                        String[] splitAuthParams = authUnsplitValues[i].split("=");

                        String key = splitAuthParams[0];
                        String value = splitAuthParams[1].substring(1, splitAuthParams[1].length() - 1);

                        authParams.put(key, value);
                    }
                    authParams.put("method", method);
                    authParams.put("url", String.valueOf(exchange.getRequestURI()));

                    try {
                        if (validateDigestCredentials(authParams)) {
                            HttpPrincipal newAuthPrincipal = new HttpPrincipal("testing", String.valueOf(exchange.getRequestURI()).substring(1) + "@localhost");

                            if(token.getToken() != null) {
                                return new Success(newAuthPrincipal);
                            } else {
                                CookieSessionManager.createSessionToken(exchange, new AuthenticationResult(true));
                                return new Success(newAuthPrincipal);
                            }
                        }
                        exchange.getResponseHeaders().add(HTTPResponseHeaders.WWW_AUTHENTICATE, "Digest realm=\"" + String.valueOf(exchange.getRequestURI()).substring(1) + "@localhost\", nonce=\"" + LocalDateTime.now() + "\", algorithm=\"MD5\"");
                        return new Failure(HTTPStatus.UNAUTHORIZED);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
        }

        exchange.getResponseHeaders().add(HTTPResponseHeaders.WWW_AUTHENTICATE, "Digest realm=\"" + String.valueOf(exchange.getRequestURI()).substring(1) + "@localhost\", nonce=\"" + LocalDateTime.now() + "\", algorithm=\"MD5\"");
        return new Failure(HTTPStatus.UNAUTHORIZED);
    }

    private boolean validateDigestCredentials(HashMap<String, String> authParams) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(("testing:" + authParams.get("realm") + ":testpassword").getBytes());
        String HA1 = HexFormat.of().formatHex(md5.digest());

        md5.update((authParams.get("method") + ":" + authParams.get("url")).getBytes());
        String HA2 = HexFormat.of().formatHex(md5.digest());

        md5.update((HA1 + ":" + authParams.get("nonce") + ":" + HA2).getBytes());
        String hash = HexFormat.of().formatHex(md5.digest());

        if(authParams.get("response").equals(hash)) {
            return true;
        }
        return false;
    }

}
