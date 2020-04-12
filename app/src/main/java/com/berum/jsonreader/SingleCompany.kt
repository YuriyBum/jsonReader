package com.berum.jsonreader

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SingleCompany {
    // Используем String, поскольку возможен буквенный id
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("img")
    @Expose
    var img: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("lat")
    @Expose
    var lat: Double? = null

    @SerializedName("lon")
    @Expose
    var lon: Double? = null

    @SerializedName("www")
    @Expose
    var www: String? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null

}