package com.nickmillward.eventinviteconcept.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.nickmillward.eventinviteconcept.R;
import com.nickmillward.eventinviteconcept.entity.Avatar;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nmillward on 6/10/16.
 */
public class AvatarListAdapter extends RecyclerView.Adapter<AvatarListAdapter.AvatarViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Avatar item);
    }

    private final List<Avatar> avatars;
    private final OnItemClickListener listener;
    private int lastPosition = -1;

    public AvatarListAdapter(List<Avatar> avatars, OnItemClickListener listener) {
        this.avatars = avatars;
        this.listener = listener;
    }

    @Override
    public AvatarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_avatar_invite, parent, false);
        return new AvatarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AvatarViewHolder holder, int position) {
//        final Avatar avatar = avatars.get(position);
//        imageLoader.loadWithTransformation(holder.avatarImage, avatar.getAvatarImage(), new CircleTransform());
//        holder.avatarName.setText(avatar.getAvatarName());

        holder.bind(avatars.get(position), listener);

        //Set view to fade in
        setFadeAnimation(holder.itemView, position);
//        setScaleAnimation(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return avatars.size();
    }

    static class AvatarViewHolder extends RecyclerView.ViewHolder {

        private ImageView avatarImage;
        private TextView avatarName;

        public AvatarViewHolder(View itemView) {
            super(itemView);
            avatarImage = (ImageView) itemView.findViewById(R.id.iv_avatar_item);
            avatarName = (TextView) itemView.findViewById(R.id.tv_avatar_item);
        }

        public void bind(final Avatar avatar, final OnItemClickListener listener) {
            avatarName.setText(avatar.getAvatarName());
//            imageLoader.loadWithTransformation(avatarImage, avatar.getAvatarImage(), new CircleTransform());
            Picasso.with(itemView.getContext()).load(avatar.getAvatarImage()).into(avatarImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(avatar);
                }
            });
        }
    }

    private void setFadeAnimation(View view, int position) {
        if (position > lastPosition) {
            AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(1000);
            view.startAnimation(anim);
            lastPosition = position;
        }
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(300);
        view.startAnimation(anim);
    }
}
