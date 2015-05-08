package alcatel.contactsaggregation;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OAuthLoginView extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth_login_view);

        // Get the webView
        WebView oAuthLoginWebView = (WebView) findViewById(R.id.activity_oauth_login);

        // Get the Provider OAuth endpoint uri
        String oAuthEndpoint = getIntent().getStringExtra("setOAuthEndpoint");

        // Enable JS for google auth page
        oAuthLoginWebView.getSettings().setJavaScriptEnabled(true);
        // Override redirection comportment
        oAuthLoginWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("[OAUTH-LOGIN]", url);  // TODO: implement
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
