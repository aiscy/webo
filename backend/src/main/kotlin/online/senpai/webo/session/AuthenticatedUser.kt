package online.senpai.webo.session

import io.ktor.auth.Principal

data class AuthenticatedUser(val userId: Long, val userName: String, val token: String) : Principal
