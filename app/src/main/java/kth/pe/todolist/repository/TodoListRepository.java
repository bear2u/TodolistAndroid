package kth.pe.todolist.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import kth.pe.todolist.MainActivity;
import kth.pe.todolist.db.dao.ItemDao;
import kth.pe.todolist.db.dao.ItemDatabase;
import kth.pe.todolist.db.entity.Items;

import static android.arch.persistence.room.Room.databaseBuilder;

public class TodoListRepository {
    private ItemDao itemDao;
    private LiveData<List<Items>> mAllItems;

    public TodoListRepository(Application application) {
        ItemDatabase itemDatabase = databaseBuilder(application, ItemDatabase.class, MainActivity.DATABASE_NAME).fallbackToDestructiveMigration().build();
        itemDao = itemDatabase.daoAccess();
        mAllItems = itemDao.fetchAllItems();
    }

    public LiveData<List<Items>> getAllItems() {
        return mAllItems;
    }

    public void insert (Items items) {
        new insertAsyncTask(itemDao).execute(items);
    }

    class insertAsyncTask extends AsyncTask<Items, Void, Void> {
        private ItemDao mAsyncTaskDao;
        insertAsyncTask(ItemDao itemDao) {
            this.mAsyncTaskDao = itemDao;
        }

        @Override
        protected Void doInBackground(Items... items) {
            itemDao.insertOnlySingleItem(items[0]);
            return null;
        }
    }


}
