package com.diegocunha.mymoviesdata.ui.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis

private const val DEFAULT_ANIM_DURATION = 300L


/**
 * Use this function into the navigation function of the FragmentA to FragmentB to create a Shared Axis Transition
 *
 * @param destination navigation directions of FragmentB
 * @param animDuration duration of animation transition. It should be the same value of the Fragment B
 * @param axis The Axis of transition. Should be: MaterialSharedAxis.X, MaterialSharedAxis.Y or MaterialSharedAxis.Z
 */
private fun Fragment.navigateWithSharedAxis(
    destination: NavDirections,
    @MaterialSharedAxis.Axis axis: Int,
    animDuration: Long = DEFAULT_ANIM_DURATION
) {
    exitTransition = MaterialSharedAxis(axis, true).apply {
        duration = animDuration
    }
    reenterTransition = MaterialSharedAxis(axis, false).apply {
        duration = animDuration
    }
    findNavController().navigate(destination)
}

/**
 * Use this function into the navigation function of the FragmentA to FragmentB to create a Shared Axis X Transition
 *
 * @param destination navigation directions of FragmentB
 * @param animDuration duration of animation transition. It should be the same value of the Fragment B
 */
fun Fragment.navigateWithSharedAxisX(
    destination: NavDirections,
    animDuration: Long = DEFAULT_ANIM_DURATION
) {
    navigateWithSharedAxis(destination, MaterialSharedAxis.X, animDuration)
}

/**
 * Use this function into the navigation function of the FragmentA to FragmentB to create a Shared Axis Z Transition
 *
 * @param destination navigation directions of FragmentB
 * @param animDuration duration of animation transition. It should be the same value of the Fragment B
 */
fun Fragment.navigateWithSharedAxisZ(
    destination: NavDirections,
    animDuration: Long = DEFAULT_ANIM_DURATION
) {
    navigateWithSharedAxis(destination, MaterialSharedAxis.Z, animDuration)
}

/**
 * Use this function into the navigation function of the FragmentA to FragmentB to create a Shared Axis Z Transition
 *
 * @param destination navigation directions of FragmentB
 * @param animDuration duration of animation transition. It should be the same value of the Fragment B
 */
fun Fragment.navigateWithSharedAxisZ(
    @IdRes destination: Int,
    bundle: Bundle? = null,
    animDuration: Long = DEFAULT_ANIM_DURATION
) {
    exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
        duration = animDuration
    }
    reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
        duration = animDuration
    }
    findNavController().navigate(destination, bundle)
}

/**
 * Use this function at onCreate of the FragmentB to create a Shared Axis Transition with Fragment A
 *
 * @param animDuration duration of animation transition. It should be the same value of the Fragment A
 * @param axis The Axis of transition. Should be: MaterialSharedAxis.X, MaterialSharedAxis.Y or MaterialSharedAxis.Z
 */
fun Fragment.setSharedAxisEnterTransition(
    @MaterialSharedAxis.Axis axis: Int,
    animDuration: Long = DEFAULT_ANIM_DURATION
) {
    enterTransition = MaterialSharedAxis(axis, true).apply {
        duration = animDuration
    }
    returnTransition = MaterialSharedAxis(axis, false).apply {
        duration = animDuration
    }
}

/**
 * Use this function into the navigation function of the FragmentA to FragmentB to create a Fade Through Transition
 *
 * @param destination navigation directions of FragmentB
 * @param animDuration duration of animation transition. It should be the same value of the Fragment B
 */
fun Fragment.navigateWithFadeThrough(
    destination: NavDirections,
    animDuration: Long = DEFAULT_ANIM_DURATION
) {
    exitTransition = MaterialFadeThrough().apply {
        duration = animDuration
    }

    reenterTransition = MaterialFadeThrough().apply {
        duration = animDuration
    }

    findNavController().navigate(destination)
}

/**
 * Use this function into the navigation function of the FragmentA to FragmentB to create a Fade Through Transition
 *
 * @param destination navigation directions of FragmentB
 * @param animDuration duration of animation transition. It should be the same value of the Fragment B
 */
fun Fragment.navigateWithFadeThrough(
    @IdRes destination: Int,
    bundle: Bundle? = null,
    animDuration: Long = DEFAULT_ANIM_DURATION
) {
    exitTransition = MaterialFadeThrough().apply {
        duration = animDuration
    }

    reenterTransition = MaterialFadeThrough().apply {
        duration = animDuration
    }

    findNavController().navigate(destination, bundle)
}

/**
 * Use this function at onCreate of the Fragment to create a Fade Through enter Transition
 *
 * @param animDuration duration of animation transition. It should be the same value of the Fragment A
 */
fun Fragment.setFadeThroughEnterTransition(animDuration: Long = DEFAULT_ANIM_DURATION) {
    enterTransition = MaterialFadeThrough().apply {
        duration = animDuration
    }
}