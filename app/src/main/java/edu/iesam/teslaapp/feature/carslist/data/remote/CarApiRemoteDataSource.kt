package edu.iesam.teslaapp.feature.carslist.data.remote

import edu.iesam.teslaapp.R
import edu.iesam.teslaapp.feature.carslist.domain.Car

class CarApiRemoteDataSource {
    fun getAll(): List<Car> {
        // All have the same car picture
        val imageSource = "@drawable/tesla_car.xml"
        return listOf(
            Car("1", "TeslaA", "2011", "AAA111", imageSource),
            Car("2", "TeslaB", "2012", "BBB222", imageSource),
            Car("3", "TeslaC", "2013", "CCC333", imageSource),
            Car("4", "TeslaD", "2014", "DDD444", imageSource),
            Car("5", "TeslaE", "2015", "EEE555", imageSource),
            Car("6", "TeslaF Royce", "2016", "FFF666", imageSource),
        )
    }

}