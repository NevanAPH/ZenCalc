package com.nevanpplg2.calcsbeta.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nevanpplg2.calcsbeta.R;
import com.nevanpplg2.calcsbeta.adapter.ProfileAdapter;
import com.nevanpplg2.calcsbeta.item.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ProfilFragment extends Fragment {

    SharedPreferences preferences;
    RecyclerView recyclerView;
    List<MenuItem> selection = new ArrayList<>();
    VoidMethod voidMethod;

    public ProfilFragment(VoidMethod voidMethod) {
        this.voidMethod = voidMethod;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferences = this.requireActivity().getSharedPreferences("calcs", MODE_PRIVATE);
        addSelection();

        recyclerView = view.findViewById(R.id.contentList);
        recyclerView.setAdapter(new ProfileAdapter(this.getContext(), selection, getResources().getColor(R.color.red)));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.list_fragment, container, false);
    }

    public void addSelection() {
        selection.add(MenuItem.create(preferences.getString("name", ""), preferences.getString("email", "")));
        selection.add(MenuItem.create("Keluar/Sign out", R.drawable.ic_menu_logout, v -> this.voidMethod.call()));
        selection.add(MenuItem.create("By Nevan 10 PPLG 2", R.drawable.ic_rocket, v -> startActivity(new Intent(Intent.ACTION_VIEW, android.net.Uri.parse("https://realzzy.xyz/")))));
    }
}