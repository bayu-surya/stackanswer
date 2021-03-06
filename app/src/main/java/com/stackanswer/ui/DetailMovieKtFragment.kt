package com.stackanswer.ui

import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.stackanswer.R
import com.stackanswer.core.BuildConfig
import com.stackanswer.core.model.Movie
import com.stackanswer.core.source.Resource
import com.stackanswer.core.source.local.room.moviefavorite.MovieFavorite
import com.stackanswer.core.utils.Constan
import com.stackanswer.core.utils.DataMapper
import com.stackanswer.core.utils.ImageUtils
import com.stackanswer.databinding.FragmentDetailMovieBinding
import com.stackanswer.viewmodel.DetailMovieViewModelKt
import com.stackanswer.viewmodel.MovieFavoriteViewModelKt
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieKtFragment : Fragment(), View.OnClickListener {

    private val viewModel: DetailMovieViewModelKt by viewModel()
    private val movieFavoriteViewModel: MovieFavoriteViewModelKt by viewModel()

    private lateinit var binding: FragmentDetailMovieBinding
    private val TAG = "MainViewModel"
    private var mMovieFavorites: List<MovieFavorite> = ArrayList()
    private var film: Movie? = null
    private var movie: Movie = Movie()
    private var bSave = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentDetailMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivKembali.setOnClickListener(this)
        binding.ivFavorite.setOnClickListener(this)

        movieFavoriteViewModel.tourism.observe(viewLifecycleOwner, { movieFavorites ->
            mMovieFavorites = movieFavorites
            whileShow()
        })

        onStartProggress()
        setup()
    }

    private fun whileShow() {
        if (film == null) {
            while (film == null) {
                Handler(Looper.getMainLooper()).postDelayed({
                    if (film != null) {
                        ivFovorite()
                    }
                }, 800)
            }
        } else {
            ivFovorite()
        }
    }

    private fun ivFovorite() {
        for (i in mMovieFavorites.indices) {
            if (mMovieFavorites[i].id == film?.id) {
                binding.ivFavorite.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red_active), PorterDuff.Mode.MULTIPLY)
                bSave = true
            }
        }
    }

    private fun setup() {
        val arguments = arguments
        if (arguments != null) {
            if (arguments.containsKey(DetailMovieKtFragment::class.java.simpleName)) {
                film = arguments.getParcelable(DetailMovieKtFragment::class.java.simpleName)
                requireArguments().remove(DetailMovieKtFragment::class.java.simpleName)

                viewModel.tourism("" + film?.id).observe(viewLifecycleOwner, { tourism ->
                    if (tourism != null) {
                        when (tourism) {
                            is Resource.Loading -> {
                                Log.d("TAG", "loadFromDB: 0")
                                onStartProggress()
                            }
                            is Resource.Success -> {
                                Log.d("TAG", "loadFromDB: 5")
                                onStopProggress()
                                tourism.data?.let { DataMapper.mapMovieToDomain(it) }?.let {
                                    Log.d(TAG, "setup: a"+it.size)
                                    it.forEachIndexed { index, value ->
                                        Log.d(TAG, "setup: $index")
                                        if (value.id == film?.id) {
                                            if (context!=null) {
                                                setupFilm(value)
                                            }
                                            movie = value
                                        }
                                    }
                                }
                            }
                            is Resource.Error -> {
                                Log.d("TAG", "loadFromDB: 6")
                                onStopProggress()
                            }
                        }
                    }
                })
            }
        }
    }

    private fun setupFilm(movie: Movie) {
        binding.tvDetailUtama.text = movie.overview
        binding.tvJudul.text = movie.title
        binding.tvBahasa.text = Constan.setBahasa(movie.originalLanguage)
        binding.tvTayang.text = movie.releaseDate
        binding.tvRating.text = movie.voteAverage.toString()
        var genre = movie.genreIds?.toString()
        if (genre!=null) {
            if (movie.genreIds.toString().contains("[") || movie.genreIds.toString().contains("]")) {
                genre = genre.replace("\\[".toRegex(), "").replace("]".toRegex(), "")
            }
        }
        binding.tvGenre.text = genre
        if (movie.posterPath != null && movie.posterPath != "" && movie.posterPath != "null") {
            val url = BuildConfig.BASE_URL_IMAGE + movie.posterPath
            ImageUtils.fromUrlWithSize(context, url, binding.ivPoster, 200, 280)
            ImageUtils.fromUrlWithSize(context, url, binding.ivPoster4, 100, 140)
            ImageUtils.fromUrlWithSize(context, url, binding.ivPoster3, 100, 140)
            ImageUtils.fromUrlWithSize(context, url, binding.ivPoster2, 100, 140)
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            if (v.id == R.id.iv_kembali) {
                requireActivity().onBackPressed()
            } else if (v.id == R.id.iv_favorite) {
                if (!bSave) {
                    saveFavorite()
                    bSave = true
                    binding.ivFavorite.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red_active), PorterDuff.Mode.MULTIPLY)
                } else {
                    deleteFavorite()
                    bSave = false
                    binding.ivFavorite.clearColorFilter()
                }
            }
        }
    }

    private fun saveFavorite() {
        val data = DataMapper.mapMovieToMovieDatabase(movie)
        val k = IntArray(mMovieFavorites.size)
        for (m in mMovieFavorites.indices) {
            k[m] = mMovieFavorites[m].id
            if (data.id == mMovieFavorites[m].id) {
                if (!Constan.compareObjects(data, mMovieFavorites[m])) {
                    movieFavoriteViewModel.updateFavoriteTourism(data, true)
                }
            }
        }
        if (!Constan.execute(k, data.id)) {
            movieFavoriteViewModel.setFavoriteTourism(data, true)
        }
    }

    private fun deleteFavorite() {
        val data = DataMapper.mapMovieToMovieDatabase(movie)
        movieFavoriteViewModel.deleteFavoriteTourism(data, true)
    }

    private fun onStartProggress() {
        binding.ivFavorite.visibility = View.GONE
        binding.clBody.visibility = View.GONE
        binding.shimmer.visibility = View.VISIBLE
        binding.shimmer2.visibility = View.VISIBLE
        binding.shimmer3.visibility = View.VISIBLE
        binding.shimmer.startShimmerAnimation()
        binding.shimmer2.startShimmerAnimation()
        binding.shimmer3.startShimmerAnimation()
    }

    private fun onStopProggress() {
        binding.ivFavorite.visibility = View.VISIBLE
        binding.clBody.visibility = View.VISIBLE
        binding.shimmer.visibility = View.GONE
        binding.shimmer2.visibility = View.GONE
        binding.shimmer3.visibility = View.GONE
        binding.shimmer.stopShimmerAnimation()
        binding.shimmer2.stopShimmerAnimation()
        binding.shimmer3.stopShimmerAnimation()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding.cvPoster.removeAllViewsInLayout()
        binding.cvPoster2.removeAllViewsInLayout()
        binding.cvPoster3.removeAllViewsInLayout()
        binding.cvPoster4.removeAllViewsInLayout()
        binding.shimmer.removeAllViewsInLayout()
        binding.shimmer2.removeAllViewsInLayout()
        binding.shimmer3.removeAllViewsInLayout()
        binding.toolbarHome.removeAllViewsInLayout()
        binding.clBody.removeAllViewsInLayout()
        binding.nestedScroll.removeAllViewsInLayout()

        if (view?.parent != null) {
            (view?.parent as ViewGroup).removeView(view)
        }
    }
}