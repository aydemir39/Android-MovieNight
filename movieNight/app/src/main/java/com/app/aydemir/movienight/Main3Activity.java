package com.app.aydemir.movienight;


import android.app.Dialog;
import android.content.Intent;

import android.content.SharedPreferences;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main3Activity extends AppCompatActivity {
    String yazi = "STAR_WARS";
    String alfabe, alfabeyeni;
    int NumberFrom2;
    File imagePath;
    ImageView iv,ivCustom;
    LinearLayout l1, l3, l4, l5, linearLayoutCustomDialog;
    TextView textViewTicket3, textViewLevel3;
    int iii = 0;
    boolean btnHarfKaldırflag = false;
    boolean btnWhatsappflag = false;
    LinearLayout rl;
    Button btnRastgeleHarf, btnHarfKaldır, btnWhatsapp;
    cards kart1, kart2;
    int boyut = 0;
    int userTicketPref3;
    ImageView imageViewBackAc3;
    Drawable drawableImageMain;

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageViewBackAc3 = (ImageView) findViewById(R.id.imageViewBackAc3);

        textViewTicket3 = (TextView) findViewById(R.id.textViewTicket3);
        textViewLevel3 = (TextView) findViewById(R.id.textViewLevel3);
        btnRastgeleHarf = (Button) findViewById(R.id.buttonHarf);
        btnWhatsapp = (Button) findViewById(R.id.buttonFacebook);
        btnHarfKaldır = (Button) findViewById(R.id.buttonHarfKaldır);
        iv = (ImageView) findViewById(R.id.imageViews);


        rl = (LinearLayout) findViewById(R.id.r);
        l1 = (LinearLayout) findViewById(R.id.LinearLayout1);
        l3 = (LinearLayout) findViewById(R.id.LinearLayout3);
        l4 = (LinearLayout) findViewById(R.id.LinearLayout4);
        l5 = (LinearLayout) findViewById(R.id.LinearLayout5);


        SharedPreferences prefs = getSharedPreferences("myUser", MODE_PRIVATE);
        userTicketPref3 = prefs.getInt("userTicketPref", 100);
        textViewTicket3.setText("" + userTicketPref3);

        Bundle b = getIntent().getExtras();
        Intent intent = getIntent();
        alfabeyeni = intent.getExtras().getString("yeniharfler");
        yazi = intent.getExtras().getString("yazi1");
        NumberFrom2 = intent.getExtras().getInt("userLevelExtra");
        byte[] v = b.getByteArray("byte");
        Bitmap bitmapİmage = BitmapFactory.decodeByteArray(v, 0, v.length);
        drawableImageMain = new BitmapDrawable(getResources(), bitmapİmage);
        iv.setBackground(drawableImageMain);
        textViewLevel3.setText("Level: " + NumberFrom2);
        ticketScan();
        alfabe = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (yazi.length() <= 6) {
            boyut = 7;
        } else if (yazi.length() > 6 && yazi.length() <= 13) {
            boyut = 14;
        } else if (yazi.length() > 13 && yazi.length() <= 20) {
            boyut = 21;
        }
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int buttonWidthAndHeight = width / 9;
        int buttonWidthAndHeight2 = width / 9;
        final cards kartlar1[] = new cards[yazi.length()];
        final cards kartlar2[] = new cards[boyut];

        btnRastgeleHarf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AudioPlayer audioPlayer=new AudioPlayer();
                audioPlayer.play(Main3Activity.this, R.raw.btnhint);
                Random r = new Random();
                int doluSayısı = 0;
                while (true) {
                    int i = r.nextInt(yazi.length());
                    if (kartlar1[i].dolumu1 == false && kartlar1[i].getText() != "_") {
                        userTicketPref3 = userTicketPref3 - 30;
                        ticketScan();
                        textViewTicket3.setText("" + userTicketPref3);
                        char a = kartlar1[i].harf;
                        for (int si = 0; si < boyut; si++) {
                            if (kartlar2[si].tıklanmıs2 == false && kartlar1[i].id1 == kartlar2[si].id2) {
                                kartlar2[si].setVisibility(View.INVISIBLE);
                                kartlar2[si].tıklanmıs2 = true;
                                kartlar1[i].tıklananid = kartlar2[si].id2;
                                kartlar1[i].setTextColor(Color.GRAY);
                                kartlar1[i].setText(String.valueOf(a));
                               kartlar1[i].setClickable(false);
                               // kartlar1[i].setEnabled(false);
                                kartlar1[i].dolumu1 = true;
                                break;
                            } else if (kartlar2[si].tıklanmıs2 == true && kartlar1[i].id1 == kartlar2[si].id2) {
                                for (int sii = 0; sii < yazi.length(); sii++) {
                                    if (kartlar1[sii].tıklananid == kartlar2[si].id2) {
                                        kartlar1[sii].setText("");
                                        kartlar1[sii].dolumu1 = false;
                                        kartlar1[sii].tıklananid = 99;
                                    }
                                }
                                kartlar1[i].setTextColor(Color.GRAY);
                                kartlar1[i].setText(String.valueOf(a));
                                kartlar1[i].setClickable(false);
                                //kartlar1[i].setEnabled(false);
                                kartlar1[i].dolumu1 = true;
                                kartlar1[i].tıklananid = kartlar2[si].id2;
                            }
                        }
                        break;
                    }
                    int dolu = 0;
                    for (int a = 0; a < yazi.length(); a++) {
                        if (kartlar1[a].getText() != "") {
                            dolu++;
                        }
                    }
                    if (dolu == yazi.length()) break;
                }
                for (int i = 0; i < yazi.length(); i++) {
                    if (kartlar1[i].getText() != "") {
                        doluSayısı++;
                    }
                }
                if (doluSayısı == yazi.length()) {
                    String s = "";
                    for (int a = 0; a < yazi.length(); a++) {
                        s += kartlar1[a].getText().toString();
                    }


                    if (s.equals(yazi)) {
                        NumberFrom2++;
                        btnRastgeleHarf.setClickable(false);
                        userTicketPref3 = userTicketPref3 + 50;
                        textViewTicket3.setText("" + userTicketPref3);
                        SharedPreferences.Editor editor = getSharedPreferences("myUser", MODE_PRIVATE).edit();
                        editor.putInt("userTicketPref", userTicketPref3);
                        editor.apply();
                        l1.setVisibility(View.INVISIBLE);
                        if (NumberFrom2 == 31||NumberFrom2 == 61||NumberFrom2 == 91||NumberFrom2 == 121||NumberFrom2 == 151||NumberFrom2 == 181) {
                            EndSectionPage();
                        }
                        else alertButtonCustom();
                    } else
                        Toast.makeText(Main3Activity.this, "Wrong Answer!", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnHarfKaldır.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioPlayer audioPlayer=new AudioPlayer();
                audioPlayer.play(Main3Activity.this, R.raw.btnhint);

                if (btnHarfKaldırflag) {

                    return;
                }
                for (int i = 0; i < boyut; i++) {
                    if (kartlar2[i].id2 >= yazi.length() && !kartlar2[i].tıklanmıs2) {
                        kartlar2[i].setVisibility(View.INVISIBLE);
                        kartlar2[i].tıklanmıs2 = true;
                    }
                    if (kartlar2[i].id2 < yazi.length() && !kartlar2[i].tıklanmıs2 && kartlar2[i].harf == '_') {
                        kartlar2[i].setVisibility(View.INVISIBLE);
                        kartlar2[i].tıklanmıs2 = true;
                    }
                }
                for (int i = 0; i < yazi.length(); i++) {
                    if (kartlar1[i].tıklananid >= yazi.length() && kartlar1[i].dolumu1 == true) {
                        kartlar1[i].setText("");
                        kartlar1[i].dolumu1 = false;
                    }
                    if (kartlar1[i].tıklananid < yazi.length() && kartlar1[i].dolumu1 == true && kartlar1[i].altıcizilimi == true) {
                        kartlar1[i].setText("");
                        kartlar1[i].dolumu1 = false;
                    }
                }
                btnHarfKaldırflag = true;
                userTicketPref3 = userTicketPref3 - 30;

                textViewTicket3.setText("" + userTicketPref3);
                btnHarfKaldır.setClickable(false);
                ticketScan();
            }
        });
        if (yazi.length() > 7) {
            for (int i = 0; i < yazi.length(); i++) {
                char a = yazi.charAt(i);
                kartlar1[i] = new cards(Main3Activity.this, buttonWidthAndHeight2);
                kartlar1[i].harf = a;
                kartlar1[i].id1 = i;
                l1.addView(kartlar1[i]);
                if (a == '_') {
                    kartlar1[i].setText("_");
                    kartlar1[i].setVisibility(View.INVISIBLE);
                }
            }
        } else {
            for (int i = 0; i < yazi.length(); i++) {
                char a = yazi.charAt(i);
                kartlar1[i] = new cards(Main3Activity.this, buttonWidthAndHeight2, 2);
                kartlar1[i].harf = a;
                kartlar1[i].id1 = i;
                l1.addView(kartlar1[i]);
                if (a == '_') {
                    kartlar1[i].setText("_");
                    kartlar1[i].setVisibility(View.INVISIBLE);
                }
            }
        }
        for (int ii = 0; ii < yazi.length(); ii++) {
            kartlar1[ii].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    kart2 = (cards) view;
                    if (kart2.dolumu1) {
                        AudioPlayer audioPlayer=new AudioPlayer();
                        audioPlayer.play(Main3Activity.this, R.raw.btnpush);
                        for (int xx = 0; xx < boyut; xx++) {
                            if (kartlar2[xx].id2 == kart2.tıklananid) {
                                kartlar2[xx].setVisibility(View.VISIBLE);
                                kartlar2[xx].tıklanmıs2 = false;
                            }
                        }
                        kart2.setText("");
                        kart2.dolumu1 = false;
                        kart2.tıklananid = 99;
                        kart2.altıcizilimi = false;
                    }
                }
            });
        }
        for (int ii = 0; ii < boyut; ii++) {
            Random r = new Random();
            char a;
            if (ii < yazi.length()) {
                a = yazi.charAt(ii);
                if (a == '_') {
                    kartlar2[ii] = new cards(Main3Activity.this, String.valueOf(alfabeyeni.charAt(r.nextInt(alfabeyeni.length() - 1))), ii, buttonWidthAndHeight);
                    kartlar2[ii].harf = a;
                    kartlar2[ii].altıcizilimi = true;
                } else {
                    kartlar2[ii] = new cards(Main3Activity.this, String.valueOf(a), ii, buttonWidthAndHeight);
                    kartlar2[ii].harf = a;
                }
            } else {

                a = alfabeyeni.charAt(r.nextInt(alfabeyeni.length() - 1));
                kartlar2[ii] = new cards(Main3Activity.this, String.valueOf(a), ii, buttonWidthAndHeight);

            }
            kartlar2[ii].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AudioPlayer audioPlayer=new AudioPlayer();
                    audioPlayer.play(Main3Activity.this, R.raw.btnpush);
                    for (int i = 0; i < yazi.length(); i++) {
                        if (kartlar1[i].dolumu1 == false) {
                        }
                    }
                    iii = 0;
                    kart1 = (cards) view;
                    for (int a = 0; a < yazi.length(); a++) {
                        if (kartlar1[a].harf != '_' && kartlar1[a].dolumu1 == false) {
                            kartlar1[a].setText(kart1.getText());
                            kartlar1[a].dolumu1 = true;
                            kartlar1[a].tıklananid = kart1.id2;
                            kartlar1[a].altıcizilimi = kart1.altıcizilimi;

                            kart1.setVisibility(View.INVISIBLE);
                            kart1.tıklanmıs2 = true;
                            break;
                        } else continue;
                    }
                    for (int a = 0; a < yazi.length(); a++) {
                        if (kartlar1[a].getText() != "") {
                            iii++;
                        }
                    }
                    if (iii == yazi.length()) {
                        String s = "";
                        for (int a = 0; a < yazi.length(); a++) {
                            s += kartlar1[a].getText().toString();
                        }
                        if (s.equals(yazi)) {
                            NumberFrom2++;
                            userTicketPref3 = userTicketPref3 + 50;
                            textViewTicket3.setText("" + userTicketPref3);
                            SharedPreferences.Editor editor = getSharedPreferences("myUser", MODE_PRIVATE).edit();
                            editor.putInt("userTicketPref", userTicketPref3);
                            editor.apply();
                            l1.setVisibility(View.INVISIBLE);
                            if (NumberFrom2 == 31||NumberFrom2 == 61||NumberFrom2 == 91||NumberFrom2 == 121||NumberFrom2 == 151||NumberFrom2 == 181) {
                                EndSectionPage();
                            }
                            else alertButtonCustom();
                        } else
                            Toast.makeText(Main3Activity.this, "Wrong Answer!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


        // İkinci Butonların yerleştirilmesi
        shuffleArray(kartlar2);
        Handler handler2=new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {

                Handler handler1 = new Handler();
                for (int a = 0; a < boyut; a++) {


                   // kartlar2[a].setBackgroundColor(Color.parseColor("#ff8964"));
                    final int finalA = a;
                    handler1.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            if (finalA < 7) {

                                l3.addView(kartlar2[finalA]);
                            } else if (finalA >= 7 && finalA < 14) {
                                l4.addView(kartlar2[finalA]);
                            } else l5.addView(kartlar2[finalA]);
                        }
                    }, 100 * a);
                }


            }
        },1000);

        btnWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnWhatsappflag) {

                    return;
                }
                userTicketPref3 = userTicketPref3 - 30;
                AudioPlayer audioPlayer=new AudioPlayer();
                audioPlayer.play(Main3Activity.this, R.raw.btnhint);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bitmap = takeScreenshot();
                        saveBitmap(bitmap);
                        shareIt();
                    }
                }, 1000);
                btnWhatsappflag = true;
                btnWhatsapp.setClickable(false);
                textViewTicket3.setText("" + userTicketPref3);
                ticketScan();
            }
        });
        imageViewBackAc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

                imageViewBackAc3.startAnimation(animFadein);
                SharedPreferences.Editor editor = getSharedPreferences("myUser", MODE_PRIVATE).edit();
                editor.putInt("userTicketPref", userTicketPref3);
                editor.apply();
                Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_fade_out);
            }
        });
    }
    @Override
    public void onBackPressed() {
        SharedPreferences.Editor editor = getSharedPreferences("myUser", MODE_PRIVATE).edit();
        editor.putInt("userTicketPref", userTicketPref3);
        editor.apply();
        Intent i1 = new Intent(Main3Activity.this, Main2Activity.class);
        startActivity(i1);
        overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_fade_out);
    }

    public static void shuffleArray(cards[] array) {
        List<cards> list = new ArrayList<>();
        for (cards i : array) {
            list.add(i);
        }
        Collections.shuffle(list);
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
    }

    public void alertButtonCustom() {
        Dialog dlg = new Dialog(this, R.style.NewDialog);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //  dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//köşeleri yok etti!!!
        dlg.setContentView(R.layout.custom_dialog);
        // dlg.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dlg.setCancelable(false);
        dlg.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        linearLayoutCustomDialog = (LinearLayout) dlg.findViewById(R.id.linearLayoutCustomDialog);
        final cards karts[] = new cards[yazi.length()];

        ivCustom=(ImageView)dlg.findViewById(R.id.imageViewCustom);
        ivCustom.setBackground(drawableImageMain);
        TextView textViewPerfect = (TextView) dlg.findViewById(R.id.textView77);

        AudioPlayer audioPlayer=new AudioPlayer();
        audioPlayer.play(Main3Activity.this, R.raw.applause);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int buttonWidthAndHeight = width / 8;
        int buttonWidthAndHeight2 = width / 8;

        if (yazi.length() > 7) {
            for (int i = 0; i < yazi.length(); i++) {
                char a = yazi.charAt(i);
                karts[i] = new cards(Main3Activity.this, buttonWidthAndHeight2);
                karts[i].harf = a;
                //  kartlar1[i].dolumu1 = false;
                karts[i].id1 = i;
                karts[i].setText("" + a);
                karts[i].setTextColor(Color.WHITE);
                linearLayoutCustomDialog.addView(karts[i]);
                //l1.setLayoutParams( );
                if (a == '_') {
                    karts[i].setText("_");
                    karts[i].setVisibility(View.INVISIBLE);
                }
            }
        } else {
            for (int i = 0; i < yazi.length(); i++) {
                char a = yazi.charAt(i);
                karts[i] = new cards(Main3Activity.this, buttonWidthAndHeight2, 2);
                karts[i].harf = a;
                // kartlar1[i].altıcizilimi = false;
                karts[i].id1 = i;
                karts[i].setText("" + a);
                karts[i].setTextColor(Color.WHITE);
                linearLayoutCustomDialog.addView(karts[i]);
                if (a == '_') {
                    karts[i].setText("");
                    karts[i].setVisibility(View.INVISIBLE);
                }
            }
        }

        Button btnNext = (Button) dlg.findViewById(R.id.buttonNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseHelper dbHelper = new DatabaseHelper(Main3Activity.this);
                try {
                    dbHelper.CreateDataBase();
                } catch (Exception ex) {
                    Log.w("hata", "Veritabanı oluşturulamadı ve kopyalanamadı!");
                }
                final SQLiteDatabase db = dbHelper.getReadableDatabase();

                Cursor imlec = db.rawQuery("SELECT * FROM movies WHERE _id = '" + NumberFrom2 + "'", null);
               // imlec.moveToFirst();
                String student_surname = null;
                byte[] b1 = new byte[0];
                if (imlec.moveToFirst()) {
                    student_surname = imlec.getString(1);
                    b1 = imlec.getBlob(2);
                }

                imlec.close();
                db.close();
                String yazi1 = student_surname;
                String alfabeyeni1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                String yaziyeni1 = yazi1.replace("_", "");
                char[] chAlfabe = yaziyeni1.toCharArray();
                for (int i = 0; i < yaziyeni1.length(); i++) {
                    alfabeyeni1 = alfabeyeni1.replace(String.valueOf(chAlfabe[i]), "");
                }
                SharedPreferences.Editor editor = getSharedPreferences("myUser", MODE_PRIVATE).edit();
                if (NumberFrom2 < 32) editor.putInt("userLevelMain1", NumberFrom2);
                else if (NumberFrom2 < 62 && NumberFrom2 >= 32)
                    editor.putInt("userLevelMain2", NumberFrom2);

                else if (NumberFrom2 < 92 && NumberFrom2 >= 62)
                    editor.putInt("userLevelMain3", NumberFrom2);

                else if (NumberFrom2 < 122 && NumberFrom2 >= 92)
                    editor.putInt("userLevelMain4", NumberFrom2);
                else if (NumberFrom2 < 152 && NumberFrom2 >= 122)
                    editor.putInt("userLevelMain5", NumberFrom2);
                else if (NumberFrom2 >= 152) editor.putInt("userLevelMain6", NumberFrom2);
                editor.putInt("userTicketPref", userTicketPref3);
                editor.apply();

                Intent i1 = new Intent(Main3Activity.this, Main3Activity.class);
                i1.putExtra("yeniharfler", alfabeyeni1);
                i1.putExtra("yazi1", yazi1);
                i1.putExtra("userLevelExtra", NumberFrom2);
                i1.putExtra("byte", b1);
                startActivity(i1);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
        dlg.show();
    }
    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    private void shareIt() {
        Uri uri = Uri.fromFile(imagePath);
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "Give me a hand :)");
        sharingIntent.setType("text/plain");
        sharingIntent.setType("image/*");
        sharingIntent.setPackage("com.whatsapp");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public void ticketScan() {
        if (userTicketPref3 < 30) {
            btnRastgeleHarf.setEnabled(false);
            btnHarfKaldır.setEnabled(false);
            btnWhatsapp.setEnabled(false);
        }
        else
            {
                btnWhatsapp.setEnabled(true);
                btnRastgeleHarf.setEnabled(true);
                btnHarfKaldır.setEnabled(true);
            }
    }

    public void EndSectionPage() {
        Dialog dlg2 = new Dialog(this, R.style.NewDialog);
        dlg2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg2.setContentView(R.layout.custom_end_section_layout);
        dlg2.setCancelable(false);
        dlg2.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        ImageView imageViewGoPacks = (ImageView) dlg2.findViewById(R.id.imageViewGoPacks);
        Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.oscar_animation);

        AudioPlayer audioPlayer=new AudioPlayer();
        audioPlayer.play(Main3Activity.this, R.raw.applause);

        imageViewGoPacks.startAnimation(animFadein);
        Button buttonGoPacks = (Button) dlg2.findViewById(R.id.buttonGoPacks);
        buttonGoPacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("myUser", MODE_PRIVATE).edit();
                if (NumberFrom2 < 32) editor.putInt("userLevelMain1", NumberFrom2);
                else if (NumberFrom2 < 62 && NumberFrom2 >= 32)
                    editor.putInt("userLevelMain2", NumberFrom2);

                else if (NumberFrom2 < 92 && NumberFrom2 >= 62)
                    editor.putInt("userLevelMain3", NumberFrom2);

                else if (NumberFrom2 < 122 && NumberFrom2 >= 92)
                    editor.putInt("userLevelMain4", NumberFrom2);
                else if (NumberFrom2 < 152 && NumberFrom2 >= 122)
                    editor.putInt("userLevelMain5", NumberFrom2);

                else if (NumberFrom2 >= 152) editor.putInt("userLevelMain6", NumberFrom2);
                editor.putInt("userTicketPref", userTicketPref3);
                editor.apply();
                Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
                startActivity(intent);

            }
        });
        dlg2.show();
    }
    public void secondButtonsAdd(){}
}

