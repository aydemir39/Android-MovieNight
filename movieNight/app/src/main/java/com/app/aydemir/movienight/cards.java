package com.app.aydemir.movienight;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class cards extends Button {
    Drawable dön;
    int id2,id1;
    char harf;
    boolean tıklanmıs2=false;
    int tıklananid=99;
    boolean altıcizilimi=false;
    boolean dolumu1=false;

    public cards(Context context, String str,int id,int size) {
        super(context);
        id2=id;
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(size, size);

        p.setMargins(5,5,5,5);
        switch (str) {
            case "A":
                setBackgroundResource(R.drawable.a);
                break;
            case "B":
                setBackgroundResource(R.drawable.b);
                break;
            case "C":
                setBackgroundResource(R.drawable.c);
                break;
            case "D":
                setBackgroundResource(R.drawable.d);
                break;
            case "E":
                setBackgroundResource(R.drawable.e);
                break;
            case "F":
                setBackgroundResource(R.drawable.f);
                break;
            case "G":
                setBackgroundResource(R.drawable.g);
                break;
            case "H":
                setBackgroundResource(R.drawable.h);
                break;
            case "I":
                setBackgroundResource(R.drawable.i);
                break;
            case "J":
                setBackgroundResource(R.drawable.j);
                break;
            case "K":
                setBackgroundResource(R.drawable.k);
                break;
            case "L":
                setBackgroundResource(R.drawable.l);
                break;
            case "M":
                setBackgroundResource(R.drawable.m);
                break;
            case "N":
                setBackgroundResource(R.drawable.n);
                break;
            case "O":
                setBackgroundResource(R.drawable.o);
                break;
            case "P":
                setBackgroundResource(R.drawable.p);
                break;
            case "Q":
                setBackgroundResource(R.drawable.q);
                break;
            case "R":
                setBackgroundResource(R.drawable.r);
                break;
            case "S":
                setBackgroundResource(R.drawable.s);
                break;
            case "T":
                setBackgroundResource(R.drawable.t);
                break;
            case "U":
                setBackgroundResource(R.drawable.u);
                break;
            case "V":
                setBackgroundResource(R.drawable.v);
                break;
            case "W":
                setBackgroundResource(R.drawable.w);
                break;
            case "X":
                setBackgroundResource(R.drawable.x);
                break;
            case "Y":
                setBackgroundResource(R.drawable.y);
                break;
            case "Z":
                setBackgroundResource(R.drawable.z);
                break;
        }
        setSoundEffectsEnabled(false);
        setTextSize(0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(18);
        }
        setLayoutParams(p);
        setTextColor(Color.BLACK);
        setText(str);
    }
    public cards(Context context,int size) {
        super(context);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        p.weight=1;
        int screenSize = context.getResources().getConfiguration().screenLayout &Configuration.SCREENLAYOUT_SIZE_MASK;
        if(screenSize==Configuration.SCREENLAYOUT_SIZE_SMALL){setTextSize( TypedValue.COMPLEX_UNIT_DIP, 20);}
        else if(screenSize==Configuration.SCREENLAYOUT_SIZE_NORMAL){setTextSize( TypedValue.COMPLEX_UNIT_DIP, 24);}
        else if(screenSize==Configuration.SCREENLAYOUT_SIZE_LARGE){setTextSize( TypedValue.COMPLEX_UNIT_DIP, 32);}
        else if(screenSize==Configuration.SCREENLAYOUT_SIZE_XLARGE){setTextSize( TypedValue.COMPLEX_UNIT_DIP, 46);}
        else setTextSize( TypedValue.COMPLEX_UNIT_SP, 23);
        setSoundEffectsEnabled(false);
        dön= AppCompatDrawableManager.get().getDrawable(context,R.drawable.button_satir);
        setBackgroundDrawable(dön);
        setLayoutParams(p);
    }
    public cards(Context context,int size,int boyut) {
        super(context);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(size*3/4, size*4/3);
        int screenSize = context.getResources().getConfiguration().screenLayout &Configuration.SCREENLAYOUT_SIZE_MASK;
        if(screenSize==Configuration.SCREENLAYOUT_SIZE_SMALL){setTextSize( TypedValue.COMPLEX_UNIT_DIP, 20);}
        else if(screenSize==Configuration.SCREENLAYOUT_SIZE_NORMAL){setTextSize( TypedValue.COMPLEX_UNIT_DIP, 24);}
        else if(screenSize==Configuration.SCREENLAYOUT_SIZE_LARGE){setTextSize( TypedValue.COMPLEX_UNIT_DIP, 32);}
        else if(screenSize==Configuration.SCREENLAYOUT_SIZE_XLARGE){setTextSize( TypedValue.COMPLEX_UNIT_DIP, 46);}
        else setTextSize( TypedValue.COMPLEX_UNIT_SP, 23);
        setSoundEffectsEnabled(false);
        dön= AppCompatDrawableManager.get().getDrawable(context,R.drawable.button_satir);
        setBackgroundDrawable(dön);
        setLayoutParams(p);
    }
}

