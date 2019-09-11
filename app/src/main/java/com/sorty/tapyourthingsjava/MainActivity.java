package com.sorty.tapyourthingsjava;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Tap> taps = Tap.listAll(Tap.class);
        if(taps.isEmpty())
            goNew();
        print(taps);
        final Button button=findViewById(R.id.button1);
/*
        final LinearLayout rootLayout = new LinearLayout(getApplicationContext());
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        //Convert DIPs to Pixels
        int dp = 50;
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(px * 4, px);
*/
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, out, Toast.LENGTH_LONG).show();
                System.out.println("go");
                goNew();
            }
        });

    }

    private void goNew(){
        Intent intent = new Intent(MainActivity.this,Adding.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
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
}
