package kth.pe.todolist;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import kth.pe.todolist.db.dao.ItemDatabase;
import kth.pe.todolist.db.entity.Items;

import static android.arch.persistence.room.Room.databaseBuilder;

public class WriteActivity extends AppCompatActivity {
    private ItemDatabase itemDatabase;
    private EditText titleView, contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        titleView = findViewById(R.id.etTitle);
        contentView = findViewById(R.id.etContent);

        initDB();
        setTitle("Write Todo");

        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });
    }

    private void initDB() {
        itemDatabase = databaseBuilder(getApplicationContext(), ItemDatabase.class, MainActivity.DATABASE_NAME).fallbackToDestructiveMigration().build();
    }

    private void addItem() {
        final String title = titleView.getText().toString();
        final String content = contentView.getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Items items = new Items();
                items.setTitle(title);
                items.setContent(content);
                itemDatabase.daoAccess().insertOnlySingleItem(items);
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WriteActivity.this, "insert done", Toast.LENGTH_SHORT).show();
                        titleView.setText("");
                        contentView.setText("");
                    }
                });
            }
        }).start();
    }



}
