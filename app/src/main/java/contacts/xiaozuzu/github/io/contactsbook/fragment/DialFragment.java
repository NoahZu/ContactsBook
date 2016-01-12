package contacts.xiaozuzu.github.io.contactsbook.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import contacts.xiaozuzu.github.io.contactsbook.R;
import contacts.xiaozuzu.github.io.contactsbook.activity.MainActivity;
import contacts.xiaozuzu.github.io.contactsbook.model.Contact;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialFragment extends Fragment {

    private Button delButton;
    private Button dialButton;
    private TextView displayNumber;
    private ListView hintList;
    private Map<Integer,String> numberMap = new HashMap<>();
    private int[] numberIds = {
            R.id.one,
            R.id.two,
            R.id.three,
            R.id.four,
            R.id.five,
            R.id.six,
            R.id.seven,
            R.id.eight,
            R.id.nine,
            R.id.star,
            R.id.zero,
            R.id.jing,
    };
    private List<Contact> contacts;
    private List<Contact> hintContacts;

    private Button[] numberButtons = new Button[12];
    private View contentView;
    private StringBuilder currentNumber = new StringBuilder();
    private MainActivity mainActivity;
    private MyAdapter myAdapter;

    public DialFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initNumberMap();
    }

    private void initNumberMap() {
        for(int i = 0;i<numberButtons.length;i++){
            if (i == 9)
                numberMap.put(numberIds[i],"*");
            else if (i == 10)
                numberMap.put(numberIds[i],"0");
            else if (i == 11)
                numberMap.put(numberIds[i],"#");
            else {
                numberMap.put(numberIds[i],(i+1)+"");
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_dial, container, false);
        return this.contentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity = (MainActivity)getActivity();
        contacts = mainActivity.getContacts();
        hintContacts = new ArrayList<>();
        myAdapter = new MyAdapter();
        initView();
        initNumberView();
    }

    private void initNumberView() {
        NumberOnClickListener numberOnClickListener = new NumberOnClickListener();
        for (int i = 0;i<numberButtons.length;i++){
            numberButtons[i] = (Button)contentView.findViewById(numberIds[i]);
        }
        for (int i = 0;i<numberButtons.length;i++){
            numberButtons[i].setOnClickListener(numberOnClickListener);
        }
    }

    private void initView() {
        displayNumber = (TextView)contentView.findViewById(R.id.display_number);
        delButton = (Button)contentView.findViewById(R.id.del_button);
        hintList = (ListView)contentView.findViewById(R.id.hint_list);
        hintList.setAdapter(myAdapter);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNumber.toString().length() >= 1)
                    currentNumber.deleteCharAt(currentNumber.toString().length() - 1);
                displayNumber.setText(currentNumber.toString());
                filterContacts();
            }
        });
        dialButton = (Button)contentView.findViewById(R.id.dial_button);
        dialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + currentNumber.toString()));
                startActivity(intent);
            }
        });
    }


    class NumberOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            currentNumber.append(numberMap.get(v.getId()).toString());
            displayNumber.setText(currentNumber.toString());
            filterContacts();
        }
    }

    public void filterContacts(){
        hintContacts.clear();
        String hintNumber = currentNumber.toString();
        if (!hintNumber.equals("")){
            for(Contact contact : contacts){
                if (contact.getNumber().startsWith(hintNumber)){
                    hintContacts.add(contact);
                }
            }
        }
        myAdapter.notifyDataSetChanged();
    }


    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return hintContacts.size();
        }

        @Override
        public Contact getItem(int position) {
            return hintContacts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.hint_list_item,null);
                ViewHolder vh = new ViewHolder(convertView);
                convertView.setTag(vh);
            }
            ViewHolder viewHolder = (ViewHolder)convertView.getTag();
            viewHolder.setValues(getItem(position));
            return convertView;
        }

        class ViewHolder{
            TextView nameTxt;
            TextView numberTxt;
            public ViewHolder(View convertView){
                nameTxt = (TextView)convertView.findViewById(R.id.hit_name);
                numberTxt = (TextView)convertView.findViewById(R.id.hit_number);
            }
            public void setValues(Contact contact){
                nameTxt.setText(contact.getName());
                numberTxt.setText(contact.getNumber());
            }
        }
    }
}
