package alcatel.contactsaggregation.Providers.Google;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by elfaus on 08/05/2015.
 */
public class GoogleAsyncGet extends AsyncTask<String[], Integer, String> {

    @Override
    protected String doInBackground(String[]... getUrls) {
        String response = "";
        for (String[] url : getUrls) {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url[0]);
            get.addHeader("Authorization", "Bearer " + url[1]);

            try {
                HttpResponse execute = client.execute(get);
                InputStream content = execute.getEntity().getContent();

                BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                String s = "";

                while ((s = buffer.readLine()) != null) {
                    response += s;
                }
            } catch (ClientProtocolException e) {
                Log.e("[GOOGLE-GET]", e.getMessage());
            } catch (IOException e) {
                Log.e("[GOOGLE-GET]", e.getMessage());
            }
        }

        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("[GOOGLE-GET]", result);
        GoogleProvider.getInstance().setContactList(result);
    }
}
