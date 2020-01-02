package com.elearing.ui.notifications;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.elearing.LoginActivity;
import com.elearing.R;
import com.elearing.RegisterActivity;

public class NotificationsFragment extends Fragment {

    private Button loginButton;
    private Button registeButton;
    private Button exitBtn;
    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        loginButton = root.findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        TextView textView = root.findViewById(R.id.textView8);
        textView.setText(LoginActivity.name);

        registeButton = root.findViewById(R.id.registeFromButton);
        registeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        final TextView signature = root.findViewById(R.id.textView2);

        signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("请输入"); //设置对话框标题
                builder.setIcon(android.R.drawable.btn_star); //设置对话框标题前的图标
                final EditText edit = new EditText(getContext());
                builder.setView(edit);builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.out.println(edit.getText());
                    signature.setText(edit.getText());
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {Toast.makeText(getContext(), "你点了取消", Toast.LENGTH_SHORT).show();}
                });

                builder.setCancelable(true); //设置按钮是否可以按返回键取消,false则不可以取消
                AlertDialog dialog = builder.create(); //创建对话框
                dialog.setCanceledOnTouchOutside(true); //设置弹出框失去焦点是否隐藏,即点击屏蔽其它地方是否隐藏
                dialog.show();
            }
        });


        exitBtn = root.findViewById(R.id.button14);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return root;
    }
}