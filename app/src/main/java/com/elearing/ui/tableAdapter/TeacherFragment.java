package com.elearing.ui.tableAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elearing.R;
import com.elearing.api.GetRequest;
import com.elearing.api.Teacher;
import com.elearing.ui.dashboard.MyAdapter;

import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link TeacherFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link TeacherFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class TeacherFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static TeacherFragment newInstance(int index) {
        TeacherFragment fragment = new TeacherFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_teacher_message, container, false);
        TextView teacherName = root.findViewById(R.id.teachername);
        TextView telephone = root.findViewById(R.id.teachertelephone);
        TextView email = root.findViewById(R.id.email);
        TextView description = root.findViewById(R.id.des);
        Intent intent = getActivity().getIntent();
        List<Teacher> teachers = (List<Teacher>) intent.getSerializableExtra("teachers");

        if(teachers!=null){
            ImageView teacherPhoto= root.findViewById(R.id.teacherphoto);
            Glide.with(getContext()).load(GetRequest.BASE_URL+"teachers/"+teachers.get(0).getUserid()+"/photo")
                    .into(teacherPhoto);

            String str1 = "<font color=\"#000000\"><big>CourseName:</big></font>"+"<br />"+teachers.get(0).getName();
            String str2 = "<font color=\"#000000\"><big>Telephone:</big></font>"+"<br />"+teachers.get(0).getTelephone();
            String str3 = "<font color=\"#000000\"><big>Email:</big></font>"+"<br />"+teachers.get(0).getEmail();
            String str4 = "<font color=\"#000000\"><big>Description:</big></font>"+"<br />"+teachers.get(0).getDescription();
            teacherName.setText(Html.fromHtml(str1));
            telephone.setText(Html.fromHtml(str2));
            email.setText(Html.fromHtml(str3));
            description.setText(Html.fromHtml(str4));
        }

        return root;
    }
}