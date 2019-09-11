package com.sorty.tapyourthingsjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Adding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
    }
    public void save (View view) {
        final EditText editText = findViewById(R.id.name);
        String insert = editText.getText().toString();
        if (insert.isEmpty() || insert.trim().length() == 0){
            editText.setError("is empty");
            Toast.makeText(Adding.this, "is empty" , Toast.LENGTH_LONG).show();
        }
        else {
            if (unique(insert)) {
                String count = String.valueOf(Tap.count(Tap.class));
                Toast.makeText(Adding.this, count, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Adding.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    public void back(View view){
        Intent intent = new Intent(Adding.this,MainActivity.class);
        startActivity(intent);
    }

    private boolean unique(String thing){
        if(Tap.find(Tap.class,"thing= ?",thing).isEmpty()){
            Tap tap=new Tap();
            tap.setTap(0);
            tap.setThing(thing);
            tap.save();
            return true;
        }else
            Toast.makeText(Adding.this, "Already exists!" , Toast.LENGTH_LONG).show();
        return false;
    }


}


