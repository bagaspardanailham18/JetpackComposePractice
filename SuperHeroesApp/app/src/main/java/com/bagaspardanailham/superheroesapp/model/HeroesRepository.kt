package com.bagaspardanailham.superheroesapp.model

import com.bagaspardanailham.superheroesapp.R

object HeroesRepository {

    val heroes = listOf(
        Hero(
            name = R.string.hero1,
            desc = R.string.description1,
            img = R.drawable.android_superhero1
        ),
        Hero(
            name = R.string.hero2,
            desc = R.string.description2,
            img = R.drawable.android_superhero2
        ),
        Hero(
            name = R.string.hero3,
            desc = R.string.description3,
            img = R.drawable.android_superhero3
        ),
        Hero(
            name = R.string.hero4,
            desc = R.string.description4,
            img = R.drawable.android_superhero4
        ),
        Hero(
            name = R.string.hero5,
            desc = R.string.description5,
            img = R.drawable.android_superhero5
        ),
        Hero(
            name = R.string.hero6,
            desc = R.string.description6,
            img = R.drawable.android_superhero6
        )
    )

}