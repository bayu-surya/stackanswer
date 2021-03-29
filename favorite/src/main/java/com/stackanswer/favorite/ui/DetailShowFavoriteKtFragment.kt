package com.stackanswer.favorite.ui

import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.stackanswer.core.BuildConfig
import com.stackanswer.core.source.local.room.showfavorite.ShowFavorite
import com.stackanswer.core.utils.Constan
import com.stackanswer.core.utils.ImageUtils
import com.stackanswer.favorite.R
import com.stackanswer.favorite.viewmodel.ShowFavoriteViewModelKt
import com.stackanswer.favorite.databinding.FragmentDetailShowFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailShowFavoriteKtFragment : Fragment(), View.OnClickListener {

    private var binding: FragmentDetailShowFavoriteBinding? = null
    private var mShowFavorite: List<ShowFavorite>? = null
    private var film: ShowFavorite? = null
    private var bSave = true
//    private var viewModel: ShowFavoriteViewModel? = null
//    private var viewModel: ShowFavoriteViewModelKt? = null

    private val viewModel: ShowFavoriteViewModelKt by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailShowFavoriteBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.ivKembali.setOnClickListener(this)
        binding!!.ivFavorite.setOnClickListener(this)

//        val factory2 = ShowFavoriteViewModelFactory.getInstance(context)
//        viewModel = ViewModelProvider(this, factory2).get(ShowFavoriteViewModel::class.java)
//        viewModel!!.allShow.observe(
//            viewLifecycleOwner,
//            { favoriteList: PagedList<ShowFavorite>? ->
//                mShowFavorite = favoriteList
//            })

//        val factory = ShowFavoriteViewModelFactory.getInstance(requireActivity())
//        viewModel = ViewModelProvider(this, factory)[ShowFavoriteViewModelKt::class.java]

        viewModel.tourism.observe(viewLifecycleOwner, { movieFavorites ->
            mShowFavorite = movieFavorites
        })

        onStartProggress()
        setup()
    }

    private fun setup() {
        val arguments = arguments
        if (arguments != null) {
            if (arguments.containsKey(DetailShowFavoriteKtFragment::class.java.simpleName)) {
                film = arguments.getParcelable(DetailShowFavoriteKtFragment::class.java.simpleName)
                requireArguments().remove(DetailShowFavoriteKtFragment::class.java.simpleName)
                setupFilm(film)
            }
        }
    }

    private fun setupFilm(show: ShowFavorite?) {
        binding!!.ivFavorite.setColorFilter(
            ContextCompat.getColor(
                requireContext(),
                R.color.red_active
            ), PorterDuff.Mode.MULTIPLY
        )
        binding!!.tvDetailUtama.text = show!!.overview
        binding!!.tvJudul.text = show.name
        binding!!.tvBahasa.text = show.originalLanguage
        binding!!.tvTayang.text = show.firstAirDate
        binding!!.tvRating.text = show.voteAverage.toString()
        var genre = show.genreIds.toString()
        if (show.genreIds.toString().contains("[") || show.genreIds.toString().contains("]")) {
            genre = genre.replace("\\[".toRegex(), "").replace("]".toRegex(), "")
        }
        binding!!.tvGenre.text = genre
        if (show.posterPath != null && show.posterPath != "" && show.posterPath != "null") {
            val url = BuildConfig.BASE_URL_IMAGE + show.posterPath
            ImageUtils.fromUrlWithSize(context, url, binding!!.ivPoster, 200, 280)
            ImageUtils.fromUrlWithSize(context, url, binding!!.ivPoster4, 100, 140)
            ImageUtils.fromUrlWithSize(context, url, binding!!.ivPoster3, 100, 140)
            ImageUtils.fromUrlWithSize(context, url, binding!!.ivPoster2, 100, 140)
        }
        Handler().postDelayed({ onStopProggress() }, 2000)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.iv_kembali) {
            requireActivity().onBackPressed()
        } else if (v.id == R.id.iv_favorite) {
            if (!bSave) {
                saveFavorite()
                bSave = true
                binding!!.ivFavorite.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.red_active
                    ), PorterDuff.Mode.MULTIPLY
                )
            } else {
                deleteFavorite()
                bSave = false
                binding!!.ivFavorite.clearColorFilter()
            }
        }
    }

    private fun saveFavorite() {
        if (film != null) {
            val k = IntArray(mShowFavorite!!.size)
            for (m in mShowFavorite!!.indices) {
                k[m] = mShowFavorite!![m].id
                if (film!!.id == mShowFavorite!![m].id) {
                    if (!Constan.compareObjects(film, mShowFavorite!![m])) {
                        viewModel.updateFavoriteTourism(film!!, true)
                    }
                }
            }
            if (!Constan.execute(k, film!!.id)) {
                viewModel.setFavoriteTourism(film!!, true)
            }
        }
    }

    private fun deleteFavorite() {
        if (film != null) {
            viewModel.deleteFavoriteTourism(film!!, true)
        }
    }

    private fun onStartProggress() {
        binding!!.ivFavorite.visibility = View.GONE
        binding!!.clBody.visibility = View.GONE
        binding!!.shimmer.visibility = View.VISIBLE
        binding!!.shimmer2.visibility = View.VISIBLE
        binding!!.shimmer3.visibility = View.VISIBLE
        binding!!.shimmer.startShimmerAnimation()
        binding!!.shimmer2.startShimmerAnimation()
        binding!!.shimmer3.startShimmerAnimation()
    }

    private fun onStopProggress() {
        binding!!.ivFavorite.visibility = View.VISIBLE
        binding!!.clBody.visibility = View.VISIBLE
        binding!!.shimmer.visibility = View.GONE
        binding!!.shimmer2.visibility = View.GONE
        binding!!.shimmer3.visibility = View.GONE
        binding!!.shimmer.stopShimmerAnimation()
        binding!!.shimmer2.stopShimmerAnimation()
        binding!!.shimmer3.stopShimmerAnimation()
    }
}