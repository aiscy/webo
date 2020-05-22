package online.senpai.webo.table.common

import org.postgresql.util.PGobject

class PostgresEnum<T : Enum<T>>(enumTypeName: String, enumValue: T?) : PGobject() {
    init {
        value = enumValue?.name
        type = enumTypeName
    }
}
