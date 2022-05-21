package com.diegocunha.mymoviesdata.ui.extensions

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.Gravity
import android.view.View
import com.diegocunha.mymoviesdata.ui.templates.activity.ActivityTransitionType
import com.diegocunha.mymoviesdata.ui.templates.activity.BaseActivity
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.google.android.material.transition.platform.MaterialElevationScale
import com.google.android.material.transition.platform.MaterialFadeThrough
import com.google.android.material.transition.platform.SlideDistanceProvider

private const val DEFAULT_ANIM_DURATION = 300L

fun Activity.startActivityWithTransition(
    intent: Intent,
    transitionType: ActivityTransitionType,
    startView: View? = null
) {

    val bundle = if (transitionType == ActivityTransitionType.CONTAINER_TRANSFORM) {
        ActivityOptions.makeSceneTransitionAnimation(
            this,
            startView,
            BaseActivity.ACTIVITY_ROOT_VIEW_TRANSITION_NAME
        ).toBundle()
    } else {
        ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
    }

    intent.putExtra(BaseActivity.ACTIVITY_TRANSITION_TYPE, transitionType.ordinal)
    startActivity(intent, bundle)
}

fun Activity.setupContainerTransformEnterAnimation(rootViewtransitionName: String) {
    window.decorView.rootView.transitionName = rootViewtransitionName
    setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())

    window.sharedElementEnterTransition = MaterialContainerTransform().apply {
        addTarget(window.decorView.rootView)
        duration = DEFAULT_ANIM_DURATION
    }
    window.sharedElementReturnTransition = MaterialContainerTransform().apply {
        addTarget(window.decorView.rootView)
        duration = DEFAULT_ANIM_DURATION
    }
}

fun Activity.setupElevationScaleEnterAnimation() {
    window.enterTransition = MaterialElevationScale(true)
    window.allowEnterTransitionOverlap = true
}

fun Activity.setupFadeThroughEnterAnimation() {
    window.enterTransition = MaterialFadeThrough().apply {
        secondaryAnimatorProvider = SlideDistanceProvider(Gravity.BOTTOM)
    }
    window.allowEnterTransitionOverlap = true
}