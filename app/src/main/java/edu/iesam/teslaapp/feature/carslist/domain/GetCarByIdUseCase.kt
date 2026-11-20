package edu.iesam.teslaapp.feature.carslist.domain

class GetCarByIdUseCase(private val carRepository: CarRepository) {
    operator fun invoke(idCar: String) : Car?{
        return carRepository.getCarById(idCar)
    }
}