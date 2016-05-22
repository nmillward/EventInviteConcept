package com.nickmillward.eventinviteconcept;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.LinearLayout;

public class EventDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout inviteOverlay;

    private boolean isInviteVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.event_detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.event_detail_fab);
        if (fab != null) {
            fab.setOnClickListener(this);
        }

        isInviteVisible = false;
        inviteOverlay = (LinearLayout) findViewById(R.id.event_detail_invite_overlay);
        if (inviteOverlay != null) {
            inviteOverlay.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.event_detail_fab:
                if (!isInviteVisible) {
                    revealInviteOverlay(inviteOverlay);
                } else {
                    hideInviteOverlay(inviteOverlay);
                }
        }

    }

    private void revealInviteOverlay(View view) {
        int cx = view.getRight();
        int cy = view.getBottom();
        int finalRadius = Math.max(view.getWidth(), view.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
        view.setVisibility(View.VISIBLE);
        isInviteVisible = true;
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
        isInviteVisible = false;
        anim.start();
    }
}
