package az.bhos.cloverophile.githubcloneapp

import java.io.Serializable

data class Repository(
    val id: String,
    val name: String,
    val logo: String,
    val commitCount: Int,
    val pullRequestCount: Int,
    val issueCount: Int,
    val description: String,
    val starCount: Int,
    val forkCount: Int,
    val programmingLang: String,
    val organizationId: String
    ) : Serializable
