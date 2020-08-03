package online.senpai.webo.model.evolve

data class LinesModel(
    val lines: Iterable<LineModel>
) {
    data class LineModel(
        val name: String,
        val text: String,
        val files: Iterable<String>
    )
}
