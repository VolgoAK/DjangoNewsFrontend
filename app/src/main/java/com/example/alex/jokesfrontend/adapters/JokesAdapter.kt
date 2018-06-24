package com.example.alex.jokesfrontend.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.alex.jokesfrontend.R
import com.example.alex.jokesfrontend.domain.model.Post

class JokesAdapter : RecyclerView.Adapter<JokesAdapter.Holder>() {

    var listener: ((Post) -> Unit)? = null

    private var data = mutableListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context)
                .inflate(R.layout.holder_post, parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(mutableList: MutableList<Post>) {
        data = mutableList
        notifyDataSetChanged()
    }

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvBody: TextView = view.findViewById(R.id.tvJokeContent)
        val tvVotes: TextView = view.findViewById(R.id.tvVotes)

        init {
            view.setOnClickListener {
                listener?.let {
                    it.invoke(data[adapterPosition])
                }
            }
        }

        fun bind(post: Post) {
            tvBody.text = post.body
            tvVotes.text = post.votes.toString()
        }
    }
}