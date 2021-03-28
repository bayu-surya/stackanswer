package com.stackanswer.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stackanswer.R
import com.stackanswer.adapter.MovieAdapter
import com.stackanswer.databinding.FragmentMovieBinding
import com.stackanswer.model.Movie
import com.stackanswer.source.Resource
import com.stackanswer.utils.kotlin.DataMapper
import com.stackanswer.viewmodel.MovieViewModelKt
import com.stackanswer.viewmodel.factory.kotlin.ViewModelFactory

class MovieKtFragment : Fragment() {

    private var rvMovie: RecyclerView? = null
    private var binding: FragmentMovieBinding? = null
    private var viewModel: MovieViewModelKt? = null

//    var viewModel: MovieViewModel? = null
//    fun MovieFragment() {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentMovieBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory).get(MovieViewModelKt::class.java)
        viewModel!!.tourism.observe(viewLifecycleOwner, { tourism ->
            if (tourism != null) {
                when (tourism) {
                    is Resource.Loading -> {
                        Log.d("TAG", "loadFromDB: 0")
                        onStartProggress()
                    }
                    is Resource.Success -> {
                        Log.d("TAG", "loadFromDB: 5")
                        onStopProggress()
                        tourism.data?.let { DataMapper.mapMovieToDomain(it) }?.let { setupRVmovie(it) }
                    }
                    is Resource.Error -> {
                        Log.d("TAG", "loadFromDB: 6")
                        onStopProggress()
                    }
                }
            }
        })


//        val factory = ViewModelFactory.getInstance(activity)
//        viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)
//        viewModel!!.movie.observe(viewLifecycleOwner, { shows: List<Movie> ->
//            setupRVmovie(shows)
//            Log.d(TAG, "onViewCreated: " + shows.size)
//        })
//        viewModel!!.isLoading.observe(viewLifecycleOwner, { isLoading: Boolean ->
//            if (isLoading) {
//                onStartProggress()
//            } else {
//                onStopProggress()
//            }
//        })
//        viewModel!!.findMovie("en-US", "1")

        rvMovie = binding!!.rvMovie
    }

    private val TAG = "MainViewModel"

    private fun setupRVmovie(filmList: List<Movie>) {
        Log.d(TAG, "loadMovie: 2 " + filmList.size)
        if (filmList.isNotEmpty()) {
            val adapter = MovieAdapter(context, filmList)
            rvMovie!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvMovie!!.adapter = adapter
            adapter.setCallback { films: Movie?, position: Int ->
                val mFragmentManager = (requireContext() as FragmentActivity).supportFragmentManager
                val fragment = DetailMovieKtFragment()
                val b = Bundle()
                b.putParcelable(DetailMovieKtFragment::class.java.simpleName, films)
                fragment.arguments = b
                val backStateName = HomeFragment::class.java.simpleName
                mFragmentManager
                        .beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.javaClass.simpleName)
                        .addToBackStack(backStateName)
                        .commit()
            }
        }
    }

    private fun onStartProggress() {
        binding!!.shimmer.visibility = View.VISIBLE
        binding!!.shimmer2.visibility = View.VISIBLE
        binding!!.shimmer3.visibility = View.VISIBLE
        binding!!.shimmer.startShimmerAnimation()
        binding!!.shimmer2.startShimmerAnimation()
        binding!!.shimmer3.startShimmerAnimation()
    }

    private fun onStopProggress() {
        binding!!.shimmer.visibility = View.GONE
        binding!!.shimmer2.visibility = View.GONE
        binding!!.shimmer3.visibility = View.GONE
        binding!!.shimmer.stopShimmerAnimation()
        binding!!.shimmer2.stopShimmerAnimation()
        binding!!.shimmer3.stopShimmerAnimation()
    }
}