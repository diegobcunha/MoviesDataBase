package com.diegocunha.mymoviesdata.ui.templates.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.annotation.StyleRes
import com.diegocunha.mymoviesdata.R
import com.diegocunha.mymoviesdata.ui.extensions.startActivityWithTransition

/**
 * Base interface for activities that use NavigationComponent.
 * The [T] and [classType] are just a workaround so we can use the type dynamically as a parameter
 * for the intent.
 */
interface Navigable<T : Activity> {

    val classType: Class<T>

    fun launch(
        from: Context,
        @NavigationRes graphId: Int,
        @StyleRes themeResId: Int = R.style.Theme_MyMoviesData,
        block: (() -> Bundle)? = null
    ) {
        val bundle = createBundleExtra(graphId, themeResId, block)
        val intent = Intent(from, classType)
        intent.putExtras(bundle)
        from.startActivity(intent)
    }

    fun launchWithTransition(params: NavigableParams) {
        with(params) {
            val bundle = createBundleExtra(graphId, themeResId, block)
            val intent = Intent(from, classType)
            intent.putExtras(bundle)
            from.startActivityWithTransition(intent, transitionType, startView)
        }
    }

    private fun createBundleExtra(
        @NavigationRes graphId: Int,
        @StyleRes themeResId: Int = R.style.Theme_MyMoviesData,
        block: (() -> Bundle)? = null
    ): Bundle {
        return (block?.invoke() ?: Bundle()).apply {
            putInt(THEME_KEY, themeResId)
            putInt(GRAPH_ID_KEY, graphId)
        }
    }

    companion object {
        const val GRAPH_ID_KEY = "GRAPH_ID_KEY"
        const val THEME_KEY = "THEME_KEY"
    }
}