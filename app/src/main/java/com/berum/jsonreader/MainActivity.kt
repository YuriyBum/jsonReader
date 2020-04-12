package com.berum.jsonreader

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.Toast
import com.berum.jsonreader.NetworkService.Companion.instance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    var postList = ArrayList<post>()
    var context: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContentView(R.layout.activity_main)
        val adapter = ListAdapter(this, R.layout.company_cell_layout, postList)
        instance!!.jSONApi.post
                .enqueue(object : Callback<ArrayList<post>> {
                    override fun onResponse(call: Call<ArrayList<post>>, response: Response<ArrayList<post>>) {
                        //Про addAll знаю, но у меня работало некорректно. Потребовалось логирование
                        for (i in response.body()!!.indices) {
                            postList.add(response.body()!![i])
                            Log.d("Posted ", """
     id: ${postList[i].id}name: ${postList[i].name}image_url: ${postList[i].img}

     """.trimIndent())
                        }
                        //Заполнение ячейки
                        val gw = findViewById<ListView>(R.id.allList)
                        gw.adapter = adapter
                        gw.onItemClickListener = OnItemClickListener { parent, view, position, id ->
                            val intent = Intent(this@MainActivity, CompanySingle::class.java)
                            intent.putExtra("companyId", postList[position].id)
                            startActivity(intent)
                            Toast.makeText(applicationContext, "Переход на страницу компании... ",
                                    Toast.LENGTH_SHORT).show()
                        }
                        //Ячейка заполнена
                    }

                    override fun onFailure(call: Call<ArrayList<post>>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
    }
    
}