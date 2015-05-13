package alcatel.contactsaggregation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import alcatel.contactsaggregation.Fragments.FragContactsDetails;
import alcatel.contactsaggregation.Providers.Google.GoogleProvider;
import alcatel.contactsaggregation.Providers.Provider;

public class MainActivity extends ActionBarActivity {

    private ListView contactListView;
    private ContactListItemAdapter contactAdapter;
    private List<ContactListItem> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2015.05 - elfaus - Updatable contact list
        this.contactList = new ArrayList<ContactListItem>();
        this.contactList.add(new ContactListItem(R.drawable.antoinebouchina, "Antoine Bouchina"));
        this.contactList.add(new ContactListItem(R.drawable.loicleuilliot, "Loic Leuilliot"));
        this.contactList.add(new ContactListItem(R.drawable.florentnuttens, "Florent Nuttens"));
        this.contactList.add(new ContactListItem(R.drawable.mathieutavernier, "Mathieu Tavernier"));
        this.contactList.add(new ContactListItem(R.drawable.julienmey, "Julien Mey"));
        this.contactList.add(new ContactListItem(R.drawable.adrienweidemann, "Adrien Weidemann"));
        this.contactList.add(new ContactListItem(R.drawable.contacts, "Anthony Martin"));
        this.contactList.add(new ContactListItem(R.drawable.dylancrespo, "Dylan Crespo"));
        this.contactList.add(new ContactListItem(R.drawable.louislaselva, "Louis La Selva"));
        this.contactList.add(new ContactListItem(R.drawable.nghiahuynh, "Nghia Huynh"));
        this.contactList.add(new ContactListItem(R.drawable.contacts, "Thibaut Weissgerber"));
        Provider googleTester = GoogleProvider.getInstance();
        ArrayList<Contact> contacts = googleTester.getContacts();

        ContactListItem[] contactListData = new ContactListItem[this.contactList.size()];

        this.contactListView = (ListView) findViewById(R.id.listContacts);

        this.contactAdapter = new ContactListItemAdapter(this, R.layout.list_item, this.contactList.toArray(contactListData));

        this.contactListView.setAdapter(this.contactAdapter);

        this.contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // utiliser les informations contenues dans view (getChild, ...)
                Bundle args = new Bundle();
                switch (position) {
                    case 0: // id1
                        args.putString("contactID", "cont1");
                        break;
                    case 1:
                        args.putString("contactID", "cont2");
                        break;
                    case 2:
                        args.putString("contactID", "cont3");
                        break;
                    case 3:
                        args.putString("contactID", "cont4");
                        break;
                    case 4:
                        args.putString("contactID", "cont5");
                        break;
                    case 5:
                        args.putString("contactID", "cont6");
                        break;
                    case 6:
                        args.putString("contactID", "cont7");
                        break;
                    case 7:
                        args.putString("contactID", "cont8");
                        break;
                    case 8:
                        args.putString("contactID", "cont9");
                        break;
                    case 9:
                        args.putString("contactID", "cont10");
                        break;
                    case 10:
                        args.putString("contactID", "cont11");
                        break;
                    default:
                        args.putString("contactID", "unknown");
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

        ArrayList<Contact> contacts = GoogleProvider.getInstance().getContact();

        if (contacts != null) {
            for (Contact c : contacts)
                Log.d("[CONTACT]", c.toString());
        }

        ContactListItem c[] = new ContactListItem[contacts.size()];
        this.contactAdapter.addAll(contacts.toArray(c));
        this.contactAdapter.notifyDataSetChanged();
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
