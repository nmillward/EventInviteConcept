package com.nickmillward.eventinviteconcept.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nickmillward.eventinviteconcept.R;
import com.nickmillward.eventinviteconcept.util.ImageLoader;

/**
 * Created by nmillward on 6/10/16.
 */
public class AvatarListAdapter extends RecyclerView.Adapter<AvatarListAdapter.AvatarViewHolder> {

    private ImageLoader imageLoader;

    private LayoutInflater inflater;

    @Override
    public AvatarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_avatar_invite, parent, false);

        AvatarViewHolder holder = new AvatarViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final AvatarViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class AvatarViewHolder extends RecyclerView.ViewHolder {

        ImageView avatarImage;
        TextView avatarName;

        public AvatarViewHolder(View itemView) {
            super(itemView);

            avatarImage = (ImageView) itemView.findViewById(R.id.iv_avatar_item);
            avatarName = (TextView) itemView.findViewById(R.id.tv_avatar_item);
        }
    }
}
