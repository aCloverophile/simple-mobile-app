package az.bhos.cloverophile.githubcloneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import az.bhos.cloverophile.githubcloneapp.databinding.ActivityOrganizationDetailBinding
import com.bumptech.glide.Glide

class OrganizationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrganizationDetailBinding
    private var organizationId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrganizationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val organization = intent.getSerializableExtra("organization") as Organization

        organizationId = organization.id.toInt()

        binding.organizationName.text = organization.name
        binding.organizationLocation.text = organization.location
        binding.organizationCreatedAt.text = organization.createdAt
        binding.repositoriesButton.setOnClickListener {
            val intent = Intent(this, RepositoriesActivity::class.java)
            intent.putExtra("organizationId", organizationId)
            startActivity(intent)
        }
        Glide.with(this).load(organization.avatar).into(binding.organizationAvatar)
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