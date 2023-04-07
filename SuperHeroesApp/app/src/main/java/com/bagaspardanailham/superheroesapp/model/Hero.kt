package com.bagaspardanailham.superheroesapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Hero(
    @StringRes val name: Int,
    @StringRes val desc: Int,
    @DrawableRes val img: Int
)
