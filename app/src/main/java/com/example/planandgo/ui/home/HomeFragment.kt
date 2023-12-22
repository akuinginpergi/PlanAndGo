package com.example.planandgo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.planandgo.ViewModelFactory
import com.example.planandgo.data.ResultState
import com.example.planandgo.data.response.DataItem
import com.example.planandgo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.hide()

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvListTour.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvListTour.addItemDecoration(itemDecoration)

        getDestinationTour()
    }

    private fun setList(listDataItem : List<DataItem?>?){
        val adapter = ListDestinationTourAdapter()
        adapter.submitList(listDataItem)
        binding.rvListTour.adapter = adapter
    }

    private fun showToast(message: String){
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean){
        if(isLoading){
            binding.ProgressBar.visibility = View.VISIBLE
        } else{
            binding.ProgressBar.visibility = View.INVISIBLE
        }
    }

    private fun getDestinationTour(){
        viewModel.getDestinationTour("Yogyakarta").observe(requireActivity()){ result ->
            if(result != null){
                when(result){
                    is ResultState.Loading -> {
                        showLoading(true)
                    }
                    is ResultState.Success -> {
                        setList(result.data.data)
                        showLoading(false)
                    }
                    is ResultState.Error -> {
                        showLoading(false)
                        showToast(result.error)
                    }
                }
            }

        }
    }
}