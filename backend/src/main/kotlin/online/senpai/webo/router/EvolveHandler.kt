package online.senpai.webo.router

import io.ktor.application.call
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route
import mu.KLogger
import mu.KotlinLogging
import online.senpai.webo.model.SuccessfulJsonResponse
import online.senpai.webo.model.evolve.LinesModel
import online.senpai.webo.model.evolve.RowsNumberModel
import online.senpai.webo.service.EvolveService
import org.koin.ktor.ext.inject

private val logger: KLogger = KotlinLogging.logger {}

@KtorExperimentalLocationsAPI
@Location("/rowsNumberOf/{name}")
data class RowsNumberOf(val name: String)

@KtorExperimentalLocationsAPI
@Location("/linesOf/{name}")
data class LinesOf(val name: String, val startRow: Int, val count: Int, val sortBy: String, val descending: Boolean)

@KtorExperimentalLocationsAPI
fun Route.evolveHandler() {
    val evolveService: EvolveService by inject()

    get<RowsNumberOf> {
        val model: RowsNumberModel = evolveService.characterRowsNumber(it.name)
        call.respond(SuccessfulJsonResponse(data = model))
    }

    get<LinesOf> {
        val model: LinesModel = evolveService.characterLines(it.name, it.startRow, it.count, it.sortBy, it.descending)
        call.respond(SuccessfulJsonResponse(data = model))
    }
}
