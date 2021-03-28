package com.stackanswer.utils.kotlin

import com.stackanswer.model.Movie
import com.stackanswer.model.Show
import com.stackanswer.retrofit.ResultsItem
import com.stackanswer.retrofit.ResultsShowItem
import com.stackanswer.retrofit.detailmoviekt.DetailMovieResponse
import com.stackanswer.retrofit.detailshowkt.DetailShowResponse
import com.stackanswer.source.local.room.movie.MoviePopular
import com.stackanswer.source.local.room.show.ShowPopular
import com.stackanswer.source.local.room.showfavorite.ShowFavorite

object DataMapper {

    fun mapDetailShowResponsesToEntities(input: DetailShowResponse): ShowPopular {
        val data = arrayListOf<String>()
        for (i in input.genres!!.indices) {
            data.add(input.genres[i]!!.name!!)
        }
        return ShowPopular(
                id = input.id ?: 0,
                firstAirDate = input.firstAirDate ?: "",
                overview = input.overview ?: "",
                originalLanguage = input.originalLanguage ?: "",
                genreIds =  data.toString(),
                posterPath = input.posterPath ?: "",
                originCountry = input.originCountry.toString(),
                backdropPath = input.backdropPath ?: "",
                originalName = input.originalName ?: "",
                popularity = input.popularity ?: 0.0,
                voteAverage = input.voteAverage ?: 0.0,
                name = input.name ?: "",
                voteCount = input.voteCount ?: 0
        )
    }

    fun mapDetailMovieResponsesToEntities(input: DetailMovieResponse): MoviePopular {
        val data = arrayListOf<String>()
        for (i in input.genres!!.indices) {
            data.add(input.genres[i]!!.name!!)
        }
        return MoviePopular(
                id = input.id ?: 0,
                overview = input.overview ?: "",
                originalLanguage = input.originalLanguage ?: "",
                originalTitle = input.originalTitle ?: "",
                video = input.video ?: false,
                title = input.title ?: "",
                genreIds = data.toString(),
                posterPath = input.posterPath ?: "",
                backdropPath = input.backdropPath ?: "",
                releaseDate = input.releaseDate ?: "",
                popularity = input.popularity ?: 0.0,
                voteAverage = input.voteAverage ?: 0.0,
                adult = input.adult ?: false,
                voteCount = input.voteCount ?: 0
        )
    }

    fun mapShowResponsesToEntities(input: List<ResultsShowItem>): List<ShowPopular> {
        val tourismList = ArrayList<ShowPopular>()
        if (input.isNotEmpty()) {
            input.map {
                val movieItem = ShowPopular(
                        id = it.id ?: 0,
                        firstAirDate = it.firstAirDate ?: "",
                        overview = it.overview ?: "",
                        originalLanguage = it.originalLanguage ?: "",
                        genreIds = it.genreIds.toString(),
                        posterPath = it.posterPath ?: "",
                        originCountry = it.originCountry.toString(),
                        backdropPath = it.backdropPath ?: "",
                        originalName = it.originalName ?: "",
                        popularity = it.popularity ?: 0.0,
                        voteAverage = it.voteAverage ?: 0.0,
                        name = it.name ?: "",
                        voteCount = it.voteCount ?: 0
                )
                tourismList.add(movieItem)
            }
        }
        return tourismList
    }

    fun mapShowToDomain(input: List<ShowPopular>): List<Show> {
        val tourismList = ArrayList<Show>()
        input.map {
            val movieItem = Show()

            movieItem.firstAirDate=it.firstAirDate
            movieItem.overview=it.overview
            movieItem.originalLanguage=it.originalLanguage
            movieItem.posterPath=it.posterPath
            movieItem.backdropPath=it.backdropPath
            movieItem.originalName=it.originalName
            movieItem.popularity=it.popularity
            movieItem.voteAverage=it.voteAverage
            movieItem.id=it.id
            movieItem.name=it.name
            movieItem.voteCount=it.voteCount

            var genre: String = it.genreIds
            if (it.genreIds.contains("[") || it.genreIds.contains("]")) {
                genre = genre.replace("\\[".toRegex(), "").replace("]".toRegex(), "")
            }
            if(genre.contains(",")){
                val array = genre.split(",")
                val data = arrayListOf<String>()
                array.forEachIndexed { _, value ->
                    data.add(value)
                }
                movieItem.genreIds = data
            } else {
                val data = mutableListOf(genre)
                movieItem.genreIds = data
            }
            var country: String = it.originCountry
            if (country.contains("[") || country.contains("]")) {
                country = country.replace("\\[".toRegex(), "").replace("]".toRegex(), "")
            }
            if(country.contains(",")){
                val array = country.split(",")
                val data = arrayListOf<String>()
                array.forEachIndexed { _, value ->
                    data.add(value)
                }
                movieItem.originCountry = data
            } else {
                val data = mutableListOf(country)
                movieItem.originCountry=data
            }

            tourismList.add(movieItem)
        }
        return tourismList
    }
    fun mapResponsesToEntities(input: List<ResultsItem>): List<MoviePopular> {
        val tourismList = ArrayList<MoviePopular>()
        if (input.isNotEmpty()) {
            input.map {
                val movieItem = MoviePopular(
//                    id = it.id,
//                    overview = it.overview,
//                    originalLanguage = if(it.originalLanguage!=null) it.originalLanguage else "",
//                    originalTitle = it.originalTitle,
//                    video = it.video,
//                    title = it.title,
//                    genreIds = it.title,
//                    posterPath = it.posterPath,
//                    backdropPath = it.backdropPath,
//                    releaseDate = it.releaseDate,
//                    popularity = it.popularity,
//                    voteAverage = it.voteAverage,
//                    adult = it.adult,
//                    voteCount = it.voteCount

                        id = it.id ?: 0,
                        overview = it.overview ?: "",
                        originalLanguage = it.originalLanguage ?: "",
                        originalTitle = it.originalTitle ?: "",
                        video = it.video ?: false,
                        title = it.title ?: "",
                        genreIds = it.genreIds.toString(),
                        posterPath = it.posterPath ?: "",
                        backdropPath = it.backdropPath ?: "",
                        releaseDate = it.releaseDate ?: "",
                        popularity = it.popularity ?: 0.0,
                        voteAverage = it.voteAverage ?: 0.0,
                        adult = it.adult ?: false,
                        voteCount = it.voteCount ?: 0
                )
                tourismList.add(movieItem)
            }
        }
        return tourismList
    }

    fun mapMovieToDomain(input: List<MoviePopular>): List<Movie> {
        val tourismList = ArrayList<Movie>()
        input.map {
            val movieItem = Movie()

            movieItem.overview=it.overview
            movieItem.originalLanguage=it.originalLanguage
            movieItem.originalTitle=it.originalTitle
            movieItem.isVideo=it.video
            movieItem.title=it.title
            movieItem.posterPath=it.posterPath
            movieItem.backdropPath=it.backdropPath
            movieItem.releaseDate=it.releaseDate
            movieItem.popularity=it.popularity
            movieItem.voteAverage=it.voteAverage
            movieItem.id=it.id
            movieItem.isAdult=it.adult
            movieItem.voteCount=it.voteCount

            var genre: String = it.genreIds
            if (it.genreIds.contains("[") || it.genreIds.contains("]")) {
                genre = genre.replace("\\[".toRegex(), "").replace("]".toRegex(), "")
            }
            if(genre.contains(",")){
                val array = genre.split(",")
                val data = arrayListOf<String>()
                array.forEachIndexed { _, value ->
                    data.add(value)
                }
                movieItem.genreIds = data
            } else {
                val data = mutableListOf(genre)
                movieItem.genreIds = data
            }

            tourismList.add(movieItem)
        }
        return tourismList
    }

    fun mapMovieToMovieDatabase(input: Movie): com.stackanswer.source.local.room.moviefavorite.MovieFavorite {
        val movie = com.stackanswer.source.local.room.moviefavorite.MovieFavorite()
        movie.id = input.id
        movie.overview = input.overview
        movie.originalLanguage = input.originalLanguage
        movie.originalTitle = input.originalTitle
        movie.isVideo = input.isVideo
        movie.title = input.title
        movie.genreIds = input.genreIds.toString()
        movie.posterPath = input.posterPath
        movie.backdropPath = input.backdropPath
        movie.releaseDate = input.releaseDate
        movie.popularity = input.popularity
        movie.voteAverage = input.voteAverage
        movie.isAdult = input.isAdult
        movie.voteCount = input.voteCount
        return movie
    }

    fun mapShowToShowDatabase(input: Show): ShowFavorite {
        val show = ShowFavorite()
        show.id = input.id
        show.overview = input.overview
        show.originalLanguage = input.originalLanguage
        show.firstAirDate = input.firstAirDate
        show.originCountry = input.originCountry.toString()
        show.originalName = input.originalName
        show.genreIds = input.genreIds.toString()
        show.posterPath = input.posterPath
        show.backdropPath = input.backdropPath
        show.popularity = input.popularity
        show.voteAverage = input.voteAverage
        show.name = input.name
        show.voteCount = input.voteCount

        return show
    }

//    fun mapEntitiesToDomain(input: List<MoviePopular>): List<MoviePopular> =
//            input.map {
//                MoviePopular(
//                        tourismId = it.tourismId,
//                        description = it.description,
//                        name = it.name,
//                        address = it.address,
//                        latitude = it.latitude,
//                        longitude = it.longitude,
//                        like = it.like,
//                        image = it.image,
//                        isFavorite = it.isFavorite
//                )
//            }

//    fun mapDomainToEntity(input: Tourism) = MoviePopular(
//            tourismId = input.tourismId,
//            description = input.description,
//            name = input.name,
//            address = input.address,
//            latitude = input.latitude,
//            longitude = input.longitude,
//            like = input.like,
//            image = input.image,
//            isFavorite = input.isFavorite
//    )
}