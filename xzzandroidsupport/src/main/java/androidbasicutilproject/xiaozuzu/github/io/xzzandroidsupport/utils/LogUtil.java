package androidbasicutilproject.xiaozuzu.github.io.xzzandroidsupport.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/1/13.
 */
public class LogUtil {
    private static String tag = "default";
    public static void setTag(String tag){
        LogUtil.tag = tag;
    }
    public static void v(String info){
        Log.v(tag, info);
    }
    public static void d(String info){
        Log.d(tag, info);
    }
    public static void i(String info){
        Log.i(tag, info);
    }
    public static void w(String info){
        Log.w(tag, info);
    }
    public static void e(String info){
        Log.e(tag, info);
    }


    public void main(){

    }
}
