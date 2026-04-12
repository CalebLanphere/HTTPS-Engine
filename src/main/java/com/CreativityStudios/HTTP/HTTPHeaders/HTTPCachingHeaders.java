package com.CreativityStudios.HTTP.HTTPHeaders;

public class HTTPCachingHeaders {
    /** The time (seconds) content has been in cache */
    public static final String CACHE_AGE = "Age";
    /** Rules for caching objects | Can contain multiple options for values */
    public static final String CACHE_RULES = "Cache-Control";
    /** Which cache on the client to clear | Can contain multiple options for values */
    public static final String CLEAR_SITE_DATA = "Clear-Site-Data";
    /** Time to consider content in cache expired */
    public static final String EXPIRES = "Expires";
}
