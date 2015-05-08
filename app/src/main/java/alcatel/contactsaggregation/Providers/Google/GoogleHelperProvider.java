package alcatel.contactsaggregation.Providers.Google;

import android.util.Log;

import alcatel.contactsaggregation.Providers.HelperProvider;

/**
 * Created by elfaus on 30/04/2015.
 */
class GoogleHelperProvider {

    private static GoogleHelperProvider instance = new GoogleHelperProvider();

    // OAuth vars
    private static String CLIENT_ID = "211996266959-785qp6avh2lvc498nalctfhpk2o90g4p.apps.googleusercontent.com";
    private static String MAIL_ADDRESS = "211996266959-785qp6avh2lvc498nalctfhpk2o90g4p@developer.gserviceaccount.com";
    private static String SECRET_ID = "i7wErbI7JT-tyYxq8o0p7DdT";
    private static String APPLICATION_NAME = "ContactAggregation";
    private static String OAUTH_ENDPOINT = "https://accounts.google.com/o/oauth2/auth";

    // OAuth parameters
    //private long _timeout; // timestamp of timeout token
    //private String _accessToken; // current access token

    // - - - - - - - - - - - - - - - - - -

    /**
     * @return Current instance of the google helper
     */
    protected static GoogleHelperProvider getInstance() {
        if (instance == null) {
            instance = new GoogleHelperProvider();
        }
        return instance;
    }

    // TODO : debug OAuth
    /**
     * Prepare the uri to use in the auth endpoint
     * @param loginHint A google user account (gmail address)
     * @return A valid uri to be use for the auth endpoint
     */
    protected String getAuthURI(String loginHint) {
        // About scope values : https://developers.google.com/+/api/oauth#login-scopes
        // About contact scope : https://developers.google.com/google-apps/contacts/v3/

        String scope = "https://www.google.com/m8/feeds"; //read & write scope
        String redirect_uri = "http://127.0.0.1";
        String state = HelperProvider.initToken(32); // init token
        String uri = "%s?scope=%s&state=%s&login_hint=%s&redirect_uri=%s&response_type=token&client_id=%s"; // request uri pattern

        String formattedUri = String.format(uri, GoogleHelperProvider.OAUTH_ENDPOINT, scope, state, loginHint, redirect_uri, GoogleHelperProvider.CLIENT_ID);

        Log.d("[Google]", formattedUri);

        return formattedUri;
    }

    /**
     * Enable to get a new token access for a specific user
     * If the old token is still up to date, do nothing
     * @param loginHint A google user account (gmail address)
     */
    protected void updateAccessToken(String loginHint) {

        String authUri = this.getAuthURI(loginHint);                // prepare the auth endpoint uri
        long currentTimeStamp = System.currentTimeMillis()/1000;    // get the current system timestamp

        // update the token only if its outdated
        if (currentTimeStamp >= GoogleProvider.getInstance().getTimeout()) {



        }
    }

}
