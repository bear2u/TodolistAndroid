package kth.pe.todolist;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import kth.pe.todolist.db.entity.Items;

public class WriteActivity extends AppCompatActivity {
    private EditText titleView, contentView;
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        titleView = findViewById(R.id.etTitle);
        contentView = findViewById(R.id.etContent);

        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        setTitle("Write Todo");

        findViewById(R.id.btnSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String title = titleView.getText().toString();
                final String content = contentView.getText().toString();

                if(TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
                    return;
                }

                Intent intent = new Intent();
                final Items items = new Items();
                items.setTitle(title);
                items.setContent(content);

                intent.putExtra("item", items);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

}
