package com.example.alex.jokesfrontend.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Vote(val id: Int, val votes: Int) : Parcelable