package contacts.xiaozuzu.github.io.contactsbook.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import contacts.xiaozuzu.github.io.contactsbook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends Fragment {

    private ListView collectList;
    private View contentView;

    String[] names = {
            "John","Noah","Anna"
    };

    public CollectFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_collect, container, false);
        initView();
        return contentView;
    }

    private void initView() {
        collectList = (ListView)contentView.findViewById(R.id.collect_list);
        MyAdapter myAdapter = new MyAdapter();
        collectList.setAdapter(myAdapter);
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
