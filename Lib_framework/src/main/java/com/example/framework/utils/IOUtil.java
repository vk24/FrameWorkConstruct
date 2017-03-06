package com.example.framework.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * function: IO流操作的工具类
 * describe:详细描述
 * email：yangchaozhi@outlook.com
 *
 * @author vinko on 2017/3/3.
 */

public class IOUtil {

    /**
     * 将InputStream --> String
     *
     * @param is       InputStream 流
     * @param encoding encoding 可以为空，默认使用utf-8 编码格式
     * @return
     * @throws IOException
     */
    public static String getInputStream2String(InputStream is, String encoding) throws IOException {
        if (is == null) {
            return null;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1) {
            outputStream.write(i);
        }
        outputStream.close();//关闭流
        String result = null;
        if (encoding == null) {
            encoding = "utf-8";
        }
        //转换成对应编码的
        result = outputStream.toString(encoding);
        return result;
    }

    /**
     * 将String --> InputStream
     *
     * @param str
     * @param encoding
     * @return
     */
    public static InputStream getString2InputStream(String str, String encoding) {
        try {
            if (str == null) {
                return null;
            }
            if (encoding == null) {
                encoding = "utf-8";
            }
            byte[] bArray = str.getBytes(encoding);
            InputStream is = new ByteArrayInputStream(bArray);
            return is;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
