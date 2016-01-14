package contacts.xiaozuzu.github.io.contactsbook.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import contacts.xiaozuzu.github.io.contactsbook.database.DataBaseHelper;
import contacts.xiaozuzu.github.io.contactsbook.model.Contact;

/**
 * Created by Administrator on 2016/1/12.
 */
public class SqlUtil {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private Context context;

    private SqlUtil(Context context){
        this.context = context;
        openHelper = new DataBaseHelper(context,"contacts.db",null,1);
        db = openHelper.getWritableDatabase();
    }
    public static SqlUtil getInstance(Context context){
        SqlUtil sqlUtil = new SqlUtil(context);
        return sqlUtil;
    }

    /**
     * sql example :insert into contacts values(null,'zs','15764225732')
     * insert contacts to database
     * @param List<Contact> contacts
     * */
    public void writeContacts(List<Contact> contacts){
        for(Contact contact : contacts){
            String sql = "insert into contacts values(null,'"+contact.getName()+"','"+contact.getNumber()+"')";
            db.execSQL(sql);
        }
    }

    public boolean writeContect(Contact contact){
        if (!isExits(contact)){
            String sql = "insert into contacts values(null,'"+contact.getName()+"','"+contact.getNumber()+"')";
            db.execSQL(sql);
            return true;
        }
        return false;
    }

    public List<Contact> getContacts(){
        String sql = "select * from contacts";
        List<Contact> contacts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,null);

        while (cursor.moveToNext()){
            Contact contact = new Contact(context,cursor.getString(1),cursor.getString(2));
            contacts.add(contact);
        }
        return contacts;
    }

    public boolean isExits(Contact contact){
        String sql = "select * from contacts";
        Cursor cursor = db.rawQuery(sql,null);

        while (cursor.moveToNext()){
            if (cursor.getString(1).equals(contact.getName()) || cursor.getString(2).equals(contact.getNumber())){
                return true;
            }
        }
        return false;
    }
}
