package com.example.mmxweather.ui.weather // Укажите свой пакет для UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mmxweather.R // Убедитесь, что R импортируется правильно
import com.example.mmxweather.data.WeatherEntry
import androidx.core.content.ContextCompat
import android.graphics.Color

class WeatherAdapter(private val weatherList: List<WeatherEntry>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    // ViewHolder для элементов списка
    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewDate: TextView = itemView.findViewById(R.id.textViewDate)
        val textViewTemperature: TextView = itemView.findViewById(R.id.textViewTemperature)
        val textViewCondition: TextView = itemView.findViewById(R.id.textViewCondition)
        val imageViewWeatherIcon: ImageView = itemView.findViewById(R.id.imageViewWeatherIcon)
        // Добавьте другие представления, если вы их добавили в item_weather.xml
    }

    // Создает новые ViewHolder (вызывается LayoutManager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(itemView)
    }

    // Заменяет содержимое представления (вызывается LayoutManager)
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val currentItem = weatherList[position]

        holder.textViewDate.text = currentItem.date
        holder.textViewTemperature.text = "Температура: ${currentItem.temperature}°C"
        holder.textViewCondition.text = "Условия: ${currentItem.condition}"

        // --- Условное оформление ---

        // Изменение цвета фона в зависимости от температуры (пример)
        val temperature = currentItem.temperature
        val backgroundColor = when {
            temperature < 0 -> ContextCompat.getColor(holder.itemView.context, R.color.color_cold)
            temperature >= 0 && temperature < 20 -> ContextCompat.getColor(holder.itemView.context, R.color.color_warm)
            else -> Color.WHITE // Цвет по умолчанию
        }
        holder.itemView.setBackgroundColor(backgroundColor)

        // Изменение иконки в зависимости от погодных условий (пример)
        val condition = currentItem.condition.toLowerCase() // Приводим к нижнему регистру для удобства сравнения
        val weatherIconResource = when {
            condition.contains("clear") || condition.contains("солнечно") -> R.drawable.ic_sunny // Замените на ваши ресурсы
            condition.contains("cloud") || condition.contains("пасмурно") -> R.drawable.ic_cloudy
            condition.contains("rain") || condition.contains("дождь") -> R.drawable.ic_rainy
            condition.contains("snow") || condition.contains("снег") -> R.drawable.ic_snowy
            else -> R.drawable.ic_launcher_foreground // Иконка по умолчанию
        }
        holder.imageViewWeatherIcon.setImageResource(weatherIconResource)

        // --- Конец условного оформления ---

        // Загрузка иконки погоды по URL (если API предоставляет URL и вы хотите его использовать)
        // Если вы используете иконки из ресурсов, этот блок не нужен или нужно добавить логику выбора между URL и ресурсом
        // val iconUrl = currentItem.iconUrl
        // if (iconUrl.isNotEmpty()) {
        //     // Используйте библиотеку для загрузки изображений (Glide, Coil)
        //     // Glide.with(holder.itemView.context).load(iconUrl).into(holder.imageViewWeatherIcon) Вне адаптера
        // }
    }

    // Возвращает размер вашего набора данных (вызывается LayoutManager)
    override fun getItemCount() = weatherList.size
}