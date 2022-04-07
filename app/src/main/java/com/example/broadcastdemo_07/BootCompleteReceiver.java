package com.example.broadcastdemo_07;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

//1 创建一个类
public class BootCompleteReceiver extends BroadcastReceiver {

    private static String TAG="BootCompleteReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"开机完成");
        Toast.makeText(context, "收到开机完成的广播", Toast.LENGTH_SHORT).show();
    }
}
