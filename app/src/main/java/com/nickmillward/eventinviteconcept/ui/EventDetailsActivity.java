package com.nickmillward.eventinviteconcept.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.ColorStateList;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nickmillward.eventinviteconcept.R;
import com.nickmillward.eventinviteconcept.adapter.AvatarListAdapter;
import com.nickmillward.eventinviteconcept.entity.Avatar;
import com.nickmillward.eventinviteconcept.model.AvatarUserData;
import com.nickmillward.eventinviteconcept.util.ImageLoader;
import com.nickmillward.eventinviteconcept.util.PicassoImageLoader;

import java.util.ArrayList;
import java.util.List;

public class EventDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private ImageView bannerImage;
    private LinearLayout avatarInviteOverlay;
    private RecyclerView avatarRecyclerView;
    private AvatarListAdapter avatarListAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<Avatar> avatars;
    private AvatarUserData avatarUserData;

    private ImageLoader imageLoader;

    private boolean isInviteOverlayVisible;
    private boolean isFabCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_event);

        bindActivity();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Holiday Party 2016");

        imageLoader = new PicassoImageLoader(this);

        imageLoader.load(bannerImage, "https://dl.dropboxusercontent.com/u/7862484/nyc.jpeg");

        if (fab != null) {
            fab.setOnClickListener(this);
        }

        isInviteOverlayVisible = false;
        if (avatarInviteOverlay != null) {
            avatarInviteOverlay.setVisibility(View.INVISIBLE);
        }

        avatarUserData = new AvatarUserData();

        if (avatars == null) {
            avatars = new ArrayList<>();
            avatars.addAll(avatarUserData.getAvatars());
        }

        avatarListAdapter = new AvatarListAdapter(avatars, new AvatarListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int item) {

                toggleAvatarSelection(item);
                toggleFabIcon(avatarListAdapter.getSelectedItemCount());

            }
        });
        avatarRecyclerView.setAdapter(avatarListAdapter);

        gridLayoutManager = new GridLayoutManager(this, 4);
        avatarRecyclerView.setLayoutManager(gridLayoutManager);
        avatarRecyclerView.setHasFixedSize(true);

    }

    private void bindActivity() {
        toolbar = (Toolbar) findViewById(R.id.event_detail_toolbar);
        bannerImage = (ImageView) findViewById(R.id.event_detail_parallax_image);
        fab = (FloatingActionButton) findViewById(R.id.event_detail_fab);
        avatarInviteOverlay = (LinearLayout) findViewById(R.id.event_detail_invite_overlay);
        avatarRecyclerView = (RecyclerView) findViewById(R.id.rv_avatar_invite);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.event_detail_fab:
                if (!isInviteOverlayVisible) {
                    revealInviteOverlay(avatarInviteOverlay);
                    fab.setImageResource(R.drawable.avd_plus_to_cross);
                } else {
                    fab.setImageResource(R.drawable.avd_cross_to_plus);
                    if (avatarListAdapter.getSelectedItemCount() == 0) {
                        hideInviteOverlay(avatarInviteOverlay);
                    } else {
                        hideInviteOverlay(avatarInviteOverlay);
                        Snackbar.make(findViewById(R.id.event_detail_coordinator_layout), "Invite Sent!", Snackbar.LENGTH_LONG).show();
                        avatarListAdapter.clearSelection();
                        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                        isFabCheck = false;
                    }
                }
                // Animate the AVD
                Drawable drawable = fab.getDrawable();
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }
        }
    }

    /**
     * Displays / Hides drawable for selected recyclerView item
     * @param position - The recyclerView item position that was clicked
     */
    private void toggleAvatarSelection(int position) {
        avatarListAdapter.toggleSelection(position);
    }

    /**
     * Handle Fab Animated Vector Drawable logic
     * @param selectedItemCount - Check if any recyclerView items are selected. Assign FAB icon accordingly
     */
    private void toggleFabIcon(int selectedItemCount) {
        if (selectedItemCount < 1) {
            fab.setImageResource(R.drawable.avd_check_to_cross);
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
            isFabCheck = false;
        } else if (selectedItemCount == 1 && !isFabCheck) {
            fab.setImageResource(R.drawable.avd_cross_to_check);
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccentGreen)));
        } else {
            fab.setImageResource(R.drawable.ic_check);
            isFabCheck = true;
        }

        Drawable drawable = fab.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
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
