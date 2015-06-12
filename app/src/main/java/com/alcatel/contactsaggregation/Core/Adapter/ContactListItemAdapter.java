package com.alcatel.contactsaggregation.Core.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.alcatel.contactsaggregation.Core.Models.Contact;
import com.alcatel.contactsaggregation.Providers.Google.GoogleProvider;
import com.alcatel.contactsaggregation.R;
import com.alcatel.contactsaggregation.StandardFields;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Adapter for ContactListItem
 */
public class ContactListItemAdapter extends ArrayAdapter<Contact> {

    private final Context _context;
    private ArrayList<Contact> _data;

    public ContactListItemAdapter(Context context, ArrayList<Contact> data) {
        super(context, R.layout.list_item, data);
        this._context = context;
        this._data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get layout object before working on them
        LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);
        ImageView contactImage = (ImageView) rowView.findViewById(R.id.contactImage);
        TextView contactName = (TextView) rowView.findViewById(R.id.contactName);

        // get the asked contact from the list
        Contact c = this._data.get(position);

        // update the row layout with default photo and  contact name
        contactName.setText(c.getField(StandardFields.TITLE));
        contactImage.setImageResource(R.drawable.contacts);

        // if a photo uri has been found, try to get it back from web
        if (c.getField(StandardFields.PHOTO) != null) {
            new DownloadImageTask(contactImage).execute(c.getField(StandardFields.PHOTO));
        }

        return rowView;
    }

    private class DownloadImageTask extends AsyncTask<String, View, Bitmap> {
        private ImageView _bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this._bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urlDisplay = urls[0];
            Bitmap mIcon11 = null;

            String token = GoogleProvider.getInstance().getToken(); // take the actual token in order to take back photo from google api
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(urls[0]);
            get.addHeader("Authorization", "Bearer " + token);  // Authenticate to google api

            try {
                HttpResponse execute = client.execute(get);
                if (execute.getStatusLine().getStatusCode() != 404) {
                    InputStream content = execute.getEntity().getContent();
                    mIcon11 = BitmapFactory.decodeStream(content);
                }
            } catch (ClientProtocolException e) {
                Log.e("[GOOGLE-GET]", e.getMessage());
            } catch (IOException e) {
                Log.e("[GOOGLE-GET]", e.getMessage());
            }

            return mIcon11;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            _bmImage.setImageBitmap(result);
        }
    }
}