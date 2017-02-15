package com.example.framework.app;

import android.app.Application;

/**
 * function:负责应用程序的生命周期管理
 * Email：yangchaozhi@outlook.com
 * @author by vinko on 2017/2/7.
 */

public class BaseApplication extends Application {

    private static BaseApplication instance;

    private BaseApplication(){}

    public static synchronized BaseApplication getInstance(){
        if (instance == null) {
            throw new RuntimeException("IlleagelStateExp : instance is null, application error");
        }
        return instance;
    }
}
