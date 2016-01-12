package contacts.xiaozuzu.github.io.contactsbook.util;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import contacts.xiaozuzu.github.io.contactsbook.model.Contact;

/**
 * Created by Administrator on 2016/1/12.
 */
public class SystemContactsUtil {
    private Context context;
    private static SystemContactsUtil systemContactsUtil;
    private SystemContactsUtil(Context context){
        this.context = context;
    }
    public static SystemContactsUtil getInstance(Context context){
        if (systemContactsUtil == null){
            synchronized (SystemContactsUtil.class){
                if (systemContactsUtil == null){
                    systemContactsUtil = new SystemContactsUtil(context);
                }
            }
        }
        return systemContactsUtil;
    }

    public List<Contact> getContacts()
    {
        List<Contact> lists=new ArrayList<Contact>();
        Cursor cursor=context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        while ( cursor.moveToNext() )
        {
            String phoneNumber = phoneNumber=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String phoneName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            lists.add(new Contact(phoneName,phoneNumber));
        }
        return  lists;
    }
    public void addContact(Contact contact){
        String name = contact.getName();
        String phone = contact.getNumber();

        ContentValues values = new ContentValues();
        values.put(Contacts.People.NAME, name);
        Uri uri = context.getContentResolver().insert(Contacts.People.CONTENT_URI, values);
        Uri numberUri = Uri.withAppendedPath(uri, Contacts.People.Phones.CONTENT_DIRECTORY);
        values.clear();

        values.put(Contacts.Phones.TYPE, Contacts.People.Phones.TYPE_MOBILE);
        values.put(Contacts.People.NUMBER, phone);
        context.getContentResolver().insert(numberUri, values);
    }

}
