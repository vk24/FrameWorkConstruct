package com.example.lib_network;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络请求
 *
 * function:OKHttp 同步请求和异步请求
 * Email：yangchaozhi@outlook.com
 * @author vinko on 2017/2/7.
 */

public class SendRequest {

    public interface requestListener <T>{
        void onResult(T t);
        void onFailed();
    }

    /**
     * 同步请求
     * <p>
     *     会阻塞当前线程
     * </p>
     * @param url
     *      请求的地址
     * @param listener
     *      请求返回的监听
     */
    public static void sendRequest(String url,requestListener listener){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                listener.onResult(response);
            } else {
                listener.onFailed();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    /**
     * 异步请求
     * @param url
     * @param listener
     */
    public static void sendAsyncRequest(String url, final requestListener listener) {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.onFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    listener.onResult(response);
                }
            }
        });
    }
}
