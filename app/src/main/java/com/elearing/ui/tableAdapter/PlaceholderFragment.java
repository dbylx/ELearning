package com.elearing.ui.tableAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.elearing.R;
import com.elearing.api.Course;
import com.google.gson.Gson;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_course_message, container, false);
        Intent intent = getActivity().getIntent();
        intent.getStringExtra("course");
        String courseJson = intent.getStringExtra("course");
        Course course = new Gson().fromJson(courseJson,Course.class);
        TextView courseName = root.findViewById(R.id.courseName);
        TextView coursePrice = root.findViewById(R.id.coursePrice);
        TextView status = root.findViewById(R.id.status);
        TextView openDate = root.findViewById(R.id.openDate);
        TextView lastUpdate = root.findViewById(R.id.lastUpdate);
        TextView shareUrl = root.findViewById(R.id.sharedURL);

        courseName.setText("CourseName:"+course.getName());
        coursePrice.setText("Price:"+course.getPrice());
        status.setText("Status:"+course.getStatus());
        openDate.setText("OpenDate:"+course.getOpenDate().toString());
        lastUpdate.setText("LastUpdate:"+course.getLastUpdateOn().toString());
        shareUrl.setText("Url:"+course.getSharedUrl());
        return root;
    }
}