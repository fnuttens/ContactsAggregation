package alcatel.contactsaggregation.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import alcatel.contactsaggregation.R;

/**
 * Created by Florent on 02/04/2015.
 */
public class FragContactsDetails extends Fragment {

    private ImageView m_image;
    private ListView m_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.frag_contacts_details, container, false);
        m_image = (ImageView) v.findViewById(R.id.contactImage);
        m_list = (ListView) v.findViewById(R.id.providerList);

        Bundle myBundle = getArguments();
        if (myBundle != null)
            loadList(myBundle);

        return v;
    }

    public void loadList(Bundle b) {
        HashMap<String, String> map;
        ArrayList<HashMap<String, String>> mylist = new ArrayList<>();

        String contactID = b.getString("contactID"); // ID du contact sélectionné
        map = new HashMap<>();

        if (contactID != null) {
            switch (contactID) {
                case "cont1": // Antoine
                    m_image.setImageResource(R.drawable.antoinebouchina);
                    map.put("Provider", "Gmail");
                    map.put("Account", "antoine.bouchina@gmail.com");
                    map.put("Provider_logo", Integer.toString(R.drawable.gmail_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Facebook");
                    map.put("Account", "antoine.bouchina");
                    map.put("Provider_logo", Integer.toString(R.drawable.facebook_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Skype");
                    map.put("Account", "antoine-b.");
                    map.put("Provider_logo", Integer.toString(R.drawable.skype_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Microsoft");
                    map.put("Account", "antoine.bouchina@live.fr");
                    map.put("Provider_logo", Integer.toString(R.drawable.microsoft_logo));
                    break;
                case "cont2": // Loic
                    m_image.setImageResource(R.drawable.loicleuilliot);
                    map.put("Provider", "Facebook");
                    map.put("Account", "facebook.com/elfaus");
                    map.put("Provider_logo", Integer.toString(R.drawable.facebook_logo));
                    mylist.add(map);
                    map = new HashMap<>(); map.put("Provider", "Gmail");
                    map.put("Account", "loic.leuilliot@gmail.com");
                    map.put("Provider_logo", Integer.toString(R.drawable.gmail_logo));
                    break;
                case "cont3": // Florent
                    map.put("Provider", "Gmail");
                    map.put("Account", "fnuttens@gmail.com");
                    map.put("Provider_logo", Integer.toString(R.drawable.gmail_logo));
                    mylist.add(map);
                    map = new HashMap<>(); map.put("Provider", "Facebook");
                    map.put("Account", "facebook.com/florent.nuttens");
                    map.put("Provider_logo", Integer.toString(R.drawable.facebook_logo));
                    break;
                case "cont4": // Adrien
                    map.put("Provider", "Gmail");
                    map.put("Account", "adrien.weidemann@gmail.com");
                    map.put("Provider_logo", Integer.toString(R.drawable.gmail_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Facebook");
                    map.put("Account", "facebook.com/adrien.haeffele");
                    map.put("Provider_logo", Integer.toString(R.drawable.facebook_logo));
                    break;
                case "cont5": // Julien
                    m_image.setImageResource(R.drawable.julienmey);
                    map.put("Provider", "Gmail");
                    map.put("Account", "meyjulien67@gmail.com");
                    map.put("Provider_logo", Integer.toString(R.drawable.gmail_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Skype");
                    map.put("Account", "trekk67");
                    map.put("Provider_logo", Integer.toString(R.drawable.skype_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Microsoft");
                    map.put("Account", "hpjuju@hotmail.fr");
                    map.put("Provider_logo", Integer.toString(R.drawable.microsoft_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Facebook");
                    map.put("Account", "julien.mey.54");
                    map.put("Provider_logo", Integer.toString(R.drawable.facebook_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Steam");
                    map.put("Account", "trek67000");
                    map.put("Provider_logo", Integer.toString(R.drawable.steam_logo));
                    break;
                //map.put("Provider_logo", Integer.toString(R.drawable.steam_logo));
                default: // Thibaut OU défaut
                    map.put("Provider", "No provider found");
            }
        } else {
            map.put("Provider", "No provider found.");
        }

        mylist.add(map);

        SimpleAdapter lv_a = new SimpleAdapter(getActivity(), mylist, R.layout.row_provider,
                new String[]{"Provider", "Account", "Provider_logo"}, // Image,... autres clés
                new int[]{R.id.providerName, R.id.account, R.id.providerImage});
        m_list.setAdapter(lv_a);
    }
}
