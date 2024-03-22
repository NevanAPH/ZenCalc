package com.nevanpplg2.calcsbeta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.nevanpplg2.calcsbeta.activities.LoginActivity;
import com.nevanpplg2.calcsbeta.activities.MenuActivity;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("calcs", MODE_PRIVATE);

        if (preferences.contains("name") && preferences.contains("email")) {
            startActivity(new Intent(this, MenuActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }

        finish();
    }
}