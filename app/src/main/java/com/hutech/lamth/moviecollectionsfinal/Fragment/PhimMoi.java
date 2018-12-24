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
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.hutech.lamth.moviecollectionsfinal.Adapter.ListMovieAdapter;
import com.hutech.lamth.moviecollectionsfinal.Adapter.SliderAdapter;
import com.hutech.lamth.moviecollectionsfinal.Category.HanhDong;
import com.hutech.lamth.moviecollectionsfinal.Category.KinhDi;
import com.hutech.lamth.moviecollectionsfinal.Category.TatCa;
import com.hutech.lamth.moviecollectionsfinal.Category.VienTuong;
import com.hutech.lamth.moviecollectionsfinal.ChiTietPhim;
import com.hutech.lamth.moviecollectionsfinal.Objects.Movie;
import com.hutech.lamth.moviecollectionsfinal.Picasso.PicassoImageLoadingService;
import com.hutech.lamth.moviecollectionsfinal.R;
import com.hutech.lamth.moviecollectionsfinal.TheLoaiPhim;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;

import spencerstudios.com.bungeelib.Bungee;
import ss.com.bannerslider.Slider;

import static android.support.constraint.Constraints.TAG;

public class PhimMoi extends Fragment {
    Slider slider;
    TwoWayView lsvListMovies,lsvListMovies2,lsvListMovies3,lsvListAllMovie;
    Button btnLinear1,btnLinear2,btnLinear3,btnTatCa;
    ListMovieAdapter listMovieAdapter;
    ArrayList<Movie> arrayAllMovie,arrayMovie,arrayMovie2,arrayMovie3;

    //Khai báo Firebase Firestore
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phimmoi,container,false);

        //Tạo Banner
        Slider.init(new PicassoImageLoadingService(getContext()));
        slider = view.findViewById(R.id.bannerSlider);
        slider.setAdapter(new SliderAdapter());

        lsvListMovies =  (TwoWayView) view.findViewById(R.id.lsvListMovies);
        lsvListMovies2 = view.findViewById(R.id.lsvListMovies2);
        lsvListMovies3 = view.findViewById(R.id.lsvListMovies3);
        lsvListAllMovie= view.findViewById(R.id.lsvListAllMovies);
        btnLinear1 = view.findViewById(R.id.btnLinear1);
        btnLinear2 = view.findViewById(R.id.btnLinear2);
        btnLinear3 = view.findViewById(R.id.btnLinear3);
        btnTatCa = view.findViewById(R.id.btnTatCa);

        buttonEvent();

        db.collection("movies")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    arrayAllMovie = new ArrayList<>();
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
                        arrayAllMovie.add(movie);
                    }
                    listMovieAdapter = new ListMovieAdapter(getContext(),R.layout.list_movie_items,arrayAllMovie);
                    lsvListAllMovie.setAdapter(listMovieAdapter);
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

        db.collection("movies").whereArrayContains("Category","Hành động")
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
                    listMovieAdapter = new ListMovieAdapter(getContext(),R.layout.list_movie_items,arrayMovie);
                    lsvListMovies.setAdapter(listMovieAdapter);
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

        db.collection("movies").whereArrayContains("Category","Viễn tưởng")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    arrayMovie2 = new ArrayList<>();
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
                        arrayMovie2.add(movie);
                    }
                    listMovieAdapter = new ListMovieAdapter(getContext(),R.layout.list_movie_items,arrayMovie2);
                    lsvListMovies2.setAdapter(listMovieAdapter);
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

        db.collection("movies").whereArrayContains("Category","Kinh dị")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    arrayMovie3 = new ArrayList<>();
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
                        arrayMovie3.add(movie);
                    }
                    listMovieAdapter = new ListMovieAdapter(getContext(),R.layout.list_movie_items,arrayMovie3);
                    lsvListMovies3.setAdapter(listMovieAdapter);
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

        lsvListAllMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getContext(),ChiTietPhim.class);
                intent1.putExtra("movieTitle",arrayAllMovie.get(position).getTitle());
                startActivity(intent1);
                //Toast.makeText(getContext(), arrayAllMovie.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        lsvListMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getContext(),ChiTietPhim.class);
                intent1.putExtra("movieTitle",arrayMovie.get(position).getTitle());
                startActivity(intent1);
                //Toast.makeText(getContext(), arrayMovie.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        lsvListMovies2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getContext(),ChiTietPhim.class);
                intent1.putExtra("movieTitle",arrayMovie2.get(position).getTitle());
                startActivity(intent1);
                //Toast.makeText(getContext(), arrayMovie2.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        lsvListMovies3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(getContext(),ChiTietPhim.class);
                intent1.putExtra("movieTitle",arrayMovie3.get(position).getTitle());
                startActivity(intent1);
                //Toast.makeText(getContext(), arrayMovie3.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void buttonEvent() {
        btnLinear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),HanhDong.class);
                startActivity(intent);
                Bungee.slideLeft(getContext());
            }
        });
        btnLinear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),VienTuong.class);
                startActivity(intent);
                Bungee.slideLeft(getContext());
            }
        });
        btnLinear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),KinhDi.class);
                startActivity(intent);
                Bungee.slideLeft(getContext());
            }
        });
        btnTatCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TatCa.class);
                startActivity(intent);
                Bungee.slideLeft(getContext());
            }
        });
    }
}
