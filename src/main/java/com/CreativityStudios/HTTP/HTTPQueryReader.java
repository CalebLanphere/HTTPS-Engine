/**
 * HTTPQueryReader class
 *
 * Handles mapping HTTP queries from the URI passed from the HTTPRequest class
 *
 * @author Caleb Lanphere
 *
 * Copyribght 2026 Caleb Lanphere, All Rights Reserved.
 */

package com.CreativityStudios.HTTP;

import java.util.HashMap;

public class HTTPQueryReader {

    /**
     * Parses a string of queries given by the HTTPRequest class into a Map
     *
     * ex. 1) Given the query string test1=4&test2=8
     *      returns {test1=4, test2=8}
     * ex. 2) Given the query string test1=7
     *      returns {test1=7}
     *
     * @param query String containing the queries to parse
     * @return HashMap<String, Object> contains the query type and its value
     */
    public static HashMap<String, Object> parseQuery(String query) {
        HashMap<String, Object> queries = new HashMap<>();

        String[] splitQueries = query.split("&");

        for (String individualQuery : splitQueries) {
            String[] keyValues = individualQuery.split("=");
            queries.put(keyValues[0], keyValues[1]);
        }

        return queries;
    }
}
