package com.stackanswer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stackanswer.R
import com.stackanswer.adapter.ShowAdapter
import com.stackanswer.databinding.FragmentShowBinding
import com.stackanswer.model.Show
import com.stackanswer.source.Resource
import com.stackanswer.tidakdigunakan.DetailShowFragment
import com.stackanswer.utils.kotlin.DataMapper
import com.stackanswer.viewmodel.ShowViewModelKt
import com.stackanswer.viewmodel.factory.kotlin.ShowViewModelFactory

class ShowKtFragment : Fragment() {

    private var rvMovie: RecyclerView? = null
    private var binding: FragmentShowBinding? = null

    private var viewModel: ShowViewModelKt? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentShowBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ShowViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory).get(ShowViewModelKt::class.java)
        viewModel!!.tourism.observe(viewLifecycleOwner, { tourism ->
            if (tourism != null) {
                when (tourism) {
                    is Resource.Loading -> onStartProggress()
                    is Resource.Success -> {
                        onStopProggress()
                        tourism.data?.let { DataMapper.mapShowToDomain(it) }?.let { setupRVmovie(it) }
                    }
                    is Resource.Error -> {
                        onStopProggress()
                    }
                }
            }
        })

        rvMovie = binding!!.rvShow

//        val factory = ShowViewModelFactory.getInstance(activity)
//        val viewModel = ViewModelProvider(this, factory).get(ShowViewModel::class.java)
//        viewModel.show.observe(viewLifecycleOwner, { shows: List<Show> ->
//            setupRVmovie(shows)
//            Log.d(TAG, "onViewCreated: " + shows.size)
//        })
//        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading: Boolean ->
//            if (isLoading) {
//                onStartProggress()
//
////                Test idling resource
//                EspressoIdlingResourceShow.increment()
//            } else {
//                onStopProggress()
//                if (!EspressoIdlingResourceShow.getEspressoIdlingResource().isIdleNow) {
//                    EspressoIdlingResourceShow.decrement()
//                }
//            }
//        })
//        rvMovie = binding!!.rvShow
//        viewModel.findShow("en-US", "1")
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

    private fun setupRVmovie(filmList: List<Show>) {
        if (filmList.isNotEmpty()) {
            val adapter = ShowAdapter(context, filmList)
            rvMovie!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvMovie!!.adapter = adapter
            adapter.setCallback { films: Show?, _: Int ->
                val mFragmentManager = (requireContext() as FragmentActivity).supportFragmentManager
                val fragment = DetailShowKtFragment()
                val b = Bundle()
                b.putParcelable(DetailShowKtFragment::class.java.simpleName, films)
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
}