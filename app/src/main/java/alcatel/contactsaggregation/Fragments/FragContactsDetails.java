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
                    map.put("Account", "elfaus");
                    map.put("Provider_logo", Integer.toString(R.drawable.facebook_logo));
                    mylist.add(map);
                    map = new HashMap<>(); map.put("Provider", "Gmail");
                    map.put("Account", "loic.leuilliot@gmail.com");
                    map.put("Provider_logo", Integer.toString(R.drawable.gmail_logo));
                    break;
                case "cont3": // Florent
                    m_image.setImageResource(R.drawable.florentnuttens);
                    map.put("Provider", "Gmail");
                    map.put("Account", "fnuttens@gmail.com");
                    map.put("Provider_logo", Integer.toString(R.drawable.gmail_logo));
                    mylist.add(map);
                    map = new HashMap<>(); map.put("Provider", "Facebook");
                    map.put("Account", "florent.nuttens");
                    map.put("Provider_logo", Integer.toString(R.drawable.facebook_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Microsoft");
                    map.put("Account", "florent.nuttens@outlook.fr");
                    map.put("Provider_logo", Integer.toString(R.drawable.microsoft_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Skype");
                    map.put("Account", "floflotte21");
                    map.put("Provider_logo", Integer.toString(R.drawable.skype_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "GitHub");
                    map.put("Account", "fnuttens");
                    map.put("Provider_logo", Integer.toString(R.drawable.github_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "BitBucket");
                    map.put("Account", "fnuttens");
                    map.put("Provider_logo", Integer.toString(R.drawable.bitbucket_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Yahoo");
                    map.put("Account", "florent.nuttens@yahoo.fr");
                    map.put("Provider_logo", Integer.toString(R.drawable.yahoo_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Steam");
                    map.put("Account", "floflotte21");
                    map.put("Provider_logo", Integer.toString(R.drawable.steam_logo));
                    break;
                case "cont4": // Mathieu
                    m_image.setImageResource(R.drawable.mathieutavernier);
                    map.put("Provider", "Gmail");
                    map.put("Account", "dragweno@gmail.com");
                    map.put("Provider_logo", Integer.toString(R.drawable.gmail_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Facebook");
                    map.put("Account", "mathieu.tavernier.9");
                    map.put("Provider_logo", Integer.toString(R.drawable.facebook_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Skype");
                    map.put("Account", "dragweno67");
                    map.put("Provider_logo", Integer.toString(R.drawable.skype_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Steam");
                    map.put("Account", "dragweno67440");
                    map.put("Provider_logo", Integer.toString(R.drawable.steam_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "GitHub");
                    map.put("Account", "Dragweno");
                    map.put("Provider_logo", Integer.toString(R.drawable.github_logo));
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
                case "cont6": // Adrien
                    m_image.setImageResource(R.drawable.adrienweidemann);
                    map.put("Provider", "Gmail");
                    map.put("Account", "adrien.weidemann@gmail.com");
                    map.put("Provider_logo", Integer.toString(R.drawable.gmail_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Facebook");
                    map.put("Account", "adrien.haeffele");
                    map.put("Provider_logo", Integer.toString(R.drawable.facebook_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "GitHub");
                    map.put("Account", "adrien-weidemann");
                    map.put("Provider_logo", Integer.toString(R.drawable.github_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Skype");
                    map.put("Account", "dirtyham");
                    map.put("Provider_logo", Integer.toString(R.drawable.skype_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Microsoft");
                    map.put("Account", "a-67210@live.com");
                    map.put("Provider_logo", Integer.toString(R.drawable.microsoft_logo));
                    break;
                case "cont7": // Anthony
                    map.put("Provider", "Gmail");
                    map.put("Account", "martin.anthonyq@gmail.com");
                    map.put("Provider_logo", Integer.toString(R.drawable.gmail_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Skype");
                    map.put("Account", "angle.90");
                    map.put("Provider_logo", Integer.toString(R.drawable.skype_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "GitHub");
                    map.put("Account", "huunneki");
                    map.put("Provider_logo", Integer.toString(R.drawable.github_logo));
                case "cont8": // Dylan
                    m_image.setImageResource(R.drawable.dylancrespo);
                    map.put("Provider", "Gmail");
                    map.put("Account", "crespo.dylan@live.fr");
                    map.put("Provider_logo", Integer.toString(R.drawable.gmail_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Microsoft");
                    map.put("Account", "crespo.dylan@live.fr");
                    map.put("Provider_logo", Integer.toString(R.drawable.microsoft_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Facebook");
                    map.put("Account", "dylan.crespo.18");
                    map.put("Provider_logo", Integer.toString(R.drawable.facebook_logo));
                case "cont9": // Louis
                    m_image.setImageResource(R.drawable.louislaselva);
                    map.put("Provider", "Gmail");
                    map.put("Account", "louis.laselva@gmail.com");
                    map.put("Provider_logo", Integer.toString(R.drawable.gmail_logo));
                    mylist.add(map);map = new HashMap<>();
                    map.put("Provider", "Microsoft");
                    map.put("Account", "louis.la-selva@hotmail.fr");
                    map.put("Provider_logo", Integer.toString(R.drawable.microsoft_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Steam");
                    map.put("Account", "Assansha");
                    map.put("Provider_logo", Integer.toString(R.drawable.steam_logo));
                case "cont10": // Nghia
                    m_image.setImageResource(R.drawable.nghiahuynh);
                    map.put("Provider", "Gmail");
                    map.put("Account", "azn0viet@gmail.com");
                    map.put("Provider_logo", Integer.toString(R.drawable.gmail_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Microsoft");
                    map.put("Account", "azn0viet@hotmail.fr");
                    map.put("Provider_logo", Integer.toString(R.drawable.microsoft_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Facebook");
                    map.put("Account", "nghia.huynh.79");
                    map.put("Provider_logo", Integer.toString(R.drawable.facebook_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Skype");
                    map.put("Account", "azn0viet");
                    map.put("Provider_logo", Integer.toString(R.drawable.skype_logo));
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Steam");
                    map.put("Account", "zarrielyuki");
                    map.put("Provider_logo", Integer.toString(R.drawable.steam_logo));
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
