package com.beryl.seabunsource.data.database.entities

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beryl.seabunsource.data.splatnet2.KeyName
import com.beryl.seabunsource.data.splatnet2.TimePeriod
import com.beryl.seabunsource.data.splatnet2.entities.Stage

@Entity(tableName = "TimePeriods")
data class TimePeriodEntity(
    @PrimaryKey @ColumnInfo(name = "Id") var id: Int,
    @ColumnInfo(name = "GameMode") val gameMode: String,
    @ColumnInfo(name = "Rule") val rule: String,
    @ColumnInfo(name = "StartTime") val start: Long,
    @ColumnInfo(name = "EndTime") val end: Long
) {

    fun toSplatnet(stages: List<Stage>, context: Context): TimePeriod {
        return TimePeriod(
            KeyName(gameMode, context),
            KeyName(rule, context), stages[0], stages[1], start, end
        )
    }
}