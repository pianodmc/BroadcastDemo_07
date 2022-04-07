package com.example.broadcastdemo_07;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.util.Log;
import android.widget.Toast;

public class NetState extends BroadcastReceiver
{

    public int  flag    = 0;
    private int flag1=0;

    @Override
    public void onReceive(Context context, Intent arg1)
    {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        if (activeNetworkInfo == null)
        {
            Toast.makeText(context, "当前无网络，请检查移动设备的网络连接", Toast.LENGTH_SHORT)
                    .show();
            flag = 1;
            flag1 = 1;
            Log.i("TAG","网络未连接+flag+"+flag);
        }
        // activeNetworkInfo.getTypeName(); 以何种方式连线
        // :cmwap/cmnet/wifi/uniwap/uninet
        // activeNetworkInfo.isAvailable(); 当前网络是否可用(true)
        // activeNetworkInfo.isFailover();网络有问题
        else
        {
            if (!activeNetworkInfo.isAvailable()
                    || activeNetworkInfo.isFailover())
            {
                Toast.makeText(context, "当前网络不可用", Toast.LENGTH_SHORT).show();
                flag = 1;
                flag1=2;
                Log.i("TAG","当前网络不可用flag+"+flag);
            }

            if (flag == 1)
            {
                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
                {
                    Toast.makeText(context, "已连接上移动数据", Toast.LENGTH_SHORT)
                            .show();
                } else
                {
                    Toast.makeText(context, "已连接上WIFI数据", Toast.LENGTH_SHORT)
                            .show();
                }
                Log.i("TAG","网络ok,flag+"+flag+"....."+flag1);
            }

        }
    }
}


