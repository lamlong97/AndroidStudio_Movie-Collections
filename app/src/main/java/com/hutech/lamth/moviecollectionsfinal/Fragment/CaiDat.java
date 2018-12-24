package com.hutech.lamth.moviecollectionsfinal.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hutech.lamth.moviecollectionsfinal.R;
import com.hutech.lamth.moviecollectionsfinal.UserController.DangKy;
import com.hutech.lamth.moviecollectionsfinal.UserController.DangNhap;

import mehdi.sakout.fancybuttons.FancyButton;

public class CaiDat extends Fragment {

    FancyButton btnDangNhap,btnDangKy;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_caidat,container,false);
        btnDangNhap = view.findViewById(R.id.btnDangNhap);
        btnDangKy = view.findViewById(R.id.btnDangKy);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),DangKy.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),DangNhap.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
