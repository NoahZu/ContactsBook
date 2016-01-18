package contacts.xiaozuzu.github.io.contactsbook.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import contacts.xiaozuzu.github.io.contactsbook.R;
import contacts.xiaozuzu.github.io.contactsbook.activity.AddContactActivity;
import contacts.xiaozuzu.github.io.contactsbook.activity.MainActivity;
import contacts.xiaozuzu.github.io.contactsbook.adapter.SortAdapter;
import contacts.xiaozuzu.github.io.contactsbook.model.Contact;
import contacts.xiaozuzu.github.io.contactsbook.model.PinyinComparator;
import contacts.xiaozuzu.github.io.contactsbook.util.SqlUtil;
import contacts.xiaozuzu.github.io.contactsbook.widget.SideBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {


    private static final String TAG = "ContactsFragment";
    private ListView contactList;
    private View contentView;
    private MainActivity mainActivity;

    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private List<Contact> contacts;

    public static final String CONTACT_KEY = "contact";

    String[] alertMenuItems = {
            "呼叫","编辑","收藏"
    };


    public ContactsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_contacts, container, false);
        return contentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity = (MainActivity)getActivity();
        contacts = mainActivity.getContacts();
        Collections.sort(contacts,new PinyinComparator());
        initView();
    }

    private void initView() {
        contactList = (ListView)contentView.findViewById(R.id.contacts_list);
        sideBar = (SideBar) contentView.findViewById(R.id.sidrbar);
        dialog = (TextView) contentView.findViewById(R.id.dialog);
        sideBar.setTextView(dialog);
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    contactList.setSelection(position + 1);
                }

            }
        });
        adapter = new SortAdapter(getActivity(),contacts);
        contactList.setAdapter(adapter);
        contactList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(contacts.get(position).getNumber());
                builder.setItems(alertMenuItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                dialContact(position);
                                break;
                            case 1:
                                editContact(position);
                                break;
                            case 2:
                                collectContact(position);
                                break;
                        }
                    }
                });
                builder.create().show();
                return true;
            }
        });
    }

    private void collectContact(int position) {
        Log.d(TAG,"collect");
        SqlUtil sqlUtil = SqlUtil.getInstance(getActivity());
        if (sqlUtil.writeContect(contacts.get(position))){
            Toast.makeText(getActivity(),"收藏成功",Toast.LENGTH_SHORT).show();
            mainActivity.getCollectFragment().notifyDataSetChanged();
        }else {
            Toast.makeText(getActivity(),"您已经收藏过该联系人",Toast.LENGTH_SHORT).show();
        }
    }

    private void editContact(int position) {
        Intent intent = new Intent(getActivity(), AddContactActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(CONTACT_KEY,contacts.get(position));
        intent.putExtra(CONTACT_KEY, bundle);
        startActivity(intent);
    }

    private void dialContact(int position) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + contacts.get(position).getNumber()));
        startActivity(intent);
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

    public void addContact(Contact contact){
        contacts.add(contact);
        Collections.sort(contacts, new PinyinComparator());
        adapter.notifyDataSetChanged();
    }
}
