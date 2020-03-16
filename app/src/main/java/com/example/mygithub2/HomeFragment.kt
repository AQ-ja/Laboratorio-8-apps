package com.example.mygithub2


import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.mygithub2.ApiService
import com.example.mygithub2.ViewModelHome
import com.example.mygithub2.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var api:ApiService

    private val viewModel:ViewModelHome by lazy {
        ViewModelProviders.of(this).get(ViewModelHome::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding=FragmentHomeBinding.inflate(inflater)

        binding.setLifecycleOwner (this)
        binding.models=viewModel


        binding.buttonBuscar.setOnClickListener {
            viewModel.valors =binding.editText.text.toString()
            viewModel.getGithubProperties()

        }


        viewModel.existe.observe(this, Observer { existe ->
            existe?.let {
                viewModel.existe.value = null
                if(existe==false){
                    binding.buttonRepos.visibility=View.VISIBLE
                    binding.imageView.visibility=View.VISIBLE
                }else if(existe==true){
                    Toast.makeText(activity,"El usuario que ingreso no existe",Toast.LENGTH_SHORT).show()
                    binding.buttonRepos.visibility=View.GONE
                    binding.imageView.visibility=View.GONE
                }


            }
        })

        return binding.root

    }
}