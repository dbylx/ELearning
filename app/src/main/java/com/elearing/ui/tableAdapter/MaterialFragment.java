package com.elearing.ui.tableAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.elearing.R;
import com.elearing.api.Material;

import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link TeacherFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link TeacherFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class MaterialFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static MaterialFragment newInstance(int index) {
        MaterialFragment fragment = new MaterialFragment();
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
        View root = inflater.inflate(R.layout.fragment_material_message, container, false);
        TextView materialUrl = root.findViewById(R.id.materialURL);
        TextView resource = root.findViewById(R.id.res);
        Intent intent = getActivity().getIntent();
        List<Material> materials = (List<Material>) intent.getSerializableExtra("materials");

        if(materials!=null){
            String str1 = "<font color=\"#000000\"><big>MaterialUrl:</big></font>"+"<br />"+materials.get(0).getMaterialUrl();
            String str2 =  "<font color=\"#000000\"><big>Description:</big></font>"+"<br />"+materials.get(0).getDescription();
            materialUrl.setText(Html.fromHtml(str1));
            resource.setText(Html.fromHtml(str2));
        }

        return root;
    }
}