package com.example.framework.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * function:常用的手机工具方法集合
 * Email：yangchaozhi@outlook.com
 * @author vinko on 2017/2/7
 */

public class PhoneUtil {

    /**
     * 是否WIFI连接
     *
     * @param c
     */
    public static boolean isWIFI(Context c) {
        ConnectivityManager connectivityManager = (ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();// 获取网络的连接情况

        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI)
            return true;
        return false;
    }

    /**
     * 是否3G网络连接
     *
     * @param c
     */
    public static boolean isMobile(Context c) {
        ConnectivityManager connectivityManager = (ConnectivityManager) c
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * 启动 app
     *
     * @param mContext
     * @param pkgName
     * @param bundle
     */
    public static void start(Context mContext, String pkgName, Bundle bundle) {
        PackageInfo pi = null;
        try {
            pi = mContext.getPackageManager().getPackageInfo(pkgName, 0);
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.setPackage(pi.packageName);
            PackageManager pm = mContext.getPackageManager();
            List<ResolveInfo> apps = pm.queryIntentActivities(resolveIntent, 0);
            ResolveInfo ri = apps.iterator().next();
            if (ri != null) {
                String packageName = ri.activityInfo.packageName;
                String className = ri.activityInfo.name;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ComponentName cn = new ComponentName(packageName, className);
                intent.setComponent(cn);
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                mContext.startActivity(intent);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    //    public boolean isAppInstalled(Context context, String uri) {
//        PackageManager pm = context.getPackageManager();
//        boolean installed = false;
//        try {
//            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
//            installed = true;
//        } catch (PackageManager.NameNotFoundException e) {
//            installed = false;
//        }
//        return installed;
//    }

    /**
     * 判断应用是否安装
     * @param context
     *      上下文
     * @param packageName
     *      包名
     * @return
     *      应用是否安装
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        List<String> pName = new ArrayList<String>();
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);
    }

    /**
     * 根据包名跳至指定应用
     * 善融:com.ccb.shop.view
     * 龙行:com.ccb.lxtx
     *
     * @param context
     * @param packageName
     * @param listener
     */
    public static void startApp(Context context, String packageName, IActivityNotFoundListener listener) {
        Intent intent = new Intent();
        intent.setAction(intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, 0);
        //获取Launcher列表名称
        Intent intent1 = null;
        for (ResolveInfo info : list) {
            if (info.activityInfo.packageName.equals(packageName)) {
                info.loadIcon(context.getPackageManager());
                info.loadLabel(context.getPackageManager());
                intent1 = context.getPackageManager().getLaunchIntentForPackage(packageName);
            }
        }
        try {
            context.startActivity(intent1);
        } catch (Exception e) {
            listener.doWhenActivityNotFound();
        }
    }

    /**
     * 回调接口
     */
    public interface IActivityNotFoundListener {
        void doWhenActivityNotFound();
    }

}
