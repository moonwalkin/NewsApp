package com.example.newsapp

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.presentation.fragments.ArticleFragment
import com.example.newsapp.presentation.fragments.NewsFragment


class MainActivity : AppCompatActivity(), Navigator {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            showNavigateUpButton()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        if (savedInstanceState == null) {
           launch(NewsFragment(), false)
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }


    override fun openArticle(article: ArticleDomain) {
        launch(ArticleFragment.newInstance(article), true)
    }


    override fun popBackStack() {
        supportFragmentManager.popBackStack()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        showNavigateUpButton()
        return true
    }

    private fun showNavigateUpButton() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowCustomEnabled(true)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowCustomEnabled(false)
        }
    }
    private fun launch(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        val container = R.id.fragment_container
        if (addToBackStack) {
            transaction.replace(container, fragment)
                .addToBackStack(null)
        } else {
            transaction.add(container, fragment)
        }
        transaction.commit()
    }

}

