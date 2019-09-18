package com.example.basicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_weather.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WeatherActivity : AppCompatActivity() {

    private var weatherData: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        weatherData = findViewById(R.id.textView)

        button.setOnClickListener { getCurrentData() }
    }

    private fun getCurrentData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(lat, lon, units, AppId)

        Log.d("REQUEST", call.toString() + "")

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!

                    val stringBuilder = "Ciudad: " +
                            weatherResponse.sys!!.country +
                            "\n" +
                            "Temperatura: " +
                            weatherResponse.main!!.temp +
                            "°C \n" +
                            "Temperatura(Min): " +
                            weatherResponse.main!!.temp_min +
                            "\n" +
                            "Temperatura(Max): " +
                            weatherResponse.main!!.temp_max +
                            "\n" +
                            "Humedad: " +
                            weatherResponse.main!!.humidity +
                            "\n" +
                            "Presion: " +
                            weatherResponse.main!!.pressure

                    weatherData!!.text = stringBuilder
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                weatherData!!.text = t.message
            }
        })
    }

    companion object {
        var BaseUrl = "http://api.openweathermap.org/"
        var AppId = "4505838efcbbad0c95be94438c78e77d"
        var lat = "21.67"
        var lon = "-98.87"
        var units = "metric"
    }

}