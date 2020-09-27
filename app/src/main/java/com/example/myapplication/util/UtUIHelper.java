package com.example.myapplication.util;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.palette.graphics.Palette;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SizeUtils;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class UtUIHelper {

    public static final int UI_LABEL_WIDTH_GROUP_1 = 36;
    public static final int UI_LABEL_WIDTH_GROUP_2 = 28;
    public static final int UI_LABEL_WIDTH_GROUP_3 = 20;
    public static final int UI_LABEL_WIDTH_GROUP_4 = 40;
    public static final int UI_LABEL_HEIGHT_GROUP_1 = 18;
    public static final int UI_LABEL_HEIGHT_GROUP_2 = 14;
    public static final int UI_LABEL_HEIGHT_GROUP_3 = 10;
    public static final int UI_LABEL_HEIGHT_GROUP_4 = 20;
    public static final int UI_LABEL_DIAMETER = 20;
    public static final int UI_LABEL_TEXT_SIZE_1 = 10;
    public static final int UI_LABEL_TEXT_SIZE_2 = 8;
    public static final int UI_LABEL_TEXT_SIZE_3 = 6;


//    private static int mScreenWidth;
//    private static int mScreenHeight;

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            view.measure(0, 0);
            totalHeight += view.getMeasuredHeight();
        }


        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)
        );
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public static int dipToPx(Context context, int dipValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, context
                .getResources().getDisplayMetrics());
    }

    public static int spToPx(Context context, int dipValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dipValue, context
                .getResources().getDisplayMetrics());
    }

    public static Bitmap resizeImage(Bitmap bitmap, int width, int height) {
        Bitmap bitmapOrg = bitmap;
        int bitmapWidth = bitmapOrg.getWidth();
        int bitmapHeigth = bitmapOrg.getHeight();

        float scaleWidth = ((float) width) / bitmapWidth;
        float scaleHeigth = ((float) height) / bitmapHeigth;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeigth);

        return Bitmap.createBitmap(bitmapOrg, 0, 0, bitmapWidth, bitmapHeigth, matrix, true);

    }

    public static byte[] bitmapToByte(Bitmap bitmap) {
        Bitmap thBitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        thBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
//        bitmap.recycle();
        thBitmap.recycle();
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] bitmapToByte(Bitmap bitmap, int width, int height, int ratio) {
        Bitmap thBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        thBitmap.compress(Bitmap.CompressFormat.JPEG, ratio, byteArrayOutputStream);
        bitmap.recycle();
        thBitmap.recycle();
        return byteArrayOutputStream.toByteArray();
    }

    public static int getScreenWidth(Context context) {
        int[] screenSize = getScreenSize(context);
        return screenSize[0];
    }

//    public static int getScreenHeight(Context context) {
//        int[] screenSize = getScreenSize(context);
//        int screenHeight = screenSize[1];
//        if (isNavigationBarExist()) {
//            screenHeight = screenHeight - getNavigationBarHeight(context);
//        }
//        return screenHeight;
//    }


//    private static int getScreenContentHeight(Context context) {
//        int height = 0;
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        if (wm != null) {
//            Display d = wm.getDefaultDisplay();
//            DisplayMetrics displayMetrics = new DisplayMetrics();
//            d.getMetrics(displayMetrics);
//            height = displayMetrics.heightPixels;
//        }
//        return height;
//    }

    private static int[] getScreenSize(Context context) {
        int[] size = new int[2];
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager == null) {
            return size;
        }
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        int widthPix = displayMetrics.widthPixels;
        int heightPix = displayMetrics.heightPixels;

        if (Build.VERSION.SDK_INT < 17) {
            try {
                widthPix = (int) Display.class.getMethod("getRawWidth").invoke(display);
                heightPix = (int) Display.class.getMethod("getRawHeight").invoke(display);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else {
            Point realSize = new Point();
            try {
                Display.class.getMethod("getRealSize", Point.class).invoke(display, realSize);
                widthPix = realSize.x;
                heightPix = realSize.y;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        size[0] = widthPix;
        size[1] = heightPix;
        return size;
    }


    public static int getStatusBarHeight(Context context) {
        if (context == null) {
            return 0;
        }
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }


    public static int getNavigationBarHeight(Context context) {
        int result = 0;
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float px2dp(Resources resources, float px) {
        final float scale = resources.getDisplayMetrics().density;
        return px / scale + 0.5f;
    }

    public static float px2dpPure(Resources resources, float px) {
        final float scale = resources.getDisplayMetrics().density;
        return px / scale;
    }

    public static float sp2px(Resources resources, float sp) {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }


//    //检查是否开启底部NavigationBar
//    public static boolean isNavigationBarExist() {
//        Activity activity = UtActivityManager.getCurrentActivity();
//        if (activity == null) {
//            return false;
//        }
//        ViewGroup vp = (ViewGroup) activity.getWindow().getDecorView();
//        for (int i = 0; i < vp.getChildCount(); i++) {
//            vp.getChildAt(i).getContext().getPackageName();
//
//            if (vp.getChildAt(i).getId() != -1 && "navigationBarBackground".equals(activity.getResources().getResourceEntryName(vp.getChildAt(i).getId()))) {
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * 通过palette 获取bitmap 最多的颜色，设置背景
     *
     * @param view
     * @param bitmap
     */
    public static void setPaletteView(View view, Bitmap bitmap) {
        if (view == null || bitmap == null) {
            return;
        }
        Palette.from(bitmap).generate(palette -> {
            if (palette != null) {
                Palette.Swatch swatch = palette.getDominantSwatch();
                if (swatch == null) {
                    view.setBackgroundColor(Color.YELLOW);
                    return;
                }
                view.setBackgroundColor(swatch.getRgb());
            }
        });
    }


    /**
     * 根据位置添加角标（背景色和文字动态）
     *
     * @param context
     * @param position
     * @param content
     * @param textWidth
     * @param textHeight
     * @param textColor
     * @param backgroundColor
     * @param textSize
     * @return
     */
    public static TextView addLabelTextView(Context context, String position, String content, int textWidth, int textHeight, String textColor, String backgroundColor, int textSize) {
        return addLabelTextView(context, position, content, textWidth, textHeight, textColor, backgroundColor, textSize, 12);
    }

    /**
     * 根据位置添加角标（背景色和文字动态）
     *
     * @param context
     * @param position
     * @param content
     * @param textWidth
     * @param textHeight
     * @param textColor
     * @param backgroundColor
     * @param textSize
     * @return
     */
    public static TextView addLabelTextView(Context context, String position, String content, int textWidth, int textHeight, String textColor, String backgroundColor, int textSize, int cornerRadii) {
        TextView textView = new TextView(context);
        textView.setIncludeFontPadding(false);
        textView.setMinHeight(UtUIHelper.dipToPx(context.getApplicationContext(), textHeight));
        textView.setMinWidth(UtUIHelper.dipToPx(context.getApplicationContext(), textWidth));
        textView.setGravity(Gravity.CENTER);
        textView.setText(content);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        textView.setPadding(UtUIHelper.dipToPx(context.getApplicationContext(), 6), UtUIHelper.dipToPx(context.getApplicationContext(), 3), UtUIHelper.dipToPx(context.getApplicationContext(), 6), UtUIHelper.dipToPx(context.getApplicationContext(), 3));
        if (!TextUtils.isEmpty(textColor)) {
            textView.setTextColor(Color.parseColor(textColor));
        }
        if (!TextUtils.isEmpty(backgroundColor)) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            Drawable backgroundDrawable = null;

            int parseColor;
            try {
                parseColor = Color.parseColor(backgroundColor);
            } catch (IllegalArgumentException e) {
                parseColor = Color.TRANSPARENT;
            }
            int px = UtUIHelper.dipToPx(context, cornerRadii);
            int dp10 = UtUIHelper.dipToPx(context, 10);
            if ("left-top".equals(position)) {
                backgroundDrawable = UtUIHelper.getGradientDrawable(parseColor, new float[]{px, px, 0, 0, dp10, dp10, 0, 0});
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            } else if ("right-top".equals(position)) {
                backgroundDrawable = UtUIHelper.getGradientDrawable(parseColor, new float[]{0, 0, px, px, 0, 0, dp10, dp10});
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            } else if ("right-bottom".equals(position)) {
                backgroundDrawable = UtUIHelper.getGradientDrawable(parseColor, new float[]{dp10, dp10, 0, 0, px, px, 0, 0});
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            } else if ("left-bottom".equals(position)) {
                backgroundDrawable = UtUIHelper.getGradientDrawable(parseColor, new float[]{0, 0, dp10, dp10, 0, 0, px, px});
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            } else if ("center".equals(position)) {
                backgroundDrawable = UtUIHelper.getGradientDrawable(parseColor, new float[]{px, px, px, px, px, px, px, px});
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            }
            textView.setLayoutParams(layoutParams);
            textView.setBackgroundDrawable(backgroundDrawable);
        }
        return textView;
    }

//    /**
//     * 获取底部弹窗背景
//     */
//    public static GradientDrawable getBottomDialogBg() {
//        GradientDrawable gradientDrawable = new GradientDrawable();
//        gradientDrawable.setColor(Color.WHITE);
//        int radius = SizeUtils.dp2px(14);
//        gradientDrawable.setCornerRadii(new float[]{radius, radius, radius, radius, 0, 0, 0, 0});
//        return gradientDrawable;
//    }

    /**
     * 获取圆角背景
     * @param radii 四个角弧度
     * @param color 颜色
     */
    public static GradientDrawable getGradientDrawable(int color, float[] radii) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadii(radii);
        return gradientDrawable;
    }

    /**
     * 获取圆角背景
     * @param radii 四个角各自的弧度
     * @param color 颜色
     */
    public static GradientDrawable getGradientDrawable(String color, float[] radii) {
        int parseColor;
        try {
            parseColor = Color.parseColor(color);
        } catch (IllegalArgumentException e) {
            parseColor = Color.TRANSPARENT;
        }
        return getGradientDrawable(parseColor, radii);
    }

    /**
     * 获取圆角背景
     * @param radiusDb 弧度
     * @param color 颜色
     */
    public static GradientDrawable getGradientDrawable(int radiusDb, String color) {
        int parseColor;
        try {
            parseColor = Color.parseColor(color);
        } catch (IllegalArgumentException e) {
            parseColor = Color.TRANSPARENT;
        }
        return getGradientDrawable(radiusDb, parseColor);
    }

    /**
     * 获取圆角背景
     * @param radiusDb 弧度
     * @param color 颜色
     */
    public static GradientDrawable getGradientDrawable(int radiusDb, int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(UtUIHelper.dp2px(Resources.getSystem(), radiusDb));
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }


    /**
     * 获取半圆角背景
     * @param color 颜色
     */
    public static GradientDrawable get360GradientDrawable(int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(360);
        gradientDrawable.setColor(color);
        return gradientDrawable;
    }

    /**
     * 获取只有边框的半圆角背景
     * @param color 颜色
     * @param width 边框宽度
     */
    public static GradientDrawable get360GradientDrawableOnlyStroke(int color, int width) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(360);
        gradientDrawable.setStroke(SizeUtils.dp2px(width), color);
        return gradientDrawable;
    }

    /**
     * 获取只有边框的圆角背景
     * @param radiusDb 弧度
     * @param color 颜色
     * @param width 边框宽度
     */
    public static GradientDrawable getGradientDrawableOnlyStroke(int radiusDb, int color, int width) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(radiusDb);
        gradientDrawable.setStroke(SizeUtils.dp2px(width), color);
        return gradientDrawable;
    }

    /**
     * 椭圆形纯色背景
     *
     * @param color
     * @return
     */
    public static GradientDrawable get360GradientDrawable(String color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(UtUIHelper.dp2px(Resources.getSystem(), 360));
        int parseColor;
        try {
            parseColor = Color.parseColor(color);
        } catch (IllegalArgumentException e) {
            parseColor = Color.TRANSPARENT;
        }
        gradientDrawable.setColor(parseColor);
        return gradientDrawable;
    }

//
//    public static ImageView addLabelImageView(Context context, String position, int width, int height, String srcUrl) {
//        return getLabelImageView(context, position, width, height, srcUrl, 0);
//    }
//
//    public static ImageView addLabelImageView(Context context, String position, int width, int height, int defSrc) {
//        return getLabelImageView(context, position, width, height, "", defSrc);
//    }


//    /**
//     * 根据位置动态添加图片角标
//     *
//     * @param context
//     * @param position
//     * @param width
//     * @param height
//     * @param srcUrl
//     * @param defSrc
//     * @return
//     */
//    private static ImageView getLabelImageView(Context context, String position, int width, int height, String srcUrl, int defSrc) {
//        ImageView imageView = new ImageView(context);
//        imageView.setMaxHeight(UtUIHelper.dipToPx(context.getApplicationContext(), height));
//        imageView.setMinimumWidth(UtUIHelper.dipToPx(context.getApplicationContext(), width));
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        if ("left-top".equals(position)) {
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//        } else if ("right-top".equals(position)) {
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//        } else if ("right-bottom".equals(position)) {
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//        } else if ("left-bottom".equals(position)) {
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//        } else if ("center".equals(position)) {
//            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//        }
//        imageView.setLayoutParams(layoutParams);
//        if (defSrc > 0) {
//            UtImageLoader.displayImage(context.getApplicationContext(), srcUrl, imageView, defSrc);
//        } else {
//            UtImageLoader.displayImage(context.getApplicationContext(), srcUrl, imageView);
//        }
//        return imageView;
//    }


//    /**
//     * 添加角标
//     *
//     * @param contentLabelLayout
//     * @param masks
//     */
//    public static void setCoverLabelView(RelativeLayout contentLabelLayout, List<Map<String, Object>> masks, int width, int height, int fontSize) {
//        if (contentLabelLayout == null) {
//            return;
//        }
//        if (masks == null || masks.size() == 0) {
//            contentLabelLayout.setVisibility(View.GONE);
//            return;
//        }
//        contentLabelLayout.removeAllViews();
//        contentLabelLayout.setVisibility(View.VISIBLE);
//        for (int i = 0; i < masks.size(); i++) {
//            Map<String, Object> mask = masks.get(i);
//            if (mask != null) {
//                String position = UtMapGetter.getMapObject2String(mask, "position");
//                String type = UtMapGetter.getMapObject2String(mask, "type");
//                String content = UtMapGetter.getMapObject2String(mask, "content");
//                String textColor = UtMapGetter.getMapObject2String(mask, "textColor");
//                String bgColor = UtMapGetter.getMapObject2String(mask, "bgColor");
//                if ("image".equals(type)) {
//                    contentLabelLayout.addView(UtUIHelper.addLabelImageView(contentLabelLayout.getContext(), position, UI_LABEL_WIDTH_GROUP_4, UI_LABEL_HEIGHT_GROUP_4, content));
//                } else {
//                    contentLabelLayout.addView(UtUIHelper.addLabelTextView(contentLabelLayout.getContext(), position, content, width, height, textColor, bgColor, fontSize));
//                }
//            }
//        }
//    }

    public static void setStatusBarHeight(View statusBar){
        if(statusBar == null) return;
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        if(layoutParams != null){
            layoutParams.height = BarUtils.getStatusBarHeight();
            statusBar.setLayoutParams(layoutParams);
        }
    }

    /**
     * 设置粗体
     * @param textView
     */
    public static void setTextBold(TextView textView){
        if(textView != null){
            textView.setTypeface(Typeface.DEFAULT);
            textView.getPaint().setFakeBoldText(true);
//            paint.setStyle(Paint.Style.FILL_AND_STROKE);
//            paint.setStrokeWidth(dp2px(0.2f)); //Bold == 4
        }
    }


    public static float dp2px(final float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return dpValue * scale + 0.5f;
    }

}
