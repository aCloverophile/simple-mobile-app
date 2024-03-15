package az.bhos.cloverophile.githubcloneapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import az.bhos.cloverophile.githubcloneapp.databinding.ActivityOrganizationDetailBinding
import az.bhos.cloverophile.githubcloneapp.databinding.ActivityRepositoryDetailBinding
import com.bumptech.glide.Glide

class RepositoryDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepositoryDetailBinding
    private var repositoryId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val repository = intent.getSerializableExtra("repository") as Repository

        repositoryId = repository.id.toInt()

        binding.repositoryName.text = repository.name
        binding.repositoryDescription.text = repository.description
//        binding.repositoryStars.text = "Stars: ${repository.starCount.toString()}"
//        binding.repositoryForks.text = repository.forkCount.toString()
//        binding.repositoryCommits.text = repository.commitCount.toString()
//        binding.repositoryPullRequests.text = repository.pullRequestCount.toString()
//        binding.repositoryIssues.text = repository.issueCount.toString()

        binding.repositoryStars.text = getString(R.string.repository_stars, repository.starCount.toString())
        binding.repositoryForks.text = getString(R.string.repository_forks, repository.forkCount.toString())
        binding.repositoryCommits.text = getString(R.string.repository_commits, repository.commitCount.toString())
        binding.repositoryPullRequests.text = getString(R.string.repository_pull_requests, repository.pullRequestCount.toString())
        binding.repositoryIssues.text = getString(R.string.repository_issues, repository.issueCount.toString())

        Glide.with(this).load(repository.logo).into(binding.repositoryLogo)

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