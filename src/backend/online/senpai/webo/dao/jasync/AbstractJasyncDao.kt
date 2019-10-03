package online.senpai.webo.dao.jasync

import com.github.jasync.sql.db.SuspendingConnection
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class AbstractJasyncDao<T> : GenericDao<T>, KoinComponent {
    protected val connection: SuspendingConnection by inject()
}
