package com.example.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Ethan on 2021/2/26
 *
 * @author Ethan
 */
data class Place(val name: String , val location: Location , @SerializedName("formatted_address") val address: String) {
}