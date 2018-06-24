package com.example.alex.jokesfrontend.presentation

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.alex.jokesfrontend.NewsViewModel
import com.example.alex.jokesfrontend.R
import org.koin.android.architecture.ext.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel by viewModel<NewsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.commandsLiveData.observe(this, Observer {
            when (it) {
                NewsViewModel.Commands.START_POST_FRAGMENT -> openPost(it.id)
            }
        })

        if (supportFragmentManager.findFragmentById(R.id.containerMain) == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.containerMain, PostListFragment())
                    .commit()
        }
    }

    private fun openPost(id: Int) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.containerMain, SinglePostFragment.newInstance(id))
                .addToBackStack(null)
                .commit()
    }
}
