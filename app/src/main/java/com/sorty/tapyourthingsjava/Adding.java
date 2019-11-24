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
        final EditText editTextCount=findViewById(R.id.count);
        String countOp=editTextCount.getText().toString();

        String insert = editText.getText().toString();
        if (insert.isEmpty() || insert.trim().length() == 0){
            editText.setError("is empty");
            Toast.makeText(Adding.this, "is empty" , Toast.LENGTH_LONG).show();
        }
        else {
            if (unique(insert,countOp)) {
                String totalRow = String.valueOf(Tap.count(Tap.class));
                Toast.makeText(Adding.this, totalRow, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Adding.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    public void back(View view){
        Intent intent = new Intent(Adding.this,MainActivity.class);
        startActivity(intent);
    }

    private boolean unique(String thing, String count){
        if(Tap.find(Tap.class,"thing= ?",thing).isEmpty()){
            Tap tap=new Tap();
            tap.setTap(getCount(count));
            tap.setThing(thing);
            tap.save();
            return true;
        }else
            Toast.makeText(Adding.this, "Already exists!" , Toast.LENGTH_LONG).show();
        return false;
    }

    private  int getCount(String count){
        if( count==null ||count.isEmpty())
            return 0;
        try {

            return Integer.valueOf(count);

        }catch (Exception e){
            return 0;
        }
    }

}


