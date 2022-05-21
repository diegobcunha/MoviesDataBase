package com.diegocunha.mymoviesdata.ui.templates.activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.annotation.NavigationRes
import androidx.annotation.StyleRes
import com.diegocunha.mymoviesdata.R

data class NavigableParams(
    val from: Activity,
    @NavigationRes val graphId: Int,
    val transitionType: ActivityTransitionType,
    @StyleRes val themeResId: Int = R.style.Theme_MyMoviesData,
    val startView: View? = null,
    val block: (() -> Bundle)? = null
)
