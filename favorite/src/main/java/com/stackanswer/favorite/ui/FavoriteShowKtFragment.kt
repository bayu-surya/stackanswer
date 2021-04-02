package com.stackanswer.favorite.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stackanswer.core.source.local.room.showfavorite.ShowFavorite
import com.stackanswer.favorite.R
import com.stackanswer.favorite.adapter.ShowFavoriteKtAdapter
import com.stackanswer.favorite.databinding.FragmentFavoriteShowBinding
import com.stackanswer.favorite.viewmodel.ShowFavoriteViewModelKt
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteShowKtFragment : Fragment() {

    private var rvMovie: RecyclerView? = null
    private lateinit var binding: FragmentFavoriteShowBinding
    private var adapter: ShowFavoriteKtAdapter? = null

    private val viewModel: ShowFavoriteViewModelKt by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentFavoriteShowBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onStartProggress()
        rvMovie = binding.rvShow

        viewModel.tourism.observe(viewLifecycleOwner, { movieFavorites ->
            setupRVmovie(movieFavorites)
            Handler(Looper.getMainLooper()).postDelayed({ onStopProggress() }, 2000)
        })

    }

    private fun setupRVmovie( filmList: List<ShowFavorite> ) {
        if (filmList.isNotEmpty()) {
            binding.rvShow.visibility=View.VISIBLE
            binding.tvNull.visibility=View.GONE
            adapter = ShowFavoriteKtAdapter(context,
                filmList)
            rvMovie?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvMovie?.adapter = adapter
            adapter?.setCallback { films: ShowFavorite?, _: Int ->
                val fragment = DetailShowFavoriteKtFragment()
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
        } else{
            binding.rvShow.visibility=View.GONE
            binding.tvNull.visibility=View.VISIBLE
        }
    }

    private fun onStartProggress() {
        binding.tvNull.visibility=View.GONE
        binding.rvShow.visibility = View.GONE
        binding.shimmer.visibility = View.VISIBLE
        binding.shimmer2.visibility = View.VISIBLE
        binding.shimmer3.visibility = View.VISIBLE
        binding.shimmer.startShimmerAnimation()
        binding.shimmer2.startShimmerAnimation()
        binding.shimmer3.startShimmerAnimation()
    }

    private fun onStopProggress() {
        binding.rvShow.visibility = View.VISIBLE
        binding.shimmer.visibility = View.GONE
        binding.shimmer2.visibility = View.GONE
        binding.shimmer3.visibility = View.GONE
        binding.shimmer.stopShimmerAnimation()
        binding.shimmer2.stopShimmerAnimation()
        binding.shimmer3.stopShimmerAnimation()
    }
}