package com.elearing.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elearing.R;
import com.elearing.Table3Activity;
import com.elearing.api.Course;
import com.elearing.api.GetRequest;
import com.elearing.api.GetRequest_Interface;
import com.elearing.api.Material;
import com.elearing.api.Teacher;
import com.elearing.catchPackage.CourseMessageApi;
import com.elearing.catchPackage.dao.CourseMessageDao;
import com.elearing.ui.home.ItemDragListener;
import com.elearing.ui.home.MyItemTouchHelperCallback;
import com.google.gson.Gson;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DashboardFragment extends Fragment implements ItemDragListener {
    ItemTouchHelper mItemTouchHelper;
    private DashboardViewModel dashboardViewModel;
    RecyclerView recyclerView;
    //    String[] dataArr = {"语文","数学","语文","数学","语文","数学","语文","数学","语文","数学","语文","数学","语文","数学"};
    List<Course> dataSet = new ArrayList<>();
    List<Material> materialDataSet = new ArrayList<>();
    List<Teacher> teacherDataSet = new ArrayList<>();
    private  MyAdapter myAdapter;
    private int process = 0;
    private CourseMessageDao courseMessageDao;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        dashboardViewModel =
//                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = root.findViewById(R.id.courseRecycle);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(dataSet,materialDataSet,teacherDataSet,this.getActivity());
//        request();

        courseMessageDao = CourseMessageApi.getCourseMessagedb(getActivity().getApplicationContext()).getCourseMessageDao();


        if(courseMessageDao.getAllCourse().size()==0){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    request();
                }
            }).start();
        }else{
            System.out.println(">0");
            List<com.elearing.catchPackage.entity.Course> courses = courseMessageDao.getAllCourse();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for(com.elearing.catchPackage.entity.Course course:courses){

                Course course1 = new Course();
                course1.setName(course.getName());
                course1.setDescription(course.getDescription());
                course1.setAvatar(course.getAvatar());
                course1.setBigAvatar(course.getBigAvatar());
                course1.setCertification(course.getCertification());
                course1.setCertificationDuration(course.getCertificationDuration());
                course1.setCode(course.getCode());
                course1.setLevel(course.getLevel());
                course1.setId(course.getId());
                course1.setCategoryId(course.getCategoryId());
                course1.setPrice(course.getPrice());
                course1.setShowType(course.getShowType());
                try {
                    course1.setOpenDate(formatter.parse((course.getOpenDate())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    course1.setLastUpdateOn(formatter.parse(course.getLastUpdateOn()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                course1.setShared(course.getShared());
                course1.setStatus(course.getStatus());
                course1.setSharedUrl(course.getSharedUrl());
                dataSet.add(course1);
            }

            myAdapter.refresh(dataSet);
        }

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
        MyItemTouchHelperCallback mCallback = new MyItemTouchHelperCallback(myAdapter);
        mItemTouchHelper = new ItemTouchHelper(mCallback);

        mItemTouchHelper.attachToRecyclerView(recyclerView);



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
                teacherDataSet.add(response.body().get(0));
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
                materialDataSet.add(response.body().get(0));
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

        System.out.println("加载中");

        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                    int i = 0;
                    List<Course> courses = response.body();
                    for (final Course course:courses) {
                        if(i%3+1==1) {
                            course.setShowType(1);
                        }

                        if(i%3+1==2) {
                            course.setShowType(2);
                        }

                        if(i%3+1==3) {
                            course.setShowType(3);
                        }

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        dataSet.add(course);
                        com.elearing.catchPackage.entity.Course course1 = new com.elearing.catchPackage.entity.Course();
                        course1.setName(course.getName());
                        course1.setDescription(course.getDescription());
                        course1.setAvatar(course.getAvatar());
                        course1.setBigAvatar(course.getBigAvatar());
                        course1.setCertification(course.getCertification());
                        course1.setCertificationDuration(course.getCertificationDuration());
                        course1.setCode(course.getCode());
                        course1.setLevel(course.getLevel());
                        course1.setId(course.getId());
                        course1.setCategoryId(course.getCategoryId());
                        course1.setPrice(course.getPrice());
                        course1.setOpenDate(formatter.format(course.getOpenDate()));
                        course1.setLastUpdateOn(formatter.format(course.getLastUpdateOn()));
                        course1.setShared(course.getShared());
                        course1.setStatus(course.getStatus());
                        course1.setSharedUrl(course.getSharedUrl());
                        course1.setShowType(course.getShowType());
                        courseMessageDao.catchCourseMessage(course1);
                        i++;

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

    @Override
    public void onStartDrags(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}


