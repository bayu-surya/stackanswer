package com.stackanswer.core.retrofit

data class ListMovieResponse(
		val page: Int? = null,
		val results: List<ResultsItem?>? = null
)

data class ResultsItem(
		val overview: String? = null,
		val originalLanguage: String? = null,
		val originalTitle: String? = null,
		val video: Boolean? = null,
		val title: String? = null,
		val genreIds: List<Int?>? = null,
		val posterPath: String? = null,
		val backdropPath: String? = null,
		val releaseDate: String? = null,
		val popularity: Double? = null,
		val voteAverage: Double? = null,
		val id: Int? = null,
		val adult: Boolean? = null,
		val voteCount: Int? = null
)

//data class ResultsItem(
//	val overview:  String,
//	val originalLanguage:  String,
//	val originalTitle:  String,
//	val video: Boolean,
//	val title:  String,
//	val genreIds: List<Int>,
//	val posterPath:  String,
//	val backdropPath:  String,
//	val releaseDate:  String,
//	val popularity: Double,
//	val voteAverage: Double,
//	val id: Int,
//	val adult: Boolean,
//	val voteCount: Int,
//)
