package com.elearing;

import android.content.Intent;
import android.os.Bundle;

import com.elearing.api.Course;
import com.elearing.api.Material;
import com.elearing.api.Teacher;
import com.elearing.ui.tableAdapter.MaterialFragment;
import com.elearing.ui.tableAdapter.PlaceholderFragment;
import com.elearing.ui.tableAdapter.TeacherFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.elearing.ui.tableAdapter.SectionsPagerAdapter;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

public class Table3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table3);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        Intent intent = getIntent();

        String courseJson = intent.getStringExtra("course");
        Intent courseIntent = new Intent(getApplicationContext(),PlaceholderFragment.class);
        courseIntent.putExtra("course",courseJson);

        Intent teacherIntent = new Intent(getApplicationContext(), TeacherFragment.class);
        List<Teacher> teachers = (List<Teacher>) intent.getSerializableExtra("teachers");
        teacherIntent.putExtra("teachers",(Serializable)teachers);

        List<Material> materials = (List<Material>) intent.getSerializableExtra("materials");
        Intent materialIntent = new Intent(getApplicationContext(), MaterialFragment.class);
        materialIntent.putExtra("materials",(Serializable)materials);
        //System.out.println(course.getName());



    }
}