package com.alcatel.contactsaggregation.Core.Models;

import com.alcatel.contactsaggregation.StandardFields;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a contact
 */
public class Contact implements Serializable, Comparable<Contact> {

    private String uniqueId;
    private HashMap<StandardFields, String> fields;

    public Contact() {
        this.fields = new HashMap<>();
    }

    public Contact(String uniqueId, HashMap<StandardFields, String> fields) {
        this.uniqueId = uniqueId;
        this.fields = fields;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getField(StandardFields name) {
        return this.fields.get(name);
    }

    public HashMap<StandardFields, String> getFields() {
        return this.fields;
    }

    public void setField(StandardFields field, String value) {
        this.fields.put(field, value);
    }

    public String toString() {
        String result = "{";

        for (Object o : this.fields.entrySet()) {
            Map.Entry field = (Map.Entry) o;
            String key = field.getKey().toString();
            String value = (String) field.getValue();
            result += key + ":" + value + ";";
        }

        return result + "}";
    }

    @Override
    public int compareTo(Contact another) {
        String thisTitle = getField(StandardFields.TITLE);
        String anotherTitle = another.getField(StandardFields.TITLE);

        return thisTitle.compareToIgnoreCase(anotherTitle);
    }
}
