package edu.iesam.teslaapp.feature.carslist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import edu.iesam.teslaapp.feature.carslist.domain.Car
import edu.iesam.teslaapp.feature.carslist.domain.GetCarByIdUseCase

class CarViewModel(
    private val getCarByIdUseCase: GetCarByIdUseCase
) : ViewModel(){
    companion object{
        fun factory(
            getCarByIdUseCase: GetCarByIdUseCase
        ) = viewModelFactory {
            initializer { CarViewModel(getCarByIdUseCase) }
        }
    }

    // Private
    private val _uiState = MutableLiveData<UiState>()

    // Accesible
    val uiState: LiveData<UiState> = _uiState


    data class UiState(
        val car: Car?
    )

     fun loadCarById(id: String) {
        _uiState.value = UiState(
            car = getCarByIdUseCase.invoke(id)
        )
    }
}
