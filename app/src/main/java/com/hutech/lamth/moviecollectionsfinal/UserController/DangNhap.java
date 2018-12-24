package com.hutech.lamth.moviecollectionsfinal.UserController;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.hutech.lamth.moviecollectionsfinal.MainActivity;
import com.hutech.lamth.moviecollectionsfinal.R;

import spencerstudios.com.bungeelib.Bungee;

public class DangNhap extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    TextView txtSignUp;
    Button btnSignIn;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Đăng nhập");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AnhXa();

        CheckUser();

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangNhap.this, DangKy.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(DangNhap.this, "Yêu cầu điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    mProgressDialog.setTitle("Đăng Nhập");
                    mProgressDialog.setMessage("Hệ thống đang xử lý vui lòng chờ");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();
                    DangNhap(email, password);
                }
            }
        });
    }
    private void CheckUser() {

    }

    private void DangNhap(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mProgressDialog.dismiss();
                            Intent intent = new Intent(DangNhap.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        } else {

                        }
                    }
                });
    }

    private void AnhXa() {
        edtUsername = findViewById(R.id.editTextUsername);
        edtPassword = findViewById(R.id.editTextPassword);
        txtSignUp = findViewById(R.id.textViewSignup);
        btnSignIn = findViewById(R.id.btnSignIn);
        mAuth = FirebaseAuth.getInstance();
        mProgressDialog = new ProgressDialog(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Bungee.slideRight(this);
    }
}
