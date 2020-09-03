package com.example.roomexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.room.Room;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import com.example.roomexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "myDB")
                .allowMainThreadQueries()
                .build();
        binding.resultText.setText(db.todoDao().getAll().toString());

        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.todoDao().insert(new Todo(binding.todoEdit.getText().toString()));
                binding.resultText.setText(db.todoDao().getAll().toString());
                binding.todoEdit.setText(null);
            }
        });


    }
}
