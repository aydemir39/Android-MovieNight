package com.app.aydemir.movienight;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btnYeniOyun;
    TextView textViewTicket1;
    ImageView imageViewSettings;
    AudioPlayer audioPlayer;
    static boolean durum=true;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioPlayer = new AudioPlayer();
        btnYeniOyun = (Button) findViewById(R.id.buttonStart);
        imageViewSettings = (ImageView) findViewById(R.id.imageViewSettings);
        textViewTicket1 = (TextView) findViewById(R.id.textViewTicket1);
        SharedPreferences prefs = getSharedPreferences("myUser", MODE_PRIVATE);
        int userTicketPref1 = prefs.getInt("userTicketPref", 100);
        textViewTicket1.setText("" + userTicketPref1);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce_button);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                btnYeniOyun.setVisibility(View.VISIBLE);
                btnYeniOyun.startAnimation(myAnim);
            }
        },1000);
        btnYeniOyun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audioPlayer.play(MainActivity.this, R.raw.btnmenuclick);
                Intent i1 = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i1);
                overridePendingTransition(R.anim.anim_slide_in_left,R.anim.anim_fade_out);
            }
        });
        imageViewSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                imageViewSettings.startAnimation(animFadein);
                custom_settings_dialog();
            }
        });
    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
    public void custom_settings_dialog() {
        final Dialog dlg1 = new Dialog(MainActivity.this);
        dlg1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//köşeleri yok etti!!!
        dlg1.setContentView(R.layout.custom_settings_layout);
        dlg1.setCancelable(true);


        ImageView imageViewStar=(ImageView)dlg1.findViewById(R.id.imageViewStar);

       final SwitchCompat aswitch = (SwitchCompat)dlg1.findViewById(R.id.switchMute);
       if(durum==true){aswitch.setChecked(true);}




        aswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (aswitch.isChecked()) {
                    Log.v("sss2", "sss"); //muzik aç
                    aswitch.setText("Sound On");
                    AudioManager manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                    manager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                    durum=true;
                } else {
                    Log.v("sss1", "sss"); //müzik kapa
                    aswitch.setText("Sound Off");
                    AudioManager manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                    manager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                    durum=false;
                }
            }
        });
        Button buttonReset=(Button)dlg1.findViewById(R.id.buttonResett);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefReset = getSharedPreferences("myUser", MODE_PRIVATE);
                prefReset.edit().clear().commit();
                textViewTicket1.setText("" + 100);
            }
        });
        dlg1.show();
        imageViewStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.app.aydemir.movienight"));
                startActivity(intent);
            }
        });


    }


}
