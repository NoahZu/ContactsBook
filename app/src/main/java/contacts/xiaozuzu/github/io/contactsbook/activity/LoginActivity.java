package contacts.xiaozuzu.github.io.contactsbook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.List;

import androidbasicutilproject.xiaozuzu.github.io.xzzandroidsupport.utils.SharedPreferenceUtil;
import androidbasicutilproject.xiaozuzu.github.io.xzzandroidsupport.utils.ToastUtil;
import contacts.xiaozuzu.github.io.contactsbook.R;
import contacts.xiaozuzu.github.io.contactsbook.model.Contact;
import contacts.xiaozuzu.github.io.contactsbook.util.SystemContactsUtil;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextPasswd;
    private CheckBox isRemember;
    private Button loginButton;


    private static final String REMEMBER_PASSWD_CONFIG = "remember_passwd";
    private static final String NAME_KEY = "name_key";
    private static final String PASSWD_KEY = "passwd_key";
    private static final String IS_REMEMBER = "is_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferenceUtil.setSharedPreferenceName(REMEMBER_PASSWD_CONFIG);
        initView();
        loadInfo();
        initEvents();
    }

    private void loadInfo() {
        if (SharedPreferenceUtil.getBoolean(LoginActivity.this,IS_REMEMBER)){
            isRemember.setChecked(true);
            editTextUserName.setText(SharedPreferenceUtil.getString(this,NAME_KEY));
            editTextPasswd.setText(SharedPreferenceUtil.getString(this,PASSWD_KEY));
        }
    }

    private void initEvents() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextUserName.getText().toString()) || TextUtils.isEmpty(editTextPasswd.getText().toString())){
                    ToastUtil.show(LoginActivity.this,"试试用自己的名字和手机号");
                    return;
                }
                if (isRemember.isChecked()){
                    SharedPreferenceUtil.addInSharePreference(LoginActivity.this,IS_REMEMBER,true);
                    SharedPreferenceUtil.addInSharePreference(LoginActivity.this,NAME_KEY,editTextUserName.getText().toString());
                    SharedPreferenceUtil.addInSharePreference(LoginActivity.this, PASSWD_KEY, editTextPasswd.getText().toString());
                }else {
                    SharedPreferenceUtil.addInSharePreference(LoginActivity.this,IS_REMEMBER,false);
                    SharedPreferenceUtil.addInSharePreference(LoginActivity.this,NAME_KEY,"");
                    SharedPreferenceUtil.addInSharePreference(LoginActivity.this, PASSWD_KEY,"");
                }
                if (isCorrectUser(editTextUserName.getText().toString(), editTextPasswd.getText().toString())){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    ToastUtil.show(LoginActivity.this, "您不是有效用户！");
                }
            }
        });

    }

    private boolean isCorrectUser(String name,String passwd) {
        SystemContactsUtil systemContactsUtil = SystemContactsUtil.getInstance(this);
        List<Contact> contacts = systemContactsUtil.getContacts();
        for (Contact contact : contacts){
            if (name.equals(contact.getName()) && passwd.equals(contact.getNumber())){
                return true;
            }
        }
        return false;
    }

    private void initView() {
        editTextUserName = (EditText) findViewById(R.id.login_name);
        editTextPasswd = (EditText) findViewById(R.id.login_passwd);
        isRemember = (CheckBox) findViewById(R.id.ck_remember);
        loginButton = (Button) findViewById(R.id.login);
    }
}
