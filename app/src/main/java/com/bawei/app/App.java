package com.bawei.app;

import android.app.Application;
import android.content.Context;

/*
 *@Auther:姓名
 *@Date: 时间
 *@Description:功能
 * */public class App extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
    }
}
