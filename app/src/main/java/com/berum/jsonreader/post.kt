package com.berum.jsonreader

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class post {
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

}