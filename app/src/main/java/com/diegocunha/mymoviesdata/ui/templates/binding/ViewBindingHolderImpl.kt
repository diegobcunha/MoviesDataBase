package com.diegocunha.mymoviesdata.ui.templates.binding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding

/**
 *  The binding object is accessible in two ways:
 *  1 - Using the binding property which is nullable is the safest option
 *  2 - Using the requireBinding() which is not nullable
 *  and must be called between the onCreateView and onDestroyView lifecycle
 *
 *  Usage:
 *
 *  class ExampleFragment : Fragment(), ViewBindingHolder<FragmentExampleBinding> by ViewBindingHolderImpl() {
 *
 *       override fun onCreateView(
 *           inflater: LayoutInflater,
 *           container: ViewGroup?,
 *           savedInstanceState: Bundle?
 *       ): View? {
 *              return initBinding(FragmentExampleBinding.inflate(layoutInflater, container, false), this)
 *       }
 *   }
 *
 */
class ViewBindingHolderImpl<T : ViewBinding> : ViewBindingHolder<T>, LifecycleObserver {

    override var binding: T? = null
    var lifecycle: Lifecycle? = null

    private lateinit var fragmentName: String

    /**
     * To not leak memory we nullify the binding when the view is destroyed.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroyView() {
        lifecycle?.removeObserver(this)
        lifecycle = null
        binding = null
    }

    override fun requireBinding(block: (T.() -> Unit)?) =
        binding?.apply {
            block?.invoke(this)
        } ?: throw IllegalStateException(
            "Accessing binding outside of Fragment lifecycle: $fragmentName"
        )

    override fun initBinding(binding: T, fragment: Fragment, onBound: (T.() -> Unit)?): View {
        this.binding = binding
        lifecycle = fragment.viewLifecycleOwner.lifecycle
        lifecycle?.addObserver(this)
        fragmentName = fragment::class.java.name
        onBound?.invoke(binding)
        return binding.root
    }
}
