package com.berum.jsonreader

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.URL
import java.util.*

class CompanySingle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_single)
        val args = intent.extras
        val s = args!!["companyId"].toString()
        NetworkService.instance!!.jSONApi.getCompany(s)
                .enqueue(object : Callback<ArrayList<SingleCompany>> {
                    override fun onResponse(call: Call<ArrayList<SingleCompany>>, response: Response<ArrayList<SingleCompany>>) {
                        //Ответ сервера воспринимается как масив, поэтому результат обрабатывается не как объект,
                        // а как массив с единственной строкой
                        val id = findViewById<TextView>(R.id.companyid)
                        id.text = response.body()!![0].id
                        val name = findViewById<TextView>(R.id.companyname)
                        name.text = response.body()!![0].name
                        val description = findViewById<TextView>(R.id.cdescription)
                        description.text = response.body()!![0].description
                        val phone = findViewById<TextView>(R.id.phone)
                        phone.text = response.body()!![0].phone
                        val website = findViewById<TextView>(R.id.cwebsite)
                        website.text = response.body()!![0].www
                        val coordinat = findViewById<TextView>(R.id.coordinates)
                        coordinat.text = response.body()!![0].lat.toString() + ", " + response.body()!![0].lon
                        val avatar = findViewById<ImageView>(R.id.companyimage)
                        try {
                            val newurl = URL("http://megakohz.bget.ru/test_task/" + response.body()!![0].img)
                            val mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream())
                            avatar.setImageBitmap(mIcon_val)
                        } catch (e: IOException) {
                            e.printStackTrace()
                            Log.d("Failed to download", e.toString())
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<SingleCompany>>, t: Throwable) {
                        t.printStackTrace()
                        val id = findViewById<TextView>(R.id.companyid)
                        id.text = "FAILED: $t"
                    }
                })
        val btn = findViewById<Button>(R.id.returnButton)
        btn.setOnClickListener {
            val intent = Intent(this@CompanySingle, MainActivity::class.java)
            startActivity(intent)
        }
    }
}