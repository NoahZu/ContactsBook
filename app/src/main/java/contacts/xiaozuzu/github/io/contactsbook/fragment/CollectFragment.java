package contacts.xiaozuzu.github.io.contactsbook.fragment;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import contacts.xiaozuzu.github.io.contactsbook.R;
import contacts.xiaozuzu.github.io.contactsbook.model.Contact;
import contacts.xiaozuzu.github.io.contactsbook.util.SqlUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends Fragment {

    private static final String TAG = "CollectFragment";
    private ListView collectList;
    private View contentView;

    List<Contact> contacts;

    private MyAdapter myAdapter;
    public CollectFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SqlUtil sqlUtil = SqlUtil.getInstance(getActivity());
        contacts = sqlUtil.getContacts();
        contentView = inflater.inflate(R.layout.fragment_collect, container, false);
        initView();
        return contentView;
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d(TAG, "contacts" + contacts.size());
    }

    private void initView() {
        collectList = (ListView)contentView.findViewById(R.id.collect_list);
        myAdapter = new MyAdapter();
        collectList.setAdapter(myAdapter);
    }

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return contacts.size();
        }

        @Override
        public Contact getItem(int position) {
            return contacts.get(position);
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
            vh.nameText.setText(getItem(position).getName());
            return convertView;
        }
        class ViewHolder{
            TextView nameText;
        }
    }


    public MyAdapter getMyAdapter() {
        return myAdapter;
    }
}
