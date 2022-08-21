package com.diatomicsoft.fruits.view.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.diatomicsoft.fruits.R
import com.diatomicsoft.fruits.databinding.FragmentDetailsBinding
import com.diatomicsoft.fruits.model.db.AppDataBase
import com.diatomicsoft.fruits.model.repository.FavRepository
import com.diatomicsoft.fruits.view.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class DetailsFragment : Fragment() {

    lateinit var bottomNav:BottomNavigationView
    private var binding: FragmentDetailsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val args: DetailsFragmentArgs by navArgs()


        //val rootView = inflater.inflate(R.layout.fragment_details, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        //val interCeptor = NetworkConnectionInterceptor(this)
        //val api = MyApi(interCeptor)
        val favDaw = AppDataBase(requireContext()).favDao()
        val repository = FavRepository(favDaw)
        val factory = DetailsViewModelFactory(repository)
        val model = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)


        val view: View? = binding?.root
        binding?.fruitInfo = args.fruit
        binding?.btnAddToFav?.setOnClickListener {
            //model.addToFavFruit(args.fruit)
            model.insert(args.fruit)
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val mainActivity = context as MainActivity
        bottomNav = mainActivity.findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav.visibility = View.GONE
    }

    override fun onDetach() {
        super.onDetach()
        bottomNav.visibility = View.VISIBLE
    }

}