package az.bhos.cloverophile.githubcloneapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import az.bhos.cloverophile.githubcloneapp.ui.main.OrganizationsAdapter
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class OrganizationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organizations)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recyclerView = findViewById<RecyclerView>(R.id.organizationsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        thread {
            val url = URL("https://62961db375c34f1f3b299286.mockapi.io/organizations")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            val responseCode = connection.responseCode
            if (responseCode == 200) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = reader.readText()
                reader.close()
                val gson = Gson()
                val organizations = gson.fromJson(response, Array<Organization>::class.java).toList()

                runOnUiThread {
                    recyclerView.adapter = OrganizationsAdapter(organizations)
                }

                Log.d("OrganizationsActivity", "Response: $response")
            } else {
                Log.e("OrganizationsActivity", "Failed to fetch data")
            }
            connection.disconnect()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Respond to the action bar's Up/Home button
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}