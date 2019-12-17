package com.elearing.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        dashboardViewModel =
//                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = root.findViewById(R.id.courseRecycle);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        MyAdapter myAdapter = new MyAdapter(dataSet,materialDataSet,teacherDataSet);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getActivity(), Table3Activity.class);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(myAdapter);



        for(int i = 0;i<9;i++){
            Course s = new Course();
            s.setName("hello"+i);
            if(i ==2 ){
                s.setShowType(2);
            }
            else if(i ==3){
            s.setShowType(3);
            }else{
                s.setShowType(1);
            }
//
            dataSet.add(s);
        }

//        request();
//        MyAdapter adapter = new MyAdapter();
//        recyclerView.setAdapter(adapter);
//        dataArr[13] = "英语";
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        System.out.println(df.format(new Date()));
//        System.out.println("changeToEnglish");
//        System.out.println(data.size());

//
//
////        root.findViewById(R.id.include_lay_1).setOnClickListener(new View.OnClickListener(){
////
////            @Override
////            public void onClick(View view) {
////                Intent intent = new Intent(getActivity(), Table3Activity.class);
////                startActivity(intent);
////            }
////        });
////
////        root.findViewById(R.id.include_lay_2).setOnClickListener(new View.OnClickListener(){
////
////            @Override
////            public void onClick(View view) {
////                Intent intent = new Intent(getActivity(), Table3Activity.class);
////                startActivity(intent);
////            }
////        });
//
//
        return root;
    }

//    private void request(){
//        Retrofit retrofit = GetRequest.getApiClient();
//
//        // 创建 网络请求接口 的实例
//        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
//
//        //对 发送请求 进行封装
//        Call<List<Course>> call = request.getCourses();
//
//        call.enqueue(new Callback<List<Course>>() {
//            @Override
//            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
//
//
//                for (Course course:response.body()){
//                    data.add(course);
//                    dataArr[12] = course.getCategoryId();
//                }
//
//
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//                System.out.println(df.format(new Date()));
//                System.out.println(data.size());
//
//            }
//
//
//            //请求失败时候的回调
//            @Override
//            public void onFailure(Call<List<Course>> call, Throwable throwable) {
//                //提示失败
//            }
//        });
//    }




}


