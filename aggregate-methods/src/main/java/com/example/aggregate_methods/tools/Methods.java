package com.example.aggregate_methods.tools;

import android.app.Activity;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

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
}


