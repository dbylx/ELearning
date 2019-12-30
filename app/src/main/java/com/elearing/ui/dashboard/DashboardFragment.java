package com.elearing.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elearing.MainActivity;
import com.elearing.R;
import com.elearing.Table3Activity;
import com.elearing.api.Course;
import com.elearing.api.GetRequest;
import com.elearing.api.GetRequest_Interface;
import com.elearing.api.Material;
import com.elearing.api.Teacher;
import com.google.gson.Gson;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.elearing.R.id.classInoformation;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    RecyclerView recyclerView;
    //    String[] dataArr = {"语文","数学","语文","数学","语文","数学","语文","数学","语文","数学","语文","数学","语文","数学"};
    List<Course> dataSet = new ArrayList<>();
    List<Material> materialDataSet = new ArrayList<>();
    List<Teacher> teacherDataSet = new ArrayList<>();
    private  MyAdapter myAdapter;
    private int process = 0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        dashboardViewModel =
//                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = root.findViewById(R.id.courseRecycle);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(dataSet,materialDataSet,teacherDataSet,this.getActivity().getApplicationContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                request();
            }
        }).start();

        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, final int position) {
                final Intent intent = new Intent(getActivity(), Table3Activity.class);
                intent.putExtra("course",new Gson().toJson(dataSet.get(position)));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getTeacher(dataSet.get(position).getId(),intent);
                        getMaterial(dataSet.get(position).getId(),intent);
                    }
                }).start();
                System.out.println(dataSet.get(position).getId());

                intent.putExtra("courses",(Serializable)dataSet);
            }
        });
        recyclerView.setAdapter(myAdapter);

        return root;
    }

    private void getTeacher(String courseId,final Intent intent){
        Retrofit retrofit = GetRequest.getApiClient();
        // 创建 网络请求接口 的实例

        final GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        Call<List<Teacher>> call = request.getCourseTeacher(courseId);

        call.enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                System.out.println(response.body().size());
                intent.putExtra("teachers",(Serializable)response.body());
                process++;
                if(process==2)
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {
                Toast toast=Toast.makeText(getContext(),"Toast提示消息",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void getMaterial(String courseId,final Intent intent){
        Retrofit retrofit = GetRequest.getApiClient();

        // 创建 网络请求接口 的实例
        final GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        Call<List<Material>> call = request.getCourseMaterial(courseId);

        call.enqueue(new Callback<List<Material>>() {
            @Override
            public void onResponse(Call<List<Material>> call, Response<List<Material>> response) {
                intent.putExtra("materials",(Serializable)response.body());
                process++;
                if(process==2)
                    startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<Material>> call, Throwable t) {
                Toast toast=Toast.makeText(getContext(),"Toast提示消息",Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    private void request(){
        Retrofit retrofit = GetRequest.getApiClient();

        // 创建 网络请求接口 的实例
        final GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<List<Course>> call = request.getCourses();

        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                    List<Course> courses = response.body();
                    for (Course course:courses) {
                        dataSet.add(course);
                    }
                    myAdapter.refresh(dataSet);
            }


            //请求失败时候的回调
            @Override
            public void onFailure(Call<List<Course>> call, Throwable throwable) {
                //提示失败
                Toast toast=Toast.makeText(getContext(),"Toast提示消息",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

}


