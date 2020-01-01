package com.elearing.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
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

import java.util.List;

public class MyAdapter222 extends RecyclerView.Adapter{
    List<String> list;
    Context context;

    public MyAdapter222(Context context){
        this.context = context;

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

            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.showtype1,parent,false);
            MyViewHolder myViewHolder = new MyViewHolder(itemView);
            return myViewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

//        ((MyViewHolder) holder).textView.setText(list.get(position).getName());
//        ((MyViewHolder) holder).classInformationText.setText(dataSet.get(position).getName() + "improve" + position);
////        holder.itemView.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClick(v,position);
//            }
//        });

    }





    //无需关注
    @Override
    public int getItemCount() {
        return 5;
    }



    private class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private TextView classInformationText;
        private ImageView view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.classname);
            classInformationText = (TextView) itemView.findViewById(R.id.classInoformation);
            view = (ImageView)itemView.findViewById(R.id.background);
        }
    }


}