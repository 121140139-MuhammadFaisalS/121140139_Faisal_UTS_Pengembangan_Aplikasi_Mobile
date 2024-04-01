package com.faisal.uts_mobile_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BerandaActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Mengambil data pengguna dari API
        fetchUsers()
    }

    private fun fetchUsers() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/users")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.getUsers()

        call.enqueue(object : Callback<UserListResponse> {
            override fun onResponse(call: Call<UserListResponse>, response: Response<UserListResponse>) {
                if (response.isSuccessful) {
                    val userList = response.body()?.data
                    userList?.let {
                        if (it.isNotEmpty()) {
                            recyclerView.adapter = UserAdapter(it)
                        } else {
                            showErrorMessage("Tidak ada data pengguna")
                        }
                    } ?: showErrorMessage("Data pengguna kosong")
                } else {
                    showErrorMessage("Gagal mendapatkan data pengguna")
                }
            }

            override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                showErrorMessage("Error: ${t.message}")
            }
        })
    }

    private fun showErrorMessage(message: String) {
        Toast.makeText(this@BerandaActivity, message, Toast.LENGTH_SHORT).show()
    }
}