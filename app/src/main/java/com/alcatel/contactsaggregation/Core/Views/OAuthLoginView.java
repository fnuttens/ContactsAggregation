/**
 * Created by Loïc LEUILLIOT on 06/03/2015.
 */

package com.alcatel.contactsaggregation.Core.Views;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.alcatel.contactsaggregation.Providers.Provider;
import com.alcatel.contactsaggregation.R;

public class OAuthLoginView extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth_login_view);

        // Get the webView
        final WebView oAuthLoginWebView = (WebView) findViewById(R.id.activity_oauth_login);

        // Get the Provider OAuth endpoint uri and its instance
        final String oAuthEndpoint = getIntent().getStringExtra("OAuthEndpoint");
        final String provider = getIntent().getStringExtra("Provider");

        // Enable JS for google auth page
        oAuthLoginWebView.getSettings().setJavaScriptEnabled(true);
        // Override redirection comportment
        oAuthLoginWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http://127.0.0.1")) {
                    try {
                        // Get provider instance by reflection
                        Class cls = Class.forName(provider);
                        Method met = cls.getMethod("getInstance", new Class[0]);
                        Object o = met.invoke(null, new Object[0]);

                        // Set the new token
                        ((Provider) o).authCallback(url);
                        ((Provider) o).pullContacts();

                    } catch (ClassNotFoundException e) {
                        Log.e("[OAUTH-LOGIN]", e.getMessage());
                    } catch (IllegalAccessException e) {
                        Log.e("[OAUTH-LOGIN]", e.getMessage());
                    } catch (NoSuchMethodException e) {
                        Log.e("[OAUTH-LOGIN]", e.getMessage());
                    } catch (InvocationTargetException e) {
                        Log.e("[OAUTH-LOGIN]", e.getMessage());
                    }
                }

                return false;
            }
        });

        // Load the web page using the credential uri
        oAuthLoginWebView.loadUrl(oAuthEndpoint);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_oauth_login_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
