package online.senpai.webo.domain

/*data class EvolveCharacterLine(
    var id: Int? = null,
    var lineName: String,
    var lineText: String,
    var lineOwner: EvolveCharacter,
    var filePath: String
) *//*{
    constructor(
        id: Int,
        lineName: String,
        lineText: String,
        lineOwner: EvolveCharacter,
        filePath: String
    ): this(lineName, lineText,lineOwner, filePath) {
        this.id = id
    }
    var id: Int by Delegates.notNull()
}*/
data class EvolveCharacterLine(
    var lineName: String,
    var lineText: String,
    var filePath: Iterable<String>
)
