package pereira.agnaldo.iot001.database.dao

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import pereira.agnaldo.iot001.database.entity.BaseEntity

interface DaoBase<T : BaseEntity> {
    @Insert
    fun insert(vararg obj: T)

    @Insert
    fun insert(T: List<T>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(obj: T)

    @Delete
    abstract fun delete(vararg objs: T)

    @Delete
    abstract fun delete(T: List<T>?)

    @Delete
    abstract fun delete(obj: T)

}