package com.example.spinnerpicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.spinnerpicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    companion object {
        var tahun = "tahun"
        var bulan = "bulan"
        var hari = "hari"
        var jam = "jam"
        var jenis_tiket = "jenis tiket"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countries = arrayOf(
            "Indonesia",
            "Malaysia",
            "Brunei",
            "Filipina",
            "Timor Leste"
        )
        val cities = resources.getStringArray(R.array.tujuan)

        with(binding){

            val countriesAdapter = ArrayAdapter(this@MainActivity,
                android.R.layout.simple_spinner_dropdown_item,
                countries)
            countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinCountries.adapter = countriesAdapter
            spinerJenistiket.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent : AdapterView<*>, view: View, position: Int, id: Long) {
                    val selectedItemPos = parent.getItemAtPosition(position).toString()
                    jenis = selectedItemPos
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }


            val citiesAdapter = ArrayAdapter(this@MainActivity,
                android.R.layout.simple_spinner_dropdown_item,
                cities)
            citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinCities.adapter = citiesAdapter

            var selectedDate=""
            datePick.init(datePick.year, datePick.month, datePick.dayOfMonth){
                _, year, month, day ->
                tahun = "$year"
                bulan = "$month"
                hari = "$day"

//                selectedDate = "$day/${month+1}/$year"
//                Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
            }
            var selectedTime= ""
            timePick.setOnTimeChangedListener{
                    _, timeOfDay, minute ->
                selectedTime = String.format("%02d:%02d", timeOfDay, minute)
                jam = selectedTime
//                Toast.makeText(this@MainActivity, selectedTime, Toast.LENGTH_SHORT).show()
            }
            pesan.setOnClickListener{
                Toast.makeText(this@MainActivity, "Tiket berhasil dipesan! Detail: $day", Toast.LENGTH_SHORT).show()
            }
        }
    }
}