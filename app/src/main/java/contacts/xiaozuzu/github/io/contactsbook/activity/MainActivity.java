package contacts.xiaozuzu.github.io.contactsbook.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import contacts.xiaozuzu.github.io.contactsbook.R;
import contacts.xiaozuzu.github.io.contactsbook.fragment.CollectFragment;
import contacts.xiaozuzu.github.io.contactsbook.fragment.ContactsFragment;
import contacts.xiaozuzu.github.io.contactsbook.fragment.DialFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton addContact;


    String[] titles = new String[]{
            "拨号",
            "通讯录",
            "收藏"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }


    private void initViews() {
        tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        addContact = (FloatingActionButton)findViewById(R.id.id_add);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0)
                    return new DialFragment();
                if (position == 1)
                    return new ContactsFragment();
                return new CollectFragment();
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
    }
}
