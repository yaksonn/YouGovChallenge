package com.example.yougovchallenge.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.yougovchallenge.utils.Inflate


abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {

    lateinit var binding: VB

    abstract fun getViewBinding(): VB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareViews()
        listeners()
        observers()
    }

    /**
     * Prepare UI Components here
     */
    open fun prepareViews() {}

    /**
     *  init Listeners Components
     */
    open fun listeners() {}

    /**
     * init Observers
     */
    open fun observers() {}
}

