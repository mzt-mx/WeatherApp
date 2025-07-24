package com.example.mmxweather.data.remote.response

import com.google.gson.annotations.SerializedName

// Основной класс ответа
data class WeatherForecastResponse(
    val city: City,
    val list: List<DailyForecast> // Список ежедневных прогнозов
)

data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int,
    val timezone: Int
)

data class Coord(
    val lon: Double,
    val lat: Double
)

data class DailyForecast(
    val dt: Long, // Время прогноза в формате Unix time
    val temp: Temp, // Детали температуры
    val pressure: Double,
    val humidity: Int,
    val weather: List<Weather>, // Список погодных условий (обычно один элемент)
    val speed: Double,
    val deg: Int,
    val clouds: Int,
    val pop: Double // Вероятность осадков
)

data class Temp(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

data class Weather(
    val id: Int,
    val main: String, // Например: "Clouds", "Clear", "Rain"
    val description: String, // Например: "scattered clouds", "clear sky", "light rain"
    val icon: String // Идентификатор иконки погоды
)