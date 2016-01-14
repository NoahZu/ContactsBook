package contacts.xiaozuzu.github.io.contactsbook.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import contacts.xiaozuzu.github.io.contactsbook.R;
import contacts.xiaozuzu.github.io.contactsbook.fragment.CollectFragment;
import contacts.xiaozuzu.github.io.contactsbook.fragment.ContactsFragment;
import contacts.xiaozuzu.github.io.contactsbook.fragment.DialFragment;
import contacts.xiaozuzu.github.io.contactsbook.model.Contact;
import contacts.xiaozuzu.github.io.contactsbook.util.SystemContactsUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton addContact;
    private SystemContactsUtil systemContactsUtil;
    private List<Contact> contacts;

    public DialFragment getDialFragment() {
        return dialFragment;
    }

    public ContactsFragment getContactsFragment() {
        return contactsFragment;
    }

    public CollectFragment getCollectFragment() {
        return collectFragment;
    }

    DialFragment dialFragment;
    ContactsFragment contactsFragment;
    CollectFragment collectFragment;

    String[] titles = new String[]{
            "拨号",
            "通讯录",
            "收藏"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        systemContactsUtil = SystemContactsUtil.getInstance(this);
        contacts = systemContactsUtil.getContacts();
        initFragment();
        initViews();
    }

    private void initFragment() {
         dialFragment = new DialFragment();
         contactsFragment = new ContactsFragment();
         collectFragment = new CollectFragment();
    }


    private void initViews() {
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        addContact = (FloatingActionButton)findViewById(R.id.id_add);

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddContactActivity.class);
                startActivity(intent);

            }
        });
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0)
                    return dialFragment;
                if (position == 1)
                    return contactsFragment;
                return collectFragment;
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    addContact.setVisibility(View.INVISIBLE);
                }else {
                    addContact.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public List<Contact> getContacts(){
        return this.contacts;
    }
}
