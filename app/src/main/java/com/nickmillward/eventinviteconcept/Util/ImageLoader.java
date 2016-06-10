package com.nickmillward.eventinviteconcept.util;

import android.widget.ImageView;

import com.squareup.picasso.Transformation;

/**
 * Created by nmillward on 6/9/16.
 */
public interface ImageLoader {

    void load(ImageView view, String url);

    void loadWithTransformation(ImageView view, String url, Transformation transformation);
}
