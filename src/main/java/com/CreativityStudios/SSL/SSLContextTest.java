package com.CreativityStudios.SSL;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLContextSpi;
import java.security.Provider;

public class SSLContextTest extends SSLContext {
    protected SSLContextTest(SSLContextSpi contextSpi, Provider provider, String protocol) {
        super(contextSpi, provider, protocol);
    }
}
