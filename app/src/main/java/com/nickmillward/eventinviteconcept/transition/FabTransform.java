package com.nickmillward.eventinviteconcept.transition;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.transition.ArcMotion;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nmillward on 6/27/16.
 *
 * References:
 * https://github.com/nickbutcher/plaid/blob/master/app/src/main/java/io/plaidapp/ui/transitions/FabTransform.java
 * https://www.google.com/design/spec/motion/transforming-material.html#transforming-material-radial-transformation
 */
public class FabTransform extends Transition {

    private static final String FAB_COLOR = "FAB_COLOR";
    private static final String FAB_ICON_RES_ID = "FAB_ICON_RES_ID";
    private static final long DEFAULT_DURATION = 240L;
    private static final String PROP_BOUNDS = "fabTransform:bounds";
    private static final String[] TRANSITION_PROPERTIES = { PROP_BOUNDS };

    private int color;
    private int icon;

    public FabTransform(@ColorInt int fabColor, @DrawableRes int fabIconResId) {
        color = fabColor;
        icon = fabIconResId;
        setPathMotion(new ArcMotion());
        setDuration(DEFAULT_DURATION);
    }

    /**
     * Equip intent with the necessary extras
     */
    public static void addExtras(@NonNull Intent intent, @ColorInt int fabColor, @DrawableRes int fabIconResId) {
        intent.putExtra(FAB_COLOR, fabColor);
        intent.putExtra(FAB_ICON_RES_ID, fabIconResId);
    }

    /**
     * Take the supplied intent extras and use them as the shared element for the
     * enter / return transition
     */
    public static boolean setup(@NonNull Activity activity,@NonNull View target) {
        final Intent intent = activity.getIntent();
        if (!intent.hasExtra(FAB_COLOR) || (!intent.hasExtra(FAB_ICON_RES_ID))) {
            return false;
        }

        final int color = intent.getIntExtra(FAB_COLOR, Color.TRANSPARENT);
        final int icon = intent.getIntExtra(FAB_ICON_RES_ID, -1);
        final FabTransform sharedEnter = new FabTransform(color, icon);
        if (target != null) {
           sharedEnter.addTarget(target);
        }
        activity.getWindow().setSharedElementEnterTransition(sharedEnter);
        return true;
    }

    @Override
    public String[] getTransitionProperties() {
        return TRANSITION_PROPERTIES;
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    /**
     * Reference: https://github.com/nickbutcher/plaid/blob/master/app/src/main/java/io/plaidapp/ui/transitions/FabTransform.java#L144
     */
    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {

        if (startValues == null || endValues == null) return null;

        final Rect startBounds = (Rect) startValues.values.get(PROP_BOUNDS);
        final Rect endBounds = (Rect) endValues.values.get(PROP_BOUNDS);

        final boolean fromFab = endBounds.width() > startBounds.width();
        final View view = endValues.view;
        final Rect dialogBounds = fromFab ? endBounds : startBounds;
        final Rect fabBounds = fromFab ? startBounds : endBounds;
//        final Interpolator fastOutSlowInInterpolator = ; //Create Interpolator
        final long duration = getDuration();
        final long halfDuration = duration / 2;
        final long twoThirdsDuration = duration * 2 / 3;

//        if (!fromFab) {
//            //
//        }

        final int transitionX = startBounds.centerX() - endBounds.centerX();
        final int transitionY = startBounds.centerY() - endBounds.centerY();
        if (fromFab) {
            view.setTranslationX(transitionX);
            view.setTranslationY(transitionY);
        }

        /**
         * Need to fake the appearance of the FAB element transitioning into the dialog
         */
        // FAB color overlay

        // FAB icon overlay

        // Circular clip to & from the FAB size

        // Translate along an arc path to the end position

        // Fade dialog content in / out

        // Fade FAB color & icon overlays in / out

        // Run all the animations together

        return super.createAnimator(sceneRoot, startValues, endValues);
    }

    private void captureValues(TransitionValues transitionValues) {
        final View view = transitionValues.view;
        if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0) return;

        transitionValues.values.put(PROP_BOUNDS, new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
    }
}
