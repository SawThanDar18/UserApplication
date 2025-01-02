package co.sawthandar.android.test.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstname: String,
    val lastname: String,
    val email: String
)
