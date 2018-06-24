package com.example.alex.jokesfrontend.presentation


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alex.jokesfrontend.BuildConfig
import com.example.alex.jokesfrontend.NewsViewModel

import com.example.alex.jokesfrontend.R
import com.example.alex.jokesfrontend.domain.model.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_single_post.*
import org.koin.android.architecture.ext.sharedViewModel


class SinglePostFragment : Fragment() {

    companion object {
        private const val EXTRA_JOKE_ID = "extra_id"

        fun newInstance(id: Int): SinglePostFragment {
            return SinglePostFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA_JOKE_ID, id)
                }
            }
        }
    }

    val viewModel by sharedViewModel<NewsViewModel>()
    val jokeId by lazy { arguments?.getInt(EXTRA_JOKE_ID) ?: -1 }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel.getJoke(jokeId).observe(this, Observer { it?.let { onJokeReady(it) } })

        return inflater.inflate(R.layout.fragment_single_post, container, false)
    }


    private fun onJokeReady(post: Post) {
        tvPostBody.text = post.body

        if(post.image.isNotBlank()) {
            val imageLink = "${BuildConfig.SERVER_URL}/media/${post.image}"
            Picasso.get()
                    .load(imageLink)
                    .fit()
                    .centerCrop()
                    .into(ivPostTitle)
        }
    }
}
