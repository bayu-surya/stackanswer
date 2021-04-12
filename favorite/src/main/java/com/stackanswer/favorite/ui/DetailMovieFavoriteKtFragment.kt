package com.stackanswer.favorite.ui

import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.stackanswer.core.BuildConfig
import com.stackanswer.core.source.local.room.moviefavorite.MovieFavorite
import com.stackanswer.core.utils.Constan
import com.stackanswer.core.utils.ImageUtils
import com.stackanswer.favorite.R
import com.stackanswer.favorite.databinding.FragmentDetailMovieFavoriteBinding
import com.stackanswer.favorite.viewmodel.MovieFavoriteViewModelKt
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieFavoriteKtFragment : Fragment(), View.OnClickListener {

    private val viewModel: MovieFavoriteViewModelKt by viewModel()

    private lateinit var binding: FragmentDetailMovieFavoriteBinding
    private var mMovieFavorite: List<MovieFavorite> = ArrayList()
    private var film: MovieFavorite? =null
    private var bSave = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailMovieFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivKembali.setOnClickListener(this)
        binding.ivFavorite.setOnClickListener(this)

        viewModel.tourism.observe(viewLifecycleOwner, { movieFavorites ->
            mMovieFavorite = movieFavorites
        })

        onStartProggress()
        setup()
    }

    private fun setup() {
        val arguments = arguments
        if (arguments != null) {
            if (arguments.containsKey(DetailMovieFavoriteKtFragment::class.java.simpleName)) {
                film = arguments.getParcelable(DetailMovieFavoriteKtFragment::class.java.simpleName)
                requireArguments().remove(DetailMovieFavoriteKtFragment::class.java.simpleName)
                setupFilm(film)
            }
        }
    }

    private fun setupFilm(movie: MovieFavorite?) {
        binding.ivFavorite.setColorFilter(
            ContextCompat.getColor(
                requireContext(),
                R.color.red_active
            ), PorterDuff.Mode.MULTIPLY
        )
        binding.tvDetailUtama.text = movie?.overview
        binding.tvJudul.text = movie?.title
        binding.tvBahasa.text = movie?.originalLanguage
        binding.tvTayang.text = movie?.releaseDate
        binding.tvRating.text = movie?.voteAverage.toString()
        var genre = movie?.genreIds.toString()
        if (movie?.genreIds.toString().contains("[") || movie?.genreIds.toString().contains("]")) {
            genre = genre.replace("\\[".toRegex(), "").replace("]".toRegex(), "")
        }
        binding.tvGenre.text = genre
        if (movie?.posterPath != null && movie.posterPath != "" && movie.posterPath != "null") {
            val url = BuildConfig.BASE_URL_IMAGE + movie.posterPath
            ImageUtils.fromUrlWithSize(context, url, binding.ivPoster, 200, 280)
            ImageUtils.fromUrlWithSize(context, url, binding.ivPoster4, 100, 140)
            ImageUtils.fromUrlWithSize(context, url, binding.ivPoster3, 100, 140)
            ImageUtils.fromUrlWithSize(context, url, binding.ivPoster2, 100, 140)
        }
        Handler(Looper.getMainLooper()).postDelayed({ onStopProggress() }, 2000)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.iv_kembali) {
            requireActivity().onBackPressed()
        } else if (v.id == R.id.iv_favorite) {
            if (!bSave) {
                saveFavorite()
                bSave = true
                binding.ivFavorite.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.red_active
                    ), PorterDuff.Mode.MULTIPLY
                )
            } else {
                deleteFavorite()
                bSave = false
                binding.ivFavorite.clearColorFilter()
            }
        }
    }

    private fun saveFavorite() {
        if (film != null) {
            val k = IntArray(mMovieFavorite.size)
            for (m in mMovieFavorite.indices) {
                k[m] = mMovieFavorite[m].id
                if (film?.id == mMovieFavorite[m].id) {
                    if (!Constan.compareObjects(film, mMovieFavorite[m])) {
                        viewModel.updateFavoriteTourism(film!!, true)
                    }
                }
            }
            if (film?.id!=null) {
                if (!Constan.execute(k, film!!.id)) {
                    viewModel.setFavoriteTourism(film!!, true)
                }
            }
        }
    }

    private fun deleteFavorite() {
        if (film != null) {
            viewModel.deleteFavoriteTourism(film!!, true)
        }
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