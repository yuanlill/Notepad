package com.example.test3_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 创建对话框构建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 获取布局
        View view2 = View.inflate(MainActivity.this, R.layout.login, null);
        // 获取布局中的控件
        final EditText username = (EditText) view2.findViewById(R.id.username);
        final EditText password = (EditText) view2.findViewById(R.id.password);
        final Button button1 = (Button) view2.findViewById(R.id.btn_cancel);
        final Button button2 = (Button) view2.findViewById(R.id.btn_sign_in);
        // 设置参数
        builder.setIcon(R.drawable.ic_launcher)
                .setView(view2);
        // 创建对话框
        final AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }
}