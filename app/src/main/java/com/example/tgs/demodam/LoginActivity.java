package com.example.tgs.demodam;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tgs.demodam.database.DatabaseHelper;
import com.example.tgs.demodam.model.User;
import com.example.tgs.demodam.sqlitedao.UserDAO;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText edtPass;
    private EditText edttk;
    private Button btid;
    private Button btdk;

    private DatabaseHelper databaseHelper;
    private UserDAO userDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtPass = findViewById(R.id.edtPass);
        edttk = findViewById(R.id.edttk);
        btid = findViewById(R.id.btid);
        btdk = findViewById(R.id.btdk);
        //khởi tạo database
        databaseHelper = new DatabaseHelper(this);
        userDAO = new UserDAO(databaseHelper);





        btid.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edttk.getText().toString().trim();
                String passwork = edtPass.getText().toString().trim();
                if (username.equals("")){
                    edttk.setError(getString(R.string.notify_empty_username));
                    return;
                }else if (passwork.equals("")) {
                    edtPass.setError(getString(R.string.notify_empty_password));
                    return;
                }else {

                    User user = userDAO.getUserByUsername(username);

                    // neu use !=null, tuc la username co tren DB thi so sanh password
                    if (user != null){
                        String passwordOnDatabase = user.getPassword();


                        // nếu password giống nhau thì cho phép đăng nhập và mở màn hình HomeActivity
                        if (passwordOnDatabase.equals(passwork)){
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(intent);
                        }
                        // nếu password không giống thì thông báo lỗi
                        else Toast.makeText(LoginActivity.this,
                                getString(R.string.notify_wrong_password),Toast.LENGTH_SHORT).show();
                    }
                    // nếu user == null thì thông báo lỗi
                    else Toast.makeText(LoginActivity.this,
                            getString(R.string.notify_wrong_password),Toast.LENGTH_SHORT).show();
                }

            }


        });
    }

    public void dangky(View view) {
        Intent intent = new Intent(LoginActivity.this,SignActivity.class);
        startActivity(intent);
    }


}

