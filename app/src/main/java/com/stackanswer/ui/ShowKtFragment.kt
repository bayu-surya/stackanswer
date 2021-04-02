package com.stackanswer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stackanswer.R
import com.stackanswer.adapter.ShowAdapter
import com.stackanswer.core.model.Show
import com.stackanswer.core.source.Resource
import com.stackanswer.core.utils.DataMapper
import com.stackanswer.databinding.FragmentShowBinding
import com.stackanswer.viewmodel.ShowViewModelKt
import org.koin.android.viewmodel.ext.android.viewModel

class ShowKtFragment : Fragment() {

    private var rvMovie: RecyclerView? = null
    private lateinit var binding: FragmentShowBinding

    private val viewModel: ShowViewModelKt by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentShowBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.tourism.observe(viewLifecycleOwner, { tourism ->
            if (tourism != null) {
                when (tourism) {
                    is Resource.Loading -> onStartProggress()
                    is Resource.Success -> {
                        onStopProggress()
                        tourism.data?.let { DataMapper.mapShowToDomain(it) }?.let { setupRVmovie(it) }
                    }
                    is Resource.Error -> {
                        onStopProggress()
                        binding.rvShow.visibility=View.GONE
                        binding.tvNull.visibility=View.VISIBLE
                    }
                }
            }
        })

        rvMovie = binding.rvShow

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

    private fun setupRVmovie(filmList: List<Show>) {
        if (filmList.isNotEmpty()) {
            binding.rvShow.visibility=View.VISIBLE
            binding.tvNull.visibility=View.GONE
            val adapter = ShowAdapter(context, filmList)
            rvMovie?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvMovie?.adapter = adapter
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
        } else{
            binding.rvShow.visibility=View.GONE
            binding.tvNull.visibility=View.VISIBLE
        }
    }
}