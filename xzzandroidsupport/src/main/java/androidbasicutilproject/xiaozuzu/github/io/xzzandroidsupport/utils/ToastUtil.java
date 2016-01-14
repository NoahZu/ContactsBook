package androidbasicutilproject.xiaozuzu.github.io.xzzandroidsupport.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/1/14.
 */
public class ToastUtil {
    public static void show(Context context,String info){
        Toast.makeText(context,info,Toast.LENGTH_LONG).show();
    }
}
