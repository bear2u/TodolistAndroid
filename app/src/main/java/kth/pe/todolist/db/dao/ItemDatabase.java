package kth.pe.todolist.db.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kth.pe.todolist.db.entity.Items;

@Database(entities = {Items.class}, version = 1, exportSchema = false)
public abstract class ItemDatabase extends RoomDatabase {
    public abstract ItemDao daoAccess();
}
