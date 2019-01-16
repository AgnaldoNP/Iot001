package pereira.agnaldo.iot001.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = OP.TABLE_NAME)
data class OP(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") @ColumnInfo(name = ID) val id: Long,
    @SerializedName("name") @ColumnInfo(name = NAME) val name: String,
    @SerializedName("description") @ColumnInfo(name = DESCRIPTION) val description: String
) : BaseEntity(), Serializable {

    companion object {
        const val TABLE_NAME = "OP"
        const val ID = "id"
        const val NAME = "name"
        const val DESCRIPTION = "description"
    }

}
