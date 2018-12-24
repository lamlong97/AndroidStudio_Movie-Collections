package com.hutech.lamth.moviecollectionsfinal.Controller;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hutech.lamth.moviecollectionsfinal.Adapter.ListMovieAdapter;
import com.hutech.lamth.moviecollectionsfinal.Category.TatCa;
import com.hutech.lamth.moviecollectionsfinal.ChiTietPhim;
import com.hutech.lamth.moviecollectionsfinal.MainActivity;
import com.hutech.lamth.moviecollectionsfinal.Objects.Movie;
import com.hutech.lamth.moviecollectionsfinal.R;

import java.util.ArrayList;

import spencerstudios.com.bungeelib.Bungee;

import static android.support.constraint.Constraints.TAG;

public class Search extends AppCompatActivity {

    TextView txtSearch;
    GridView gridSearch;
    ListMovieAdapter gridMovieAdapter;
    ArrayList<Movie> arrayMovie;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        //Bundle bundle = callerIntent.getBundleExtra("myBundle");

        String searchText = intent.getStringExtra("mySearch");

        getSupportActionBar().setTitle(searchText);

        gridSearch = findViewById(R.id.gridSearch);

//        Client client = new Client("YRQ2XKFTP9", "e56e237466d7221dd710fd40910f8c02");
//        Index index = client.getIndex("MyIndex");


        db.collection("movies").orderBy("Title").startAt(searchText).endAt(searchText+"\uf8ff")
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
                                document.get("Kind"),
                                document.get("Country"),
                                document.getString("Detail"),
                                document.getString("Starts"),
                                document.getString("Trailer"));
                        arrayMovie.add(movie);
                    }
                    if(arrayMovie.isEmpty()){
                        Intent intent = new Intent(Search.this, Search_null.class);
                        startActivity(intent);
                        finish();
                        Bungee.slideLeft(Search.this);
                    }else {
                    gridMovieAdapter = new ListMovieAdapter(Search.this,R.layout.grid_movie_items,arrayMovie);
                    gridSearch.setAdapter(gridMovieAdapter);}

                    gridSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent1 = new Intent(Search.this,ChiTietPhim.class);
                            intent1.putExtra("movieTitle",arrayMovie.get(position).getTitle());
                            startActivity(intent1);
                        }
                    });
                } else {
//                    Intent intent = new Intent(Search.this, Search_null.class);
////                    startActivity(intent);
////                    Bungee.slideLeft(Search.this);
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
        //super.onBackPressed();
        Intent intent = new Intent(Search.this,MainActivity.class);
        startActivity(intent);
        finish();
        Bungee.slideRight(this);
    }
}
