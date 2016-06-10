package com.nickmillward.eventinviteconcept;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EventDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout inviteOverlay;
    private FloatingActionButton fab;
    private ImageView fabIcon;
    private Button buttonCheck;

    private boolean isInviteOverlayVisible;
    private boolean isFabBgVisible;
    private boolean isAvatarSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.event_detail_toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.event_detail_fab);
        isFabBgVisible = true;

        buttonCheck = (Button) findViewById(R.id.btn_check);
        isAvatarSelected = false;

        if (fab != null) {
            fab.setOnClickListener(this);
        }

        if (buttonCheck != null) {
            buttonCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isAvatarSelected) {
                        fab.setImageResource(R.drawable.avd_cross_to_check);
                        isAvatarSelected = true;
                    } else {
                        fab.setImageResource(R.drawable.avd_check_to_cross);
                        isAvatarSelected = false;
                    }

                    // Animate the AVD
                    Drawable drawable = fab.getDrawable();
                    if (drawable instanceof Animatable) {
                        ((Animatable) drawable).start();
                    }
                }
            });
        }

        //Enable Layout Transitions on Coordinator Layout
//        CoordinatorLayout fabContainer = (CoordinatorLayout) findViewById(R.id.event_detail_coordinator_layout);
//        LayoutTransition layoutTransition = fabContainer.getLayoutTransition();
//        layoutTransition.enableTransitionType(LayoutTransition.CHANGING);

//        animateFabPosition(fab);

        isInviteOverlayVisible = false;
        inviteOverlay = (LinearLayout) findViewById(R.id.event_detail_invite_overlay);
        if (inviteOverlay != null) {
            inviteOverlay.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.event_detail_fab:

                // If invite overlay is NOT visible, show FAB with PLUS
                // Fab onClick = reveal overlay, change FAB icon to CROSS
                if (!isInviteOverlayVisible) {
                    revealInviteOverlay(inviteOverlay);
                    fab.setImageResource(R.drawable.avd_plus_to_cross);
                    fab.setCompatElevation(0);

//                    animateFabPosition(fab);

                }

                // If invite overlay is visible AND Avatar is NOT selected, show FAB with CROSS
                // Fab onClick = hide overlay, change FAB icon to PLUS
                else {
                    fab.setImageResource(R.drawable.avd_cross_to_plus);
                    fab.setCompatElevation(4);

                    if (!isAvatarSelected) {
                        hideInviteOverlay(inviteOverlay);
                    } else {
                        Toast.makeText(this, "Invite Sent!", Toast.LENGTH_SHORT).show();
                        hideInviteOverlay(inviteOverlay);
                        isAvatarSelected = false;
                    }

//                    animateFabPosition(fab);
                }

                // Animate the AVD
                Drawable drawable = fab.getDrawable();
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }


        }
    }

    private void animateFabPosition(FloatingActionButton fab) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();

        if (!isFabBgVisible) {
            params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;

//            Animation rotate = new RotateAnimation(0, 45, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//            rotate.setFillBefore(true);
//            rotate.setFillAfter(true);
//            rotate.setFillEnabled(true);
//            rotate.setDuration(450);
//            rotate.setInterpolator(new FastOutSlowInInterpolator());
//            fab.setAnimation(rotate);

            isFabBgVisible = true;
        } else {
            params.gravity = Gravity.BOTTOM | Gravity.END;

//            Animation rotate = new RotateAnimation(45, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//            rotate.setFillBefore(true);
//            rotate.setFillAfter(true);
//            rotate.setFillEnabled(true);
//            rotate.setDuration(450);
//            rotate.setInterpolator(new FastOutSlowInInterpolator());
//            fab.setAnimation(rotate);

            isFabBgVisible = false;
        }

        fab.setLayoutParams(params);
    }

    private void revealInviteOverlay(View view) {
        int cx = view.getRight();
        int cy = view.getBottom();
        int finalRadius = Math.max(view.getWidth(), view.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
        view.setVisibility(View.VISIBLE);
        isInviteOverlayVisible = true;
        anim.start();
    }

    private void hideInviteOverlay(final View view) {
        int cx = view.getRight();
        int cy = view.getBottom();
        int initialRadius = view.getWidth();

        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, initialRadius, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });
        isInviteOverlayVisible = false;
        anim.start();
    }
}
