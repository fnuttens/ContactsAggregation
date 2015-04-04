package alcatel.contactsaggregation.Providers.Google;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import alcatel.contactsaggregation.Contact;
import alcatel.contactsaggregation.Providers.HelperProvider;
import alcatel.contactsaggregation.Providers.Provider;
import alcatel.contactsaggregation.StandardFields;

/**
 * Created by elfaus on 06/03/2015.
 */
public class GoogleProvider extends Provider {

    private static GoogleProvider instance = new GoogleProvider();
    private String m_providerContactId;

    // TODO : implement
    private GoogleProvider() {
    }

    public static GoogleProvider getInstance() {
        if (instance == null) {
            instance = new GoogleProvider();
        }
        return instance;
    }

    public String getProviderContactId() {
        return m_providerContactId;
    }

    public void setProviderContactId(String m_providerContactId) {
        this.m_providerContactId = m_providerContactId;
    }

    @Override
    // TODO : implement
    public void deleteContact(Contact c) {

    }

    @Override
    // TODO : implement
    public void deleteContact(List<Contact> c) {

    }

    @Override
    // TODO : implement
    public Contact getContact(Contact c) {

        return null;
    }

    @Override
    // TODO : implement
    public ArrayList<Contact> getContact() {

        ArrayList<Contact> contactList = new ArrayList<>();

        // TODO : debug
        try {
            // TODO : implement remote fetch API
            String in = "{ \"feed\":{ \"category\":[ { \"term\":\"http://schemas.google.com/contact/2008#contact\", \"scheme\":\"http://schemas.google.com/g/2005#kind\" } ], \"updated\":{ \"$t\":\"2015-04-02T14:41:21.710Z\" }, \"xmlns$gd\":\"http://schemas.google.com/g/2005\", \"generator\":{ \"$t\":\"Contacts\", \"version\":\"1.0\", \"uri\":\"http://www.google.com/m8/feeds\" }, \"title\":{ \"$t\":\"Alcatel Alcatel's Contacts\" }, \"author\":[ { \"name\":{ \"$t\":\"Alcatel Alcatel\" }, \"email\":{ \"$t\":\"contact.aggregation@gmail.com\" } } ], \"openSearch$itemsPerPage\":{ \"$t\":\"25\" }, \"xmlns$gContact\":\"http://schemas.google.com/contact/2008\", \"openSearch$startIndex\":{ \"$t\":\"1\" }, \"xmlns$batch\":\"http://schemas.google.com/gdata/batch\", \"xmlns\":\"http://www.w3.org/2005/Atom\", \"link\":[ { \"href\":\"https://www.google.com/\", \"type\":\"text/html\", \"rel\":\"alternate\" }, { \"href\":\"https://www.google.com/m8/feeds/contacts/contact.aggregation%40gmail.com/full\", \"type\":\"application/atom+xml\", \"rel\":\"http://schemas.google.com/g/2005#feed\" }, { \"href\":\"https://www.google.com/m8/feeds/contacts/contact.aggregation%40gmail.com/full\", \"type\":\"application/atom+xml\", \"rel\":\"http://schemas.google.com/g/2005#post\" }, { \"href\":\"https://www.google.com/m8/feeds/contacts/contact.aggregation%40gmail.com/full/batch\", \"type\":\"application/atom+xml\", \"rel\":\"http://schemas.google.com/g/2005#batch\" }, { \"href\":\"https://www.google.com/m8/feeds/contacts/contact.aggregation%40gmail.com/full?max-results=25&alt=json\", \"type\":\"application/atom+xml\", \"rel\":\"self\" } ], \"xmlns$openSearch\":\"http://a9.com/-/spec/opensearch/1.1/\", \"gd$etag\":\"\\\"QHk6eDVSLyt7I2A9XRRUGUUPQQw.\\\"\", \"entry\":[ { \"category\":[ { \"term\":\"http://schemas.google.com/contact/2008#contact\", \"scheme\":\"http://schemas.google.com/g/2005#kind\" } ], \"updated\":{ \"$t\":\"2015-04-02T12:51:45.691Z\" }, \"gContact$groupMembershipInfo\":[ { \"deleted\":\"false\", \"href\":\"http://www.google.com/m8/feeds/groups/contact.aggregation%40gmail.com/base/6\" } ], \"gd$name\":{ \"gd$familyName\":{ \"$t\":\"Nuttens\" }, \"gd$givenName\":{ \"$t\":\"Florent\" }, \"gd$fullName\":{ \"$t\":\"Florent Nuttens\" } }, \"title\":{ \"$t\":\"Florent Nuttens\" }, \"gContact$website\":[ { \"href\":\"https://plus.google.com/116264413358106847332\", \"rel\":\"profile\" } ], \"link\":[ { \"href\":\"https://www.google.com/m8/feeds/photos/media/contact.aggregation%40gmail.com/acdf0c30b5b077e\", \"type\":\"image/*\", \"rel\":\"http://schemas.google.com/contacts/2008/rel#photo\" }, { \"href\":\"https://www.google.com/m8/feeds/contacts/contact.aggregation%40gmail.com/full/acdf0c30b5b077e\", \"type\":\"application/atom+xml\", \"rel\":\"self\" }, { \"href\":\"https://www.google.com/m8/feeds/contacts/contact.aggregation%40gmail.com/full/acdf0c30b5b077e\", \"type\":\"application/atom+xml\", \"rel\":\"edit\" } ], \"gd$etag\":\"\\\"RHgyeTVSLit7I2A9XRRUGUoDRgQ.\\\"\", \"app$edited\":{ \"xmlns$app\":\"http://www.w3.org/2007/app\", \"$t\":\"2015-04-02T12:51:45.691Z\" }, \"id\":{ \"$t\":\"http://www.google.com/m8/feeds/contacts/contact.aggregation%40gmail.com/base/acdf0c30b5b077e\" } }, { \"category\":[ { \"term\":\"http://schemas.google.com/contact/2008#contact\", \"scheme\":\"http://schemas.google.com/g/2005#kind\" } ], \"updated\":{ \"$t\":\"2015-04-02T12:52:03.572Z\" }, \"gContact$groupMembershipInfo\":[ { \"deleted\":\"false\", \"href\":\"http://www.google.com/m8/feeds/groups/contact.aggregation%40gmail.com/base/6\" } ], \"gd$name\":{ \"gd$familyName\":{ \"$t\":\"Leuilliot\" }, \"gd$givenName\":{ \"$t\":\"Lo\\u00efc\" }, \"gd$fullName\":{ \"$t\":\"Lo\\u00efc Leuilliot\" } }, \"title\":{ \"$t\":\"Lo\\u00efc Leuilliot\" }, \"gContact$website\":[ { \"href\":\"https://plus.google.com/118162028452141283871\", \"rel\":\"profile\" } ], \"link\":[ { \"href\":\"https://www.google.com/m8/feeds/photos/media/contact.aggregation%40gmail.com/c56d2740d70658f\", \"type\":\"image/*\", \"rel\":\"http://schemas.google.com/contacts/2008/rel#photo\", \"gd$etag\":\"\\\"Tzd0IQIhSit7I2BiLFBReUVYIWImeGYAUyE.\\\"\" }, { \"href\":\"https://www.google.com/m8/feeds/contacts/contact.aggregation%40gmail.com/full/c56d2740d70658f\", \"type\":\"application/atom+xml\", \"rel\":\"self\" }, { \"href\":\"https://www.google.com/m8/feeds/contacts/contact.aggregation%40gmail.com/full/c56d2740d70658f\", \"type\":\"application/atom+xml\", \"rel\":\"edit\" } ], \"gd$etag\":\"\\\"Qns8ejVSLit7I2A9XRRUGUoDRgY.\\\"\", \"app$edited\":{ \"xmlns$app\":\"http://www.w3.org/2007/app\", \"$t\":\"2015-04-02T12:52:03.572Z\" }, \"id\":{ \"$t\":\"http://www.google.com/m8/feeds/contacts/contact.aggregation%40gmail.com/base/c56d2740d70658f\" } }, { \"category\":[ { \"term\":\"http://schemas.google.com/contact/2008#contact\", \"scheme\":\"http://schemas.google.com/g/2005#kind\" } ], \"updated\":{ \"$t\":\"2015-04-02T12:53:06.634Z\" }, \"gContact$groupMembershipInfo\":[ { \"deleted\":\"false\", \"href\":\"http://www.google.com/m8/feeds/groups/contact.aggregation%40gmail.com/base/6\" } ], \"gd$name\":{ \"gd$familyName\":{ \"$t\":\"WEIDEMANN\" }, \"gd$givenName\":{ \"$t\":\"Adrien\" }, \"gd$fullName\":{ \"$t\":\"Adrien WEIDEMANN\" } }, \"title\":{ \"$t\":\"Adrien WEIDEMANN\" }, \"gContact$website\":[ { \"href\":\"https://plus.google.com/114869906940111104818\", \"rel\":\"profile\" } ], \"link\":[ { \"href\":\"https://www.google.com/m8/feeds/photos/media/contact.aggregation%40gmail.com/502b172809b22b9e\", \"type\":\"image/*\", \"rel\":\"http://schemas.google.com/contacts/2008/rel#photo\" }, { \"href\":\"https://www.google.com/m8/feeds/contacts/contact.aggregation%40gmail.com/full/502b172809b22b9e\", \"type\":\"application/atom+xml\", \"rel\":\"self\" }, { \"href\":\"https://www.google.com/m8/feeds/contacts/contact.aggregation%40gmail.com/full/502b172809b22b9e\", \"type\":\"application/atom+xml\", \"rel\":\"edit\" } ], \"gd$etag\":\"\\\"R3g4fDVSLit7I2A9XRRUGUoDRgw.\\\"\", \"app$edited\":{ \"xmlns$app\":\"http://www.w3.org/2007/app\", \"$t\":\"2015-04-02T12:53:06.634Z\" }, \"id\":{ \"$t\":\"http://www.google.com/m8/feeds/contacts/contact.aggregation%40gmail.com/base/502b172809b22b9e\" } }, { \"category\":[ { \"term\":\"http://schemas.google.com/contact/2008#contact\", \"scheme\":\"http://schemas.google.com/g/2005#kind\" } ], \"updated\":{ \"$t\":\"2015-04-02T12:52:51.619Z\" }, \"gContact$groupMembershipInfo\":[ { \"deleted\":\"false\", \"href\":\"http://www.google.com/m8/feeds/groups/contact.aggregation%40gmail.com/base/6\" } ], \"gd$name\":{ \"gd$familyName\":{ \"$t\":\"WEISSGERBER\" }, \"gd$givenName\":{ \"$t\":\"Thibaut\" }, \"gd$fullName\":{ \"$t\":\"Thibaut WEISSGERBER\" } }, \"title\":{ \"$t\":\"Thibaut WEISSGERBER\" }, \"gContact$website\":[ { \"href\":\"https://plus.google.com/101674470162772697646\", \"rel\":\"profile\" } ], \"link\":[ { \"href\":\"https://www.google.com/m8/feeds/photos/media/contact.aggregation%40gmail.com/52e09eb70a650d63\", \"type\":\"image/*\", \"rel\":\"http://schemas.google.com/contacts/2008/rel#photo\" }, { \"href\":\"https://www.google.com/m8/feeds/contacts/contact.aggregation%40gmail.com/full/52e09eb70a650d63\", \"type\":\"application/atom+xml\", \"rel\":\"self\" }, { \"href\":\"https://www.google.com/m8/feeds/contacts/contact.aggregation%40gmail.com/full/52e09eb70a650d63\", \"type\":\"application/atom+xml\", \"rel\":\"edit\" } ], \"gd$etag\":\"\\\"QHg6cTVSLit7I2A9XRRUGUoDRgM.\\\"\", \"app$edited\":{ \"xmlns$app\":\"http://www.w3.org/2007/app\", \"$t\":\"2015-04-02T12:52:51.619Z\" }, \"id\":{ \"$t\":\"http://www.google.com/m8/feeds/contacts/contact.aggregation%40gmail.com/base/52e09eb70a650d63\" } } ], \"openSearch$totalResults\":{ \"$t\":\"4\" }, \"id\":{ \"$t\":\"contact.aggregation@gmail.com\" } }, \"version\":\"1.0\", \"encoding\":\"UTF-8\" }";
            JSONObject reader = new JSONObject(in);

            // Search for the contacts array in feed root
            JSONArray contactsArray = (JSONArray) HelperProvider.getJSONObjectByPath(reader, "feed.entry");

            for (int i = 0; i < contactsArray.length(); i++) {
                Contact c = new Contact();
                // Get contact object from contacts array
                JSONObject contactObject = contactsArray.getJSONObject(i);

                // Search for name fields and isolate fullName value
                String fullName = HelperProvider.getJSONObjectByPath(contactObject, "gd$name.gd$fullName.$t").toString();

                // Affect values to fields
                c.setField(StandardFields.FN, fullName);

                // insert the contact to the list
                contactList.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return contactList;
    }

    @Override
    // TODO : implement
    public void updateContact(Contact c) {

    }

    @Override
    // TODO : implement
    public void setCredentials() {

    }

    @Override
    // TODO : implement
    public void addContact(Contact c) {

    }

    @Override
    // TODO : implement
    public String getCurrentVersion() {
        return null;
    }
}
