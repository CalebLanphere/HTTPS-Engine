/**
 * Opens and runs an HTTPSServlet
 *
 * This servlet establishes classes based off Endpoints.json
 *
 * @author Caleb Lanphere
 *
 * Copyright 2026 Caleb Lanphere, All Rights Reserved
 */

package com.CreativityStudios.HTTPS;

import com.CreativityStudios.File.FileManager;
import com.CreativityStudios.File.FileReader;
import com.CreativityStudios.HTTPS.Authentication.Basic.BasicAuthentication;
import com.CreativityStudios.HTTPS.Endpoint.EndpointConfiguration;
import com.CreativityStudios.HTTPS.Endpoint.HTTPEndpointGenerator;
import com.CreativityStudios.JSON.JSONReader;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTTPSServlet {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Gets all listed endpoints under the Endpoints.json configuration file and all endpoints
     * that are generated from the files and subdirectories inside the ./web directory
     * @return EndpointConfiguration[] an array of all EndpointConfiguration's read from the configuration file
     * @throws IOException If a file access operation fails or the file is not readable from its stored location
     */
    private static EndpointConfiguration[] getEndpointsFromFile() throws IOException{
        String endpointFileLocation = "src/main/resources/HTTPEndpoints/Endpoints.json";
        EndpointConfiguration[] configs;

        if(!FileManager.doesFileExist(endpointFileLocation)) {
            LOGGER.log(Level.SEVERE, "Endpoints.json does not exist at directory: "
                    + FileManager.getAbsolutePath(endpointFileLocation));
            throw new FileNotFoundException();
            // TODO when file is not found, check if application is on first startup or if other files are present that imply previous usage
        }
        if(!FileManager.isReadable(endpointFileLocation)) {
            LOGGER.log(Level.SEVERE, "Endpoints.json cannot be read; Is the file read protected?");
            throw new IOException();
        }

        FileReader reader = new FileReader(endpointFileLocation);
        EndpointConfiguration[] predeclaredConfigs =
                JSONReader.parseJsonArrayAsEndpointConfigurations(JSONReader.jsonStringToJsonArray(reader.readFileToString()));
        EndpointConfiguration[] runtimeConfigs = new HTTPEndpointGenerator("src/main/resources/web/").generateEndpointsInsideFolder();

        configs = new EndpointConfiguration[predeclaredConfigs.length + runtimeConfigs.length];

        for(int i = 0, j = 0; i < configs.length; i++) {
            if(i < predeclaredConfigs.length) {
                configs[i] = predeclaredConfigs[i];
            } else {
                configs[i] = runtimeConfigs[j];
                j++;
            }
        }

        return configs;
    }

    /**
     * Creates the SSL context that the HTTPS Server uses to authenticate secure connections
     * @return SSLContext the SSLContext created
     * @throws NoSuchAlgorithmException If the algorithms requested in getInstance() do not exist
     * @throws KeyStoreException If the KeyStore does not exist
     * @throws IOException If the file stream loading the KeyStores is interrupted
     * @throws UnrecoverableKeyException If they keys are corrupted
     * @throws CertificateException If the certificate pulled from the KeyStore has an error
     * @throws KeyManagementException If the Key is invalid
     */
    private SSLContext createSSLContext() throws NoSuchAlgorithmException, KeyStoreException, IOException, UnrecoverableKeyException, CertificateException, KeyManagementException {
        // Creates an SSLContext with the TLS protocol
        SSLContext sslContext = SSLContext.getInstance("TLS");

        KeyStore keys = KeyStore.getInstance("JKS");
        // Loads a KeyStore file, generated with the command
        // "keytool -keystore 'nameoffile' -genkey -alias 'keystore name' -keyalg 'algorithm of the key'"
        // Last generated keystore used 'RSA' for the algorithm, 'clientkeystore' as the file name,
        // and 'server' for the alias
        keys.load(new FileInputStream("src/main/resources/SSLKeystores/clientkeystore"), "Password".toCharArray());

        // Creates a factory that can send around copies of the KeyStore
        KeyManagerFactory keyFactory = KeyManagerFactory.getInstance("SunX509");
        keyFactory.init(keys, "Password".toCharArray());

        // Acts as a trust manager for secure communications over sockets, based off
        // the KeyStore
        TrustManagerFactory trustFactory = TrustManagerFactory.getInstance("SunX509");
        trustFactory.init(keys);

        // Initializes the context and adds a SecureRandom number
        sslContext.init(keyFactory.getKeyManagers(), trustFactory.getTrustManagers(), new SecureRandom());

        return sslContext;
    }

    /**
     * Starts the HTTPS servlet
     *
     * First, the HTTPS servlet is created, binding it to the local address at port 8080, with the
     * server devices backlog value used
     * Second, the SSL context is created and prepared for use with each endpoint
     * Third, the executor is set to create new Threads for each request and reuse previously created threads if possible
     *
     * Fourth, all user-created endpoints are loaded from the Endpoints.json file and
     * all files from the ./web directory, including subdirectories
     * Then, the endpoints are mapped with an authentication requirement if they are dictated to need one
     * Finally, the server is started
     * @throws IOException If reading getEndpointsFromFile() method has an exception
     * @throws ClassNotFoundException If the HTTPHandler class requested cannot be found
     * @throws NoSuchMethodException If the HTTPHandler has no such method to be called for getConstructor()
     * @throws InvocationTargetException If the HttpHandler constructor throws an exception
     * @throws InstantiationException If the newInstance() method being called cannot be called on the class that is attempted to be created
     * @throws IllegalAccessException If the newInstance() method being called does not have permission to create the new instance
     */
    public void start() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException, KeyStoreException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        HttpsServer httpsServer = HttpsServer.create(new InetSocketAddress(8080), 0);
        SSLContext sslContext = createSSLContext();
        httpsServer.setExecutor(Executors.newCachedThreadPool());

        for (EndpointConfiguration config : getEndpointsFromFile()) {
            if(config.isAuthRequired()) {
                httpsServer.createContext(config.getEndpoint(),
                        config.getInstanceOfHandler())
                        .setAuthenticator(new BasicAuthentication());
                httpsServer.setHttpsConfigurator(new HttpsConfigurator(sslContext));
            } else {
                httpsServer.createContext(config.getEndpoint(), config.getInstanceOfHandler());
                httpsServer.setHttpsConfigurator(new HttpsConfigurator(sslContext));

            }
        }

        httpsServer.start();
    }
}
