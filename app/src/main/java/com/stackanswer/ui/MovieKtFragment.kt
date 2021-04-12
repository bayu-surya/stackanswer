package com.stackanswer.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stackanswer.R
import com.stackanswer.adapter.MovieAdapter
import com.stackanswer.core.model.Movie
import com.stackanswer.core.source.Resource
import com.stackanswer.core.utils.DataMapper
import com.stackanswer.databinding.FragmentMovieBinding
import com.stackanswer.viewmodel.MovieViewModelKt
import org.koin.android.viewmodel.ext.android.viewModel

class MovieKtFragment : Fragment() {

    private var rvMovie: RecyclerView? = null
    private lateinit var binding: FragmentMovieBinding

    private val viewModel: MovieViewModelKt by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.tourism.observe(viewLifecycleOwner) { tourism ->
            if (tourism != null) {
                when (tourism) {
                    is Resource.Loading -> {
                        Log.d("TAG", "loadFromDB: 0")
                        onStartProggress()
                    }
                    is Resource.Success -> {
                        Log.d("TAG", "loadFromDB: 5")
                        onStopProggress()
                        tourism.data?.let { DataMapper.mapMovieToDomain(it) }
                            ?.let {
                                if (context!=null) {
                                    setupRVmovie(it)
                                }
                            }
                    }
                    is Resource.Error -> {
                        Log.d("TAG", "loadFromDB: 6")
                        onStopProggress()
                        binding.rvMovie.visibility=View.GONE
                        binding.tvNull.visibility=View.VISIBLE
                    }
                }
            }
        }

        rvMovie = binding.rvMovie
    }

    private val TAG = "MainViewModel"

    private fun setupRVmovie(filmList: List<Movie>) {
        Log.d(TAG, "loadMovie: 2 = " + filmList.size)
        if (filmList.isNotEmpty()) {
            binding.rvMovie.visibility=View.VISIBLE
            binding.tvNull.visibility=View.GONE
            val adapter = MovieAdapter(context, filmList)
            rvMovie?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvMovie?.adapter = adapter
            adapter.setCallback { films: Movie?, _: Int ->
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
        } else{
            binding.rvMovie.visibility=View.GONE
            binding.tvNull.visibility=View.VISIBLE
        }
    }

    private fun onStartProggress() {
        binding.shimmer.visibility = View.VISIBLE
        binding.shimmer2.visibility = View.VISIBLE
        binding.shimmer3.visibility = View.VISIBLE
        binding.shimmer.startShimmerAnimation()
        binding.shimmer2.startShimmerAnimation()
        binding.shimmer3.startShimmerAnimation()
    }

    private fun onStopProggress() {
        binding.shimmer.visibility = View.GONE
        binding.shimmer2.visibility = View.GONE
        binding.shimmer3.visibility = View.GONE
        binding.shimmer.stopShimmerAnimation()
        binding.shimmer2.stopShimmerAnimation()
        binding.shimmer3.stopShimmerAnimation()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding.rvMovie.setOnClickListener(null)
        binding.linear.removeOnLayoutChangeListener(null)
        binding.linear.setOnClickListener(null)

        binding.rvMovie.removeAllViewsInLayout()
        binding.shimmer.removeAllViewsInLayout()
        binding.shimmer2.removeAllViewsInLayout()
        binding.shimmer3.removeAllViewsInLayout()
        binding.loading.removeAllViewsInLayout()

        binding.linear.removeAllViews()
        binding.linear.removeAllViewsInLayout()

        if (view?.parent != null) {
            (view?.parent as ViewGroup).removeView(view)
        }
    }
}