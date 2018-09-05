package kth.pe.todolist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import kth.pe.todolist.db.entity.Items;
import kth.pe.todolist.repository.TodoListRepository;

public class MainViewModel extends AndroidViewModel {
    private TodoListRepository mRepository;
    private LiveData<List<Items>> mAllItems;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TodoListRepository(application);
        mAllItems = mRepository.getAllItems();
    }

    public LiveData<List<Items>> getAllItems() {
        return mAllItems;
    }

    public void insert(Items item) {
        mRepository.insert(item);
    }

}
