package com.example.mmxweather.ui.weather // Укажите свой пакет

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mmxweather.data.WeatherEntry
import com.example.mmxweather.data.remote.WeatherApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherViewModel : ViewModel() {

    private val _weatherList = MutableLiveData<List<WeatherEntry>>()
    val weatherList: LiveData<List<WeatherEntry>> get() = _weatherList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val weatherApiService: WeatherApiService // Экземпляр API сервиса

    init {
        // Создаем экземпляр Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/") // Базовый URL OpenWeatherMap
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Создаем экземпляр API сервиса
        weatherApiService = retrofit.create(WeatherApiService::class.java)
    }

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = weatherApiService.getDailyWeatherForecast(city, apiKey = apiKey)

                // Преобразуем ответ API (WeatherForecastResponse) в список WeatherEntry
                val weatherEntries = response.list.map { dailyForecast ->
                    // Преобразование данных из DailyForecast в WeatherEntry
                    // Вам нужно будет реализовать эту логику преобразования
                    WeatherEntry(
                        date = java.text.SimpleDateFormat("yyyy-MM-dd").format(java.util.Date(dailyForecast.dt * 1000)), // Преобразование Unix timestamp в дату
                        temperature = dailyForecast.temp.day, // Или другое поле температуры из Temp
                        condition = dailyForecast.weather.firstOrNull()?.description ?: "N/A", // Берем описание первого погодного условия
                        iconUrl = dailyForecast.weather.firstOrNull()?.icon?.let { icon ->
                            "https://openweathermap.org/img/wn/$icon@2x.png" // URL иконки погоды
                        } ?: ""
                    )
                }
                _weatherList.postValue(weatherEntries)

            } catch (e: Exception) {
                _errorMessage.postValue("Ошибка при загрузке погоды: ${e.message}")
                // Обработка ошибок (например, отображение сообщения пользователю)
            }
        }
    }
}