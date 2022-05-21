package com.diegocunha.mymoviesdata.ui.templates.fragment

import android.app.Activity
import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes layoutRes: Int = 0): Fragment(layoutRes) {

    final override fun onAttach(context: Context) {
        super.onAttach(context)
        onAttachContext(context)
    }

    @Suppress("DEPRECATION", "OverridingDeprecatedMember")
    final override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        onAttachContext(activity)
    }

    open fun onAttachContext(context: Context?) {}
}