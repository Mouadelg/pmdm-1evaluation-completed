package edu.iesam.teslaapp.feature.carslist.domain

interface CarRepository {
    fun getCarById(idCar: String) : Car?
}