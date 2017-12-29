package com.xyfindables.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xyfindables.ui.R;

public class XYSplashDialog extends Dialog {
    public XYSplashDialog(Context context) {
        super(context, R.style.xy_full_screen_dialog);
        setContentView(R.layout.dialog_splash);
        startAnimation();
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
    }

    private void animate(@NonNull final View v, final int animResource) {
        Animation anim = AnimationUtils.loadAnimation(getContext(), animResource);
        v.setAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener() {

            Animation nextAnim;

            @Override
            public void onAnimationStart(Animation animation) {

                nextAnim = AnimationUtils.loadAnimation(getContext(), animResource);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                nextAnim.setAnimationListener(this);
                v.startAnimation(nextAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

        });
    }

    public void startAnimation() {
        RelativeLayout still_splash_layout = findViewById(R.id.still_splash_layout);
        still_splash_layout.setVisibility(View.INVISIBLE);

        RelativeLayout animation_splash_layout = findViewById(R.id.animation_splash_layout);
        animation_splash_layout.setVisibility(View.VISIBLE);

        ImageView outerRing = findViewById(R.id.splash_ring2);
        outerRing.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.full_rotate_reverse_infinite));

        ImageView middleRing = findViewById(R.id.splash_ring1);
        middleRing.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.full_rotate_infinite));

        ImageView signalLarge = findViewById(R.id.splash_signal1);

        ImageView signalLargeTwo = findViewById(R.id.splash_signal0);

        ImageView signalLargeThree = findViewById(R.id.splash_signal2);

        ImageView signalSmall = findViewById(R.id.splash_signal_small);

        animate(signalLarge, R.animator.scale_outer);
        animate(signalLargeTwo, R.animator.scale_outer2);
        animate(signalLargeThree, R.animator.scale_outer_reverse);
        animate(signalSmall, R.animator.scale_inner);
    }
}
