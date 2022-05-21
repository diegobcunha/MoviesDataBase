package com.diegocunha.mymoviesdata.ui.templates.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.diegocunha.mymoviesdata.ui.extensions.getEnumExtra
import com.diegocunha.mymoviesdata.ui.extensions.setupContainerTransformEnterAnimation
import com.diegocunha.mymoviesdata.ui.extensions.setupElevationScaleEnterAnimation
import com.diegocunha.mymoviesdata.ui.extensions.setupFadeThroughEnterAnimation

class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        setupEnterAnimationIfNeeded()
    }

    override fun setContentView(view: View?) {
        super.setContentView(view)
        setupEnterAnimationIfNeeded()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onHomeClicked()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onHomeClicked() {
        onBackPressed()
    }

    private fun setupEnterAnimationIfNeeded() {
        when (intent?.getEnumExtra<ActivityTransitionType>(ACTIVITY_TRANSITION_TYPE)) {
            ActivityTransitionType.CONTAINER_TRANSFORM -> {
                setupContainerTransformEnterAnimation(ACTIVITY_ROOT_VIEW_TRANSITION_NAME)
            }
            ActivityTransitionType.ELEVATION_SCALE -> {
                setupElevationScaleEnterAnimation()
            }
            ActivityTransitionType.FADE_THROUGH -> {
                setupFadeThroughEnterAnimation()
            }
        }
    }

    companion object {
        const val ACTIVITY_TRANSITION_TYPE = "ACTIVITY_TRANSITION_TYPE"
        const val ACTIVITY_ROOT_VIEW_TRANSITION_NAME = "ACTIVITY_ROOT_VIEW_TRANSITION_NAME"
    }
}