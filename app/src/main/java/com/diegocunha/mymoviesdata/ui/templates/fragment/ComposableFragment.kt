package com.diegocunha.mymoviesdata.ui.templates.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.diegocunha.mymoviesdata.R
import com.diegocunha.mymoviesdata.databinding.FragmentComposableBinding
import com.diegocunha.mymoviesdata.ui.templates.binding.ViewBindingHolder
import com.diegocunha.mymoviesdata.ui.templates.binding.ViewBindingHolderImpl
import com.diegocunha.mymoviesdata.ui.theme.MovieAppTheme

abstract class ComposableFragment : BaseFragment(R.layout.fragment_composable),
    ViewBindingHolder<FragmentComposableBinding> by ViewBindingHolderImpl() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initBinding(
            FragmentComposableBinding.inflate(inflater, container, false),
            this
        ) {
            composeView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    MovieAppTheme {
                        ComposableContent()
                    }
                }
            }
        }
    }

    /**
     * Emits the ui content inside of a [MovieAppTheme]
     */
    @Composable
    abstract fun ComposableContent()
}