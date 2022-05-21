package com.diegocunha.mymoviesdata.ui.templates.activity

import android.content.res.Resources
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.diegocunha.mymoviesdata.R
import com.diegocunha.mymoviesdata.ui.extensions.find
import com.diegocunha.mymoviesdata.ui.extensions.getIntOrThrow

/**
 * A base activity without a toolbar to be used with [NavHostFragment]. It should be used to ease navigation
 * between a flow with fragments. In case there is navigation and you don't want to handle
 * the toolbar yourself, use [ToolbarNavigationActivity]. In case there is only one fragment and there is no navigation
 * you should extend [SingleFragmentActivity].
 */
open class NavigationActivity(@LayoutRes open val contentViewId: Int = R.layout.activity_navigation) : BaseActivity() {

    open lateinit var navigationController: NavController
    protected open val navigationGraphId: Int by lazy { intent.getIntOrThrow(Navigable.GRAPH_ID_KEY) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewId)
        supportFragmentManager.find<NavHostFragment>(R.id.fragmentContainer)?.let {
            setupNavigation(it)
        }
    }

    override fun getTheme(): Resources.Theme {
        val theme = super.getTheme()
        val themeStyle = intent.getIntExtra(Navigable.THEME_KEY, 0)
        if (themeStyle != 0) {
            theme.applyStyle(themeStyle, true)
        }
        return theme
    }

    open fun setupNavigation(navHostFragment: NavHostFragment) {
        navigationController = navHostFragment.navController.apply {
            val graph = navInflater.inflate(navigationGraphId)
            getStartDestination()?.let { startDestination ->
                graph.setStartDestination(startDestination)
            }

            setGraph(graph, getInitialArguments())
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (!(navigationController.navigateUp() || super.onSupportNavigateUp())) {
            onBackPressed()
        }
        return true
    }

    @IdRes
    open fun getStartDestination(): Int? = null

    open fun getInitialArguments(): Bundle? = intent.extras

    companion object : Navigable<NavigationActivity> {
        override val classType: Class<NavigationActivity>
            get() = NavigationActivity::class.java
    }
}