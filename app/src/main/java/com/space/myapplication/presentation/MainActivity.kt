package com.space.myapplication.presentation

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import com.space.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.navHostFragment.setOnApplyWindowInsetsListener { view, insets ->
            var consumed = false

            (view as ViewGroup).forEach { child ->
                // Dispatch the insets to the child*
                val childResult = child.dispatchApplyWindowInsets(insets)
                // If the child consumed the insets, record it
                if (childResult.isConsumed) {
                    consumed = true
                }
            }

            // If any of the children consumed the insets, return
            // an appropriate value*
            if (consumed) insets.consumeSystemWindowInsets() else insets
        }
    }
}