package com.example.alex.jokesfrontend

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.alex.jokesfrontend.domain.NewsService
import com.example.alex.jokesfrontend.domain.model.Post
import com.example.alex.jokesfrontend.extensions.ioAndMain
import com.example.alex.jokesfrontend.utils.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber


class NewsViewModel(app: Application, val api: NewsService) : AndroidViewModel(app) {

    val commandsLiveData = SingleLiveEvent<Commands>()
    private val jokesLiveData = MutableLiveData<MutableList<Post>>()
    private val jokeLiveData = MutableLiveData<Post>()

    val disposable = CompositeDisposable()

    fun getJokes(): LiveData<MutableList<Post>> {
        if (jokesLiveData.value == null) updateJokes()
        return jokesLiveData
    }

    fun updateJokes() {
        api.getAllPosts()
                .ioAndMain()
                .subscribe({ list -> jokesLiveData.value = list },
                        { error -> Timber.e(error) })
                .apply { disposable.add(this) }
    }

    fun getJoke(id: Int): LiveData<Post> {
        if (jokeLiveData.value == null || jokeLiveData.value?.id != id) {
            api.getPostById(id)
                    .ioAndMain()
                    .subscribe({ joke -> jokeLiveData.value = joke },
                            { error -> Timber.e(error) })
                    .apply { disposable.add(this) }
        }

        return jokeLiveData
    }

    fun openJoke(id: Int) {
        commandsLiveData.value = Commands.START_POST_FRAGMENT.apply { this.id = id }
    }

    enum class Commands {
        START_POST_FRAGMENT;

        var id: Int = 0
    }

}