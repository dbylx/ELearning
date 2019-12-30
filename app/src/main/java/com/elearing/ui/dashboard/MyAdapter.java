package com.elearing.ui.dashboard;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.elearing.R;
import com.elearing.VideoActivity;
import com.elearing.api.Course;
import com.elearing.api.Material;
import com.elearing.api.Teacher;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter{
    List<Course> dataSet ;
    List<Material> materialDataSet;
    List<Teacher> teacherDataSet ;
    Context context;

    public MyAdapter(List<Course> dataSet, List<Material> materialDataSet, List<Teacher> teacherDataSet, Context context){
        this.context = context;
        this.dataSet = dataSet;
        this.materialDataSet = materialDataSet;
        this.teacherDataSet = teacherDataSet;
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
        switch(viewType){
            case 1:
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.showtype1,parent,false);
                MyViewHolder myViewHolder = new MyViewHolder(itemView);

                return myViewHolder;

            case 2:
                View itemView2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.showtype2,parent,false);
                MyViewHolder2 myViewHolder2 = new MyViewHolder2(itemView2);

                System.out.println("yes");
                return myViewHolder2;
            case 3:
                View itemView3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.showtype3,parent,false);
                MyViewHolder3 myViewHolder3 = new MyViewHolder3(itemView3);
                System.out.println("yes333");
                return myViewHolder3;


        }


        return null;
    }


    public void refresh(List<Course> dataSet){
        //这个方法是我们自己手写的，主要是对适配器的一个刷新
        notifyDataSetChanged();
        System.out.println("加载成功");
        this.dataSet = dataSet;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        switch (dataSet.get(position).getShowType()) {
            case 1:
                System.out.println("填装数据");
                ((MyViewHolder) holder).textView.setText(dataSet.get(position).getName());
                ((MyViewHolder) holder).classInformationText.setText(dataSet.get(position).getName() + "improve" + position);
                Glide.with(context).load("http://localhost:8080/elearn/courses/"+dataSet.get(position).getId()+"/photo")
                        .into((ImageView) ((MyViewHolder) holder).itemView.findViewById(R.id.background));
                ImageView imageView = (ImageView) ((MyViewHolder) holder).itemView.findViewById(R.id.background);
                imageView.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.com_facebook_button_icon));
                System.out.println("http://172.24.34.138:8080/elearn/courses/"+dataSet.get(position).getId()+"/photo");
                break;
            case 2:
                ((MyViewHolder2) holder).textView.setText(dataSet.get(position).getName());
                ((MyViewHolder2) holder).classInformationText.setText(dataSet.get(position).getName());
                System.out.println("http://172.24.34.138:8080/elearn/courses/"+dataSet.get(position).getId()+"/photo");
                Picasso.with(context).load("http://172.24.34.138:8080/elearn/courses/"+dataSet.get(position).getId()+"/photo")
                        .into((ImageView) ((MyViewHolder) holder).itemView.findViewById(R.id.background));
                break;

            case 3:
                ((MyViewHolder3) holder).classname.setText(dataSet.get(position).getName());
                ((MyViewHolder3) holder).classInformationText.setText(dataSet.get(position).getName());
                System.out.println("yes555");
                System.out.println("http://172.24.34.138:8080/elearn/courses/"+dataSet.get(position).getId()+"/photo");
                Picasso.with(context).load("http://172.24.34.138:8080/elearn/courses/"+dataSet.get(position).getId()+"/photo")
                        .into((ImageView) ((MyViewHolder) holder).itemView.findViewById(R.id.background));
                break;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v,position);
            }
        });

    }





    //无需关注
    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    @Override
    public int getItemViewType(int position) {
//            Log.e("eeeeeeeeeeeeeeee", dataSet.get(position).getShowType()+"");
        switch (dataSet.get(position).getShowType()) {

            case 1:
                return 1;
            case 2:

                return 2;
            case 3:
                return 3;
            default:
                return -1;
        }


    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private TextView classInformationText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.classname);
            classInformationText = (TextView) itemView.findViewById(R.id.classInoformation);

        }
    }

    private class MyViewHolder2 extends RecyclerView.ViewHolder {

        private TextView textView;
        private TextView classInformationText;
        private VideoView videoView;
        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.classname);
            classInformationText = (TextView) itemView.findViewById(R.id.classInoformation);
            videoView = (VideoView) itemView.findViewById(R.id.coursevideo);

        }
    }

    private class MyViewHolder3 extends RecyclerView.ViewHolder {

        private TextView classname;
        private TextView classInformationText;
        public MyViewHolder3(@NonNull View itemView) {
            super(itemView);
            classname = (TextView) itemView.findViewById(R.id.classname);
            classInformationText = (TextView) itemView.findViewById(R.id.classInoformation);

        }
    }
}