package az.bhos.cloverophile.githubcloneapp

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import az.bhos.cloverophile.githubcloneapp.ui.main.RepositoriesAdapter
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class RepositoriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding = ActivityRepositoriesBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_repositories)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recyclerView = findViewById<RecyclerView>(R.id.repositoriesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val organizationId = intent.getIntExtra("organizationId", -1)
        Log.d("DEBUG", "Organization ID: $organizationId")

        thread {
            val url = URL("https://62961db375c34f1f3b299286.mockapi.io/organizations/$organizationId/repositories")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connect()

            val responseCode = connection.responseCode
            if (responseCode == 200) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = reader.readText()
                reader.close()
                val gson = Gson()
                val repositories = gson.fromJson(response, Array<Repository>::class.java).toList()

                runOnUiThread {
                    recyclerView.adapter = RepositoriesAdapter(repositories)
                }

                Log.d("RepositoriesActivity", "Response: $response")
            } else {
                Log.e("RepositoriesActivity", "Failed to fetch data")
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