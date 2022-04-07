package com.example.broadcastdemo_07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="电量接收器";
    private  TextView mBatteryReceiver;
   private BatteryLevelReceiver mBatteryLevelReceiver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        registerBatteryReceiver();

        NetState receiver = new NetState();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(receiver, filter);//注册
        receiver.onReceive(this, null);//接收
    }

    private void initView() {

        mBatteryReceiver = this.findViewById(R.id.battery_level);
    }

    private void registerBatteryReceiver() {
        //2 我们要收听的频道是：电量变化
        IntentFilter intentFilter=new IntentFilter();
        //3 设置频道
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);//获取电量
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);//连接上usb
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);//断开usb
        BatteryLevelReceiver batteryLevelReceiver=new BatteryLevelReceiver();
        //4
        mBatteryLevelReceiver = new BatteryLevelReceiver();
        //5 注册广播  动态注册
        this.registerReceiver(batteryLevelReceiver,intentFilter);
    }

    //1 创建一个广播接收者
    private class BatteryLevelReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
                Log.d(TAG, "当前电量:" + intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0));

            if (mBatteryReceiver != null) {
                    mBatteryReceiver.setText("当前电量：" + intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0));
                }
            }
           else if (Intent.ACTION_POWER_CONNECTED.equals(action)){
                Log.d(TAG,"usb连接上了......正在充电");
            }
           else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)){
                Log.d(TAG,"usb断开拉......断开充电");
            }

        }
    }

    protected void onDestroy(){
        super.onDestroy();
        //取消广播注册，否则会导致内存泄露
        if (mBatteryLevelReceiver != null){
            this.unregisterReceiver(mBatteryLevelReceiver);
        }
    }
}