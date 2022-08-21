package com.diatomicsoft.fruits.view.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.diatomicsoft.fruits.R
import com.diatomicsoft.fruits.adapter.FruitAdapter
import com.diatomicsoft.fruits.adapter.RecyclerViewListener
import com.diatomicsoft.fruits.model.network.MyApi
import com.diatomicsoft.fruits.model.network.NetworkConnectionInterceptor
import com.diatomicsoft.fruits.model.repository.HomeRepository
import com.diatomicsoft.fruits.network.responses.Fruit


class HomeFragment : Fragment(), RecyclerViewListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        val btnMove = rootView.findViewById<Button>(R.id.btnMove)
        btnMove.setOnClickListener {
            findNavController().navigate(R.id.favoriteFragment)
        }
        val iv = rootView.findViewById<AppCompatImageView>(R.id.ivGlide)
        //val imgUrl = "https://via.placeholder.com/600/1e5390"
        //val imgUrl = "https://lh6.ggpht.com/9SZhHdv4URtBzRmXpnWxZcYhkgTQurFuuQ8OR7WZ3R7fyTmha77dYkVvcuqMu3DLvMQ=w300"
        //val imgUrl = "https://iquote.diatomicsoft.com/uploads/images/walpapers/1.jpg"
        val imgUrl = "http://d1s5a4e3za7rni.cloudfront.net/Custom/Content/Products/10/13/1013450_purificador-de-agua-electrolux-pc41x-prata_s4_636822411415208179"
        //Glide.with(iv).load(imgUrl).into(iv)

        Glide.with(iv)
            .load(imgUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(iv)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val interCeptor = NetworkConnectionInterceptor(requireContext())
        val api = MyApi(interCeptor)
        val repository = HomeRepository(api)
        val factory = HomeViewModelFactory(repository)
        val model = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        //btnMove = view.findViewById<Button>(R.id.btnMove)



        model.fruitsList.observe(viewLifecycleOwner, Observer {
            view.findViewById<RecyclerView>(R.id.rvHome).also { rv ->
                rv.layoutManager = LinearLayoutManager(requireContext())
                rv.setHasFixedSize(true)
                rv.adapter = FruitAdapter(it, this)
            }
        })
    }

    override fun onRecyclerViewItemClick(view: View, fruit: Fruit) {
        Toast.makeText(requireContext(), fruit.name + "", Toast.LENGTH_LONG).show()
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                fruit
            )
        )
    }
    

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

}