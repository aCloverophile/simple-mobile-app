package az.bhos.cloverophile.githubcloneapp.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import az.bhos.cloverophile.githubcloneapp.OrganizationsActivity
import az.bhos.cloverophile.githubcloneapp.databinding.ListSelectionViewHolderBinding

class ListSelectionRecyclerViewAdapter:RecyclerView.Adapter<ListSelectionViewHolder>() {

    val listTitles = arrayOf("Issues", "Pull Requests", "Discussions", "Projects", "Repositories", "Organizations", "Starred")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val binding = ListSelectionViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListSelectionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listTitles.size
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.binding.itemString.text = listTitles[position]
        if (listTitles[position] == "Organizations") {
            holder.binding.itemString.setOnClickListener {
                val intent = Intent(it.context, OrganizationsActivity::class.java)
                it.context.startActivity(intent)
            }
        }
    }

}