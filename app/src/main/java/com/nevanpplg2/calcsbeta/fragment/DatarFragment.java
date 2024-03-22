package com.nevanpplg2.calcsbeta.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nevanpplg2.calcsbeta.MainActivity;
import com.nevanpplg2.calcsbeta.R;
import com.nevanpplg2.calcsbeta.activities.CalcActivity;
import com.nevanpplg2.calcsbeta.adapter.RecyclerAdapter;
import com.nevanpplg2.calcsbeta.item.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DatarFragment extends Fragment {

    RecyclerView recyclerView;
    List<MenuItem> selection = new ArrayList<>();
    CalcMethod calcMethod;

    public DatarFragment(CalcMethod calcMethod) {
        this.calcMethod = calcMethod;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addSelection();

        recyclerView = view.findViewById(R.id.contentList);
        recyclerView.setAdapter(new RecyclerAdapter(this.requireContext(), selection, getResources().getColor(R.color.green)));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    private void openCalc(String type) {
        calcMethod.call(type, R.color.green);
    }

    public void addSelection() {
        selection.add(MenuItem.create("Luas Segitiga", R.drawable.ic_menu_triangle, v -> openCalc("triangle")));
        selection.add(MenuItem.create("Luas Persegi/Panjang", R.drawable.ic_menu_rectangle, v -> openCalc("rectangle")));
        selection.add(MenuItem.create("Luas Lingkaran", R.drawable.ic_menu_circle, v -> openCalc("circle")));
        selection.add(MenuItem.create("Luas Trapesium", R.drawable.ic_menu_trapezoid, v -> openCalc("trapezoid")));
        selection.add(MenuItem.create("Luas Jajar Genjang", R.drawable.ic_menu_parallelogram, v -> openCalc("parallelogram")));
        selection.add(MenuItem.create("Luas Belah Ketupat", R.drawable.ic_menu_diamond, v -> openCalc("diamond")));
    }
}