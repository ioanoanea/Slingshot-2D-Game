package com.ioanoanea.slingshot.Animation;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.ioanoanea.slingshot.R;

public class ViewAnimator {

    private final Context context;
    public static final int DURATION = 150;
    public static final int SLIDE_DOWN = R.anim.slide_down;
    public static final int BOUNCE = R.anim.bounce;
    public static final int BLINK = R.anim.blink;
    public static final int SEQUENTIAL = R.anim.sequential;
    public static final int BOUNCE_BALL = R.anim.bounce_ball;

    public ViewAnimator(Context context){
        this.context = context;
    }

    /**
     * Animate a view
     * @param view - view object
     * @param animationResource - animation resource
     */
    public void animate(final View view, int animationResource){
        Animation animation = AnimationUtils.loadAnimation(context, animationResource);
        animation.setDuration(DURATION);
        view.startAnimation(animation);
    }

    /**
     * Animate a view
     * @param view - view object
     * @param animationResource - animation resource
     * @param startOffset - start offset time
     */
    public void animate(final View view, int animationResource, int startOffset){
        Animation animation = AnimationUtils.loadAnimation(context, animationResource);
        animation.setDuration(DURATION);
        animation.setStartOffset(startOffset);
        view.startAnimation(animation);
    }
}
