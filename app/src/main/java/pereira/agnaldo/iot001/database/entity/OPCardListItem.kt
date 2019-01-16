package pereira.agnaldo.iot001.database.entity

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = OPCardListItem.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = OPCard::class,
        parentColumns = arrayOf(OPCard.ID),
        childColumns = arrayOf(OPCardListItem.OPCARD_ID)
    )],
    indices = [Index(
        OPCardListItem.OPCARD_ID, OPCardListItem.DATE,
        unique = true
    )]
)
data class OPCardListItem(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") @ColumnInfo(name = ID) val id: Long,
    @SerializedName("opCardId") @ColumnInfo(name = OPCARD_ID) val opCardId: Long,
    @SerializedName("date") @ColumnInfo(name = DATE) val date: String,
    @SerializedName("quantity") @ColumnInfo(name = QUANTITY) val quantity: Long,
    @SerializedName("description") @ColumnInfo(name = DESCRIPTION) val description: String
) : BaseEntity(), Serializable {

    constructor(id: Long, opCard: OPCard, date: String, quantity: Long, description: String) : this (id, opCard.id, date, quantity, description)

    companion object {
        const val TABLE_NAME = "OPCardListItem"
        const val ID = "id"
        const val OPCARD_ID = "opCardId"
        const val DATE = "date"
        const val QUANTITY = "quantity"
        const val DESCRIPTION = "description"
    }

}
