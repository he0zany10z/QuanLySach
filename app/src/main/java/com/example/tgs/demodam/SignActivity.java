package com.example.tgs.demodam;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tgs.demodam.database.DatabaseHelper;
import com.example.tgs.demodam.model.NguoiDung;
import com.example.tgs.demodam.model.User;
import com.example.tgs.demodam.sqlitedao.UserDAO;

import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class SignActivity extends AppCompatActivity implements Constant{
    private EditText edtPass;
    private EditText edtPas;
    private EditText edttk;
    private Button btid;
    private ImageView imvBack;
    private List<User> userList;
    private DatabaseHelper databaseHelper;
    private UserDAO userDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);


        edtPass = findViewById(R.id.edtPass);
        edtPas = findViewById(R.id.edtPas);
        edttk = findViewById(R.id.edttk);
        btid = findViewById(R.id.btid);
        imvBack = findViewById(R.id.imvBack);

        //   databaseHelper = new DatabaseHelper(this);
        databaseHelper = new DatabaseHelper(this);
        userDAO = new UserDAO(databaseHelper);
        userList =  userDAO.getAllUser();

        btid.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edttk.getText().toString().trim();
                String passwork = edtPass.getText().toString().trim();
                String passworks = edtPas.getText().toString().trim();
                String thongbao = "";
                if (username.equals("")){
                    edttk.setError(getString(R.string.notify_empty_username));
                }if (passwork.equals("")) {
                    edtPass.setError(getString(R.string.notify_empty_password));
                }else {

                    User user = new User(username,passwork);

                    if (user!=null){
                        userDAO.insertUser(user);

                    }
                    onBackPressed();

                }

            }


        });

        imvBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

}