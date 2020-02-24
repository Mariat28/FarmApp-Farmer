package com.example.glosales.supporttools;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.glosales.R;
import com.example.glosales.supporttools.fragments.ManualsFragment;
import com.example.glosales.supporttools.fragments.NoticesFragment;
import com.example.glosales.supporttools.fragments.StoriesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class SupportActivityMain extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Manuals");
        openFragment(new ManualsFragment());
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.manuals:
                        toolbar.setTitle("Manuals");
                        fragment = new ManualsFragment();
                        openFragment(fragment);
                        return true;

                    case R.id.notices:
                        toolbar.setTitle("Notices");
                        fragment = new NoticesFragment();
                        openFragment(fragment);
                        return true;
                    case R.id.stories:
                        toolbar.setTitle("Stories");
                        fragment = new StoriesFragment();
                        openFragment(fragment);
                        return true;
                }
                return false;
            }
        };

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);


    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.supportmenu, menu);
        return true;
    }
}
