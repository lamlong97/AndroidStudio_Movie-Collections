package com.hutech.lamth.moviecollectionsfinal.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hutech.lamth.moviecollectionsfinal.Adapter.KindAdapter;
import com.hutech.lamth.moviecollectionsfinal.Objects.Kind;
import com.hutech.lamth.moviecollectionsfinal.R;
import com.hutech.lamth.moviecollectionsfinal.TheLoaiPhim;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class TheLoai extends Fragment {

    ListView lsvTheLoai;
    KindAdapter kindAdapter;
    ArrayList<Kind> arrayTheLoai;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theloai,container,false);

        lsvTheLoai = view.findViewById(R.id.lsvTheLoai);
        lsvTheLoai.setDivider(null);

        db.collection("category")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    arrayTheLoai = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Kind kind = new Kind(document.getString("name"),
                                document.getString("icon"),
                                document.getString("description"));
                                arrayTheLoai.add(kind);
                    }
                    kindAdapter = new KindAdapter(getContext(),R.layout.theloai_items,arrayTheLoai);
                    lsvTheLoai.setAdapter(kindAdapter);

                    lsvTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getContext(),TheLoaiPhim.class);
                            intent.putExtra("TheLoai",arrayTheLoai.get(position).getName());
                            startActivity(intent);
                            //Toast.makeText(getContext(), arrayTheLoai.get(position).getName(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

//        lsvTheLoai.setAdapter(kindAdapter);
//        lsvTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //String string = (String) categoryAdapter.getItem(position);
//                //Object o = lsvTheLoai.getItemAtPosition(position);
//                //Kind kind = (Kind) o;
//                Toast.makeText(getContext(), position, Toast.LENGTH_LONG).show();
//            }
//        });
        return view;
    }

}
