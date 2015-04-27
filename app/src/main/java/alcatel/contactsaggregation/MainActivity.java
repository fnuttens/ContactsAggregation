package alcatel.contactsaggregation;

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

import alcatel.contactsaggregation.Fragments.FragContactsDetails;
import alcatel.contactsaggregation.Providers.Google.GoogleProvider;
import alcatel.contactsaggregation.Providers.Provider;

public class MainActivity extends ActionBarActivity {

    private ListView listContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Provider googleTester = GoogleProvider.getInstance();
        ArrayList<Contact> contacts = googleTester.getContacts();

        if (contacts != null) {
            for (Contact c : contacts)
                Log.d("[CONTACT]", c.toString());
        }

        ContactListItem contactList_data[] = new ContactListItem[]
                {
                        new ContactListItem(R.drawable.antoinebouchina, "Antoine Bouchina"), // id1
                        new ContactListItem(R.drawable.loicleuilliot, "Loic Leuilliot"), // id2
                        new ContactListItem(R.drawable.florentnuttens, "Florent Nuttens"), // id3
                        new ContactListItem(R.drawable.mathieutavernier, "Mathieu Tavernier"), // id4
                        new ContactListItem(R.drawable.julienmey, "Julien Mey"), // id5
                        new ContactListItem(R.drawable.adrienweidemann, "Adrien Weidemann"), // id6
                        new ContactListItem(R.drawable.contacts, "Anthony Martin"), // id7
                        new ContactListItem(R.drawable.dylancrespo, "Dylan Crespo"), // id8
                        new ContactListItem(R.drawable.louislaselva, "Louis La Selva"), // id9
                        new ContactListItem(R.drawable.nghiahuynh, "Nghia Huynh"), // id10
                        new ContactListItem(R.drawable.contacts, "Thibaut Weissgerber") // id11
                };

        listContacts = (ListView) findViewById(R.id.listContacts);

        ContactListItemAdapter adapter = new ContactListItemAdapter(this, R.layout.list_item, contactList_data);

        //View header = (View)getLayoutInflater().inflate(R.layout.abc_action_bar_title_item, null);
        //listContacts.addHeaderView(header);

        listContacts.setAdapter(adapter);

        listContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
