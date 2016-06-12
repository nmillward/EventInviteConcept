package com.nickmillward.eventinviteconcept.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nickmillward.eventinviteconcept.R;
import com.nickmillward.eventinviteconcept.entity.Avatar;
import com.nickmillward.eventinviteconcept.util.CircleTransform;
import com.nickmillward.eventinviteconcept.util.ImageLoader;

import java.util.List;

/**
 * Created by nmillward on 6/10/16.
 */
public class AvatarListAdapter extends RecyclerView.Adapter<AvatarListAdapter.AvatarViewHolder> {

    private LayoutInflater inflater;
    private ImageLoader imageLoader;
    private List<Avatar> avatars;

    public AvatarListAdapter(List<Avatar> avatars, ImageLoader imageLoader) {
        this.avatars = avatars;
        this.imageLoader = imageLoader;
    }

    @Override
    public AvatarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_avatar_invite, parent, false);

        AvatarViewHolder holder = new AvatarViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final AvatarViewHolder holder, int position) {
        final Avatar avatar = avatars.get(position);
        imageLoader.loadWithTransformation(holder.avatarImage, avatar.getAvatarImage(), new CircleTransform());
        holder.avatarName.setText(avatar.getAvatarName());
    }

    @Override
    public int getItemCount() {
        return avatars.size();
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
