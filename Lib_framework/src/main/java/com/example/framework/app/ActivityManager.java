package com.example.framework.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * function:活动管理器
 * Email：yangchaozhi@outlook.com
 * @author vinko on 2017/2/7.
 */

public class ActivityManager {

    private final static String TAG = ActivityManager.class.getSimpleName();

    //单例
    private static ActivityManager instance = null;

    private ActivityManager () {
    }

    public static synchronized ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    /**
     * 用于缓存当前已打开的Activity
     */
    private List<WeakReference<BaseActivity>> activityCache = new ArrayList<>();

    private Map<Long, BaseFragment> fragments = new ConcurrentHashMap<>();

    private List<BaseActivity> activities = new ArrayList<>();
    /**
     * 临时缓存activities
     ***/
    private List<BaseActivity> tempActivities = new ArrayList<>();



    //添加活动
    public void addActivity (BaseActivity act) {
        if (act == null) {
            return;
        }
        synchronized (act){

        }
        activities.add(act);
    }

    //移除活动
    public void removeActivity(BaseActivity activity) {
        if (null == activities || activity == null) {
            return ;
        }
        activities.remove(activity);
    }

    /**
     * 移除所有BaseActivity
     */
    public void removeAllActivities() {
        synchronized (activities) {
            while (!activities.isEmpty()) {
                BaseActivity a = activities.remove(0);
                if (a != null && !a.isFinishing()) {
                    if( activities.size()!=0){
                    }
                    a.finish();
                }
            }
        }
    }

    //获取当前存储的活动窗体
    public List<BaseActivity> getAllActivities () {
        return activities;
    }

    //获取当前最顶层的activity
    public BaseActivity getTopActivity() {
        if (null == activities || 0 == activities.size()) {
            return null;
        }
        BaseActivity topAct = activities.get(activities.size() - 1);
        if (topAct.isFinishing()) {
            removeActivity(topAct);
            return getTopActivity();
        }
        return topAct;
    }

    //跳转指定class页面
    public void starActivity (Context context,Class clz) {
        String clzName = clz.getSimpleName();
        Log.d(TAG,"+clzName=" + clzName);

        //转成对应的类,根据父类跳转
        if (BaseActivity.class.isAssignableFrom(clz)) {
            Log.d(TAG,"基类是BaseActivity");
            Intent intent = new Intent(context,clz);
            context.startActivity(intent);
        }
        else if (BaseFragment.class.isAssignableFrom(clz)) {

        }
    }

    public void starActivity(Context context, Class clz, Bundle bundle){
        String clzName = clz.getSimpleName();
        Log.d(TAG,"+clzName=" + clzName);

        //转成对应的类,根据父类跳转
        if (BaseActivity.class.isAssignableFrom(clz)) {
            Log.d(TAG,"基类是BaseActivity");
            Intent intent = new Intent(context,clz);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }

    /**
     * 检查某个类是否在栈中
     * @param clz
     * @return
     */
    public BaseActivity isInn (Class clz) {
        for (BaseActivity activity : activities) {
            if (activity.getClass().equals(clz)) {
                return activity;
            }
        }
        return null;
    }
}
