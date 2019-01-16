package pereira.agnaldo.iot001.database.entity

import android.arch.persistence.room.*
import pereira.agnaldo.iot001.extensions.toDBDate
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity(
    tableName = OPCard.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = OPCard::class,
        parentColumns = arrayOf(OP.ID),
        childColumns = arrayOf(OPCard.OP_ID)
    )],
    indices = [Index(
        OPCard.OP_ID,
        unique = true
    )]
)
data class OPCard(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") @ColumnInfo(name = ID) val id: Long,
    @SerializedName("opId") @ColumnInfo(name = OP_ID) val opId: Long,
    @SerializedName("oee") @ColumnInfo(name = OEE) val oee: Float,
    @SerializedName("dateRangeType") @ColumnInfo(name = DATE_RANGE_TYPE) val dateRangeType: String
) : BaseEntity(), Serializable {

    @Ignore
    var opcardItems: ArrayList<OPCardListItem> = ArrayList()

    @Ignore
    var op: OP? = null

    constructor(id: Long, op: OP, oee: Float, dateRangeType: String) : this(id, op.id, oee, dateRangeType) {
        this.op = op
    }

    companion object {
        const val TABLE_NAME = "OPCard"
        const val ID = "id"
        const val OP_ID = "opId"
        const val OEE = "oee"
        const val DATE_RANGE_TYPE = "dateRangeType"
    }

    fun getOpcardItemsSortedByDate(): ArrayList<OPCardListItem> {
        opcardItems.sortBy { it.date }
        return opcardItems
    }

    @Ignore
    private var fromDate: Date? = null

    fun getFromDate(): Date? {
        if (fromDate == null) {
            fromDate = getOpcardItemsSortedByDate().first()?.date?.toDBDate()
        }

        return fromDate
    }

    @Ignore
    private var toDate: Date? = null

    fun getToDate(): Date? {
        if (toDate == null) {
            toDate = getOpcardItemsSortedByDate().last()?.date?.toDBDate()
        }
        return toDate
    }

}
