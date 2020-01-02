package com.elearing.ui.home;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.elearing.R;
import com.elearing.api.Course;
import com.elearing.api.Material;
import com.elearing.api.Teacher;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyAdapter222 extends RecyclerView.Adapter implements ItemMoveListener {
    List<String> list;
    Context context;
    private ItemDragListener mItemDragListener;

    public MyAdapter222(Context context, ItemDragListener itemDragListener){
        this.context = context;
        mItemDragListener = itemDragListener;
        list = new ArrayList<>();
        list.add("语文");
        list.add("数学");
        list.add("英语");
        list.add("物理");
        list.add("化学");
        list.add("生物");
        list.add("地理");

    }


    private OnItemClickListener onItemClickListener = null;

    //setter方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //回调接口
    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            System.out.println(dataSet.size());

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course,parent,false);
            MyViewHolder myViewHolder = new MyViewHolder(itemView);
            return myViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        ((MyViewHolder) holder).textView.setText(list.get(position));
        int con;
        if(list.get(position).compareTo("语文") == 0) {
            con = 0;
        }else if(list.get(position).compareTo("数学") == 0){
            con =1;
        }else if(list.get(position).compareTo("英语") == 0){
            con =2;
        }else if(list.get(position).compareTo("物理") == 0){
            con =3;
        }else if(list.get(position).compareTo("化学") == 0){
            con =4;
        }else if(list.get(position).compareTo("生物") == 0){
            con =5;
        }else if(list.get(position).compareTo("地理") == 0){
            con =6;
        }
        switch (position){
            case 0:
                ((MyViewHolder) holder).view.setImageDrawable(context.getResources().getDrawable(R.drawable.yw));
                break;
            case 1:
                ((MyViewHolder) holder).view.setImageDrawable(context.getResources().getDrawable(R.drawable.sx));
                break;
            case 2:
                ((MyViewHolder) holder).view.setImageDrawable(context.getResources().getDrawable(R.drawable.yy));
                break;
            case 3:
                ((MyViewHolder) holder).view.setImageDrawable(context.getResources().getDrawable(R.drawable.wl));
                break;
            case 4:
                ((MyViewHolder) holder).view.setImageDrawable(context.getResources().getDrawable(R.drawable.hx));
                break;
            case 5:
                ((MyViewHolder) holder).view.setImageDrawable(context.getResources().getDrawable(R.drawable.sw));
                break;
            case 6:
                ((MyViewHolder) holder).view.setImageDrawable(context.getResources().getDrawable(R.drawable.dili));
                break;
        }
//        notifyItemInserted(position);
//        notifyItemRemoved(position);

        ((MyViewHolder) holder).view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mItemDragListener.onStartDrags(holder);
                Log.v("ee","rf");
                return false;
            }
        });



        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v,position);
            }
        });


    }
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //1、交换数据
        Collections.swap(list, fromPosition, toPosition);
        //2、刷新
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public boolean onItemRemove(int position) {
        //1、删除数据
        list.remove(position);
        //2、刷新
        notifyItemRemoved(position);
        return true;
    }



    //无需关注
    @Override
    public int getItemCount() {
        return list.size();
    }




    private class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private TextView classInformationText;
        private ImageView view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.profile_name);
            classInformationText = (TextView) itemView.findViewById(R.id.timestamp);
            view = (ImageView)itemView.findViewById(R.id.profile_picture);
        }
    }


}