package com.diegocunha.mymoviesdata.ui.templates.fragment

import androidx.compose.runtime.Composable
import com.diegocunha.mymoviesdata.R
import com.diegocunha.mymoviesdata.databinding.FragmentComposableBinding
import com.diegocunha.mymoviesdata.ui.templates.binding.ViewBindingHolder
import com.diegocunha.mymoviesdata.ui.templates.binding.ViewBindingHolderImpl
import com.diegocunha.mymoviesdata.ui.theme.MovieAppTheme

abstract class ComposableFragment : BaseFragment(R.layout.fragment_composable),
    ViewBindingHolder<FragmentComposableBinding> by ViewBindingHolderImpl() {

    /**
     * Emits the ui content inside of a [MovieAppTheme]
     */
    @Composable
    abstract fun ComposableContent()
}