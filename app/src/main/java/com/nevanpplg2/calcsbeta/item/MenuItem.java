package com.nevanpplg2.calcsbeta.item;

import android.view.View;

public class MenuItem {
    String name, email;
    int image;
    View.OnClickListener onClickListener;

    public MenuItem(String name, int image, View.OnClickListener onClickListener) {
        this.name = name;
        this.image = image;
        this.onClickListener = onClickListener;
    }

    public MenuItem(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() { return email; }

    public int getImage() {
        return image;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public static MenuItem create(String name, int image, View.OnClickListener onClickListener) {
        return new MenuItem(name, image, onClickListener);
    }

    public static MenuItem create(String name, String email) {
        return new MenuItem(name, email);
    }
}
