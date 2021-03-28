package com.stackanswer.source.local.room.show

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "showpopular")
data class ShowPopular(

        @PrimaryKey
        var id: Int,
        var firstAirDate: String,
        var overview: String,
        var originalLanguage: String,
        var genreIds: String,
        var posterPath: String,
        var originCountry: String,
        var backdropPath: String,
        var originalName: String,
        var popularity: Double,
        var voteAverage: Double,
        var name: String,
        var voteCount: Int,
)