package com.elearing.ui.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.elearing.R;
import com.elearing.Table3Activity;

import org.w3c.dom.Text;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    RecyclerView recyclerView;
    String[] dataArr = {"语文","数学","语文","数学","语文","数学","语文","数学","语文","数学","语文","数学","语文","数学"};


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        dashboardViewModel =
//                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = root.findViewById(R.id.courseRecycle);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
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


    public class MyAdapter extends RecyclerView.Adapter{


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course,null);
            MyViewHolder myViewHolder = new MyViewHolder(itemView);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((MyViewHolder) holder).textView.setText(dataArr[position]);
            ((MyViewHolder) holder).timestamp.setText(dataArr[position]+"improve"+position);
        }

        @Override
        public int getItemCount() {
            return dataArr.length;
        }


        private class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView textView;
            private TextView timestamp;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.profile_name);
                timestamp = (TextView) itemView.findViewById(R.id.timestamp);

            }
        }
    }


}