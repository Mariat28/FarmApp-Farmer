package ug.global.glofarmedited.supporttools;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import ug.global.glofarmedited.R;
import ug.global.glofarmedited.supporttools.fragments.Manuals;
import ug.global.glofarmedited.supporttools.fragments.Notices;
import ug.global.glofarmedited.supporttools.fragments.Stories;

public class SupportToolsMain extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_tools_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Manuals");
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        openFragment(new Manuals());
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.manuals:
                        toolbar.setTitle("Manuals");
                        fragment = new Manuals();
                        openFragment(fragment);
                        return true;

                    case R.id.notices:
                        toolbar.setTitle("Notices");
                        fragment = new Notices();
                        openFragment(fragment);
                        return true;
                    case R.id.stories:
                        toolbar.setTitle("Stories");
                        fragment = new Stories();
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
/*
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.supportmenu, menu);
        return true;
    }*/
}
