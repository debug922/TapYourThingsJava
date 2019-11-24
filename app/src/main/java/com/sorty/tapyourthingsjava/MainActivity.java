package com.sorty.tapyourthingsjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout= findViewById(R.id.rootMain);
        printFromIntent();
        List<Tap> taps = Tap.listAll(Tap.class);
        if(taps.isEmpty())
            goNew();
        print(taps);
        final Button button=findViewById(R.id.button1);
        final Button manage=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, out, Toast.LENGTH_LONG).show();
                System.out.println("go");
                goNew();
            }
        });

        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, out, Toast.LENGTH_LONG).show();
                System.out.println("manage");
                Intent intent = new Intent(MainActivity.this, SelectTap.class);
                startActivity(intent);
            }
        });
    }

    private void goNew(){
        Intent intent = new Intent(MainActivity.this,Adding.class);
        startActivity(intent);
    }

    private void print(List<Tap> list){
        final LinearLayout myLayout=findViewById(R.id.layout);

        for (Tap tap : list){
            TextView textView= new TextView(MainActivity.this);
            textView.setHeight(150);
            textView.setWidth(50);
            textView.setText(tap.getThing());
            textView.setId(View.generateViewId());
            Button button1= new Button(MainActivity.this);
            button1.setText(String.valueOf(tap.getTap()));
            button1.setId(View.generateViewId());
            button1.setTag(tap);
            button1.setOnClickListener(new ButtonTap());
            myLayout.addView(textView);
            myLayout.addView(button1);

        }
    }

    private class ButtonTap implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Tap tap=(view.getTag() instanceof  Tap?(Tap)view.getTag():null);
                try {
                    if(tap==null)
                        throw new IllegalArgumentException("wrong cast");
                    tap.setTap(tap.getTap()+1);
                    tap.save();
                    Button button=(Button)view;
                    button.setText(String.valueOf(tap.getTap()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    private void printFromIntent(){
        Intent intent=getIntent();
        if(intent!=null){
            String msg=(String)intent.getSerializableExtra("msg");

            if((msg!=null) && (!msg.isEmpty()))
                Snackbar.make(constraintLayout, msg, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
        }

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // more than one press, so we want to close
            if (event.getRepeatCount() > 1) {
                finish();
            } else { // just one, so open the activity
                finishAffinity();
                //finish();
                //moveTaskToBack(true);
            }
        }
        return super.onKeyUp(keyCode, event);
    }


}
