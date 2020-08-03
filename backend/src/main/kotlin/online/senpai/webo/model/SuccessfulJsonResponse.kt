package online.senpai.webo.model

data class SuccessfulJsonResponse(
    val data: Any,
    override val message: String = ""
) : GenericJsonResponse {
    override val success: Boolean = true
}
