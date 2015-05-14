package com.alcatel.contactsaggregation.Core.Fragments;

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

import com.alcatel.contactsaggregation.Core.Models.Contact;
import com.alcatel.contactsaggregation.R;
import com.alcatel.contactsaggregation.StandardFields;

/**
 * Created by Antoine BOUCHINA on 02/04/2015.
 */
public class FragContactsDetails extends Fragment {

    private ImageView m_image;
    private ListView m_list;
    private ArrayList<HashMap<String, String>> _mylist = new ArrayList<>();

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
        Contact contact = (Contact) b.getSerializable("contact"); // ID du contact sélectionné

        if (contact != null) {
            this.inflate(contact);
        } else {
            HashMap<String, String> map = new HashMap<>();
            map.put("Provider", "No provider found.");
            this._mylist.add(map);
        }

        SimpleAdapter lv_a = new SimpleAdapter(getActivity(), this._mylist, R.layout.row_provider,
                new String[]{"Provider", "Account", "Provider_logo"}, // Image,... autres clés
                new int[]{R.id.providerName, R.id.account, R.id.providerImage});
        m_list.setAdapter(lv_a);
    }

    public void inflate(Contact c) {

        String mail = c.getField(StandardFields.EMAIL);

        if (mail != null) {
            if (mail.contains("@gmail")) {
                HashMap<String, String> map = new HashMap<>();
                map.put("Provider", "Gmail");
                map.put("Account", mail);
                map.put("Provider_logo", Integer.toString(R.drawable.gmail_logo));
                this._mylist.add(map);
            }

            if (mail.contains("@live") || mail.contains("@hotmail")) {
                HashMap<String, String> map = new HashMap<>();
                map.put("Provider", "Microsoft");
                map.put("Account", mail);
                map.put("Provider_logo", Integer.toString(R.drawable.microsoft_logo));
                this._mylist.add(map);
            }
        }
    }
}
