package com.example.framework.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.framework.R;
import com.example.framework.app.BaseApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * function:关于字符的工具类
 * Email：yangchaozhi@outlook.com
 * @author vinko on 2017/2/7.
 */

public class StringUtil {


    /**
     * 格式化金额
     *
     * @author lh
     * @param s
     * @return
     */
    public static String formatMoney(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        NumberFormat formater = null;
        s = s.replaceAll(",", "");
        double num = Double.parseDouble(s);
        formater = new DecimalFormat("#,###.00");
        String returnstr = formater.format(num);
        if (returnstr.startsWith(".")) {
            return "0" + returnstr;
        } else if (returnstr.startsWith("-.")) {
            return "-0" + returnstr.substring(1, returnstr.length());
        } else {
            return formater.format(num);
        }
    }

    /**
     * 格式化金额,只对整数部分格式,小数部分保持原样
     *
     * @author lh
     * @param s
     * @return
     */
    public static String formatMoneyWithOutDecimalPoint(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        NumberFormat formater = null;
        s = s.replaceAll(",", "");
        String[] numArr = s.split("\\.");

        double num = Double.parseDouble(numArr[0]);
        formater = new DecimalFormat("#,###");
        String returnstr = formater.format(num);
        if (returnstr.startsWith(".")) {
            return "0" + returnstr;
        } else if (returnstr.startsWith("-.")) {
            return "-0" + returnstr.substring(1, returnstr.length());
        } else {
            if (numArr.length == 2)
                return formater.format(num) + "." + numArr[1];
            else
                return formater.format(num);
        }
    }

    /**
     * 金额格式化数字
     *
     * @author lh
     * @param s
     * @return
     */
    public static String formatNumberAmount(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        NumberFormat formater = null;
        s = s.replaceAll(",", "");
        double num = Double.valueOf(s);
        formater = new DecimalFormat("#.0");
        String returnstr = formater.format(num);
        if (returnstr.startsWith(".")) {
            return "0" + returnstr;
        } else if (returnstr.startsWith("-.")) {
            return "-0" + returnstr.substring(1, returnstr.length());
        } else {
            return formater.format(num);
        }
    }

    /**
     * 根据指定的格式格式化金额
     *
     * @param s
     * @param format
     *            e.g.:#,###.00
     * @return
     */
    public static String formatMoney(String s, String format) {
        if (s == null || s.length() < 1) {
            return "";
        }
        NumberFormat formater = null;
        s = s.replaceAll(",", "");
        double num = Double.parseDouble(s);
        formater = new DecimalFormat(format);
        String returnstr = formater.format(num);
        if (returnstr.startsWith(".")) {
            return "0" + returnstr;
        } else if (returnstr.startsWith("-.")) {
            return "-0" + returnstr.substring(1, returnstr.length());
        } else {
            return formater.format(num);
        }
    }

    /**
     * 金额格式化数字
     *
     * @author lh
     * @param s
     * @return
     */
    public static String formatNumber(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        NumberFormat formater = null;
        s = s.replaceAll(",", "");
        double num = Double.valueOf(s);
        formater = new DecimalFormat("#.00");
        String returnstr = formater.format(num);
        if (returnstr.startsWith(".")) {
            return "0" + returnstr;
        } else if (returnstr.startsWith("-.")) {
            return "-0" + returnstr.substring(1, returnstr.length());
        } else {
            return formater.format(num);
        }
    }

    /**
     * 手机号账号434格式格式化
     *
     * @param s
     * @return String
     */

    public static String format434(String s) {

        return (s != null && s.length() >= 8) ? s.substring(0, 4) + "***" + s.substring(s.length() - 4, s.length()) : s;

    }

    /**
     * 从左到右以指定的符号和指定的长度分隔字符串
     *
     * @param str
     *            需要分隔的字符串
     * @param split
     *            分隔符
     * @param n
     *            分隔长度,须为正整数，否则返回原字符串
     * @return 分隔后的字符串
     */
    public static String splitString(String str, char split, int n) {
        StringBuilder builder = new StringBuilder(str);
        if (n > 0) {
            for (int i = n; i < builder.length(); i += (n + 1)) {
                builder.insert(i, split);
            }
        }
        return builder.toString();
    }

    /**
     * 字符折行显示
     *
     * @param src
     * @param maxCharPerCol
     *            每行最多字符个数
     * @return
     */
    public static String formatStringByMaxLength(String src, int maxCharPerCol) {
        if (src == null || src.length() <= maxCharPerCol) {
            return src;
        }

        int row = (src.length() + maxCharPerCol - 1) / maxCharPerCol;// 行数
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            int start = i * maxCharPerCol;// 起始
            int end = start + maxCharPerCol;// 结束
            end = (end > src.length()) ? src.length() : end;
            sb.append(src.substring(start, end));
            if (!(end == src.length())) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    /**
     * 字符折行显示
     *
     * @param src
     * @param maxCharPerCol
     *            每行最多字符个数
     * @return
     */
    public static String formatStringByMaxLengthForFavor(String src, int maxCharPerCol) {
        if (src == null || src.length() <= maxCharPerCol) {
            return src;
        }

        int row = (src.length() + maxCharPerCol - 1) / maxCharPerCol;// 行数
        int len = src.length() / row + src.length() % row;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            int start = i * len;// 起始
            int end = start + len;// 结束
            end = (end > src.length()) ? src.length() : end;
            sb.append(src.substring(start, end));
            if (!(end == src.length())) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    /**
     * 是否存在问号
     *
     * @param url
     */
    public static boolean isWen(String url) {
        if (isObjectEmpty(url)) {
            return false;
        }
        return url.indexOf("?") != -1;
    }

    /**
     * 对象是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isObjectEmpty(String obj) {
        return obj == null || "".equals(obj);
    }

    /****
     * 关闭系统键盘
     *
     * @param focusEt
     */
    public static void hideSystemBoard(View focusEt) {
        if (null == focusEt)
            return;
        Context ctx = focusEt.getContext();
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(focusEt.getWindowToken(), 0);
        } else {
            InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(focusEt.getWindowToken(), 0);
            try {
                Class<EditText> cls = EditText.class;
                Method setSoftInputShownOnFocus;
                setSoftInputShownOnFocus = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
                setSoftInputShownOnFocus.setAccessible(true);
                setSoftInputShownOnFocus.invoke(focusEt, false);
            } catch (Exception e) {
                // e.printStackTrace();
            }
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(focusEt, false);
            } catch (Exception e) {
                // e.printStackTrace();
            }
        }
    }

    // 从文件读取byte[]数据
    public static byte[] getFileBytesFromFileName(File file) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fin = null;

        try {
            fin = new FileInputStream(file);
            int b = 0;
            while ((b = fin.read()) != -1) {
                baos.write(b);
            }

        } catch (FileNotFoundException e) {
            Log.e("e",e.toString());
        } catch (IOException e) {
            Log.e("e",e.toString());
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return baos.toByteArray();
    }

    // 把byte[]数据存储文件
    public static void savaBytesToFile(byte[] bytes, File file) {

        BufferedOutputStream stream = null;
        try {
            FileOutputStream fos = new FileOutputStream(file);
            stream = new BufferedOutputStream(fos);
            stream.write(bytes);
            fos.close();
        } catch (Exception e) {
            Log.e("e",e.toString());
        } finally {
            try {
                if (null != stream) {
                    stream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 方法功能说明：通过json对象，获取String对象
     *
     * @param jsonObject
     * @param key
     * @return
     */
    public static String getStringByJSON(JSONObject jsonObject, String key) {
        try {
            return (jsonObject != null) ? (jsonObject.getString(key)) : "";
        } catch (JSONException e) {
//            MbsLogManager.logE(e.toString());
            Log.e("e",e.toString());
        }
        return "";
    }

    /**
     * 获取字符串的长度,一个汉字或日韩文长度为2,英文字符长度为1
     *
     * @param s
     *            需要得到长度的字符串
     * @return int 得到的字符串长度
     */
    public static int length(String s) {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }

    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    /**
     * 字符串首位和末位不变，中间用*号替代。
     *
     * @param str
     * @return
     */
    public static String format1to1(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        if (str.length() <= 2) {
            return str;
        }
        String start = str.substring(0, 1);
        String end = str.substring(str.length() - 1, str.length());
        String star = "";
        for (int i = 0; i < str.length() - 2; i++) {
            star += "*";
        }
        return start + star + end;
    }

    public static class DECIMAL_DIGITS_SEL {
        /* 小数点后两位 */
        public final static int TWO = 2;

        /* 小数点后四位 */
        public final static int FOUR = 4;

        /* 小数点后六位 */
        public final static int SIX = 6;
    }

    /**
     * 设置输入框小数位数控制
     */
    public static InputFilter setDotDigitsFilter(final int DECIMAL_DIGITS) {
        InputFilter lengthfilter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                // 删除等特殊字符，直接返回
                if ("".equals(source.toString())) {
                    return null;
                }
                String dValue = dest.toString();
                String[] splitArray = dValue.split("\\.");
                if (splitArray.length > 1) {
                    String dotValue = splitArray[1];
                    int diff = dotValue.length() + 1 - DECIMAL_DIGITS;
                    if (diff > 0) {
                        return source.subSequence(start, end - diff);
                    }
                }
                return null;
            }
        };
        return lengthfilter;
    }

    public static void setEditTextInput(final int DECIMAL_DIGITS, EditText... ets) {
        if (DECIMAL_DIGITS > 0) {
            for (EditText et : ets) {
                et.setFilters(new InputFilter[]{setDotDigitsFilter(DECIMAL_DIGITS)});
            }
        } else if (DECIMAL_DIGITS == 0) {
            for (EditText et : ets) {
                IntegerFormatWatcher(et);
            }
        }
    }

    /**
     * 控制输入只能为整数
     */
    public static void IntegerFormatWatcher(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                // 这部分是处理如果输入框内小数点后有俩位，那么舍弃最后一位赋值，光标移动到最后
                if (s.toString().contains(".")) {

                    if (s.length() - 1 - s.toString().indexOf(".") >= 0) {
                        editText.setText(s.toString().subSequence(0, s.toString().indexOf(".")));
                        editText.setSelection(s.toString().subSequence(0, s.toString().indexOf(".")).length());
                    }
                }
                // 这部分是处理如果用户输入以.开头，在前面加上0
                if (s.toString().trim().substring(0).equals(".")) {
                    editText.setText("0");
                    editText.setSelection(1);
                }
                // 这里处理用户 多次输入.的处理
                if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

        });
    }

    public static void setIntegerFormatWatcher(EditText... ets) {
        for (EditText et : ets) {
            IntegerFormatWatcher(et);
        }
    }


    /**
     * EditText输入小数点后几位数控制
     */
    public static void setInputPoint(final EditText editText, final int digit) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > digit) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + digit + 1);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(s.length());
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 获取asset目录下文件
     *
     * @param fileName
     *            文件全称,包含后缀名 例如a.png , x.9.png , yy.jpg
     * @return inputStream
     */
    public static InputStream asset_getFile(String fileName) {
        try {
            InputStream inStream = BaseApplication.getInstance().getAssets().open(fileName);
            return inStream;
        } catch (IOException e) {
            Log.e("error","asset:" + fileName + ",no exist");
        }
        return null;
    }

    /**
     * 将InputStream 转换为String
     *
     * @param is
     * @param encoding
     *            编码格式,可以为null,null表示适用utf-8
     */
    public static String stream_2String(InputStream is, String encoding) throws IOException {
        if (is == null)
            return null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        baos.close();
        String result = null;
        if (encoding == null) {
            encoding = "utf-8";
        }
        result = baos.toString(encoding);
        return result;
    }

    /**
     * String => inputStream
     *
     * @param src
     * @param charsetName
     *            编码格式 可以为null,null表示适用utf-8
     * @return
     */
    public static InputStream string_2stream(String src, String charsetName) {
        try {
            if (null == charsetName) {
                charsetName = "utf-8";
            }
            byte[] bArray = src.getBytes(charsetName);
            InputStream is = new ByteArrayInputStream(bArray);
            return is;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取raw文件，返回流
     *
     * @return 返回string
     */
    public static InputStream readRawFileAsStream(int rawResId) {
        Context appContext = BaseApplication.getInstance();
        InputStream inStream = appContext.getResources().openRawResource(rawResId);
        return inStream;
    }

    /**
     * 读取raw文件，返回String
     *
     * @param rawResId
     * @return
     * @throws IOException
     */
    public static String readRawFileAsString(int rawResId) throws IOException {
        InputStream inStream = readRawFileAsStream(rawResId);
        return stream_2String(inStream, null);
    }

    /**
     * 处理数字后面两位放大效果
     * @param context
     * @param str
     * @return
     */
    public static SpannableString getLastTowLargeSpannable(Context context, String str) {
        try {
            if (str.contains(".")) {
                String text;
                try {
                    text = Double.parseDouble(str) + "";
                } catch (NumberFormatException e) {
                    text = "0.00";
                }
                SpannableString span = new SpannableString(text);
                int pointLength = text.substring(text.indexOf(".")).length();
                if (pointLength >= 3) {
                    return getSpanStrByLength(context, span, text, 2);
                } else if (pointLength == 2) {
                    return getSpanStrByLength(context, span, text, 1);
                } else {
                    return new SpannableString(Double.parseDouble(text) == 0 ? "--" : text);
                }
            } else {
                SpannableString span = new SpannableString(str);
                if (str.length() > 2) {
                    return getSpanStrByLength(context, span, str, 2);
                } else {
                    return new SpannableString(Double.parseDouble(str) == 0 ? "--" : str);
                }
            }
        } catch (Exception e) {
            return new SpannableString("--");
        }
    }

    private static SpannableString getSpanStrByLength (Context context, SpannableString spanStr, String str, int length) {
        if (Double.parseDouble(str) == 0) {
            return new SpannableString("--");
        } else {
            spanStr.setSpan(new AbsoluteSizeSpan(context.getResources().getDimensionPixelSize(R.dimen.x37)), 0, str.length() - length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanStr.setSpan(new AbsoluteSizeSpan(context.getResources().getDimensionPixelSize(R.dimen.x49)), str.length() - length,
                    str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spanStr;
        }
    }


    public static String defType = "drawable";
    public static String defTypeMipmap = "mipmap";

//    /**
//     * 读取drawable下的图片
//     *
//     * @param context
//     * @param imageName
//     *            不包括图片后缀名
//     * @return
//     */
//    public static Bitmap readBitMap(Context context, String imageName) {
//        int resId = BaseApplication.getInstance().getResources(context,imageName);
//        if (0 == resId)
//            return null;
//        return readBitMap(context, resId);
//    }

    /**
     * 从Context中获取Activity
     *
     * @param mContext
     * @return
     */
    public static Activity getActFromContext(Context mContext) {
        if (mContext == null)
            return null;
        else if (mContext instanceof Activity)
            return (Activity) mContext;
        else if (mContext instanceof ContextWrapper)
            return getActFromContext(((ContextWrapper) mContext).getBaseContext());
        return null;
    }

}
