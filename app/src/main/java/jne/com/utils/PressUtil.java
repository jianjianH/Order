package jne.com.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import jne.com.order.OrderContext;

/**
 * 处理按钮的按下效果
 * Created by JianHuang
 * Date: 2015/1/13
 * Time: 11:08
 */
public class PressUtil {
    /**
     * color.xml里面的id
     */
    public static int getColor(int colorResId){
        Context context = OrderContext.getInstance().getApplicationContext();
        return context.getResources().getColor(colorResId);
    }

    public static Drawable getDrawable(int drawableResId){
        Context context = OrderContext.getInstance().getApplicationContext();
        return context.getResources().getDrawable(drawableResId);
    }

    public static AutoBuildBackgroundDrawable getBgDrawable(Drawable drawable){
        return new AutoBuildBackgroundDrawable(drawable);
    }

    public static AutoBuildBackgroundDrawable getBgDrawable(int resId){
        return new AutoBuildBackgroundDrawable(getDrawable(resId));
    }
}
