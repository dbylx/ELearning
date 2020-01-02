package com.elearing.ui.tableAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
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

        String str1 = "<font color=\"#000000\"><big>CourseName:</big></font>"+"<br />"+course.getName();
        String str2 = "<font color=\"#000000\"><big>Price:</big></font>"+"<br />"+course.getPrice();
        String str3 = "<font color=\"#000000\"><big>Status:</big></font>"+"<br />"+course.getStatus();
        String str4 = "<font color=\"#000000\"><big>OpenDate:</big></font>"+"<br />"+course.getOpenDate().toString();
        String str5 = "<font color=\"#000000\"><big>LastUpdate:</big></font>"+"<br />"+course.getLastUpdateOn().toString();
        String str6 = "<font color=\"#000000\"><big>Url:</big></font>"+"<br />"+course.getSharedUrl();

        courseName.setText(Html.fromHtml(str1));
        coursePrice.setText(Html.fromHtml(str2));
        status.setText(Html.fromHtml(str3));
        openDate.setText(Html.fromHtml(str4));
        lastUpdate.setText(Html.fromHtml(str5));
        shareUrl.setText(Html.fromHtml(str6));
        return root;
    }
}