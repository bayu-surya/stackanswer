package com.stackanswer.ui

import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.stackanswer.BuildConfig
import com.stackanswer.R
import com.stackanswer.databinding.FragmentDetailShowBinding
import com.stackanswer.model.Show
import com.stackanswer.source.Resource
import com.stackanswer.source.local.room.showfavorite.ShowFavorite
import com.stackanswer.utils.Constan
import com.stackanswer.utils.ImageUtils
import com.stackanswer.utils.kotlin.DataMapper
import com.stackanswer.viewmodel.DetailShowViewModelKt
import com.stackanswer.viewmodel.ShowFavoriteViewModelKt
import com.stackanswer.viewmodel.factory.kotlin.DetailShowViewModelFactory

class DetailShowKtFragment : Fragment(), View.OnClickListener {

//    private var viewModel: DetailShowViewModel? = null
//    private var showFavoriteViewModel: ShowFavoriteViewModel? = null
    private var showFavoriteViewModel: ShowFavoriteViewModelKt? = null
    private var viewModel: DetailShowViewModelKt? = null
    private var binding: FragmentDetailShowBinding? = null
    private var mShowFavorite: List<ShowFavorite>? = null
    private var film: Show? = null
    private var movie: Show? = null
    private var bSave = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailShowBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.ivKembali.setOnClickListener(this)
        binding!!.ivFavorite.setOnClickListener(this)

//        val factory = DetailShowViewModelFactory.getInstance(activity)
//        viewModel = ViewModelProvider(this, factory).get(DetailShowViewModel::class.java)
//        viewModel!!.isLoading.observe(
//            viewLifecycleOwner,
//            { isLoading: Boolean ->
//                if (isLoading) {
//                    onStartProggress()
//
////                Test idling resource
//                    EspressoIdlingResourceDetail2.increment()
//                } else {
//                    onStopProggress()
//                    if (!EspressoIdlingResourceDetail2.getEspressoIdlingResource().isIdleNow) {
//                        EspressoIdlingResourceDetail2.decrement()
//                    }
//                }
//            })

        val factory = DetailShowViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory).get(DetailShowViewModelKt::class.java)

//        val factory2 = ShowFavoriteViewModelFactory.getInstance(context)
//        showFavoriteViewModel = ViewModelProvider(this, factory2).get(ShowFavoriteViewModel::class.java)
//        showFavoriteViewModel!!.allShow.observe(viewLifecycleOwner, { favoriteList: PagedList<ShowFavorite>? ->
//            mShowFavorite = favoriteList
//            whileShow()
//        })

        val factory2 = com.stackanswer.viewmodel.factory.kotlin.ShowFavoriteViewModelFactory.getInstance(requireActivity())
        showFavoriteViewModel = ViewModelProvider(this, factory2)[ShowFavoriteViewModelKt::class.java]
        showFavoriteViewModel!!.tourism.observe(viewLifecycleOwner, { movieFavorites ->
            mShowFavorite = movieFavorites
            whileShow()
        })

        onStartProggress()
        setup()
    }

    private fun whileShow() {
        if (film == null) {
            while (film == null) {
                Handler().postDelayed({
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
        for (i in mShowFavorite!!.indices) {
            if (mShowFavorite!![i].id == film!!.id) {
                binding!!.ivFavorite.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.red_active
                    ), PorterDuff.Mode.MULTIPLY
                )
                bSave = true
            }
        }
    }

    private fun setup() {
        val arguments = arguments
        if (arguments != null) {
            if (arguments.containsKey(DetailShowKtFragment::class.java.simpleName)) {
                film = arguments.getParcelable(DetailShowKtFragment::class.java.simpleName)
                requireArguments().remove(DetailShowKtFragment::class.java.simpleName)

//                viewModel!!.getShow().observe(viewLifecycleOwner, { shows: Show ->
//                    setupFilm(shows)
//                    Log.d(TAG, "onViewCreated: $shows")
//                })
//                viewModel!!.findDetailShow("en-US", film!!.id.toString())

                viewModel!!.tourism("" + film?.id).observe(viewLifecycleOwner, { tourism ->
                    if (tourism != null) {
                        when (tourism) {
                            is Resource.Loading -> {
                                onStartProggress()
                            }
                            is Resource.Success -> {
                                onStopProggress()
                                tourism.data?.let { DataMapper.mapShowToDomain(it) }?.let {
                                    it.forEachIndexed { _, value ->
                                        if (value.id == film?.id) {
                                            setupFilm(value)
                                            movie = value
                                        }
                                    }
                                }
                            }
                            is Resource.Error -> {
                                onStopProggress()
                            }
                        }
                    }
                })
            }
        }
    }

    private fun setupFilm(show: Show) {
        binding!!.tvDetailUtama.text = show.overview
        binding!!.tvJudul.text = show.name
        binding!!.tvBahasa.text = Constan.setBahasa(show.originalLanguage)
        binding!!.tvTayang.text = show.firstAirDate
        binding!!.tvRating.text = show.voteAverage.toString()
        var genre = show.genreIds?.toString()
        if (genre!=null) {
            if (show.genreIds.toString().contains("[") || show.genreIds.toString().contains("]")) {
                genre = genre.replace("\\[".toRegex(), "").replace("]".toRegex(), "")
            }
        }
        binding!!.tvGenre.text = genre
        if (show.posterPath != null && show.posterPath != "" && show.posterPath != "null") {
            val url = BuildConfig.BASE_URL_IMAGE + show.posterPath
            ImageUtils.fromUrlWithSize(context, url, binding!!.ivPoster, 200, 280)
            ImageUtils.fromUrlWithSize(context, url, binding!!.ivPoster4, 100, 140)
            ImageUtils.fromUrlWithSize(context, url, binding!!.ivPoster3, 100, 140)
            ImageUtils.fromUrlWithSize(context, url, binding!!.ivPoster2, 100, 140)
        }
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
        if (movie != null) {
            val data = DataMapper.mapShowToShowDatabase(movie!!)
            val k = IntArray(mShowFavorite!!.size)
            for (m in mShowFavorite!!.indices) {
                k[m] = mShowFavorite!![m].id
                if (data.id == mShowFavorite!![m].id) {
                    if (!Constan.compareObjects(data, mShowFavorite!![m])) {
                        showFavoriteViewModel!!.updateFavoriteTourism(data, true)
                    }
                }
            }
            if (!Constan.execute(k, data.id)) {
                showFavoriteViewModel!!.setFavoriteTourism(data, true)
            }
        }
    }

    private fun deleteFavorite() {
        if (movie != null) {
            val data = DataMapper.mapShowToShowDatabase(movie!!)
            showFavoriteViewModel!!.deleteFavoriteTourism(data, true)
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
