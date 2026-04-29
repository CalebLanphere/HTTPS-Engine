package com.CreativityStudios.HTTPS.Authentication;

import com.CreativityStudios.HTTPS.HTTPHeaders.HTTPRequestHeaders;
import com.CreativityStudios.HTTPS.HTTPHeaders.HTTPResponseHeaders;
import com.CreativityStudios.HTTPS.HTTPStatus;
import com.CreativityStudios.HTTPS.SessionManager;
import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DigestAuthenticator extends Authenticator {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final SessionManager sessions = new SessionManager();

    @Override
    public Result authenticate(HttpExchange exchange) {
        LOGGER.log(Level.INFO, exchange.getRequestMethod());
        HttpPrincipal principal = exchange.getPrincipal();
        String session = null;
        LOGGER.log(Level.INFO, exchange.getRequestMethod());
        LOGGER.log(Level.INFO, String.valueOf(exchange.getRequestHeaders()));

        if(exchange.getRequestHeaders().get("Cookie") != null) {
            session = String.valueOf(exchange.getRequestHeaders().get("Cookie"));
        }

        if(sessions.isSessionValid(session)) {
            System.out.println(session);
            LOGGER.log(Level.INFO, "Authenticated");
            HttpPrincipal newAuthPrincipal = new HttpPrincipal("testing", String.valueOf(exchange.getRequestURI()).substring(1) + "@localhost");

            return new Success(newAuthPrincipal);
        }
        System.out.println(exchange.getRequestHeaders().get(HTTPRequestHeaders.AUTHORIZATION) == null);

        // TODO MAKE SESSIONS WORK
        if(exchange.getRequestHeaders().get(HTTPRequestHeaders.AUTHORIZATION) != null) {
            LOGGER.log(Level.INFO, "failed isSessionValid");
            String authProtocol = exchange.getRequestHeaders().get(HTTPRequestHeaders.AUTHORIZATION).getFirst().split(" ")[0];

            switch (authProtocol) {
                case AuthenticationMethods.DIGEST:
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

                            if(session != null) {
//                                sessions.add("session=1");
                                return new Success(newAuthPrincipal);
                            } else {
                                exchange.getResponseHeaders().add("Set-Cookie", "session=1");
                                return new Success(newAuthPrincipal);
                            }
                        }
                        exchange.getResponseHeaders().add(HTTPResponseHeaders.WWW_AUTHENTICATE, "Digest realm=\"" + String.valueOf(exchange.getRequestURI()).substring(1) + "@localhost\", nonce=\"" + LocalDateTime.now() + "\", algorithm=\"MD5\"");
                        return new Failure(HTTPStatus.UNAUTHORIZED);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                default:
                    exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                    exchange.getResponseHeaders().add("Access-Control-Allow-Credentials", "true");
                    exchange.getResponseHeaders().add(HTTPResponseHeaders.WWW_AUTHENTICATE, "Digest realm=\"" + String.valueOf(exchange.getRequestURI()).substring(1) + "@localhost\", nonce=\"" + LocalDateTime.now() + "\", algorithm=\"MD5\"");
                    return new Retry(HTTPStatus.UNAUTHORIZED);
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
