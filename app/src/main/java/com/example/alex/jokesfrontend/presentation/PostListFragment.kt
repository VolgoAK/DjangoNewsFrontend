package com.example.alex.jokesfrontend.presentation


import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alex.jokesfrontend.NewsViewModel
import com.example.alex.jokesfrontend.R
import com.example.alex.jokesfrontend.adapters.JokesAdapter
import com.example.alex.jokesfrontend.domain.model.Post
import kotlinx.android.synthetic.main.fragment_post_list.*
import org.koin.android.architecture.ext.sharedViewModel


/**
 * A simple [Fragment] subclass.
 *
 */
class PostListFragment : Fragment() {

    val viewModel by sharedViewModel<NewsViewModel>()
    lateinit var adapter: JokesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel.getJokes().observe(this, Observer { list -> list?.let { onPostsReady(it) } })
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = JokesAdapter()
        adapter.listener = { viewModel.openJoke(it.id) }
        rvPosts.layoutManager = LinearLayoutManager(activity as Context)
        rvPosts.adapter = adapter
    }

    private fun onPostsReady(posts: MutableList<Post>) {
        adapter.setData(posts)
    }

}
