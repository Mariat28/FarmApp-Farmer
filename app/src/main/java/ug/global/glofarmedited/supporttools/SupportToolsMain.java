package ug.global.glofarmedited.supporttools;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

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
        toolbar.setTitle("FAQs");
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
                        toolbar.setTitle("FAQs");
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.supportmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.askaquestion) {
            AlertDialog builder = new MaterialAlertDialogBuilder(this).create();
            builder.setTitle("Ask a question");
            builder.setView(getLayoutInflater().inflate(R.layout.ask_question_alert, null));
            builder.setButton(DialogInterface.BUTTON_POSITIVE, "SUBMIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }

            });
            builder.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
