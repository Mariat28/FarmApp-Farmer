package ug.global.glofarmedited;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    View snackbarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        snackbarView = findViewById(R.id.mainView);
        TextInputEditText textInputEditText = findViewById(R.id.pinTxt);
        textInputEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        findViewById(R.id.newAccountTxt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(LoginActivity.this, NewAccountActivity.class), Constants.NEW_FARM_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.NEW_FARM_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Snackbar.make(snackbarView, "You have successfully registered. Please login", BaseTransientBottomBar.LENGTH_SHORT).setBackgroundTint(getResources().getColor(R.color.design_default_color_primary)).show();
            } else {
                Snackbar.make(snackbarView, "Registration failed", BaseTransientBottomBar.LENGTH_LONG).setAction("TRY AGAIN", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivityForResult(new Intent(LoginActivity.this, NewAccountActivity.class), Constants.NEW_FARM_REQUEST_CODE);
                    }
                }).setBackgroundTint(getResources().getColor(R.color.design_default_color_primary)).show();
            }
        }
    }
}
