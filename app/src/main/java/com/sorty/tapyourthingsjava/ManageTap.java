package com.sorty.tapyourthingsjava;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ManageTap extends AppCompatActivity {
    private Tap tap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_tap);
        Intent data=getIntent();
        tap=(Tap) data.getSerializableExtra("tap");
        final EditText thing=findViewById(R.id.thingEditText);
        final EditText count=findViewById(R.id.countEditText);
        thing.setText(tap.getThing());
        count.setText( String.valueOf(tap.getTap()));
    }

    public void Save(View view){
        EditText edThing=findViewById(R.id.thingEditText);
        EditText edCount=findViewById(R.id.countEditText);
        int count =Integer.valueOf(edCount.getText().toString());
        String thing=edThing.getText().toString();
        if(thing.equals(tap.getThing()) && count==tap.getTap()){
            System.out.println("dddddd rrrrrrrrrrrrrrrrr  the same" );
            goHome("don't change because the value are the same");
            return;
        }
        tap.save();
        if(tap.delete()){
            Tap tap=new Tap();
            tap.setThing(thing);
            tap.setTap(count);
            tap.save();
            goHome("update item");
        }else
            goHome("error to update the item");
    }

    public void Delete(View view){
        String msg;
        tap.save();
        if(tap.delete())
           msg="deleted item";
        else
            msg="error when try to delete the item";
        goHome(msg);

    }

    private void goHome(String msg ){
        Intent intent= new Intent(ManageTap.this,MainActivity.class);
        intent.putExtra("msg",msg);
        startActivity(intent);
    }
}
