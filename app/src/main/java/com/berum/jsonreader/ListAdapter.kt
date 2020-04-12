package com.berum.jsonreader

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.io.IOException
import java.net.URL
import java.util.*

class ListAdapter(context: Context?, private val layout: Int, private val pst: ArrayList<post>) : ArrayAdapter<post>(context!!, layout, pst) {
    private val inflater: LayoutInflater
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = inflater.inflate(layout, parent, false)
        val orgs = pst[position]
        val image_url = orgs.img
        val id = orgs.id
        val name = orgs.name
        val companyName = view.findViewById<TextView>(R.id.companyname)
        val companyId = view.findViewById<TextView>(R.id.companyid)
        val imview = view.findViewById<ImageView>(R.id.companyimage)

        // Асинхронная загрузка изображений не потребовалась
        try {
            val newurl = URL("http://megakohz.bget.ru/test_task/$image_url")
            val mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream())
            imview.setImageBitmap(mIcon_val)
        } catch (e: IOException) {
            e.printStackTrace()
            Log.d("Failed to download", e.toString())
        }
        Log.d("Added index", position.toString())
        companyId.text = id
        companyName.text = name
        return view
    }

    init {
        inflater = LayoutInflater.from(context)
    }
}