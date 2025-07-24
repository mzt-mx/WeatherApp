package com.example.mmxweather.ui.weather // Укажите свой пакет для UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mmxweather.R // Убедитесь, что R импортируется правильно
import com.example.mmxweather.data.WeatherEntry

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

        // Здесь нужно будет загружать иконку погоды, например, с помощью Glide или Coil
        // Пока оставим заглушку
        // holder.imageViewWeatherIcon.setImageResource(...) или использовать библиотеку для загрузки изображений
    }

    // Возвращает размер вашего набора данных (вызывается LayoutManager)
    override fun getItemCount() = weatherList.size
}