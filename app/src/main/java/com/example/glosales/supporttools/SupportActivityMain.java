package com.example.glosales.supporttools;

import android.os.Bundle;
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
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        openFragment(new ManualsFragment());

        toolbar.setTitle("Manuals");
        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.manuals:
                        toolbar.setTitle("Manuals");
                        ManualsFragment fragment = new ManualsFragment();
                        openFragment(fragment);
                        return true;

                    case R.id.notices:
                        toolbar.setTitle("Notices");
                        NoticesFragment noticesfragment = new NoticesFragment();
                        openFragment(noticesfragment);
                        return true;
                    case R.id.stories:
                        toolbar.setTitle("Stories");
                        StoriesFragment storiesfragment = new StoriesFragment();
                        openFragment(storiesfragment);
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

    }
}
