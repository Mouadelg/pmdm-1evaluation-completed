package edu.iesam.teslaapp.feature.carslist

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import edu.iesam.teslaapp.R
import edu.iesam.teslaapp.feature.carslist.data.remote.CarApiRemoteDataSource
import edu.iesam.teslaapp.feature.carslist.data.repository.CarDataRepository
import edu.iesam.teslaapp.feature.carslist.domain.CarRepository
import edu.iesam.teslaapp.feature.carslist.domain.GetCarByIdUseCase
import edu.iesam.teslaapp.feature.carslist.presentation.CarViewModel

class CarsListActivity : AppCompatActivity() {

    private val viewModel: CarViewModel by viewModels {
        CarViewModel.factory(
            GetCarByIdUseCase(CarDataRepository(CarApiRemoteDataSource()))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.uiState.observe(this){ uiState ->
            mostrarUiState(uiState)
        }

        initListeners()
    }

    private fun initListeners() {
        val labelCarId = findViewById<EditText>(R.id.input_car_id)
        val actionFind = findViewById<Button>(R.id.action_find)
        actionFind.setOnClickListener {
            buttonFindClicked(labelCarId.text.toString())
        }
    }

    private fun buttonFindClicked(carId: String) {
        //el usuario ha pulsado el botón.
        Log.d(CarsListActivity::class.java.simpleName, carId)
        viewModel.loadCarById(carId)


    }

    private fun mostrarUiState(uiState: CarViewModel.UiState){
        val labelCarView = findViewById<LinearLayout>(R.id.car_info_layout)
        val labelViewCarNotFound = findViewById<TextView>(R.id.id_not_exist_text)
        if(uiState.car == null){
            labelCarView.visibility = View.GONE
            labelViewCarNotFound.visibility = View.VISIBLE
        }else{
            labelCarView.findViewById<TextView>(R.id.car_name).text = uiState.car.brand
            labelCarView.findViewById<TextView>(R.id.car_model).text = uiState.car.model
            labelCarView.findViewById<TextView>(R.id.car_plate).text = uiState.car.plate
            labelViewCarNotFound.visibility = View.GONE
            labelCarView.visibility = View.VISIBLE
        }



    }
}