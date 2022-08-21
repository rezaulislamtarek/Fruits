package com.diatomicsoft.fruits.view.favourite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.diatomicsoft.fruits.R
import com.diatomicsoft.fruits.adapter.FruitAdapter
import com.diatomicsoft.fruits.adapter.RecyclerViewListener
import com.diatomicsoft.fruits.databinding.FragmentFavoriteBinding
import com.diatomicsoft.fruits.model.db.AppDataBase
import com.diatomicsoft.fruits.network.responses.Fruit
import com.diatomicsoft.fruits.model.repository.FavRepository


class FavoriteFragment : Fragment(), RecyclerViewListener {

    private var binding: FragmentFavoriteBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_favorite, container, false)

        var dao = AppDataBase(requireContext()).favDao()
        var repository = FavRepository(dao)
        var factory = FavViewModelFactory(repository)
        val model = ViewModelProvider(this, factory).get(FavViewModel::class.java)
        binding?.favData = model
        model.getAllFavFruits().observe(viewLifecycleOwner, Observer {

            binding?.rvFav?.also { rv ->
                rv.layoutManager = LinearLayoutManager(requireContext())
                rv.setHasFixedSize(true)
                rv.adapter = FruitAdapter(it, this)
            }
        })
        return binding?.root
    }

    override fun onRecyclerViewItemClick(view: View, fruit: Fruit) {
        findNavController().navigate(
            FavoriteFragmentDirections.actionFavoriteFragmentToDetailsFragment(fruit)
        )
    }

}