package com.hutech.lamth.moviecollectionsfinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hutech.lamth.moviecollectionsfinal.Adapter.ListMovieAdapter;
import com.hutech.lamth.moviecollectionsfinal.Category.HanhDong;
import com.hutech.lamth.moviecollectionsfinal.Objects.Movie;

import java.util.ArrayList;

import spencerstudios.com.bungeelib.Bungee;

import static android.support.constraint.Constraints.TAG;

public class TheLoaiPhim extends AppCompatActivity {

    GridView gridMovie;
    ListMovieAdapter gridMovieAdapter;
    ArrayList<Movie> arrayMovie;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theloaiphim);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Intent intent = getIntent();
        String theLoai = intent.getStringExtra("TheLoai");

        getSupportActionBar().setTitle("Phim "+theLoai.toLowerCase());

        gridMovie = findViewById(R.id.gridMovie);

        db.collection("movies").whereArrayContains("Category",theLoai)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    arrayMovie = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Movie movie = new Movie(document.getString("Title"),
                                document.getString("Poster"),
                                document.getString("Time"),
                                document.getString("Year"),
                                document.getString("Director"),
                                document.getString("Writer"),
                                document.get("Category"),
                                document.get("Country"),
                                document.getString("Detail"),
                                document.getString("Starts"),
                                document.getString("Trailer"));
                        arrayMovie.add(movie);
                    }
                    gridMovieAdapter = new ListMovieAdapter(TheLoaiPhim.this,R.layout.grid_movie_items,arrayMovie);
                    gridMovie.setAdapter(gridMovieAdapter);

                    gridMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent1 = new Intent(TheLoaiPhim.this,ChiTietPhim.class);
                            intent1.putExtra("movieTitle",arrayMovie.get(position).getTitle());
                            intent1.putExtra("url",arrayMovie.get(position).getTrailer());
                            startActivity(intent1);
                            //Toast.makeText(TheLoaiPhim.this, arrayMovie.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

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
