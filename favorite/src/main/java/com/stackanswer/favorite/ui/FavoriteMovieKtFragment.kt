package com.stackanswer.favorite.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stackanswer.core.source.local.room.moviefavorite.MovieFavorite
import com.stackanswer.favorite.R
import com.stackanswer.favorite.adapter.MovieFavoriteKtAdapter
import com.stackanswer.favorite.databinding.FragmentFavoriteMovieBinding
import com.stackanswer.favorite.viewmodel.MovieFavoriteViewModelKt
import org.koin.android.viewmodel.ext.android.viewModel


class FavoriteMovieKtFragment : Fragment() {

    private var rvMovie: RecyclerView? = null
    private lateinit var binding: FragmentFavoriteMovieBinding
    private var adapter: MovieFavoriteKtAdapter? = null

    private val viewModel: MovieFavoriteViewModelKt by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavoriteMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onStartProggress()
        rvMovie = binding.rvMovie

        viewModel.tourism.observe(viewLifecycleOwner) { movieFavorites ->
            setupRVmovie(movieFavorites)
            Handler().postDelayed({ onStopProggress() }, 2000)
        }
    }

    private fun setupRVmovie(filmList: List<MovieFavorite>) {
        adapter =
            MovieFavoriteKtAdapter(context,
                filmList)
        rvMovie?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMovie?.adapter = adapter
        adapter?.setCallback { films: MovieFavorite?, _: Int ->
            val fragment = DetailMovieFavoriteKtFragment()
            val b = Bundle()
            b.putParcelable(fragment.javaClass.simpleName, films)
            fragment.arguments = b
            val backStateName = FavoriteFragment::class.java.simpleName

            (requireActivity() as FavoriteActivity).supportFragmentManager
                .beginTransaction()
                .replace(R.id.container_layout_fav, fragment, fragment.javaClass.simpleName)
                .addToBackStack(backStateName)
                .commit()
        }
    }

    private fun onStartProggress() {
        binding.rvMovie.visibility = View.GONE
        binding.shimmer.visibility = View.VISIBLE
        binding.shimmer2.visibility = View.VISIBLE
        binding.shimmer3.visibility = View.VISIBLE
        binding.shimmer.startShimmerAnimation()
        binding.shimmer2.startShimmerAnimation()
        binding.shimmer3.startShimmerAnimation()
    }

    private fun onStopProggress() {
        binding.rvMovie.visibility = View.VISIBLE
        binding.shimmer.visibility = View.GONE
        binding.shimmer2.visibility = View.GONE
        binding.shimmer3.visibility = View.GONE
        binding.shimmer.stopShimmerAnimation()
        binding.shimmer2.stopShimmerAnimation()
        binding.shimmer3.stopShimmerAnimation()
    }
}