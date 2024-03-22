package com.nevanpplg2.calcsbeta;

import android.view.View;

public class Animator {

    public static void fadeIn(View v) {
        v.setAlpha(0f);
        v.animate()
                .alpha(1f)
                .setDuration(300)
                .start();
    }

    public static void fadeInLeft(View v) {
        v.setTranslationX(-20);
        v.setAlpha(0f);
        v.animate()
                .translationX(0)
                .alpha(1f)
                .setInterpolator(new android.view.animation.OvershootInterpolator(2f))
                .setDuration(500)
                .start();
    }

    public static void bounceOnce(View v) {
        v.setScaleX(1.03f);
        v.setScaleY(1.03f);
        v.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(30)
                .start();
    }

    public static void bounceInLogin(View v, int position) {
        v.setTranslationY(20);
        v.setAlpha(0f);
        v.animate()
                .alpha(1f)
                .translationY(0)
                .setDuration(500)
                .setInterpolator(new android.view.animation.OvershootInterpolator(2f))
                .setStartDelay(100L*position)
                .start();
    }

    public static void bounceOutLogin(View v, int position) {
        v.animate()
                .alpha(0f)
                .translationY(20)
                .setDuration(300)
                .setStartDelay(100L*position)
                .start();
    }

    public static void bounceInUp(View v) {
        v.setTranslationY(50);
        v.setAlpha(0f);
        v.animate()
                .translationY(0)
                .alpha(1f)
                .setDuration(500)
                .setInterpolator(new android.view.animation.OvershootInterpolator(1.5f))
                .setStartDelay(500)
                .start();
    }

    public static void bounceInUp2(View v) {
        v.setTranslationY(50);
        v.setAlpha(0f);
        v.animate()
                .translationY(0)
                .alpha(1f)
                .setDuration(400)
                .setInterpolator(new android.view.animation.OvershootInterpolator(1.5f))
                .setStartDelay(100)
                .start();
    }

    public static void bounceInDown(View v) {
        v.setTranslationY(-50);
        v.setAlpha(0f);
        v.animate()
                .translationY(0)
                .alpha(1f)
                .setDuration(500)
                .setInterpolator(new android.view.animation.OvershootInterpolator(1.5f))
                .setStartDelay(500).start();
    }

    public static void bounceInRecycler(View v, int position) {
        v.setTranslationY(20);
        v.setAlpha(0f);

        // create task to delay animation by 100*position
        v.animate()
                .translationY(0)
                .alpha(1f)
                .setDuration(500)
                .setInterpolator(new android.view.animation.OvershootInterpolator(3f))
                .setStartDelay(100L * position).start();
    }

    public static void bounceOutUp(View v) {
        v.animate()
                .translationY(-50)
                .alpha(0f)
                .setDuration(400)
                .setInterpolator(new android.view.animation.OvershootInterpolator(1.5f))
                .start();
    }

    public static void bounceOutDown(View v) {
        v.animate()
                .translationY(50)
                .alpha(0f)
                .setDuration(400)
                .setInterpolator(new android.view.animation.OvershootInterpolator(1.5f))
                .setStartDelay(500)
                .start();
    }

    public static void bounceOutDown2(View v) {
        v.animate()
                .translationY(50)
                .alpha(0f)
                .setDuration(400)
                .setInterpolator(new android.view.animation.OvershootInterpolator(1.5f))
                .setStartDelay(100)
                .start();
    }

    public static void fadeOut(View v) {
        v.animate()
                .alpha(0f)
                .setDuration(300)
                .start();
    }

    public static void fadeOutLeft(View v) {
        v.animate()
                .translationX(-20)
                .alpha(0f)
                .setInterpolator(new android.view.animation.OvershootInterpolator(2f))
                .setDuration(500)
                .start();
    }
}
