package com.diatomicsoft.fruits.view.wallpaper

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.diatomicsoft.fruits.adapter.LoaderStateAdapter
import com.diatomicsoft.fruits.adapter.WallpaperAdapter
import com.diatomicsoft.fruits.databinding.FragmentWallpaperBinding
import com.diatomicsoft.fruits.model.network.MyApi
import com.diatomicsoft.fruits.model.network.NetworkConnectionInterceptor
import com.diatomicsoft.fruits.view.wallpaper.viewmodel.WallpaperViewModel
import kotlinx.coroutines.flow.collectLatest

class WallpaperFragment : Fragment() {

    var binding: FragmentWallpaperBinding? = null
    lateinit var wallpaperAdapter: WallpaperAdapter
    lateinit var viewModel : WallpaperViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wallpaperAdapter = WallpaperAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWallpaperBinding.inflate(layoutInflater, container, false)
        val interCeptor = NetworkConnectionInterceptor(requireContext())
        val api = MyApi(interCeptor)
        val factory = WallpaperViewModelFactory(api)
        viewModel = ViewModelProvider(this, factory).get(WallpaperViewModel::class.java)
        initRecyclerView()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Fragment", "Wallpaper Fragment created()")

        binding?.lifecycleOwner = this
        //binding?.viewModel = viewModel


        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            Log.d("Fragment", "Called in coroutine scope ")

            viewModel.getAllWallPaper().collectLatest {
                Log.d("Fragment", "Inside Flow" + it)
                wallpaperAdapter.submitData(it)
            }
        }

    }

    private fun initRecyclerView() {
        binding?.apply {
            rvWallpaper.layoutManager = GridLayoutManager(requireContext(), 2)
            rvWallpaper.adapter = wallpaperAdapter.withLoadStateFooter(
                /*header = LoaderStateAdapter { wallpaperAdapter::retry },*/
                footer = LoaderStateAdapter { wallpaperAdapter::retry })

        }
    }

}

