package com.test.pumpit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * mark main params
 */
data class CarModel(
    @Expose
    val latitude: Float,
    @Expose
    val longitude: Float,
    val pin_img: String,
    val color: String,
    @Expose
    val model: String,
    val img: String,
    val transmission: String,
    val fuel: Int,
    val discount: Int,
    val is_parther: Boolean
)

/**
 * api contains not only 'cars' but 'areas' and 'area_groups'
 */
data class YoudriveResponse(
    @SerializedName("cars")
    @Expose
    val cars: ArrayList<CarModel>
)