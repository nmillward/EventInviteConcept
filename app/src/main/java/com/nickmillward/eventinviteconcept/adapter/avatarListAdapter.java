package com.nickmillward.eventinviteconcept.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nmillward on 6/10/16.
 */
public class AvatarListAdapter extends RecyclerView.Adapter<AvatarListAdapter.AvatarViewHolder> {

    @Override
    public AvatarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(final AvatarViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class AvatarViewHolder extends RecyclerView.ViewHolder {

        public AvatarViewHolder(View itemView) {
            super(itemView);
        }
    }
}
