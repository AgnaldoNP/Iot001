@file:Suppress("SpellCheckingInspection")

package pereira.agnaldo.iot001.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import pereira.agnaldo.iot001.database.entity.OP
import pereira.agnaldo.iot001.database.entity.OPCard
import pereira.agnaldo.iot001.database.entity.OPCardListItem
import pereira.agnaldo.iot001.database.entity.User

@Dao
abstract class DaoOPCardListItem : DaoBase<OPCardListItem> {

    @Query("DELETE FROM '${OPCardListItem.TABLE_NAME}'")
    abstract fun deleteAll()

    @Query("SELECT * FROM ${OPCardListItem.TABLE_NAME} WHERE ${OPCardListItem.OPCARD_ID} = :opCardId")
    abstract fun getAllByOpCard(opCardId: Long): List<OPCardListItem>


    @Query(
        """
        SELECT * FROM '${OPCardListItem.TABLE_NAME}'
         WHERE ${OPCardListItem.OPCARD_ID} = :opCardId
         AND strftime('%s', ${OPCardListItem.DATE}) BETWEEN strftime('%s', :startDate) AND strftime('%s', :endDate)
            """
    )
    abstract fun getByOpCardAndRange(opCardId: Long, startDate: String, endDate: String): List<OPCardListItem>

}