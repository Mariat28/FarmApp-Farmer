package ug.global.glofarmedited;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        final Button button = findViewById(R.id.loginButton);
        snackbarView = findViewById(R.id.mainView);
        TextView create = findViewById(R.id.newAccountTxt);
        create.setVisibility(getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getBoolean("is_registered", false) ? View.INVISIBLE : View.VISIBLE);

        final TextInputEditText textInputEditText = findViewById(R.id.pinTxt);
        textInputEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                button.setEnabled(s.length() >= 4);

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInputEditText.setEnabled(false);
                button.setEnabled(false);
                String pin = textInputEditText.getEditableText().toString().trim();
                if (pin.equals(getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("pin", null))) {
                    startActivity(new Intent(LoginActivity.this, Home.class));
                } else {
                    textInputEditText.setEnabled(true);
                    Snackbar.make(snackbarView, "Unknown PIN", BaseTransientBottomBar.LENGTH_SHORT).setBackgroundTint(getResources().getColor(R.color.design_default_color_error)).show();

                }

            }
        });
        create.setOnClickListener(new View.OnClickListener() {
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
