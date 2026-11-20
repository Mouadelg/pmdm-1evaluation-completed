package edu.iesam.teslaapp.feature.carslist.data.repository

import edu.iesam.teslaapp.feature.carslist.data.local.CarXmlLocalDataSource
import edu.iesam.teslaapp.feature.carslist.data.remote.CarApiRemoteDataSource
import edu.iesam.teslaapp.feature.carslist.domain.Car
import edu.iesam.teslaapp.feature.carslist.domain.CarRepository

class CarDataRepository(
    private val apiRemoteDataSource: CarApiRemoteDataSource,
    private val carXmlLocalDataSource: CarXmlLocalDataSource
) : CarRepository {
    override fun getCarById(idCar: String): Car? {
        val car = carXmlLocalDataSource.findById(idCar)
        car?.let { c -> return car }
        return getApiRemoteCars().find { it.id == idCar }
    }

    private fun getApiRemoteCars(): List<Car> {
        return apiRemoteDataSource.getAll()
    }

}
