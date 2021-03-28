package com.stackanswer.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stackanswer.R
import com.stackanswer.adapter.MovieFavoriteKtAdapter
import com.stackanswer.databinding.FragmentFavoriteMovieBinding
import com.stackanswer.source.local.room.moviefavorite.MovieFavorite
import com.stackanswer.viewmodel.MovieFavoriteViewModelKt
import com.stackanswer.viewmodel.factory.kotlin.MovieFavoriteViewModelFactory

class FavoriteMovieKtFragment : Fragment() {

    private var rvMovie: RecyclerView? = null
    private var binding: FragmentFavoriteMovieBinding? = null
    private var adapter: MovieFavoriteKtAdapter? = null
    private var viewModel: MovieFavoriteViewModelKt? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMovieBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val factory = MovieFavoriteViewModelFactory.getInstance(context)
//        val movieFavoriteViewModel = ViewModelProvider(this, factory).get(
//            MovieFavoriteViewModel::class.java
//        )

        val factory = MovieFavoriteViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[MovieFavoriteViewModelKt::class.java]

        onStartProggress()
        rvMovie = binding!!.rvMovie

        viewModel!!.tourism.observe(viewLifecycleOwner, { movieFavorites ->
            setupRVmovie(movieFavorites)
            Handler().postDelayed({ onStopProggress() }, 2000)
        })

//        viewModel.allMovie.observe(viewLifecycleOwner, { movieFavorites: PagedList<MoviePopular?> ->
//                adapter!!.submitList(
//                    movieFavorites
//                )
//            })
    }

    private fun setupRVmovie( filmList: List<MovieFavorite> ) {
        adapter = MovieFavoriteKtAdapter(context, filmList)
        rvMovie!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMovie!!.adapter = adapter
        adapter!!.setCallback { films: MovieFavorite?, _: Int ->
            val mFragmentManager = (requireContext() as FragmentActivity).supportFragmentManager
            val fragment = DetailMovieFavoriteKtFragment()
            val b = Bundle()
            b.putParcelable(fragment.javaClass.simpleName, films)
            fragment.arguments = b
            val backStateName = HomeFragment::class.java.simpleName
            mFragmentManager
                .beginTransaction()
                .replace(R.id.container_layout, fragment, fragment.javaClass.simpleName)
                .addToBackStack(backStateName)
                .commit()
        }
    }

    private fun onStartProggress() {
        binding!!.rvMovie.visibility = View.GONE
        binding!!.shimmer.visibility = View.VISIBLE
        binding!!.shimmer2.visibility = View.VISIBLE
        binding!!.shimmer3.visibility = View.VISIBLE
        binding!!.shimmer.startShimmerAnimation()
        binding!!.shimmer2.startShimmerAnimation()
        binding!!.shimmer3.startShimmerAnimation()
    }

    private fun onStopProggress() {
        binding!!.rvMovie.visibility = View.VISIBLE
        binding!!.shimmer.visibility = View.GONE
        binding!!.shimmer2.visibility = View.GONE
        binding!!.shimmer3.visibility = View.GONE
        binding!!.shimmer.stopShimmerAnimation()
        binding!!.shimmer2.stopShimmerAnimation()
        binding!!.shimmer3.stopShimmerAnimation()
    }
}