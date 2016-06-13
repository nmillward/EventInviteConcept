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
import com.nickmillward.eventinviteconcept.util.CircleTransform;
import com.nickmillward.eventinviteconcept.util.ImageLoader;

import java.util.List;

/**
 * Created by nmillward on 6/10/16.
 */
public class AvatarListAdapter extends RecyclerView.Adapter<AvatarListAdapter.AvatarViewHolder> {

    private ImageLoader imageLoader;
    private List<Avatar> avatars;

    public AvatarListAdapter(List<Avatar> avatars, ImageLoader imageLoader) {
        this.avatars = avatars;
        this.imageLoader = imageLoader;
    }

    @Override
    public AvatarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_avatar_invite, parent, false);
        return new AvatarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AvatarViewHolder holder, int position) {
        final Avatar avatar = avatars.get(position);
        imageLoader.loadWithTransformation(holder.avatarImage, avatar.getAvatarImage(), new CircleTransform());
        holder.avatarName.setText(avatar.getAvatarName());

        //Set view to fade in
        setFadeAnimation(holder.itemView);
//        setScaleAnimation(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return avatars.size();
    }

    public class AvatarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView avatarImage;
        TextView avatarName;

        public AvatarViewHolder(View itemView) {
            super(itemView);

            avatarImage = (ImageView) itemView.findViewById(R.id.iv_avatar_item);
            avatarName = (TextView) itemView.findViewById(R.id.tv_avatar_item);
        }

        @Override
        public void onClick(View view) {

        }
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(300);
        view.startAnimation(anim);
    }
}
