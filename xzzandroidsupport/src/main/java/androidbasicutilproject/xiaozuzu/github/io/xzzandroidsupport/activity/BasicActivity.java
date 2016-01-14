package androidbasicutilproject.xiaozuzu.github.io.xzzandroidsupport.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androidbasicutilproject.xiaozuzu.github.io.xzzandroidsupport.R;
import androidbasicutilproject.xiaozuzu.github.io.xzzandroidsupport.utils.ActivityCollector;
import androidbasicutilproject.xiaozuzu.github.io.xzzandroidsupport.utils.LogUtil;

public class BasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.setTag("BasicActivity");
        LogUtil.d(getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
