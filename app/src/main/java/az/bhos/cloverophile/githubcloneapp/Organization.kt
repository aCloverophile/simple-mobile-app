package az.bhos.cloverophile.githubcloneapp

import java.io.Serializable

data class Organization(
    val id: String,
    val name: String,
    val avatar: String,
    val location: String,
    val createdAt: String
) : Serializable