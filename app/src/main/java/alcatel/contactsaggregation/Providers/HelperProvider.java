package alcatel.contactsaggregation.Providers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by elfaus on 03/04/2015.
 */
public class HelperProvider {

    public static Object getJSONObjectByPath(JSONObject jsonObject, String pathname) {
        Log.d("HelperProvider", "Current pathname is " + pathname);

        String[] pathElement = pathname.trim().split("\\.");

        // check if there is any element in the tree
        if (pathElement.length > 1) {
            // check if the first element is in the current object
            if (jsonObject.has(pathElement[0])) {

                // try to get the next object element.
                JSONObject tmpObject = jsonObject.optJSONObject(pathElement[0]);

                // if it's not an array, we follow the path
                if (tmpObject != null) {
                    String newPathname = pathname.substring(pathname.indexOf(".") + 1, pathname.length());
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

}
