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

    get("/rowsNumberOf") {
        val name: String = call.request.queryParameters["name"] ?: throw BadRequest()
        call.respond(evolveService.characterRowsNumber(name))
    }

    get("/linesOf") {
        val name: String = call.request.queryParameters["name"] ?: throw BadRequest()
        try {
            call.respond(evolveService.characterLines(name))
        } catch (e: IllegalArgumentException) {
            call.respondText("Unknown character name!")
        }
    }
}
