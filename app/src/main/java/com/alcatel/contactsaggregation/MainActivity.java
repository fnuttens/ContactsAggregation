package com.alcatel.contactsaggregation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import com.alcatel.contactsaggregation.Core.Adapter.ContactListItemAdapter;
import com.alcatel.contactsaggregation.Core.Fragments.FragContactsDetails;
import com.alcatel.contactsaggregation.Core.Models.Contact;
import com.alcatel.contactsaggregation.Core.Views.SettingsActivity;
import com.alcatel.contactsaggregation.Providers.Google.GoogleProvider;
import com.alcatel.contactsaggregation.Core.Views.OAuthLoginView;

public class MainActivity extends ActionBarActivity {

    private ContactListItemAdapter _contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2015.05 - elfaus - Updatable contact list
        ArrayList<Contact> contactListData = new ArrayList<Contact>();
        Contact c1 = new Contact();
        c1.setField(StandardFields.TITLE, "Antoine Bouchina");
        Contact c2 = new Contact();
        c2.setField(StandardFields.TITLE, "Loïc Leuilliot");
        Contact c3 = new Contact();
        c3.setField(StandardFields.TITLE, "Florent Nuttens");
        Contact c4 = new Contact();
        c4.setField(StandardFields.TITLE, "Mathieu Tavernier");
        Contact c5 = new Contact();
        c5.setField(StandardFields.TITLE, "Julien Mey");
        Contact c6 = new Contact();
        c6.setField(StandardFields.TITLE, "Adrien Weidmann");
        Contact c7 = new Contact();
        c7.setField(StandardFields.TITLE, "Anthony Martin");
        Contact c8 = new Contact();
        c8.setField(StandardFields.TITLE, "Dylan Crespo");
        Contact c9 = new Contact();
        c9.setField(StandardFields.TITLE, "Louis La Selva");
        Contact c10 = new Contact();
        c10.setField(StandardFields.TITLE, "Nghia Huynh");
        Contact c11 = new Contact();
        c11.setField(StandardFields.TITLE, "Thibaut Weissgerber");


        final ListView contactListView = (ListView) findViewById(R.id.listContacts);
        this._contactAdapter = new ContactListItemAdapter(this, contactListData);

        contactListView.setAdapter(this._contactAdapter);

        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // utiliser les informations contenues dans view (getChild, ...)
                Bundle args = new Bundle();

                args.putString("contactID", "unknown");
                Contact c = (Contact) parent.getAdapter().getItem(position);
                if (c != null) {
                    args.putSerializable("contact", c);
                }

                try {
                    load_fragment(FragContactsDetails.class.newInstance(), args);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // TODO : refactor - add new account - authorize access <<
        long currentTimeStamp = System.currentTimeMillis()/1000;    // get the current system timestamp

        // Check if the stored token is outdated and renew it if needed
        if (currentTimeStamp >= GoogleProvider.getInstance().getTimeout()) {

            Intent oauthLoginView = new Intent(this, OAuthLoginView.class);

            // Load the OAuthLoginView with the oauth provider uri
            oauthLoginView.putExtra("OAuthEndpoint", GoogleProvider.getInstance().getAuthUri());
            oauthLoginView.putExtra("Provider", GoogleProvider.class.getName());

            // Start the authentication activity
            startActivity(oauthLoginView);
        }
        // >>
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Get contacts from provider list
        ArrayList<Contact> contacts = GoogleProvider.getInstance().getContacts();
        if (contacts.size() > 0) {
            this._contactAdapter.clear();
            this._contactAdapter.addAll(contacts);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // Get contacts from provider list
        ArrayList<Contact> contacts = GoogleProvider.getInstance().getContacts();
        if (contacts.size() > 0) {
            this._contactAdapter.clear();
            this._contactAdapter.addAll(contacts);
        }
    }

    private void load_fragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contactDetailsContainer, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent settings = new Intent(this, SettingsActivity.class);
            // Start the authentication activity
            startActivity(settings);
        }

        return super.onOptionsItemSelected(item);
    }
}
