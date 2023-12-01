package com.example.planandgo.ui.createplan

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.commit
import com.example.planandgo.R
import com.example.planandgo.databinding.FragmentCreatePlanBinding
import com.example.planandgo.ui.responseplan.PlanSuggestionActivity
import com.google.android.material.appbar.AppBarLayout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreatePlanFragment : Fragment(), View.OnClickListener {

    private lateinit var binding : FragmentCreatePlanBinding
    var calendar = Calendar.getInstance()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCreatePlanBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Autocompleted Text Suggestion City
        val cities = resources.getStringArray(R.array.indonesia_cities)
        val arrayAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_list_item_1, cities)
        binding.autoCompleteBasedCity.setAdapter(arrayAdapter)
        binding.autoCompleteDestinationCity.setAdapter(arrayAdapter)

        //Configuration to show calendar Departure and Return time
        val dateSetListenerDepartureTime =
            DatePickerDialog.OnDateSetListener { p0, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                setDateTextDepartureTime()
            }

        val dateSetListenerReturnTime =
            DatePickerDialog.OnDateSetListener { p0, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                setDateTextReturnTime()
            }

        binding.departureTime.setOnClickListener{
            DatePickerDialog(requireActivity(),
                dateSetListenerDepartureTime,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.returnTime.setOnClickListener {
            DatePickerDialog(requireActivity(),
                dateSetListenerReturnTime,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
            if (binding.returnTime.text.isNullOrEmpty()){
                binding.textInputLayoutReturnTime.error = getString(R.string.empty_input)
            } else{
                binding.textInputLayoutReturnTime.error = null
            }
        }

        //Autocompleted text for suggestion TypeofDestinatination (Themme Destination)
        val typeOfDestination = resources.getStringArray(R.array.holiday_destination_types)
        val destinationAdapter = ArrayAdapter<String>(requireActivity(), android.R.layout.simple_list_item_1, typeOfDestination)
        binding.autoCompleteThemeDestination.setAdapter(destinationAdapter)

        binding.btnStartCreatePlan.setOnClickListener(this)


    }

    override fun onClick(view: View) {
        val fragmentManager = parentFragmentManager
        if(
            binding.autoCompleteBasedCity.text.isEmpty()
            or binding.autoCompleteDestinationCity.text.isEmpty()
            or binding.departureTime.text.isNullOrEmpty()
            or binding.returnTime.text.isNullOrEmpty()
            or binding.totalBudgetUser.text.isNullOrEmpty()
            or binding.autoCompleteThemeDestination.text.isEmpty()
        ){
            showToast("Semua Kolom Formulir Wajib Diisi!")
        } else {
            fragmentManager.beginTransaction().apply {
                val intent = Intent(requireActivity(), PlanSuggestionActivity::class.java)
                addToBackStack(null)
                startActivity(intent)
                activity?.finish()
            }
        }
    }

    private fun setDateTextDepartureTime(){
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.departureTime.setText(sdf.format(calendar.time))
    }

    private fun setDateTextReturnTime(){
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.returnTime.setText(sdf.format(calendar.time))
    }

    private fun showToast(message : String){
        Toast.makeText(requireActivity(), message,Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}