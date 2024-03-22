package com.nevanpplg2.calcsbeta.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nevanpplg2.calcsbeta.Animator;
import com.nevanpplg2.calcsbeta.R;
import com.nevanpplg2.calcsbeta.adapter.SliderAdapter;
import com.nevanpplg2.calcsbeta.fragment.DatarFragment;
import com.nevanpplg2.calcsbeta.fragment.ProfilFragment;
import com.nevanpplg2.calcsbeta.fragment.RuangFragment;

public class MenuActivity extends AppCompatActivity {

    SharedPreferences preferences;
    ViewPager2 viewPager;
    BottomNavigationView bottomNavigationView;
    TextView title;
    ImageView logo;
    SliderAdapter sliderAdapter;

    // Only used to prevent onresume running immediately after oncreate (bug)
    boolean shouldRunResume = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        preferences = getSharedPreferences("calcs", MODE_PRIVATE);

        viewPager = findViewById(R.id.main_content);
        bottomNavigationView = findViewById(R.id.main_bottomnav);
        title = findViewById(R.id.title);
        logo = findViewById(R.id.logo_watermark);

        Animator.bounceInUp(findViewById(R.id.card_bottomnav));
        Animator.bounceInDown(findViewById(R.id.card_topnav));

        setMenuActive(0);
        connectSlider();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bangun_datar) {
                setHeaderTitle(0);
                viewPager.setCurrentItem(0);
            } else if (item.getItemId() == R.id.bangun_ruang) {
                setHeaderTitle(1);
                viewPager.setCurrentItem(1);
            } else if (item.getItemId() == R.id.menu_profil) {
                setHeaderTitle(2);
                viewPager.setCurrentItem(2);
            }
            return true;
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                setHeaderTitle(position);
                setMenuActive(position);
            }
        });

        // give 1s delay for onresume to be able to
        // execute later (this would be from calcactivity)
        // also, 1s, since not less and not more delay
        // than actual people can try to avoid this.
        new android.os.Handler().postDelayed(() -> shouldRunResume = true, 1000);

    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() != 0) {
            viewPager.setCurrentItem(0);
            return;
        }
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (shouldRunResume) {
            Animator.fadeIn(viewPager);
            Animator.bounceInUp2(findViewById(R.id.card_bottomnav));
        }
    }

    private void setMenuActive(int position) {
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            if (i == position) {
                bottomNavigationView.getMenu().getItem(i).setChecked(true);
                break;
            }
            continue;
        }
    }

    private void setHeaderTitle(int position) {
        switch (position) {
            case 0:
                bottomNavigationView.setItemIconTintList(getResources().getColorStateList(R.color.nav_color_green));
                bottomNavigationView.setItemTextColor(getResources().getColorStateList(R.color.nav_color_green));
                title.setText("Pilih perhitungan datar");
                break;
            case 1:
                bottomNavigationView.setItemIconTintList(getResources().getColorStateList(R.color.nav_color_purple));
                bottomNavigationView.setItemTextColor(getResources().getColorStateList(R.color.nav_color_purple));
                title.setText("Pilih perhitungan ruang");
                break;
            default:
                bottomNavigationView.setItemIconTintList(getResources().getColorStateList(R.color.nav_color_red));
                bottomNavigationView.setItemTextColor(getResources().getColorStateList(R.color.nav_color_red));
                title.setText("Profil user");
                break;
        }
    }

    private void connectSlider() {
        sliderAdapter = new SliderAdapter(getSupportFragmentManager(), getLifecycle());
        sliderAdapter.addFragment(new DatarFragment(this::gotoCalc));
        sliderAdapter.addFragment(new RuangFragment(this::gotoCalc));
        sliderAdapter.addFragment(new ProfilFragment(this::logoutUser));

        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setAdapter(sliderAdapter);
    }

    private void gotoCalc(String type, int color) {
        Intent transition = new Intent(this, CalcActivity.class);
        transition.putExtra("type", type);
        transition.putExtra("color", color);

        Animator.fadeOut(viewPager);
        Animator.bounceOutDown2(findViewById(R.id.card_bottomnav));
        new android.os.Handler().postDelayed(() -> {
            startActivity(transition);
            overridePendingTransition(0, 0);
        }, 300);
    }

    private void logoutUser() {
        preferences.edit().clear().apply();
        Animator.fadeOut(viewPager);
        Animator.fadeOut(logo);
        Animator.bounceOutUp(findViewById(R.id.card_topnav));
        Animator.bounceOutDown(findViewById(R.id.card_bottomnav));

        new android.os.Handler().postDelayed(() -> {
            startActivity(new Intent(this, LoginActivity.class));
            overridePendingTransition(0, 0);
            finish();
        }, 600);
    }
}
