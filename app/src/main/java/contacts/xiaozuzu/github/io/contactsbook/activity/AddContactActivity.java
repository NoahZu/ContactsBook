package contacts.xiaozuzu.github.io.contactsbook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import contacts.xiaozuzu.github.io.contactsbook.R;
import contacts.xiaozuzu.github.io.contactsbook.fragment.ContactsFragment;
import contacts.xiaozuzu.github.io.contactsbook.model.Contact;
import contacts.xiaozuzu.github.io.contactsbook.util.SystemContactsUtil;

public class AddContactActivity extends AppCompatActivity {

    private EditText nameEdit;
    private EditText numberEdit;
    private Button saveBtn;
    private Button resetBtn;
    private TextView closeTxt;
    private Contact contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(ContactsFragment.CONTACT_KEY);
        if (bundle != null){
            contact = (Contact)bundle.get(ContactsFragment.CONTACT_KEY);
        }
        initView();
        initViewEvents();
    }

    private void initViewEvents() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();
                String number = numberEdit.getText().toString();
                if (name.equals("") || number.equals("")){
                    Toast.makeText(AddContactActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!number.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")){
                    Toast.makeText(AddContactActivity.this,"号码请输入数字",Toast.LENGTH_SHORT).show();
                    return;
                }
                addContact(new Contact(name,number));
                finish();
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEdit.setText("");
                numberEdit.setText("");
            }
        });
        closeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addContact(Contact contact) {
        SystemContactsUtil systemContactsUtil = SystemContactsUtil.getInstance(this);
        systemContactsUtil.addContact(contact);
        Toast.makeText(this,"添加成功",Toast.LENGTH_LONG).show();
    }

    private void initView() {
        nameEdit = (EditText)findViewById(R.id.edt_name);
        numberEdit = (EditText)findViewById(R.id.edt_number);
        if (contact != null){
            nameEdit.setText(contact.getName());
            numberEdit.setText(contact.getNumber());
        }
        saveBtn = (Button)findViewById(R.id.btn_save);
        resetBtn = (Button)findViewById(R.id.btn_reset);
        closeTxt = (TextView)findViewById(R.id.close);
    }
}
