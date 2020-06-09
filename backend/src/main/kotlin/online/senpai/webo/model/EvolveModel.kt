package online.senpai.webo.model

data class EvolveModel(
    val id: Int,
    val lineName: String,
    val lineText: String,
    val filePath: Iterable<String>
)
