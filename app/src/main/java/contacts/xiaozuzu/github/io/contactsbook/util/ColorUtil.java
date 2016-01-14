package contacts.xiaozuzu.github.io.contactsbook.util;

import android.content.Context;
import android.graphics.Color;

import java.util.Random;

import androidbasicutilproject.xiaozuzu.github.io.xzzandroidsupport.utils.LogUtil;
import contacts.xiaozuzu.github.io.contactsbook.R;

/**
 * Created by Administrator on 2016/1/14.
 */
public class ColorUtil {
    private static final int[] colorList= new int[]{
                R.color.header_back1,
                R.color.header_back2,
                R.color.header_back3,
                R.color.header_back4,
                R.color.header_back5,
                R.color.header_back6,
                R.color.header_back7,
                R.color.header_back8,
                R.color.header_back9,
                R.color.header_back10,
                R.color.header_back11,
                R.color.header_back12,
                R.color.header_back13,
                R.color.header_back14,
                R.color.header_back15,
                R.color.header_back16,
                R.color.header_back17,
                R.color.header_back18,
                R.color.header_back19
    };

    private static Random random = new Random();
    public static int getRandomColor(Context context){
        int index = random.nextInt(19);
        return context.getResources().getColor(colorList[index]);
    }
}
