package pereira.agnaldo.iot001.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import pereira.agnaldo.iot001.database.dao.DaoOP
import pereira.agnaldo.iot001.database.dao.DaoOPCard
import pereira.agnaldo.iot001.database.dao.DaoOPCardListItem
import pereira.agnaldo.iot001.database.dao.DaoUser
import pereira.agnaldo.iot001.database.entity.OP
import pereira.agnaldo.iot001.database.entity.OPCard
import pereira.agnaldo.iot001.database.entity.OPCardListItem
import pereira.agnaldo.iot001.database.entity.User

@Database(entities = [OP::class, OPCardListItem::class, OPCard::class, User::class], version = 1)
abstract class IoT001Database : RoomDatabase() {
    abstract fun daoUser(): DaoUser
    abstract fun daoOP(): DaoOP
    abstract fun daoOPCard(): DaoOPCard
    abstract fun daoOPCardListItem(): DaoOPCardListItem

    companion object {
        private var INSTANCE: IoT001Database? = null

        fun destroyInstance() {
            INSTANCE = null
        }

        fun getInstance(context: Context): IoT001Database {
            synchronized(IoT001Database::class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, IoT001Database::class.java, "iot001.db"
                    ).build()
                }
                return INSTANCE!!
            }
        }
    }
}
