package contacts.xiaozuzu.github.io.contactsbook.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

import androidbasicutilproject.xiaozuzu.github.io.xzzandroidsupport.utils.ToastUtil;
import contacts.xiaozuzu.github.io.contactsbook.R;
import contacts.xiaozuzu.github.io.contactsbook.model.Contact;
import contacts.xiaozuzu.github.io.contactsbook.model.PinyinComparator;
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
        Collections.sort(contacts,new PinyinComparator());
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
        collectList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("不在收藏"+contacts.get(position).getName()+"?");
                builder.setItems(new String[]{"删除", "呼叫"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            //// TODO: 2016/1/14 删除 
                            if (SqlUtil.getInstance(getActivity()).delContact(contacts.get(position))){
                                ToastUtil.show(getActivity(),"删除成功");
                                contacts.remove(contacts.get(position));
                                myAdapter.notifyDataSetChanged();
                            }else {
                                ToastUtil.show(getActivity(),"删除失败");
                            }

                        }
                        if (which == 1) {
                            //// TODO: 2016/1/14 呼叫
                            dialContact(position);
                        }
                    }
                });
                builder.create().show();
                return true;
            }
        });
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
                vh.tvLetter = (TextView)convertView.findViewById(R.id.letter);
                vh.tvTitle = (TextView)convertView.findViewById(R.id.name);
                vh.tvHeader = (TextView)convertView.findViewById(R.id.header_icon);
                vh.tvLetter.setVisibility(View.GONE);
                convertView.setTag(vh);
            }
            ViewHolder vh = (ViewHolder)convertView.getTag();
            vh.tvTitle.setText(getItem(position).getName());
            vh.tvHeader.setText(contacts.get(position).getName().subSequence(0, 1).toString());
            vh.tvHeader.setBackgroundColor(contacts.get(position).getHeaderColor());
            return convertView;
        }
        class ViewHolder{
            TextView tvLetter;
            TextView tvTitle;
            TextView tvHeader;
        }
    }


    public MyAdapter getMyAdapter() {
        return myAdapter;
    }
    public void notifyDataSetChanged(){
        contacts = SqlUtil.getInstance(getActivity()).getContacts();
        myAdapter.notifyDataSetChanged();
    }
}
