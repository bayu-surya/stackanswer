package com.stackanswer.core.source.local.room.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moviepopular")
data class MoviePopular (

//    @PrimaryKey
//    private val id = 0
//    private val overview: String? = null
//    private val originalLanguage: String? = null
//    private val originalTitle: String? = null
//    private val video = false
//    private val title: String? = null
//    private val genreIds: String? = null
//    private val posterPath: String? = null
//    private val backdropPath: String? = null
//    private val releaseDate: String? = null
//    private val popularity = 0.0
//    private val voteAverage = 0.0
//    private val adult = false
//    private val voteCount = 0

        @PrimaryKey
        var id: Int,
        var overview:  String,
        var originalLanguage:  String,
        var originalTitle:  String,
        var video: Boolean = false,
        var title:  String,
        var genreIds:  String,
        var posterPath:  String,
        var backdropPath:  String,
        var releaseDate:  String,
        var popularity: Double,
        var voteAverage: Double,
        var adult: Boolean = false,
        var voteCount: Int,
)