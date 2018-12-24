package com.hutech.lamth.moviecollectionsfinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hutech.lamth.moviecollectionsfinal.Controller.Search;
import com.hutech.lamth.moviecollectionsfinal.Fragment.CaiDat;
import com.hutech.lamth.moviecollectionsfinal.Fragment.PhimMoi;
import com.hutech.lamth.moviecollectionsfinal.Fragment.TheLoai;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import spencerstudios.com.bungeelib.Bungee;

public class MainActivity extends AppCompatActivity {
    MaterialSearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        toolbar.setLogo(R.drawable.ic_logo);


        searchView = (MaterialSearchView) findViewById(R.id.search_view) ;

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent intent = new Intent(MainActivity.this,Search.class);
                intent.putExtra("mySearch",query);
                startActivity(intent);
                finish();
                Bungee.slideLeft(MainActivity.this);
                //Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               return false;
            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });



        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PhimMoi()).commit();
        BottomNavigation(); //Xử lý Bottom Navigation

    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        MenuItem item = menu.findItem(R.id.id_search);
        searchView.setMenuItem(item);
        return true;
    }

    private void BottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.action_hot_movies:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new PhimMoi()).commit();
                        break;
                    case R.id.action_category:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TheLoai()).commit();
                        break;
                    case R.id.action_setting:
                                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CaiDat()).commit();
                                break;
                }
                return true;
            }
        });

    }

}
