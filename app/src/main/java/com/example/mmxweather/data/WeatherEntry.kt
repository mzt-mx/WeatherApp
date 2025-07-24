package com.example.mmxweather.data

data class WeatherEntry(
    val date: String, // Дата прогноза
    val temperature: Double, // Температура
    val condition: String, // Описание условий (например, "Солнечно", "Пасмурно")
    val iconUrl: String, // URL иконки погоды (если API предоставляет URL)
    // Добавьте другие поля по необходимости (например, влажность, скорость ветра и т.д.)
)