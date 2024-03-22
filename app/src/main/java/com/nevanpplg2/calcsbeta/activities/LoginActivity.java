package com.nevanpplg2.calcsbeta.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.nevanpplg2.calcsbeta.Animator;
import com.nevanpplg2.calcsbeta.R;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences preferences;
    ImageView watermark, circle1, circle2;
    MaterialCardView card;
    EditText name, email;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        preferences = getSharedPreferences("calcs", MODE_PRIVATE);
        watermark = findViewById(R.id.logo_watermark);
        circle1 = findViewById(R.id.circle_1);
        circle2 = findViewById(R.id.circle_2);
        card = findViewById(R.id.card_login);

        name = findViewById(R.id.inputNama);
        email = findViewById(R.id.inputEmail);
        submit = findViewById(R.id.btnSubmit);

        Animator.bounceInLogin(circle1, 1);
        Animator.bounceInLogin(circle2, 2);
        Animator.bounceInLogin(card, 3);

        submit.setOnClickListener(v -> {
            if (name.getText().length() < 1 || email.getText().length() < 1) {
                Snackbar.make(v, "Nama dan email tidak boleh kosong.", Snackbar.LENGTH_SHORT).show();
                return;
            }

            if (!email.getText().toString().contains("@") || !email.getText().toString().contains(".")) {
                Snackbar.make(v, "Email tidak valid.", Snackbar.LENGTH_SHORT).show();
                return;
            }

            preferences.edit().putString("name", name.getText().toString()).apply();
            preferences.edit().putString("email", email.getText().toString()).apply();

            watermark.setVisibility(ImageView.VISIBLE);
            Animator.bounceOutLogin(card, 1);
            Animator.bounceOutLogin(circle2, 2);
            Animator.bounceOutLogin(circle1, 3);

            new android.os.Handler().postDelayed(() -> {
                startActivity(new Intent(this, MenuActivity.class));
                overridePendingTransition(0, 0);
                finish();
            }, 600);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
