package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etAddress;
    Button btnAddUser;
    RecyclerView rvUser;
    UserAdapter userAdapter;
    List<User> mlistUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName=findViewById(R.id.etName);
        etAddress=findViewById(R.id.etAddress);
        rvUser=findViewById(R.id.rvUser);
        btnAddUser=findViewById(R.id.btnAddUser);

        userAdapter = new UserAdapter();
        mlistUser = new ArrayList<>();
        userAdapter.setData(mlistUser);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvUser.setLayoutManager(linearLayoutManager);
        rvUser.setAdapter(userAdapter);
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

    }

    private void addUser() {
        String userName = etName.getText().toString().trim();
        String userAddress = etAddress.getText().toString().trim();
        if(TextUtils.isEmpty(userName)|| TextUtils.isEmpty(userAddress)){
            return;
        }
        User user = new User(userName, userAddress);
        UserDatabase.getInstance(this).userDao().insertUser(user);
        Toast.makeText(this,"Successfully", Toast.LENGTH_SHORT).show();
        etAddress.setText("");
        etName.setText("");
        hideKeyboard();

        mlistUser = UserDatabase.getInstance(this).userDao().getListUser();
        userAdapter.setData(mlistUser);
    }
    public void hideKeyboard(){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }
}
