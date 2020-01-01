package com.elearing.new_board;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.elearing.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class myBoardCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("err","receive");





        String name = "name";
        String id = "sdf";
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(context)
                    .setChannelId(id)
                    .setContentTitle("APP更新")
                    .setContentText("APP于2019年12月1日更新")
                    .setSmallIcon(R.mipmap.ic_launcher).build();
        } else {
//            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setContentTitle("活动")
//                    .setContentText("您有一项新活动")
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setOngoing(true)
//                    .setChannelId(id);//无效
//            notification = notificationBuilder.build();
        }
        notificationManager.notify(1,notification);//前台通知(会一直显示在通知栏)




    }
}
