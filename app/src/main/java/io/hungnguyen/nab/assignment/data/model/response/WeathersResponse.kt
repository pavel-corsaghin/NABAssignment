package io.hungnguyen.nab.assignment.data.model.response

import com.google.gson.annotations.SerializedName

data class WeathersResponse(
    @SerializedName("cod") 
    val cod: String? = null,
    @SerializedName("message") 
    val message: Double? = null,
    @SerializedName("cnt") 
    val cnt: Int? = null,
    @SerializedName("list") 
    val weathers: ArrayList<Weather> = arrayListOf()
)

data class Temperature(
    @SerializedName("day")
    val day: Double? = null,
    @SerializedName("min")
    val min: Double? = null,
    @SerializedName("max")
    val max: Double? = null,
    @SerializedName("night")
    val night: Double? = null,
    @SerializedName("eve")
    val eve: Double? = null,
    @SerializedName("morn")
    val morn: Double? = null
)

data class Description(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("main")
    val main: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("icon")
    val icon: String? = null
)

data class Weather(
    @SerializedName("dt")
    val dt: Long? = null,
    @SerializedName("sunrise")
    val sunrise: Int? = null,
    @SerializedName("sunset")
    val sunset: Int? = null,
    @SerializedName("temp")
    val temp: Temperature? = Temperature(),
    @SerializedName("pressure")
    val pressure: Int? = null,
    @SerializedName("humidity")
    val humidity: Int? = null,
    @SerializedName("weather")
    val description: ArrayList<Description> = arrayListOf(),
    @SerializedName("speed")
    val speed: Double? = null,
    @SerializedName("deg")
    val deg: Int? = null,
    @SerializedName("gust")
    val gust: Double? = null,
    @SerializedName("clouds")
    val clouds: Int? = null,
    @SerializedName("pop")
    val pop: Double? = null,
    @SerializedName("rain")
    val rain: Double? = null
)