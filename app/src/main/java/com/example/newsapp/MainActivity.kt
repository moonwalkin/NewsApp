package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.newsapp.domain.models.ArticleDomain


class MainActivity : AppCompatActivity(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
           launch(NewsFragment(), false)
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

    override fun openArticle(article: ArticleDomain) {
        launch(ArticleFragment.newInstance(article), true)
    }
}