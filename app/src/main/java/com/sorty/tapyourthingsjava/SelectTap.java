package com.sorty.tapyourthingsjava;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

public class SelectTap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RadioGroup radioGroup=findViewById(R.id.radioId);
                int select=radioGroup.getCheckedRadioButtonId();
                if(select==-1)
                    Snackbar.make(view, "select item", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                else{
                    RadioButton radioButton = findViewById(select);
                    Toast.makeText(SelectTap.this,
                            radioButton.getText(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SelectTap.this,ManageTap.class);
                    Tap tap=getTap(radioButton);
                    if(tap==null)
                        GoHome();
                    intent.putExtra("tap",tap);
                    startActivity(intent);
                }
            }
        });

        List<Tap> taps = Tap.listAll(Tap.class);
        if(taps.isEmpty())
            goNew();
        fillActivity(taps);
    }

    private Tap getTap(RadioButton view) {
        return (view.getTag() instanceof  Tap?(Tap)view.getTag():null);
    }

    private void GoHome(){
        Intent intent = new Intent(SelectTap.this,MainActivity.class);
        startActivity(intent);
    }

    private void goNew(){
        Intent intent = new Intent(SelectTap.this,Adding.class);
        startActivity(intent);
    }

    private void fillActivity(List<Tap> list){
        final RadioGroup radioGroup=findViewById(R.id.radioId);
        for (Tap tap : list){
            RadioButton radioButton=new RadioButton(this);
            radioButton.setTag(tap);
            radioButton.setText(tap.getThing());
            radioButton.setId(View.generateViewId());
            radioGroup.addView(radioButton);
        }
    }

}
