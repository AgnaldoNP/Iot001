package pereira.agnaldo.iot001.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import pereira.agnaldo.iot001.database.entity.OP
import pereira.agnaldo.iot001.database.entity.OPCard
import pereira.agnaldo.iot001.database.entity.User

@Dao
abstract class DaoOP : DaoBase<OP> {

    @Query("SELECT * FROM '${OP.TABLE_NAME}'")
    abstract fun getAll(): List<OP>

    @Query("DELETE FROM '${OP.TABLE_NAME}'")
    abstract fun deleteAll()


    @Query("SELECT * FROM ${OP.TABLE_NAME} WHERE ${OP.ID} = :id LIMIT 1")
    abstract fun getById(id: Long): OP

}
