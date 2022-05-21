package com.diegocunha.mymoviesdata.ui.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

inline fun <reified T : Fragment> FragmentManager.find(@IdRes id: Int): T? {
    return findFragmentById(id) as T?
}