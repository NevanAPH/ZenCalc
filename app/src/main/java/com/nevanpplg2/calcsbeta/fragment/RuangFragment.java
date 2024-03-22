package com.nevanpplg2.calcsbeta.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nevanpplg2.calcsbeta.R;
import com.nevanpplg2.calcsbeta.activities.CalcActivity;
import com.nevanpplg2.calcsbeta.adapter.RecyclerAdapter;
import com.nevanpplg2.calcsbeta.item.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class RuangFragment extends Fragment {

    RecyclerView recyclerView;
    List<MenuItem> selection = new ArrayList<>();
    CalcMethod calcMethod;

    public RuangFragment(CalcMethod calcMethod) {
        this.calcMethod = calcMethod;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addSelection();

        recyclerView = view.findViewById(R.id.contentList);
        recyclerView.setAdapter(new RecyclerAdapter(this.requireContext(), selection, getResources().getColor(R.color.purple)));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    private void openCalc(String type) {
        calcMethod.call(type, R.color.purple);
    }

    public void addSelection() {
        selection.add(MenuItem.create("Luas Prisma", R.drawable.ic_menu_prism, v -> openCalc("prism")));
        selection.add(MenuItem.create("Luas Balok", R.drawable.ic_menu_cube, v -> openCalc("cube")));
        selection.add(MenuItem.create("Luas Limas", R.drawable.ic_menu_pyramid, v -> openCalc("pyramid")));
        selection.add(MenuItem.create("Luas Tabung", R.drawable.ic_menu_tube, v -> openCalc("tube")));
        selection.add(MenuItem.create("Luas Bola", R.drawable.ic_menu_ball, v -> openCalc("ball")));
        selection.add(MenuItem.create("Luas Kerucut", R.drawable.ic_menu_cone, v -> openCalc("cone")));
    }
}