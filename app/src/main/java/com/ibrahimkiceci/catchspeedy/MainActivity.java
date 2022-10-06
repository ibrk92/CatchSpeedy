package com.ibrahimkiceci.catchspeedy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    TextView scoreText;
    int score;
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);
        imageView = findViewById(R.id.imageView);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);

        imageArray = new ImageView[]{imageView, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8};
        hideImages();

        score = 0;


        // Zaman ekran acilir acilmaz geriye dogru saysin;

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {

                // Herbir saniyede ne olacagini yaziyoruz

                timeText.setText("Time: " + l/1000); // her bir saniyede kendisini yenilemesini istiyoruz...

            }

            @Override
            public void onFinish() {
                //Bitince ne olacagini yaziyoruz.

                timeText.setText("Time off");
                handler.removeCallbacks(runnable);
                // speedy resmini gizleyelim;
                for (ImageView image: imageArray){

                    image.setVisibility(View.INVISIBLE);
                }

               AlertDialog.Builder my_alert = new AlertDialog.Builder(MainActivity.this);

                my_alert.setTitle("Restart Game");
                my_alert.setMessage("Do you want to restart the game ?");
                my_alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Restart
                        // Intent hatirlarsan activityler arasinda gecis yapmamizi saglar.Ancak burada ayni aktiviteyi tekrar acmak icin de kullanabiliriz.
                        Intent intent = getIntent();
                        finish();// Guncel aktiviteyi bitirmeni saglar...
                        startActivity(intent); // Activiteyi bastan baslatmayi saglar


                    }
                });

                my_alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(MainActivity.this, "You successfully exit from the game", Toast.LENGTH_SHORT).show();

                    }
                });

                my_alert.show();




            }
        }.start();



    }

    public void increaseScore(View view){

        score+=1;
        scoreText.setText("Score: " + score);

    }

    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                // Once tum imagelari gorunmez yapalim

                for (ImageView image: imageArray){

                    image.setVisibility(View.INVISIBLE);
                }

                // Oncelikle Runnable belirli bir zaman periyodunda yapmak istedigimiz bir seyi yapmayi saglar. Handler da bunu saglayan bir arayuzdur denebilir
                // Simdi random bir sayi alalim ve o sayiya giore random bir resim acilsin

                Random random = new Random();
                int i =  random.nextInt(9); // bizim arrayimiz hatirlarsan 0'dan 8'e kadar.
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,500); // Belirlenen periyod icerisinde runnable calistir ve bunu rotarli calistir(ben yarim saniyede bir yaptim)

            }
        };

        handler.post(runnable);




    }

}