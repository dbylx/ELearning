package com.elearing.ui.home;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;




//滑动窗口
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.elearing.R;
import com.elearing.Table3Activity;
import com.elearing.new_board.myBoardCast;
import com.elearing.shipinjiemian;
import com.elearing.ui.dashboard.DashboardFragment;
import com.elearing.ui.dashboard.MyAdapter;
import com.elearing.ui.notifications.NotificationsFragment;
import com.facebook.appevents.suggestedevents.ViewOnClickListener;
import com.google.gson.Gson;

import static android.content.Context.NOTIFICATION_SERVICE;

public class HomeFragment extends Fragment implements ItemDragListener {
    ItemTouchHelper mItemTouchHelper;
    private HomeViewModel homeViewModel;
    public MyAdapter222 myAdapter;

    public RecyclerView recyclerView;
    public ImageView v1;
    public ImageView v2;
    public ImageView v3;
    public ImageView v4;
    public ImageButton b;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        mViewPaper = (ViewPager) root.findViewById(R.id.vp);

        v1 = root.findViewById(R.id.p1);
        v2 = root.findViewById(R.id.p2);
        v3 = root.findViewById(R.id.p3);
        v4 = root.findViewById(R.id.p4);
        b = root.findViewById(R.id.imageButton);
        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.v("e","d");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, new DashboardFragment())

                        .commit();
            }
        });

        v1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //分享
            }
        });


        v3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//                getActivity().onBackPressed();
                Log.v("e","d");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, new NotificationsFragment())

                        .commit();


            }
        });

        v4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.v("e","d");
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, new DashboardFragment())

                        .commit();

            }
        });

        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i = 0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的小点
        dots = new ArrayList<View>();
        dots.add(root.findViewById(R.id.dot_0));
        dots.add(root.findViewById(R.id.dot_1));
        dots.add(root.findViewById(R.id.dot_2));
        dots.add(root.findViewById(R.id.dot_3));
        dots.add(root.findViewById(R.id.dot_4));

        title = (TextView) root.findViewById(R.id.title);
        title.setText(titles[0]);

        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);

        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.drawable.abc);
                dots.get(oldPosition).setBackgroundResource(R.drawable.abc);

                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });



        recyclerView = root.findViewById(R.id.courseRecycle_main);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter222(this.getActivity(),this);
        myAdapter.setOnItemClickListener(new MyAdapter222.OnItemClickListener(){

            @Override
            public void onItemClick(View v, int position) {
               Intent intent = new Intent(getActivity(), shipinjiemian.class);
               intent.putExtra("who",position);
               startActivity(intent);

            }
        });
        recyclerView.setAdapter(myAdapter);


        MyItemTouchHelperCallback mCallback = new MyItemTouchHelperCallback(myAdapter);
        mItemTouchHelper = new ItemTouchHelper(mCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        return root;
    }



    public void onStartDrags(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }





    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.mipmap.gg1,
            R.mipmap.gg2,
            R.mipmap.gg3,
            R.mipmap.gg4,
            R.mipmap.gg5,
    };
    //存放图片的标题
    private String[]  titles = new String[]{
            "学习英语",
            "坚持自己的梦想加油",
            "坚持听听力你可以的",
            "管理好自己的情绪",
            "新的一年，新的梦想"
    };
    private TextView title;
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;


    /**
     * 自定义Adapter
     * @author liuyazhuang
     *
     */
    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//			super.destroyItem(container, position, object);
//			view.removeView(view.getChildAt(position));
//			view.removeViewAt(position);
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    /**
     * 利用线程池定时执行动画轮播
     */
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);




    }


    /**
     * 图片轮播任务
     * @author liuyazhuang
     *
     */
    private class ViewPageTask implements Runnable{

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 接收子线程传递过来的数据
     */
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        };
    };
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(scheduledExecutorService != null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }

    public void onResume(){
        super.onResume();







    }



}