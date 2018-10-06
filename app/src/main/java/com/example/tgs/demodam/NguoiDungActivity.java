package com.example.tgs.demodam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tgs.demodam.database.DatabaseHelper;
import com.example.tgs.demodam.model.User;
import com.example.tgs.demodam.sqlitedao.UserDAO;

public class NguoiDungActivity extends AppCompatActivity implements Constant{
    private TextView tvUserName;
    private EditText edtUser;
    private EditText edtPass1;
    private EditText edtPass2;
    private EditText edtPhone;
    private EditText edtFullUser;
    private Button btnluu;
    private Button btnhuy;
    private Button btnshow;

    private User user;
    private DatabaseHelper databaseHelper;
    private UserDAO userDAO;

    private int position =-1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);

        initViews();
        initData();

        initActions();
    }

    private void initActions() {
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getUsername();
                String password = edtPass1.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String name = edtUser.getText().toString().trim();

                User user = new User(username,password,phone,name);
                userDAO.updateUser(user);

                Intent intent = new Intent(ON_UPDATE_USER);
                intent.putExtra(DATA,user);
                intent.putExtra(POSITION,position);
                sendBroadcast(intent);

                finish();
            }
        });
    }

    private void initData() {
        user = (User) getIntent().getSerializableExtra(DATA);
        position = getIntent().getIntExtra(POSITION, -1);
        tvUserName.setText(user.getUsername());
        edtUser.setText(user.getName());
        edtPhone.setText(user.getPhone());

        databaseHelper = new DatabaseHelper(this);
        userDAO = new UserDAO(databaseHelper);


    }

    private void initViews() {
        setContentView(R.layout.activity_nguoi_dung);
        tvUserName = findViewById(R.id.tvUserName);
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPass1 = (EditText) findViewById(R.id.edtPass1);
        edtPass2 = (EditText) findViewById(R.id.edtPass2);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtFullUser = (EditText) findViewById(R.id.edtFullUser);
        btnluu = (Button) findViewById(R.id.btnluu);
        btnhuy = (Button) findViewById(R.id.btnhuy);
        btnshow = (Button) findViewById(R.id.btnshow);
    }
}
