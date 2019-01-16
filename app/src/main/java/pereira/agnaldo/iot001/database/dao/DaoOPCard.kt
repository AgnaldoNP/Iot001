@file:Suppress("SpellCheckingInspection")

package pereira.agnaldo.iot001.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.content.Context
import pereira.agnaldo.iot001.database.IoT001Database
import pereira.agnaldo.iot001.database.entity.OP
import pereira.agnaldo.iot001.database.entity.OPCard

@Dao
abstract class DaoOPCard : DaoBase<OPCard> {

    @Query("DELETE FROM '${OPCard.TABLE_NAME}'")
    abstract fun deleteAll()

    @Query("SELECT * FROM '${OPCard.TABLE_NAME}'")
    abstract fun getAll(): List<OPCard>?

    @Query("SELECT * FROM '${OPCard.TABLE_NAME}' WHERE ${OPCard.OP_ID} = :opId")
    abstract fun getByOpId(opId: Long): List<OPCard>


    fun insertAll(context: Context, opCard: OPCard) {
        insert(opCard)

        val daoOPCardListItem = IoT001Database.getInstance(context).daoOPCardListItem()
        opCard.opcardItems.forEach { daoOPCardListItem.insert(it) }
    }


    fun getAllFilled(context: Context): List<OPCard> {
        val opCards = getAll()
        val daoOPCardListItem = IoT001Database.getInstance(context).daoOPCardListItem()
        val daoOP = IoT001Database.getInstance(context).daoOP()
        opCards?.forEach {
            it.op = daoOP.getById(it.opId)
            it.opcardItems.addAll(daoOPCardListItem.getAllByOpCard(it.id))
        }
        return opCards ?: ArrayList()
    }

    fun getByOpdAndRange(context: Context, op: OP, startDate: String, endDate: String): List<OPCard> {
        // TODO adicionar tipo de range de data (minutos, segundos, etc) na query
        val daoOPCardListItem = IoT001Database.getInstance(context).daoOPCardListItem()
        val opCards = getByOpId(op.id)
        opCards.forEach {
            it.op = op
            it.opcardItems.addAll(daoOPCardListItem.getByOpCardAndRange(it.id, startDate, endDate))
        }
        return opCards
    }

}