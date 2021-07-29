package com.example.sunnyweather.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.example.sunnyweather.logic.model.Place
import com.example.sunnyweather.logic.network.SunnyWeatherNetWork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.RuntimeException

object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetWork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val place = placeResponse.places
                Result.success(place)
            } else {
                Log.d("TAG","Failed Request!")
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))

            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}