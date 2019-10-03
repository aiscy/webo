package online.senpai.webo.dao.extension

import org.jetbrains.exposed.sql.CustomFunction
import org.jetbrains.exposed.sql.DateColumnType
import org.joda.time.DateTime

val currentTimeStamp = CustomFunction<DateTime>("CURRENT_TIMESTAMP", DateColumnType(time = true))
