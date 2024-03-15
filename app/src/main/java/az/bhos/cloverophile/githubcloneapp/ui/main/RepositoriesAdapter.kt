package az.bhos.cloverophile.githubcloneapp.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import az.bhos.cloverophile.githubcloneapp.Repository
import az.bhos.cloverophile.githubcloneapp.RepositoryDetailActivity
import az.bhos.cloverophile.githubcloneapp.databinding.RepositoriesViewHolderBinding
import com.bumptech.glide.Glide

class RepositoriesAdapter(private var repositories: List<Repository>) : RecyclerView.Adapter<RepositoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val binding = RepositoriesViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        val repository = repositories[position]
        holder.binding.repositoryName.text = repository.name
        holder.binding.repositoryDescription.text = repository.description
        Glide.with(holder.itemView.context).load(repository.logo).into(holder.binding.repositoryLogo)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, RepositoryDetailActivity::class.java)
            intent.putExtra("repository", repository)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = repositories.size

    fun updateRepositories(newRepositories: List<Repository>) {
        repositories = newRepositories
        notifyDataSetChanged()
    }
}