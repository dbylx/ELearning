package com.elearing;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.elearing.ui.home.MyAdapter222;

import java.util.ArrayList;
import java.util.List;

public class shipinjiemian extends AppCompatActivity {
    List<String> list;
    List<String> detailList;
    private VideoView videoView ;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipinjiemian);
        int who = 0;



        list = new ArrayList<>();
        detailList = new ArrayList<>();
        list.add("语文");
        detailList.add("语文精品课程");
        list.add("数学");
        detailList.add("数学精品课程");
        list.add("英语");
        detailList.add("英语精品课程");
        list.add("物理");
        detailList.add("物理精品课程");
        list.add("化学");
        detailList.add("化学精品课程");
        list.add("生物");
        detailList.add("生物精品课程");
        list.add("地理");
        detailList.add("地理精品课程");

        who = getIntent().getIntExtra("who",who);
        ((TextView)findViewById(R.id.profile_name)).setText(list.get(who));

        switch (who){
            case 0:
                ((ImageView)findViewById(R.id.profile_picture)).setImageDrawable(this.getResources().getDrawable(R.drawable.yw));
                break;
            case 1:
                ((ImageView)findViewById(R.id.profile_picture)).setImageDrawable(this.getResources().getDrawable(R.drawable.sx));
                break;
            case 2:
                ((ImageView)findViewById(R.id.profile_picture)).setImageDrawable(this.getResources().getDrawable(R.drawable.yy));
                break;
            case 3:
                ((ImageView)findViewById(R.id.profile_picture)).setImageDrawable(this.getResources().getDrawable(R.drawable.wl));
                break;
            case 4:
                ((ImageView)findViewById(R.id.profile_picture)).setImageDrawable(this.getResources().getDrawable(R.drawable.hx));
                break;
            case 5:
                ((ImageView)findViewById(R.id.profile_picture)).setImageDrawable(this.getResources().getDrawable(R.drawable.sw));
                break;
            case 6:
                ((ImageView)findViewById(R.id.profile_picture)).setImageDrawable(this.getResources().getDrawable(R.drawable.dili));
                break;
        }
        
        
        //网络视频
        String videoUrl2  = "android.resource://" + getPackageName() + "/raw/" +R.raw.test;
        Log.v("content",videoUrl2);

        Uri uri = Uri.parse( videoUrl2 );

        videoView = (VideoView)this.findViewById(R.id.activity_local_video);

        MediaController myMediaCon = new MediaController(this);
//        myMediaCon.setMediaPlayer(videoView);
        //设置视频控制器
        videoView.setMediaController(myMediaCon);

        //播放完成回调
        videoView.setOnCompletionListener(new MyPlayerOnCompletionListener());

        //设置视频路径
        videoView.setVideoURI(uri);

        findViewById(R.id.return1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //开始播放视频

        Log.v("sfsdf", String.valueOf(Environment.getExternalStorageDirectory()));
        videoView.start();
    }
    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText( shipinjiemian.this, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }
}
