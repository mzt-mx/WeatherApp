package com.example.mmxweather // Укажите свой пакет

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast // Для отображения сообщений об ошибке
import androidx.activity.viewModels // Для получения ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mmxweather.data.WeatherEntry
import com.example.mmxweather.ui.weather.WeatherAdapter
import com.example.mmxweather.ui.weather.WeatherViewModel // Импортируем ViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewWeather: RecyclerView
    private lateinit var weatherAdapter: WeatherAdapter
    private val weatherViewModel: WeatherViewModel by viewModels() // Получаем ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather) // Устанавливаем макет activity_weather.xml

        recyclerViewWeather = findViewById(R.id.recyclerViewWeather)

        // Устанавливаем LayoutManager
        recyclerViewWeather.layoutManager = LinearLayoutManager(this)

        // Инициализируем адаптер с пустым списком (данные будут загружены позже)
        weatherAdapter = WeatherAdapter(emptyList())
        recyclerViewWeather.adapter = weatherAdapter

        // Наблюдаем за weatherList в ViewModel
        weatherViewModel.weatherList.observe(this) { weatherList ->
            // Когда данные о погоде обновляются, обновляем адаптер
            weatherAdapter = WeatherAdapter(weatherList) // Создаем новый адаптер с новыми данными
            recyclerViewWeather.adapter = weatherAdapter // Присваиваем новый адаптер RecyclerView
        }

        // Наблюдаем за errorMessage в ViewModel
        weatherViewModel.errorMessage.observe(this) { errorMessage ->
            // Если есть сообщение об ошибке, отображаем его (например, в Toast)
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

        // Вызываем функцию получения погоды (например, для определенного города и вашего API ключа)
        val city = "London" // Замените на нужный город
        val apiKey = "85afd36bff2ab36aac6bdf3a9e0bec09" // Ваш API ключ
        weatherViewModel.fetchWeather(city, apiKey)
    }
}