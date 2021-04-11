package com.example.aggregate_methods.tools;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Environment;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CREATE BY LiYang
 * ON 2021-01-26
 * SUPPLY : Thanks for watching
 */
public class Methods {
    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static String getTimeStamp() {
        long millis = System.currentTimeMillis();
        return millis + "";
    }

    /**
     * 将年月日时分秒转成时间戳
     *
     * @param dateTime
     * @return
     */
    public static String dateToStamp(String dateTime) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /**
     * 将时间戳转成年月日时分秒
     *
     * @param timeStamp
     * @return
     */
    public static String stampToDate(String timeStamp) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long l = Long.valueOf(timeStamp);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

    /**
     * 将时间戳转成年月日汉字
     *
     * @param timeStamp
     * @return
     */
    public static String stampToDateCN(String timeStamp) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        long l = Long.valueOf(timeStamp);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

    /**
     * 补全月日的前置0单位
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String completionDate(int year, int month, int day) {
        String date = null;
        if (month + 1 < 10) {
            if (day < 10) {
                date = new StringBuffer().append(year).append("-").append("0").
                        append(month).append("-").append("0").append(day).toString();
            } else {
                date = new StringBuffer().append(year).append("-").append("0").
                        append(month).append("-").append(day).toString();
            }

        } else {
            if (day < 10) {
                date = new StringBuffer().append(year).append("-").
                        append(month).append("-").append("0").append(day).toString();
            } else {
                date = new StringBuffer().append(year).append("-").
                        append(month).append("-").append(day).toString();
            }
        }
        return date;
    }


    /**
     * 键盘回收
     *
     * @param activity
     */

    public static void isKeyboardBack(Activity activity) {
        View v = activity.getWindow().peekDecorView();
        if (v != null && v.getWindowToken() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    /**
     * 丰文
     *
     * @param content
     * @param start
     * @param end
     * @param color
     * @param textView
     */
    public static void isSpannable(String content, int start, int end, String color, TextView textView) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(content);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor(color));
        stringBuilder.setSpan(foregroundColorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(stringBuilder);
    }

    /**
     * 判断字符串是否为空或者null
     *
     * @param content
     * @return
     */
    public static boolean isEmpty(String content) {
        if (null == content || "".equals(content) || "null".equals(content)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 图片根据imageView的最短边进行等比例缩小
     *
     * @param imageView 展示的imageView
     * @param width     图片实际的宽度
     * @param height    图片实际的高度
     */
    public static void imageViewScale(ImageView imageView, int width, int height) {
        if (imageView.getWidth() <= imageView.getHeight() || imageView.getHeight() == 0) {
            int imageViewWidth = imageView.getWidth();
            float sy = (float) (imageViewWidth * 0.1) / (float) (width * 0.1);
            //计算图片等比例放大后的高
            int imageViewHeight = (int) (height * sy);
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.height = imageViewHeight;
            imageView.setLayoutParams(params);
        } else {
            int imageViewHeight = imageView.getHeight();
            float sy = (float) (imageViewHeight * 0.1) / (float) (height * 0.1);
            //计算图片等比例放大后的高
            int imageViewWidth = (int) (width * sy);
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.width = imageViewWidth;
            imageView.setLayoutParams(params);
        }
    }

    /**
     * 搜索内容的关键字
     *
     * @param title
     * @param keyword
     * @return
     */
    public static String matcherSearchTitle(String title, String keyword) {
        String content = title;
        String wordReg = "(?i)" + keyword;//用(?i)来忽略大小写
        StringBuffer sb = new StringBuffer();
        Matcher matcher = Pattern.compile(wordReg).matcher(content);
        while (matcher.find()) {
            //这样保证了原文的大小写没有发生变化
            matcher.appendReplacement(sb, "<font color=\"#ff0014\">" + matcher.group() + "</font>");
        }
        matcher.appendTail(sb);
        content = sb.toString();
        Logger.i("内容：", content);
        //如果匹配和替换都忽略大小写,则可以用以下方法
        //content = content.replaceAll(wordReg,"<font color=\"#ff0014\">"+keyword+"</font>");
        Logger.i("内容：", content);
        return content;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context
     * @param dpValue
     * @return 单位
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 计算ListView动态高度
     *
     * @param listView
     */
    public static void calculationListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * 判断电话号码正则表达式
     *
     * @param mobiles
     * @return boolean
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,//D])|(18[0,5-9]))//d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 身份证号校验
     *
     * @param idCard
     * @return boolean
     */
    public static boolean isIdCardNO(String idCard) {
        String reg = "^\\d{15}$|^\\d{17}[0-9Xx]$";
        if (!idCard.matches(reg)) {
            return false;
        }
        return true;
    }

    /**
     * 获取当前版本号
     *
     * @param context
     * @return 版本号
     */
    public static String getAppVersionCode(Context context) {
        //默认版本为1
        int versioncode = 1;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versioncode = pi.versionCode;
        } catch (Exception e) {
            Logger.e("VersionInfo", "Exception" + e.getMessage());
        }
        return versioncode + "";
    }

    /**
     * 获取当前版本名称
     *
     * @param context
     * @return 版本名称
     */
    public static String getAppVersionName(Context context) {
        //默认最初版本
        String versionName = "1.0.0";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (Exception e) {
            Logger.e("VersionInfo", "Exception" + e.getMessage());
        }
        return versionName;
    }

    /**
     * 获取当前手机宽度
     *
     * @param activity
     * @return 宽度
     */
    public static int getPhoneWidth(Activity activity) {
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     * 获取当前手机高度
     *
     * @param activity
     * @return 高度
     */
    public static int getPhoneHeight(Activity activity) {
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    /**
     * 判断当前手机没有SD卡，或者SD正好被移除情况
     *
     * @return boolean
     */
    public static boolean obtainSDCardState() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable())
            return true;
        else
            return false;
    }

    /**
     * 获取disk缓存文件路径，默认在/sdcard/Android/data/<application package>/cache
     * 当不存在或者移除SD卡时直接存储到/data/data/<application package>/cache
     *
     * @param context
     * @param fileName
     * @return
     */
    public static File getDiskCacheDir(Context context, String fileName) {
        String cachePath;
        if (obtainSDCardState()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + fileName);
    }

    /**
     * 创建文件
     *
     * @param filePath
     */
    public static void createFile(String filePath) {
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 循环路径的文件夹
     *
     * @param filePath
     * @return
     */
    public static LinkedList<File> folderFiles(String filePath) {
        LinkedList<File> list = new LinkedList<>();
        File docFile = new File(filePath);
        File[] fileArray = docFile.listFiles();
        for (File f : fileArray) {
            if (f.isFile()) {
                list.add(f);
            }
        }
        return list;
    }

    /**
     * 拷贝文件到对应的目录
     *
     * @param path
     * @param copyPath
     * @return
     */

    public static boolean copyFile(String path, String copyPath) {
        try {
            createFile(copyPath);
            File oldFile = new File(path);
            File newFile = new File(copyPath + "/" + oldFile.getName());
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            try {
                //获得原文件流
                FileInputStream inputStream = new FileInputStream(oldFile);
                byte[] data = new byte[1024];
                //输出流
                FileOutputStream outputStream = new FileOutputStream(newFile);
                //开始处理流
                while (inputStream.read(data) != -1) {
                    outputStream.write(data);
                }
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}


