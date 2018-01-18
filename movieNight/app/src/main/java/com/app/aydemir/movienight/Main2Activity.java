package com.app.aydemir.movienight;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
public class Main2Activity extends AppCompatActivity {
    String yazi1 = "";
    String yaziyeni1 = "";
    String alfabeyeni1;

    LinearLayout r3, r2, r1,r4,r5,r6;
    TextView textViewLevel1, textViewNumber1, textViewLevel2, textViewNumber2, textViewLevel3, textViewNumber3, textViewLevel4, textViewNumber4, textViewLevel5, textViewNumber5, textViewLevel6, textViewNumber6;
    ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6;
    TextView textViewTicket2;

    ImageView imageView1Lock, imageView2Lock, imageView3Lock, imageView4Lock, imageView5Lock, imageView6Lock;
    ImageView imageViewBackAc2;

    int levels1, levels2, levels3, levels4, levels5, levels6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textViewTicket2 = (TextView) findViewById(R.id.textViewTicket2);
        imageViewBackAc2=(ImageView)findViewById(R.id.imageViewBackAc2);

        imageView1Lock = (ImageView) findViewById(R.id.imageView1Lock);
        imageView2Lock = (ImageView) findViewById(R.id.imageView2Lock);
        imageView3Lock = (ImageView) findViewById(R.id.imageView3Lock);
        imageView4Lock = (ImageView) findViewById(R.id.imageView4Lock);
        imageView5Lock = (ImageView) findViewById(R.id.imageView5Lock);
        imageView6Lock = (ImageView) findViewById(R.id.imageView6Lock);
        r1 = (LinearLayout) findViewById(R.id.relativeLayout1);
        r2 = (LinearLayout) findViewById(R.id.relativeLayout2);
        r3 = (LinearLayout) findViewById(R.id.relativeLayout3);
        r4 = (LinearLayout) findViewById(R.id.relativeLayout4);
        r5 = (LinearLayout) findViewById(R.id.relativeLayout5);
        r6 = (LinearLayout) findViewById(R.id.relativeLayout6);


        SharedPreferences prefs = getSharedPreferences("myUser", MODE_PRIVATE);
        levels1 = prefs.getInt("userLevelMain1", 1);
        levels2 = prefs.getInt("userLevelMain2", 31);
        levels3 = prefs.getInt("userLevelMain3", 61);
        levels4 = prefs.getInt("userLevelMain4", 91);
        levels5 = prefs.getInt("userLevelMain5", 121);
        levels6 = prefs.getInt("userLevelMain6", 151);
        int userTicketPref2 = prefs.getInt("userTicketPref", 100);
        textViewTicket2.setText("" + userTicketPref2);

        ListeVeriDoldur1(levels1);
        ListeVeriDoldur2(levels2);
        ListeVeriDoldur3(levels3);
        ListeVeriDoldur4(levels4);
        ListeVeriDoldur5(levels5);
        ListeVeriDoldur6(levels6);

       // animationLayouts();
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioPlayer audioPlayer=new AudioPlayer();
                audioPlayer.play(Main2Activity.this, R.raw.btnmenuclick);
                DatabaseConnectAndGet(levels1);
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioPlayer audioPlayer=new AudioPlayer();
                audioPlayer.play(Main2Activity.this, R.raw.btnmenuclick);
                DatabaseConnectAndGet(levels2);
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioPlayer audioPlayer=new AudioPlayer();
                audioPlayer.play(Main2Activity.this, R.raw.btnmenuclick);
                DatabaseConnectAndGet(levels3);
            }
        });
        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioPlayer audioPlayer=new AudioPlayer();
                audioPlayer.play(Main2Activity.this, R.raw.btnmenuclick);
                DatabaseConnectAndGet(levels4);
            }
        });
        r5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioPlayer audioPlayer=new AudioPlayer();
                audioPlayer.play(Main2Activity.this, R.raw.btnmenuclick);
                DatabaseConnectAndGet(levels5);
            }
        });
        r6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioPlayer audioPlayer=new AudioPlayer();
                audioPlayer.play(Main2Activity.this, R.raw.btnmenuclick);
                DatabaseConnectAndGet(levels6);
            }
        });

        imageViewBackAc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

                imageViewBackAc2.startAnimation(animFadein);
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_fade_out);
            }
        });
    }



    public void onBackPressed() {
        Intent i1 = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(i1);
        overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_fade_out);
    }
    public void DatabaseConnectAndGet(int levels) {
        final DatabaseHelper dbHelper = new DatabaseHelper(Main2Activity.this);
        try {
            dbHelper.CreateDataBase();
        } catch (Exception ex) {
            Log.w("hata", "Veritabanı oluşturulamadı ve kopyalanamadı!");
        }

        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor imlec = db.rawQuery("SELECT * FROM movies WHERE _id = '" + levels + "'", null);
        imlec.moveToFirst();
        String student_surname = imlec.getString(1);
        byte[] b = imlec.getBlob(2);
        imlec.close();
        db.close();

        yazi1 = student_surname;

        alfabeyeni1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        yaziyeni1 = yazi1.replace("_", "");
        char[] chAlfabe = yaziyeni1.toCharArray();
        for (int i = 0; i < yaziyeni1.length(); i++)
        {
            alfabeyeni1 = alfabeyeni1.replace(String.valueOf(chAlfabe[i]), "");
        }
        Intent i1 = new Intent(Main2Activity.this, Main3Activity.class);
        i1.putExtra("yeniharfler", alfabeyeni1);
        i1.putExtra("yazi1", yazi1);
        i1.putExtra("userLevelExtra", levels);
        i1.putExtra("byte", b);
        startActivity(i1);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
    public void ListeVeriDoldur1(int levels) {
        imageView1Lock.setVisibility(View.VISIBLE);
        textViewLevel1 = (TextView) findViewById(R.id.textView11);
        textViewNumber1 = (TextView) findViewById(R.id.textView12);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        textViewNumber1.setText("" + (levels-1) + "/30");
        progressBar1.setProgress((levels - 1) * 3);
        if (levels < 31) {
            imageView1Lock.setVisibility(View.INVISIBLE);
            r2.setEnabled(false);
            r3.setEnabled(false);
            r4.setEnabled(false);
            r5.setEnabled(false);
        }
        if(levels==31){r1.setEnabled(false); imageView1Lock.setImageResource(R.drawable.ic_done_black); imageView2Lock.setVisibility(View.INVISIBLE);}
    }
    public void ListeVeriDoldur2(int levels) {
        textViewLevel2 = (TextView) findViewById(R.id.textView21);
        textViewNumber2 = (TextView) findViewById(R.id.textView22);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        textViewNumber2.setText("" + (levels - 31) + "/30");
        progressBar2.setProgress((levels - 31) * 3);
        if (levels< 61) {
            r3.setEnabled(false);
            r4.setEnabled(false);
            r5.setEnabled(false);
        }
        if(levels==61){r2.setEnabled(false);imageView2Lock.setImageResource(R.drawable.ic_done_black); imageView2Lock.setVisibility(View.VISIBLE);imageView3Lock.setVisibility(View.INVISIBLE);}
    }
    public void ListeVeriDoldur3(int levels) {
        textViewLevel3 = (TextView) findViewById(R.id.textView31);
        textViewNumber3 = (TextView) findViewById(R.id.textView32);
        progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        textViewNumber3.setText("" + (levels - 61) + "/30");
        progressBar3.setProgress((levels - 61) * 3);
        if (levels< 91) {
            r4.setEnabled(false);
            r5.setEnabled(false);
        }
        if(levels==91){r3.setEnabled(false);imageView3Lock.setImageResource(R.drawable.ic_done_black); imageView3Lock.setVisibility(View.VISIBLE);imageView4Lock.setVisibility(View.INVISIBLE);}
    }
    public void ListeVeriDoldur4(int levels) {
        textViewLevel4 = (TextView) findViewById(R.id.textView41);
        textViewNumber4 = (TextView) findViewById(R.id.textView42);
        progressBar4 = (ProgressBar) findViewById(R.id.progressBar4);
        textViewNumber4.setText("" + (levels - 91) + "/30");
        progressBar4.setProgress((levels - 91) * 3);
        if (levels< 121) {
            r5.setEnabled(false);
        }
        if(levels==121){r4.setEnabled(false);imageView4Lock.setImageResource(R.drawable.ic_done_black); imageView4Lock.setVisibility(View.VISIBLE);imageView5Lock.setVisibility(View.INVISIBLE);}
    }
    public void ListeVeriDoldur5(int levels) {
        textViewLevel5 = (TextView) findViewById(R.id.textView51);
        textViewNumber5 = (TextView) findViewById(R.id.textView52);
        progressBar5 = (ProgressBar) findViewById(R.id.progressBar5);
        textViewNumber5.setText("" + (levels - 121) + "/30");
        progressBar5.setProgress((levels - 121) * 3);
        if (levels< 151) {r6.setEnabled(false);}
        if(levels==151){r5.setEnabled(false);imageView5Lock.setImageResource(R.drawable.ic_done_black); imageView5Lock.setVisibility(View.VISIBLE);imageView6Lock.setVisibility(View.INVISIBLE);}
    }
    public void ListeVeriDoldur6(int levels) {
        textViewLevel6 = (TextView) findViewById(R.id.textView61);
        textViewNumber6 = (TextView) findViewById(R.id.textView62);
        progressBar6 = (ProgressBar) findViewById(R.id.progressBar6);
        textViewNumber6.setText("" + (levels - 151) + "/30");
        progressBar6.setProgress((levels - 151) * 3);
        if (levels< 181) {}
        if(levels==181){imageView6Lock.setImageResource(R.drawable.ic_done_black);imageView6Lock.setVisibility(View.VISIBLE);r6.setEnabled(false);}
    }
   /* public void animationLayouts() {
        Animation fadeIn = new AlphaAnimation(0, 1);
    }*/
}
