package com.example.newsapp.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.newsapp.Navigator
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.domain.entities.ArticleDomain
import com.example.newsapp.presentation.fragments.ArticleFragment
import com.example.newsapp.presentation.fragments.NewsFragment
import com.example.newsapp.presentation.fragments.SavedNewsFragment
import com.example.newsapp.presentation.fragments.SearchFragment


class MainActivity : AppCompatActivity(), Navigator {
    private lateinit var binding: ActivityMainBinding
    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            showNavigateUpButton()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        setSupportActionBar(binding.toolbar)
        if (savedInstanceState == null) {
           launch(NewsFragment(), false)
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }


    override fun openArticlePage(article: ArticleDomain) {
        launch(ArticleFragment.newInstance(article), true)
    }


    override fun popBackStack() {
        supportFragmentManager.popBackStack()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
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
                .addToBackStack(fragment.javaClass.name)
        } else {
            transaction.add(container, fragment)
        }
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                launch(SearchFragment(), true)
                return true
            }
            R.id.favorite -> {
                launch(SavedNewsFragment(), true)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

}

