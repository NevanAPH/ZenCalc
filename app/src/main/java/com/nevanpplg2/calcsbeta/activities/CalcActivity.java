package com.nevanpplg2.calcsbeta.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.nevanpplg2.calcsbeta.Animator;
import com.nevanpplg2.calcsbeta.R;

import java.util.Objects;

public class CalcActivity extends AppCompatActivity {

    LinearLayout back;
    ImageView shape;
    TextView title, result;

    MaterialCardView card1, card2, card_input1, card_input2, card_input3;
    EditText input1, input2, input3;
    Button submit;

    String type;
    int color, inputCount;
    boolean firstResult = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_activity);

        type = Objects.requireNonNull(getIntent().getStringExtra("type"));
        color = getIntent().getIntExtra("color", 0);

        back = findViewById(R.id.btnBack);
        shape = findViewById(R.id.shape);
        title = findViewById(R.id.title);
        result = findViewById(R.id.result);

        card1 = findViewById(R.id.card_view1);
        card2 = findViewById(R.id.card_view2);
        card2.setVisibility(View.GONE);

        card_input1 = findViewById(R.id.card_input1);
        card_input2 = findViewById(R.id.card_input2);
        card_input3 = findViewById(R.id.card_input3);

        input1 = findViewById(R.id.input_1);
        input2 = findViewById(R.id.input_2);
        input3 = findViewById(R.id.input_3);
        submit = findViewById(R.id.btnSubmit);

        Animator.fadeInLeft(back);
        back.setOnClickListener(v -> onBackPressed());
        submit.setOnClickListener(this::submitClick);

        setType();
        setInput();
        setColor();

        Animator.bounceInUp2(shape);
        animateFirstCard();
    }

    @Override
    public void onBackPressed() {
        Animator.fadeOutLeft(back);
        Animator.fadeOut(shape);
        Animator.bounceOutDown2(card1);
        Animator.bounceOutDown2(card2);

        new android.os.Handler().postDelayed(() -> {
            overridePendingTransition(0,0);
            super.onBackPressed();
            overridePendingTransition(0,0);
        }, 300);
    }

    private void submitClick(View v) {
        if (!cekInputTerisi()) {
            Snackbar.make(v, "Input tidak boleh kosong.", Snackbar.LENGTH_SHORT).show();
            return;
        }

        result.setText(String.format("Hasil: %.2fcm", hitungHasil()));

        if (firstResult) {
            card2.setVisibility(View.VISIBLE);
            Animator.bounceInRecycler(card2, 0);
            Animator.bounceInRecycler(result, 1);
            firstResult = false;
        } else {
            Animator.bounceOnce(result);
        }
    }

    private boolean cekInputTerisi() {
        switch (inputCount) {
            case 1:
                return !input1.getText().toString().isEmpty() && !input1.getText().toString().equals("-");
            case 2:
                return !input1.getText().toString().isEmpty() && !input2.getText().toString().isEmpty() && !input1.getText().toString().equals("-") && !input2.getText().toString().equals("-");
            case 3:
                return !input1.getText().toString().isEmpty() && !input2.getText().toString().isEmpty() && !input3.getText().toString().isEmpty() && !input1.getText().toString().equals("-") && !input2.getText().toString().equals("-") && !input3.getText().toString().equals("-");
            default:
                return false;
        }
    }

    private double hitungHasil() {
        double angka1, angka2, angka3;
        double hasil = 0;

        switch (type) {
            case "triangle":
                angka1 = Double.parseDouble(input1.getText().toString());
                angka2 = Double.parseDouble(input2.getText().toString());
                hasil = 0.5 * angka1 * angka2;
                break;
            case "rectangle":
                angka1 = Double.parseDouble(input1.getText().toString());
                angka2 = Double.parseDouble(input2.getText().toString());
                hasil = angka1 * angka2;
                break;
            case "circle":
                angka1 = Double.parseDouble(input1.getText().toString());
                hasil = Math.PI * Math.pow(angka1, 2);
                break;
            case "trapezoid":
                angka1 = Double.parseDouble(input1.getText().toString());
                angka2 = Double.parseDouble(input2.getText().toString());
                angka3 = Double.parseDouble(input3.getText().toString());
                hasil = 0.5 * (angka1 + angka2) * angka3;
                break;
            case "parallelogram":
                angka1 = Double.parseDouble(input1.getText().toString());
                angka2 = Double.parseDouble(input2.getText().toString());
                hasil = angka1 * angka2;
                break;
            case "diamond":
                angka1 = Double.parseDouble(input1.getText().toString());
                angka2 = Double.parseDouble(input2.getText().toString());
                hasil = 0.5 * angka1 * angka2;
                break;
            case "prism":
                angka1 = Double.parseDouble(input1.getText().toString());
                angka2 = Double.parseDouble(input2.getText().toString());
                hasil = 2 * angka1 + angka2;
                break;
            case "cube":
                angka1 = Double.parseDouble(input1.getText().toString());
                angka2 = Double.parseDouble(input2.getText().toString());
                angka3 = Double.parseDouble(input3.getText().toString());
                hasil = 2 * (angka1 * angka2 + angka1 * angka3 + angka2 * angka3);
                break;
            case "pyramid":
                angka1 = Double.parseDouble(input1.getText().toString());
                angka2 = Double.parseDouble(input2.getText().toString());
                angka3 = Double.parseDouble(input3.getText().toString());
                hasil = angka1 + angka2 + angka3;
                break;
            case "tube":
                angka1 = Double.parseDouble(input1.getText().toString());
                angka2 = Double.parseDouble(input2.getText().toString());
                hasil = 2 * Math.PI * angka1 * (angka1 + angka2);
                break;
            case "ball":
                angka1 = Double.parseDouble(input1.getText().toString());
                hasil = 4 * Math.PI * Math.pow(angka1, 2);
                break;
            case "cone":
                angka1 = Double.parseDouble(input1.getText().toString());
                angka2 = Double.parseDouble(input2.getText().toString());
                hasil = Math.PI * angka1 * (angka1 + angka2);
                break;
        }
        return hasil;
    }

    private void setColor() {
        card1.setStrokeColor(getResources().getColor(color));
        result.setTextColor(
                color == R.color.green ?
                        getResources().getColorStateList(R.color.darkgreen) :
                        getResources().getColorStateList(color)
        );
        submit.setBackgroundTintList(
                color == R.color.green ?
                        getResources().getColorStateList(R.color.darkgreen) :
                        getResources().getColorStateList(color)
        );
    }

    private void setInput() {
        switch (inputCount) {
            case 1:
                card_input1.setVisibility(View.VISIBLE);
                card_input2.setVisibility(View.GONE);
                card_input3.setVisibility(View.GONE);
                break;
            case 2:
                card_input1.setVisibility(View.VISIBLE);
                card_input2.setVisibility(View.VISIBLE);
                card_input3.setVisibility(View.GONE);
                break;
            case 3:
                card_input1.setVisibility(View.VISIBLE);
                card_input2.setVisibility(View.VISIBLE);
                card_input3.setVisibility(View.VISIBLE);
                break;
            default:
                card_input1.setVisibility(View.GONE);
                card_input2.setVisibility(View.GONE);
                card_input3.setVisibility(View.GONE);
        }
    }

    private void setType() {
        switch (this.type) {
            case "triangle":
                title.setText("Luas Segitiga");
                shape.setImageResource(R.drawable.ic_menu_triangle);
                input1.setHint("Masukan alas (cm)");
                input2.setHint("Masukan tinggi (cm)");
                inputCount = 2;
                break;
            case "rectangle":
                title.setText("Luas Persegi/Panjang");
                shape.setImageResource(R.drawable.ic_menu_rectangle);
                input1.setHint("Masukan panjang (cm)");
                input2.setHint("Masukan lebar (cm)");
                inputCount = 2;
                break;
            case "circle":
                title.setText("Luas Lingkaran");
                shape.setImageResource(R.drawable.ic_menu_circle);
                input1.setHint("Masukan jari-jari (cm)");
                inputCount = 1;
                break;
            case "trapezoid":
                title.setText("Luas Trapesium");
                shape.setImageResource(R.drawable.ic_menu_trapezoid);
                input1.setHint("Masukan sisi atas (cm)");
                input2.setHint("Masukan sisi bawah (cm)");
                input3.setHint("Masukan tinggi (cm)");
                inputCount = 3;
                break;
            case "parallelogram":
                title.setText("Luas Jajar Genjang");
                shape.setImageResource(R.drawable.ic_menu_parallelogram);
                input1.setHint("Masukan alas (cm)");
                input2.setHint("Masukan tinggi (cm)");
                inputCount = 2;
                break;
            case "diamond":
                title.setText("Luas Belah Ketupat");
                shape.setImageResource(R.drawable.ic_menu_diamond);
                input1.setHint("Masukan diagonal 1 (cm)");
                input2.setHint("Masukan diagonal 2 (cm)");
                inputCount = 2;
                break;
            case "prism":
                title.setText("Luas Prisma");
                shape.setImageResource(R.drawable.ic_menu_prism);
                input1.setHint("Masukan luas alas (cm²)");
                input2.setHint("Masukan keliling alas (cm)");
                inputCount = 2;
                break;
            case "cube":
                title.setText("Luas Balok");
                shape.setImageResource(R.drawable.ic_menu_cube);
                input1.setHint("Masukan panjang (cm)");
                input2.setHint("Masukan lebar (cm)");
                input3.setHint("Masukan tinggi (cm)");
                inputCount = 3;
                break;
            case "pyramid":
                title.setText("Luas Limas");
                shape.setImageResource(R.drawable.ic_menu_pyramid);
                input1.setHint("Masukan luas alas (cm²)");
                input2.setHint("Masukan keliling alas (cm)");
                input3.setHint("Masukan tinggi (cm)");
                inputCount = 3;
                break;
            case "tube":
                title.setText("Luas Tabung");
                shape.setImageResource(R.drawable.ic_menu_tube);
                input1.setHint("Masukan jari-jari (cm)");
                input2.setHint("Masukan tinggi (cm)");
                inputCount = 2;
                break;
            case "ball":
                title.setText("Luas Bola");
                shape.setImageResource(R.drawable.ic_menu_ball);
                input1.setHint("Masukan jari-jari (cm)");
                inputCount = 1;
                break;
            case "cone":
                title.setText("Luas Kerucut");
                shape.setImageResource(R.drawable.ic_menu_cone);
                input1.setHint("Masukan jari-jari (cm)");
                input2.setHint("Masukan tinggi (cm)");
                inputCount = 2;
                break;
            default:
                title.setText("Perhitungan tidak diketahui");
                inputCount = 0;
                break;
        }
    }

    private void animateFirstCard() {
        int count = 1;
        Animator.bounceInRecycler(card1, count++);

        for (int i = 0; i < inputCount; i++) {
            switch (i) {
                case 0:
                    Animator.bounceInRecycler(card_input1, count++);
                    break;
                case 1:
                    Animator.bounceInRecycler(card_input2, count++);
                    break;
                case 2:
                    Animator.bounceInRecycler(card_input3, count++);
                    break;
            }
        }
        Animator.bounceInRecycler(submit, count);
    }
}
