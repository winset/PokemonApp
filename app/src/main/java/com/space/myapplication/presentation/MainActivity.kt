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
        insetsListener()
    }

    private fun insetsListener() {
        binding.navHostFragment.setOnApplyWindowInsetsListener { view, insets ->
            var consumed = false
            (view as ViewGroup).forEach { child ->
                val childResult = child.dispatchApplyWindowInsets(insets)
                if (childResult.isConsumed) consumed = true
            }
            if (consumed) insets.consumeSystemWindowInsets() else insets
        }
    }
}