package contacts.xiaozuzu.github.io.contactsbook.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import contacts.xiaozuzu.github.io.contactsbook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {


    private static final String TAG = "ContactsFragment";
    private ListView contactList;
    private View contentView;
    private Context context;

    String[] alertMenuItems = {
            "呼叫","编辑","收藏"
    };

    String[] names = {
            "俎金好",
            "周凯",
            "张三",
            "俎金好",
            "周凯",
            "张三",
            "俎金好",
            "周凯",
            "张三"
    };

    public ContactsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_contacts, container, false);
        initView();
        return contentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void initView() {
        contactList = (ListView)contentView.findViewById(R.id.contacts_list);
        MyAdapter adapter = new MyAdapter();
        contactList.setAdapter(adapter);

        contactList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setItems(alertMenuItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG,"which"+which);
                    }
                });
                builder.create().show();
                return true;
            }
        });
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public String getItem(int position) {
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_list_item,null);
                ViewHolder vh = new ViewHolder();
                vh.nameText = (TextView)convertView.findViewById(R.id.name);
                convertView.setTag(vh);
            }
            ViewHolder vh = (ViewHolder)convertView.getTag();
            vh.nameText.setText(getItem(position));
            return convertView;
        }
        class ViewHolder{
            TextView nameText;
        }
    }
}
