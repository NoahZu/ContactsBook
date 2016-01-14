package androidbasicutilproject.xiaozuzu.github.io.xzzandroidsupport.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2016/1/13.
 */
public class ImageUtil {
    public static Drawable bitmap2Drawable(Context context,Bitmap bitmap){
        Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
        return drawable;
    }
}
