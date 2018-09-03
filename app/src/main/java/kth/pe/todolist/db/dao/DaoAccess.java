package kth.pe.todolist.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import kth.pe.todolist.db.entity.Items;

@Dao
public interface DaoAccess {
    @Insert
    void insertOnlySingleItem(Items items);
    @Query("select * from items order by id desc")
    List<Items> fetchAllItems();
}
