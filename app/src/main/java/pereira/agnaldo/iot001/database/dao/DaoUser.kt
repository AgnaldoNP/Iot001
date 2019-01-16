package pereira.agnaldo.iot001.database.dao

import android.arch.persistence.room.*
import pereira.agnaldo.iot001.database.entity.User

@Dao
abstract class DaoUser : DaoBase<User> {

    @Query("SELECT * FROM '${User.TABLE_NAME}' LIMIT 1")
    abstract fun getUser(): User

    @Query("DELETE FROM '${User.TABLE_NAME}'")
    abstract fun deleteAll()

}