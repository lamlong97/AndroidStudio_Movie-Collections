package com.hutech.lamth.moviecollectionsfinal;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hutech.lamth.moviecollectionsfinal.Objects.Movie;
import com.squareup.picasso.Picasso;

import mehdi.sakout.fancybuttons.FancyButton;
import spencerstudios.com.bungeelib.Bungee;

public class ChiTietPhim extends YouTubeBaseActivity {

    FancyButton btnPlay;
    ImageView imgPoster;
    TextView txtFlimName, txtFlimTime, txtFlimDate,txtDetail, txtCommentScreen;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Movie movie;
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener ;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phim);

        btnPlay = findViewById(R.id.btnPlay);
        imgPoster = findViewById(R.id.imgPoster);
        txtFlimName = findViewById(R.id.txtFilmName);
        txtFlimDate = findViewById(R.id.txtFilmDate);
        txtFlimTime = findViewById(R.id.txtFilmTime);
        txtDetail = findViewById(R.id.txtDetail);
        txtCommentScreen = findViewById(R.id.txtCommentScreen);

        youTubePlayerView = findViewById(R.id.youtubePlay);

        final Intent intent = getIntent();
        String title = intent.getStringExtra("movieTitle");
        new YouTubeUrl().setUrl(intent.getStringExtra("url"));

        Query docRef = db.collection("movies").whereEqualTo("Title",title);
        docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //movie = new Movie();
                        movie = document.toObject(Movie.class);
                    }
                    //new YouTubeUrl(movie.getTrailer());
                    txtFlimName.setText(movie.getTitle());
                    txtFlimTime.setText("Thời lượng: "+ movie.getTime());
                    txtFlimDate.setText("Năm sản xuất: "+ movie.getYear());
                    txtDetail.setText(movie.getDetail());
                    //txtUrl.setText(movie.getTrailer());
                    Picasso.get().load(movie.getPoster()).into(imgPoster);
                    //YOUTUBE_URL = movie.getTrailer();
                    YouTubeUrl myURL = new YouTubeUrl();
                    myURL.setUrl(movie.getTrailer());
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.cueVideo(YouTubeUrl.getUrl());
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        txtCommentScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChiTietPhim.this, MyComment.class));
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize(YouTubeConfig.getApiKey(),onInitializedListener);
                //Toast.makeText(ChiTietPhim.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnPlay.performClick();
            }
        }, 900);
//        btnPlay.callOnClick();
    }
}
