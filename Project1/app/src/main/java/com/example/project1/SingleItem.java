package com.example.project1;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SingleItem {

    private String PHOTO_URI;
    private String DISPLAY_NAME;
    private String COMPANY;
    private String NUMBER;
    private String ADDRESS;
    private String URL;
    private String NOTE;
    private String IS_PRIMARY;

    @Override
    public String toString() {
        return  "PHOTO_URI='" + PHOTO_URI + "\n" +
                "DISPLAY_NAME='" + DISPLAY_NAME + "\n" +
                "COMPANY='" + COMPANY + "\n" +
                "NUMBER='" + NUMBER + "\n" +
                "ADDRESS='" + ADDRESS + "\n" +
                "URL='" + URL + "\n" +
                "NOTE='" + NOTE + "\n" +
                "IS_PRIMARY='" + IS_PRIMARY + "\n" ;
    }

    public SingleItem(String PHOTO_URI, String DISPLAY_NAME, String COMPANY, String NUMBER, String ADDRESS, String URL, String NOTE, String IS_PRIMARY) {
        this.PHOTO_URI = PHOTO_URI;
        this.DISPLAY_NAME = DISPLAY_NAME;
        this.COMPANY = COMPANY;
        this.NUMBER = NUMBER;
        this.ADDRESS = ADDRESS;
        this.URL = URL;
        this.NOTE = NOTE;
        this.IS_PRIMARY = IS_PRIMARY;
    }

    public static List<SingleItem> getContacts(Context context) {

        // where to save loaded data
        // here, to list with elements of type "SingleItem" named "data"
        List<SingleItem> data = new ArrayList<>();

        // resolver is context-specific.
        ContentResolver resolver = context.getContentResolver();

        // query setting we have to set  5 arguments

        Uri contact_application_uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String[] projection = {
                ContactsContract.CommonDataKinds.Phone.PHOTO_URI,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Organization.COMPANY,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Website.URL,
                ContactsContract.CommonDataKinds.Note.NOTE,
                ContactsContract.CommonDataKinds.Phone.IS_PRIMARY
        };

        String selectionClause = null;
        String[] selectionArgs = {""};
        String sortOrder = "IS_PRIMARY DESC DISPLAY_NAME ASC";

        for (int i = 0; i < projection.length; i++)
            Log.d("auaicn", i + " : " + projection[i]);

        // resolver to application_specific_provider query.
        Cursor cursor = resolver.query(contact_application_uri, projection, null, null, null);
        Log.d("auaicn", "number of items read is " + cursor.getCount());

        ArrayList<SingleItem> items = new ArrayList<SingleItem>();
        if (cursor != null) {

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {

                    // load 8 elements

                    int index_PHOTO_URI = cursor.getColumnIndex(projection[0]);
                    int index_DISPLAY_NAME = cursor.getColumnIndex(projection[1]);
                    int index_COMPANY = cursor.getColumnIndex(projection[2]);
                    int index_NUMBER = cursor.getColumnIndex(projection[3]);
                    int index_ADDRESS = cursor.getColumnIndex(projection[4]);
                    int index_URL = cursor.getColumnIndex(projection[5]);
                    int index_NOTE = cursor.getColumnIndex(projection[6]);
                    int index_IS_PRIMARY = cursor.getColumnIndex(projection[7]);

                    String data_PHOTO_URI = cursor.getString(index_PHOTO_URI);
                    String data_DISPLAY_NAME = cursor.getString(index_DISPLAY_NAME);
                    String data_COMPANY = cursor.getString(index_COMPANY);
                    String data_NUMBER = cursor.getString(index_NUMBER);
                    String data_ADDRESS = cursor.getString(index_ADDRESS);
                    String data_URL = cursor.getString(index_URL);
                    String data_NOTE = cursor.getString(index_NOTE);
                    String data_IS_PRIMARY = cursor.getString(index_IS_PRIMARY);

                    items.add(new SingleItem(
                            data_PHOTO_URI,
                            data_DISPLAY_NAME,
                            data_COMPANY,
                            data_NUMBER,
                            data_ADDRESS,
                            data_URL,
                            data_NOTE,
                            data_IS_PRIMARY));
                }

            } else {

                // nothing found for query
                /*
                 * Insert code here to notify the user that the search was unsuccessful. This isn't necessarily
                 * an error. You may want to offer the user the option to insert a new row, or re-type the
                 * search term.
                 */

            }
        } else {
            // On Error
            // Some providers return null if an error occurs, others throw an exception
        }

        for (int i = 0; i < items.size(); i++)
            data.add(items.get(i));

        // 데이터 계열은 반드시 닫아줘야 한다.
        cursor.close();
        return data;
    }
    public String getPHOTO_URI () {
        return PHOTO_URI;
    }

    public void setPHOTO_URI (String PHOTO_URI){
        this.PHOTO_URI = PHOTO_URI;
    }

    public String getDISPLAY_NAME () {
        return DISPLAY_NAME;
    }

    public void setDISPLAY_NAME (String DISPLAY_NAME){
        this.DISPLAY_NAME = DISPLAY_NAME;
    }

    public String getCOMPANY () {
        return COMPANY;
    }

    public void setCOMPANY (String COMPANY){
        this.COMPANY = COMPANY;
    }

    public String getNUMBER () {
        return NUMBER;
    }

    public void setNUMBER (String NUMBER){
        this.NUMBER = NUMBER;
    }

    public String getADDRESS () {
        return ADDRESS;
    }

    public void setADDRESS (String ADDRESS){
        this.ADDRESS = ADDRESS;
    }

    public String getURL () {
        return URL;
    }

    public void setURL (String URL){
        this.URL = URL;
    }

    public String getNOTE () {
        return NOTE;
    }

    public void setNOTE (String NOTE){
        this.NOTE = NOTE;
    }

    public String getIS_PRIMARY () {
        return IS_PRIMARY;
    }

    public void setIS_PRIMARY (String IS_PRIMARY){
        this.IS_PRIMARY = IS_PRIMARY;
    }
}
