package ru.mirea.kanban.room.kanbanList

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityKanbanList(
    @ColumnInfo(name = "name") var name: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

// TODO: fix literals
