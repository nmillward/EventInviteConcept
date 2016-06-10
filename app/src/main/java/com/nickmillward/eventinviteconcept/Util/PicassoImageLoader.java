package com.nickmillward.eventinviteconcept.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by nmillward on 6/9/16.
 */
public class PicassoImageLoader implements ImageLoader {

    private Picasso picasso;

    public PicassoImageLoader(Context context) {
        this.picasso = Picasso.with(context);
    }

    @Override
    public void load(ImageView view, String url) {
        picasso.load(url).into(view);
    }

    @Override
    public void loadWithTransformation(ImageView view, String url, Transformation transformation) {
        picasso.load(url).transform(transformation).into(view);
    }
}
