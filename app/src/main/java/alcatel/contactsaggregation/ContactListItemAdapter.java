package alcatel.contactsaggregation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Florent on 02/04/2015.
 */
public class ContactListItemAdapter extends ArrayAdapter<ContactListItem> {

    Context m_context;
    int m_layoutResourceId;
    ContactListItem m_data[] = null;

    public ContactListItemAdapter(Context context, int layoutResourceId, ContactListItem[] data) {
        super(context, layoutResourceId, data);
        this.m_context = context;
        this.m_layoutResourceId = layoutResourceId;
        this.m_data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ContactItemHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) m_context).getLayoutInflater();
            row = inflater.inflate(m_layoutResourceId, parent, false);

            holder = new ContactItemHolder();
            holder.imgIcon = (ImageView) row.findViewById(R.id.contactImage);
            holder.txtTitle = (TextView) row.findViewById(R.id.contactName);

            row.setTag(holder);
        } else {
            holder = (ContactItemHolder) row.getTag();
        }

        ContactListItem itm = m_data[position];
        holder.txtTitle.setText(itm.m_title);
        holder.imgIcon.setImageResource(itm.m_icon);

        return row;
    }

    static class ContactItemHolder {
        ImageView imgIcon;
        TextView txtTitle;
    }
}