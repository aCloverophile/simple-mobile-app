package az.bhos.cloverophile.githubcloneapp.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import az.bhos.cloverophile.githubcloneapp.Organization
import az.bhos.cloverophile.githubcloneapp.OrganizationDetailActivity
import az.bhos.cloverophile.githubcloneapp.databinding.OrganizationsViewHolderBinding
import com.bumptech.glide.Glide

class OrganizationsAdapter(private val organizations: List<Organization>) : RecyclerView.Adapter<OrganizationsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrganizationsViewHolder {
        val binding = OrganizationsViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrganizationsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrganizationsViewHolder, position: Int) {
        val organization = organizations[position]
        holder.binding.organizationName.text = organization.name
        holder.binding.organizationLocation.text = organization.location
        Glide.with(holder.itemView.context).load(organization.avatar).into(holder.binding.organizationAvatar)

        holder.itemView.setOnClickListener {
             val intent = Intent(holder.itemView.context, OrganizationDetailActivity::class.java)
             intent.putExtra("organization", organization)
             holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = organizations.size
}