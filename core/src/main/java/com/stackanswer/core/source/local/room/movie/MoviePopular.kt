package com.stackanswer.core.source.local.room.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moviepopular")
data class MoviePopular (

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