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
import com.stackanswer.adapter.ShowFavoriteKtAdapter
import com.stackanswer.databinding.FragmentFavoriteShowBinding
import com.stackanswer.source.local.room.showfavorite.ShowFavorite
import com.stackanswer.viewmodel.ShowFavoriteViewModelKt
import com.stackanswer.viewmodel.factory.kotlin.ShowFavoriteViewModelFactory

class FavoriteShowKtFragment : Fragment() {

    private var rvMovie: RecyclerView? = null
    private var binding: FragmentFavoriteShowBinding? = null
    private var adapter: ShowFavoriteKtAdapter? = null
    private var viewModel: ShowFavoriteViewModelKt? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentFavoriteShowBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val factory = ShowFavoriteViewModelFactory.getInstance(context)
//        val showFavoriteViewModel = ViewModelProvider(this, factory).get(ShowFavoriteViewModel::class.java)

        val factory = ShowFavoriteViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[ShowFavoriteViewModelKt::class.java]

        onStartProggress()
        rvMovie = binding!!.rvShow

        viewModel!!.tourism.observe(viewLifecycleOwner, { movieFavorites ->
            setupRVmovie(movieFavorites)
            Handler().postDelayed({ onStopProggress() }, 2000)
        })

//        setupRVmovie()
//        showFavoriteViewModel.allShow.observe(viewLifecycleOwner, { movieFavorites: PagedList<ShowFavorite?> -> adapter!!.submitList(movieFavorites) })
    }

    private fun setupRVmovie( filmList: List<ShowFavorite> ) {
        adapter = ShowFavoriteKtAdapter(context, filmList)
        rvMovie!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvMovie!!.adapter = adapter
        adapter!!.setCallback { films: ShowFavorite?, _: Int ->
            val mFragmentManager = (requireContext() as FragmentActivity).supportFragmentManager
            val fragment = DetailShowFavoriteKtFragment()
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
        binding!!.rvShow.visibility = View.GONE
        binding!!.shimmer.visibility = View.VISIBLE
        binding!!.shimmer2.visibility = View.VISIBLE
        binding!!.shimmer3.visibility = View.VISIBLE
        binding!!.shimmer.startShimmerAnimation()
        binding!!.shimmer2.startShimmerAnimation()
        binding!!.shimmer3.startShimmerAnimation()
    }

    private fun onStopProggress() {
        binding!!.rvShow.visibility = View.VISIBLE
        binding!!.shimmer.visibility = View.GONE
        binding!!.shimmer2.visibility = View.GONE
        binding!!.shimmer3.visibility = View.GONE
        binding!!.shimmer.stopShimmerAnimation()
        binding!!.shimmer2.stopShimmerAnimation()
        binding!!.shimmer3.stopShimmerAnimation()
    }
}