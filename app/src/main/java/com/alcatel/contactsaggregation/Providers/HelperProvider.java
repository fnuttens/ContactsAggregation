package com.alcatel.contactsaggregation.Providers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;

/**
 * Created by Loïc LEUILLIOT on 03/04/2015.
 */
public class HelperProvider {

    public static Object getJSONObjectByPath(JSONObject jsonObject, String pathname) {
        String[] pathElement = pathname.trim().split("\\.");

        // check if there is any element in the tree
        if (pathElement.length > 1) {
            // check if the first element is in the current object
            if (jsonObject.has(pathElement[0])) {

                // try to get the next object element.
                JSONObject tmpObject = jsonObject.optJSONObject(pathElement[0]);

                // if it's not an array, we follow the path
                if (tmpObject != null) {
                    String newPathname = pathname.substring(pathname.indexOf(".")+1, pathname.length());
                    return HelperProvider.getJSONObjectByPath(tmpObject, newPathname);
                }

            } else {
                // item not found
                // TODO : ERROR/DO NOTHING/NULL
                Log.d("HelperProvider", "Unable to find " + pathElement[0] + " in the current JSONObject");
            }
        } else {
            // check if the first element is in the current object
            if (jsonObject.has(pathname)) {

                // try to get the next object element.
                JSONObject tmpObject = jsonObject.optJSONObject(pathname);

                // if it's not an array, we follow the path
                if (tmpObject != null) {
                    return tmpObject;
                } else {
                    JSONArray tmpArray = jsonObject.optJSONArray(pathname);
                    if (tmpArray != null) {
                        return tmpArray;
                    } else {
                        return jsonObject.opt(pathname);
                    }
                }

            } else {
                // item not found
                // TODO : ERROR/DO NOTHING/NULL
                Log.d("HelperProvider", "Unable to find " + pathElement[0] + " in the current JSONObject");
            }
        }

        return null;
    }

    /**
     * Extract parameters from an uri using parameter name as name
     * @param uri An uri from which extract parameters
     * @return The parameters list
     */
    public static HashMap<String, String> getParameters(String uri) {
        // split each parameter in a string array
        String parameterString[] = uri.split("&");
        HashMap<String, String> params = new HashMap<String, String>();

        // store parameter in parameters map
        // first string if the parameter name, second is the parameter value
        for (String p : parameterString) {
            String paramPair[] = p.split("=");
            params.put(paramPair[0], paramPair[1]);
        }

        return params;
    }

    /**
     * Build a random string of 32 chars
     * @param size The chars amount
     * @return A random string of 32 chars
     */
    public static String initToken(int size)
    {
        // Create a state token to prevent request forgery.
        // Store it in the session for later validation.
        return new BigInteger(130, new SecureRandom()).toString(size);
    }

}
