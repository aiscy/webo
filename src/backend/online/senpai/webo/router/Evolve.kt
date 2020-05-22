package online.senpai.webo.router

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import online.senpai.webo.exception.BadRequest
import online.senpai.webo.service.EvolveService
import org.koin.ktor.ext.inject

fun Route.evolveHandler() {
    val evolveService: EvolveService by inject()

    get("/getLinesOf") {
        val character: String = call.request.queryParameters["name"] ?: throw BadRequest()
        try {
            call.respond(evolveService.characterLines(character))
        } catch (e: IllegalArgumentException) {
            call.respondText("Unknown character name!")
        }
    }
}
