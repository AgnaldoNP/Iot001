package pereira.agnaldo.iot001.database.entity

import android.arch.persistence.room.ColumnInfo
import pereira.agnaldo.iot001.extensions.toDBDateString
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Suppress("JoinDeclarationAndAssignment")
abstract class BaseEntity : Serializable {

    @SerializedName("createdAt")
    @ColumnInfo(name = CREATED_AT)
    var createdAt: String

    @SerializedName("createdAt")
    @ColumnInfo(name = UPDATE_AT)
    var updateAt: String

    companion object {
        const val CREATED_AT = "createdAt"
        const val UPDATE_AT = "updateAt"
    }

    init {
        createdAt = Date().toDBDateString()
        updateAt = Date().toDBDateString()
    }

}
