package com.example.nutripets;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Mainpage extends AppCompatActivity {
    private ImageButton infobutton;
    TextView Countfruit;
    TextView CountVege;
    ImageView bunnypic;
    ImageButton fruit;
    ImageButton vegetable;
    int numFruits;
    int numVegs;
    int countft;
    int counterupft= 0;
    //int counterft = countft;
    int countvg;
    int counterupvg=0;
   // int countervg = countvg;
    int sum;
    int flag;
    Boolean reach = false;
    Boolean vgreach = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        //Fruits
        numFruits = getIntent().getExtras().getInt("fruits");
        countft = numFruits;
        Countfruit = (TextView) findViewById(R.id.ftcount);
        Countfruit.setText(Integer.toString(numFruits));

        //Vegs
        numVegs = getIntent().getExtras().getInt("vegetables");
        countvg=numVegs;
        CountVege = (TextView) findViewById(R.id.vgcount);
        CountVege.setText(Integer.toString(numVegs));
        sum= numVegs+numFruits;
        flag=sum;
        //Bunny
        bunnypic = (ImageView) findViewById(R.id.hnsbunny);

        // Buttons
        fruit= (ImageButton) findViewById(R.id.fruits);
        vegetable= (ImageButton) findViewById(R.id.veges);

        // Info
        infobutton = (ImageButton) findViewById(R.id.infobtn);
        infobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenInfo();
            }
        });
    }
    public void OpenInfo(){
        Intent intent= new Intent(this, Information.class);
        startActivity(intent);

    }
    public void fruitDE(View v){
        if (flag<=1){
            Toast.makeText(Mainpage.this, "Good Job! You have accomplished today's goal!", Toast.LENGTH_LONG).show();
            bunnypic.setImageResource(R.drawable.bunny_happy_med);
        }
        else if ((flag <= (sum/2)) && (countvg <= (numVegs - 1)) && !reach){
            bunnypic.setImageResource(R.drawable.bunny_neutral_med);
        }
        if (countft==1){
            fruit.setColorFilter(Color.argb(120,0,100,0));
        }
        if (countft == 0){
            countft++;
            counterupft++;

            countup(v, counterupft, Countfruit);
            reach = true;

        } else if (!reach){
            countft--;
            flag--;
            Countfruit.setText(Integer.toString(countft));
        } else {
            counterupft++;
            countup(v, counterupft, Countfruit);
        }



    }

    public void vegeDE(View v){
        if (flag<=1){
            Toast.makeText(Mainpage.this, "Good Job! You have accomplished today's goal!", Toast.LENGTH_LONG).show();
            bunnypic.setImageResource(R.drawable.bunny_happy_med);

        }
        else if ((flag <= (sum/2)) && (countft <= (numFruits - 1)) && !vgreach){
            bunnypic.setImageResource(R.drawable.bunny_neutral_med);
        }
       if (countvg==1){
           vegetable.setColorFilter(Color.argb(120,0,100,0));

       }
        if (countvg == 0){
            countvg++;
            counterupvg++;
            countup(v, counterupvg, CountVege);
            vgreach = true;
        } else if (!vgreach){
            countvg--;
            flag--;
            CountVege.setText(Integer.toString(countvg));
        } else {

            counterupvg++;
            countup(v, counterupvg, CountVege);
        }



    }

    public void countup(View v, int c, TextView Count){
        if (c >= 10){
            Toast.makeText(Mainpage.this, "Limit", Toast.LENGTH_LONG).show();
        } else {
            Count.setText(Integer.toString(c));
        }
    }

}
