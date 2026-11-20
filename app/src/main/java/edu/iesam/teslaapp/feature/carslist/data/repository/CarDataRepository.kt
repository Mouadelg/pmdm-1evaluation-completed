package edu.iesam.teslaapp.feature.carslist.data.repository

import edu.iesam.teslaapp.feature.carslist.data.remote.CarApiRemoteDataSource
import edu.iesam.teslaapp.feature.carslist.domain.Car
import edu.iesam.teslaapp.feature.carslist.domain.CarRepository

class CarDataRepository(
    private val apiRemoteDataSource: CarApiRemoteDataSource
) : CarRepository {
    override fun getCarById(idCar: String): Car? {
        return apiRemoteDataSource.getAll().find { it.id == idCar }
    }

    private fun getApiRemoteCars(): List<Car> {
        return apiRemoteDataSource.getAll()
    }

}
