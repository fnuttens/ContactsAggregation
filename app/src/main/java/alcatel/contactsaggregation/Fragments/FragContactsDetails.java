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
                case "cont1": // id1
                    map.put("Provider", "Google 1");
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Google 2");
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Google 3");
                    break;
                case "cont2":
                    map.put("Provider", "Facebook 1");
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Facebook 2");
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Facebook 3");
                    break;
                case "cont3":
                    map.put("Provider", "Exchange 1");
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Exchange 2");
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Exchange 3");
                    break;
                case "cont4":
                    map.put("Provider", "Skype 1");
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Skype 2");
                    mylist.add(map);
                    map = new HashMap<>();
                    map.put("Provider", "Skype 3");
                    break;
                default:
                    map.put("Provider", "No provider found");
            }
        } else {
            map.put("Provider", "No provider found.");
        }

        mylist.add(map);

        SimpleAdapter lv_a = new SimpleAdapter(getActivity(), mylist, R.layout.row_provider,
                new String[]{"Provider"}, // Image,... autres clés
                new int[]{R.id.providerName});
        m_list.setAdapter(lv_a);
    }
}
