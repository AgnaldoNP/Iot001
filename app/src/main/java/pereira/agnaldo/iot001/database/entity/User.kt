package pereira.agnaldo.iot001.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity(tableName = User.TABLE_NAME)
data class User(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") @ColumnInfo(name = ID) val id: Int,
    @SerializedName("name") @ColumnInfo(name = NAME) val name: String,
    @SerializedName("name") @ColumnInfo(name = PHOTO_URL) val photoUrl: String,
    @SerializedName("name") @ColumnInfo(name = EMAIL) val email: String,
    @SerializedName("auth") @ColumnInfo(name = AUTH) val auth: String
) : BaseEntity(), Serializable {

    companion object {
        const val TABLE_NAME = "User"
        const val ID = "id"
        const val NAME = "name"
        const val AUTH = "auth"
        const val PHOTO_URL = "photoUrl"
        const val EMAIL = "email"
    }

}
