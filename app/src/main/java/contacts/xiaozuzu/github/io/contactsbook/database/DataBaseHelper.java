package contacts.xiaozuzu.github.io.contactsbook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/1/12.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_TABLE = "create table contacts ("
            + "id integer primary key autoincrement, "
            + "name text, "
            + "phone text)";
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
