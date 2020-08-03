package online.senpai.webo.model

data class UnsuccessfulJsonResponse(
    override val message: String = ""
) : GenericJsonResponse {
    override val success: Boolean = false
}
