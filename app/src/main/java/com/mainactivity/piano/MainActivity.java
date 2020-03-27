package com.mainactivity.piano;

import android.content.SharedPreferences;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import static com.mainactivity.piano.R.id.b16;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MediaRecorder mediaRecorder;
    public static String mFilename1 = null;
    public static String mFilename2 = null;
    public static String mFilename3 = null;
    public static String mFilename4 = null;
    public static String mFilename5 = null;
    public static String mFilename6 = null;

    boolean mStartRecording = true;

    public int recordingno;


    private Button leftNavigation, rightNavigation, recordButton, playButton;
    private SoundPool soundPool;

    private int c3, c3Black, d3, d3Black, e3, f3, f3Black, g3, g3Black, a3, a3Black, b3;
    private int c4, c4Black, d4, d4Black, e4, f4, f4Black, g4, g4Black, a4, a4Black, b4;
    private int c5, c5Black, d5, d5Black, e5, f5, f5Black, g5, g5Black, a5, a5Black, b5;
    private int c6, c6Black, d6, d6Black, e6, f6, f6Black, g6, g6Black, a6, a6Black, b6;
    private int c7, c7Black, d7, d7Black, e7, f7, f7Black, g7, g7Black, a7, a7Black, b7;

    private HorizontalScrollView scrollView;

    private Button buttonC3, C3black, buttonD3, buttonD3Black, buttonE3, buttonF3, buttonF3Black, buttonG3, buttonG3Black,
            buttonA3, buttonA3Black, buttonB3;
    private Button buttonC4, C4black, buttonD4, buttonD4Black, buttonE4, buttonF4, buttonF4Black, buttonG4, buttonG4Black,
            buttonA4, buttonA4Black, buttonB4;
    private Button buttonC5, C5black, buttonD5, buttonD5Black, buttonE5, buttonF5, buttonF5Black, buttonG5, buttonG5Black,
            buttonA5, buttonA5Black, buttonB5;
    private Button buttonC6, C6black, buttonD6, buttonD6Black, buttonE6, buttonF6, buttonF6Black, buttonG6, buttonG6Black,
            buttonA6, buttonA6Black, buttonB6;
    private Button buttonC7, C7black, buttonD7, buttonD7Black, buttonE7, buttonF7, buttonF7Black, buttonG7, buttonG7Black,
            buttonA7, buttonA7Black, buttonB7;

    private TextView C3, D3, E3, F3, G3, A3, B3;
    private TextView C4, D4, E4, F4, G4, A4, B4;
    private TextView C5, D5, E5, F5, G5, A5, B5;
    private TextView C6, D6, E6, F6, G6, A6, B6;
    private TextView C7, D7, E7, F7, G7, A7, B7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView = (HorizontalScrollView) findViewById(R.id.scroll_view);
        initAllPianoKeys();
        TextViewsId();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(6)
                .build();
        sounds();

        setListener();

        leftNavigation = findViewById(R.id.bt_left_navigation);
        rightNavigation = findViewById(R.id.bt_right_navigation);
        recordButton = findViewById(R.id.bt_record);
        playButton = findViewById(R.id.bt_play_recording);

        leftNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.scrollTo((int) scrollView.getScrollX() - 60, (int) scrollView.getScrollX());
            }
        });

        rightNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.scrollTo((int) scrollView.getScrollX() + 60, (int) scrollView.getScrollX());
            }
        });

        mFilename1 = getExternalCacheDir().getAbsolutePath();
        mFilename1 += "/audiorecorder1.3gp";
        mFilename2 = getExternalCacheDir().getAbsolutePath();
        mFilename2 += "/audiorecorder2.3gp";
        mFilename3 = getExternalCacheDir().getAbsolutePath();
        mFilename3 += "/audiorecorder3.3gp";
        mFilename4 = getExternalCacheDir().getAbsolutePath();
        mFilename4 += "/audiorecorder4.3gp";
        mFilename5 = getExternalCacheDir().getAbsolutePath();
        mFilename5 += "/audiorecorder5.3gp";
        mFilename6 = getExternalCacheDir().getAbsolutePath();
        mFilename6 += "/audiorecorder6.3gp";

        SharedPreferences preferences = getSharedPreferences("FILENO", MODE_PRIVATE);
        recordingno = preferences.getInt("fileno", 1);
    }

    private void initAllPianoKeys() {
        buttonC3 = (Button) findViewById(R.id.p1);
        C3black = (Button) findViewById(R.id.b1);
        buttonD3 = (Button) findViewById(R.id.p2);
        buttonD3Black = (Button) findViewById(R.id.b2);
        buttonE3 = (Button) findViewById(R.id.p3);
        buttonF3 = (Button) findViewById(R.id.p4);
        buttonF3Black = (Button) findViewById(R.id.b4);
        buttonG3 = (Button) findViewById(R.id.p5);
        buttonG3Black = (Button) findViewById(R.id.b5);
        buttonA3 = (Button) findViewById(R.id.p6);
        buttonA3Black = (Button) findViewById(R.id.b6);
        buttonB3 = (Button) findViewById(R.id.p7);

        buttonC4 = (Button) findViewById(R.id.p8);
        C4black = (Button) findViewById(R.id.b8);
        buttonD4 = (Button) findViewById(R.id.p9);
        buttonD4Black = (Button) findViewById(R.id.b9);
        buttonE4 = (Button) findViewById(R.id.p10);
        buttonF4 = (Button) findViewById(R.id.p11);
        buttonF4Black = (Button) findViewById(R.id.b11);
        buttonG4 = (Button) findViewById(R.id.p12);
        buttonG4Black = (Button) findViewById(R.id.b12);
        buttonA4 = (Button) findViewById(R.id.p13);
        buttonA4Black = (Button) findViewById(R.id.b13);
        buttonB4 = (Button) findViewById(R.id.p14);

        buttonC5 = (Button) findViewById(R.id.p15);
        C5black = (Button) findViewById(R.id.b15);
        buttonD5 = (Button) findViewById(R.id.p16);
        buttonD5Black = (Button) findViewById(b16);
        buttonE5 = (Button) findViewById(R.id.p17);
        buttonF5 = (Button) findViewById(R.id.p18);
        buttonF5Black = (Button) findViewById(R.id.b18);
        buttonG5 = (Button) findViewById(R.id.p19);
        buttonG5Black = (Button) findViewById(R.id.b19);
        buttonA5 = (Button) findViewById(R.id.p20);
        buttonA5Black = (Button) findViewById(R.id.b20);
        buttonB5 = (Button) findViewById(R.id.p21);

        buttonC6 = (Button) findViewById(R.id.p22);
        C6black = (Button) findViewById(R.id.b22);
        buttonD6 = (Button) findViewById(R.id.p23);
        buttonD6Black = (Button) findViewById(R.id.b23);
        buttonE6 = (Button) findViewById(R.id.p24);
        buttonF6 = (Button) findViewById(R.id.p25);
        buttonF6Black = (Button) findViewById(R.id.b25);
        buttonG6 = (Button) findViewById(R.id.p26);
        buttonG6Black = (Button) findViewById(R.id.b26);
        buttonA6 = (Button) findViewById(R.id.p27);
        buttonA6Black = (Button) findViewById(R.id.b27);
        buttonB6 = (Button) findViewById(R.id.p28);

        buttonC7 = (Button) findViewById(R.id.p29);
        C7black = (Button) findViewById(R.id.b29);
        buttonD7 = (Button) findViewById(R.id.p30);
        buttonD7Black = (Button) findViewById(R.id.b30);
        buttonE7 = (Button) findViewById(R.id.p31);
        buttonF7 = (Button) findViewById(R.id.p32);
        buttonF7Black = (Button) findViewById(R.id.b32);
        buttonG7 = (Button) findViewById(R.id.p33);
        buttonG7Black = (Button) findViewById(R.id.b33);
        buttonA7 = (Button) findViewById(R.id.p34);
        buttonA7Black = (Button) findViewById(R.id.b34);
        buttonB7 = (Button) findViewById(R.id.p35);

    }

    private void TextViewsId() {
        C3 = (TextView) findViewById(R.id.tc3);
        D3 = (TextView) findViewById(R.id.td3);
        E3 = (TextView) findViewById(R.id.te3);
        F3 = (TextView) findViewById(R.id.tf3);
        G3 = (TextView) findViewById(R.id.tg3);
        A3 = (TextView) findViewById(R.id.ta3);
        B3 = (TextView) findViewById(R.id.tb3);

        C4 = (TextView) findViewById(R.id.tc4);
        D4 = (TextView) findViewById(R.id.td4);
        E4 = (TextView) findViewById(R.id.te4);
        F4 = (TextView) findViewById(R.id.tf3);
        G4 = (TextView) findViewById(R.id.tg4);
        A4 = (TextView) findViewById(R.id.ta4);
        B4 = (TextView) findViewById(R.id.tb4);

        C5 = (TextView) findViewById(R.id.tc5);
        D5 = (TextView) findViewById(R.id.td5);
        E5 = (TextView) findViewById(R.id.te5);
        F5 = (TextView) findViewById(R.id.tf5);
        G5 = (TextView) findViewById(R.id.tg5);
        A5 = (TextView) findViewById(R.id.ta5);
        B5 = (TextView) findViewById(R.id.tb5);

        C6 = (TextView) findViewById(R.id.tc6);
        D6 = (TextView) findViewById(R.id.td6);
        E6 = (TextView) findViewById(R.id.te6);
        F6 = (TextView) findViewById(R.id.tf6);
        G6 = (TextView) findViewById(R.id.tg6);
        A6 = (TextView) findViewById(R.id.ta6);
        B6 = (TextView) findViewById(R.id.tb6);

        C7 = (TextView) findViewById(R.id.tc7);
        D7 = (TextView) findViewById(R.id.td7);
        E7 = (TextView) findViewById(R.id.te7);
        F7 = (TextView) findViewById(R.id.tf7);
        G7 = (TextView) findViewById(R.id.tg7);
        A7 = (TextView) findViewById(R.id.ta7);
        B7 = (TextView) findViewById(R.id.tb7);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tc3:
            case R.id.p1:
                soundPool.play(c3, 1, 1, 0, 0, 1);
                break;
            case R.id.b1:
                soundPool.play(c3Black, 1, 1, 0, 0, 1);
                break;
            case R.id.td3:
            case R.id.p2:
                soundPool.play(d3, 1, 1, 0, 0, 1);
                break;
            case R.id.b2:
                soundPool.play(d3Black, 1, 1, 0, 0, 1);
                break;

            case R.id.te3:
            case R.id.p3:
                soundPool.play(e3, 1, 1, 1, 0, 1);
                break;
            case R.id.tf3:
            case R.id.p4:
                soundPool.play(f3, 1, 1, 0, 0, 1);
                break;
            case R.id.b4:
                soundPool.play(f3Black, 1, 1, 0, 0, 1);
                break;

            case R.id.tg3:
            case R.id.p5:
                soundPool.play(g3, 1, 1, 0, 0, 1);
                break;
            case R.id.b5:
                soundPool.play(g3Black, 1, 1, 0, 0, 1);
                break;

            case R.id.ta3:
            case R.id.p6:
                soundPool.play(a3, 1, 1, 0, 0, 1);
                break;

            case R.id.b6:
                soundPool.play(a3Black, 1, 1, 0, 0, 1);
                break;

            case R.id.tb3:
            case R.id.p7:
                soundPool.play(b3, 1, 1, 0, 0, 1);
                break;

            case R.id.tc4:
            case R.id.p8:
                soundPool.play(c4, 1, 1, 0, 0, 1);
                break;
            case R.id.b8:
                soundPool.play(c4Black, 1, 1, 0, 0, 1);
                break;

            case R.id.td4:
            case R.id.p9:
                soundPool.play(d4, 1, 1, 0, 0, 1);
                break;

            case R.id.b9:
                soundPool.play(d4Black, 1, 1, 0, 0, 1);
                break;

            case R.id.te4:
            case R.id.p10:
                soundPool.play(e4, 1, 1, 0, 0, 1);
                break;
            case R.id.tf4:
            case R.id.p11:
                soundPool.play(f4, 1, 1, 0, 0, 1);
                break;

            case R.id.b11:
                soundPool.play(f4Black, 1, 1, 0, 0, 1);
                break;

            case R.id.tg4:
            case R.id.p12:
                soundPool.play(g4, 1, 1, 0, 0, 1);
                break;

            case R.id.b13:
                soundPool.play(g4Black, 1, 1, 0, 0, 1);
                break;

            case R.id.ta4:
            case R.id.p13:
                soundPool.play(a4, 1, 1, 0, 0, 1);
                break;

            case R.id.b15:
                soundPool.play(a4Black, 1, 1, 0, 0, 1);
                break;

            case R.id.tb4:
            case R.id.p14:
                soundPool.play(b4, 1, 1, 0, 0, 1);
                break;


            case R.id.tc5:
            case R.id.p15:
                soundPool.play(c5, 1, 1, 0, 0, 1);
                break;
            case R.id.b16:
                soundPool.play(c5Black, 1, 1, 0, 0, 1);
                break;

            case R.id.td5:
            case R.id.p16:
                soundPool.play(d5, 1, 1, 0, 0, 1);
                break;

            case R.id.b18:
                soundPool.play(d5Black, 1, 1, 0, 0, 1);
                break;

            case R.id.te5:
            case R.id.p17:
                soundPool.play(e5, 1, 1, 0, 0, 1);
                break;
            case R.id.tf5:
            case R.id.p18:
                soundPool.play(f5, 1, 1, 0, 0, 1);
                break;

            case R.id.b19:
                soundPool.play(f5Black, 1, 1, 0, 0, 1);
                break;

            case R.id.tg5:
            case R.id.p19:
                soundPool.play(g5, 1, 1, 0, 0, 1);
                break;

            case R.id.b20:
                soundPool.play(g5Black, 1, 1, 0, 0, 1);
                break;

            case R.id.ta5:
            case R.id.p20:
                soundPool.play(a5, 1, 1, 0, 0, 1);
                break;

            case R.id.b22:
                soundPool.play(a5Black, 1, 1, 0, 0, 1);
                break;

            case R.id.tb5:
            case R.id.p21:
                soundPool.play(b5, 1, 1, 0, 0, 1);
                break;


            case R.id.tc6:
            case R.id.p22:
                soundPool.play(c6, 1, 1, 0, 0, 1);
                break;
            case R.id.b23:
                soundPool.play(c6Black, 1, 1, 0, 0, 1);
                break;

            case R.id.td6:
            case R.id.p23:
                soundPool.play(d6, 1, 1, 0, 0, 1);
                break;

            case R.id.b25:
                soundPool.play(d6Black, 1, 1, 0, 0, 1);
                break;

            case R.id.te6:
            case R.id.p24:
                soundPool.play(e6, 1, 1, 0, 0, 1);
                break;

            case R.id.tf6:
            case R.id.p25:
                soundPool.play(f6, 1, 1, 0, 0, 1);
                break;

            case R.id.b26:
                soundPool.play(f6Black, 1, 1, 0, 0, 1);
                break;

            case R.id.tg6:
            case R.id.p26:
                soundPool.play(g6, 1, 1, 0, 0, 1);
                break;

            case R.id.b27:
                soundPool.play(g6Black, 1, 1, 0, 0, 1);
                break;

            case R.id.ta6:
            case R.id.p27:
                soundPool.play(a6, 1, 1, 0, 0, 1);
                break;

            case R.id.b29:
                soundPool.play(a6Black, 1, 1, 0, 0, 1);
                break;

            case R.id.tb6:
            case R.id.p28:
                soundPool.play(b4, 1, 1, 0, 0, 1);
                break;


            case R.id.tc7:
            case R.id.p29:
                soundPool.play(c7, 1, 1, 0, 0, 1);
                break;
            case R.id.b30:
                soundPool.play(c7Black, 1, 1, 0, 0, 1);
                break;

            case R.id.td7:
            case R.id.p30:
                soundPool.play(d7, 1, 1, 0, 0, 1);
                break;

            case R.id.b32:
                soundPool.play(d7Black, 1, 1, 0, 0, 1);
                break;

            case R.id.te7:
            case R.id.p31:
                soundPool.play(e7, 1, 1, 0, 0, 1);
                break;

            case R.id.tf7:
            case R.id.p32:
                soundPool.play(f7, 1, 1, 0, 0, 1);
                break;

            case R.id.b33:
                soundPool.play(f7Black, 1, 1, 0, 0, 1);
                break;

            case R.id.tg7:
            case R.id.p33:
                soundPool.play(g7, 1, 1, 0, 0, 1);
                break;

            case R.id.b34:
                soundPool.play(g7Black, 1, 1, 0, 0, 1);
                break;

            case R.id.ta7:
            case R.id.p34:
                soundPool.play(a7, 1, 1, 0, 0, 1);
                break;

            case R.id.b12:
                soundPool.play(a7Black, 1, 1, 0, 0, 1);
                break;

            case R.id.tb7:
            case R.id.p35:
                soundPool.play(b7, 1, 1, 0, 0, 1);
                break;

        }
    }

    private void sounds() {
        c3 = soundPool.load(this, R.raw.c3, 1);
        c3Black = soundPool.load(this, R.raw.c3black, 1);
        e3 = soundPool.load(this, R.raw.e3, 1);
        f3 = soundPool.load(this, R.raw.f3, 1);
        f3Black = soundPool.load(this, R.raw.f3black, 1);
        g3 = soundPool.load(this, R.raw.g3, 1);
        g3Black = soundPool.load(this, R.raw.g3black, 1);
        d3 = soundPool.load(this, R.raw.d3, 1);
        d3Black = soundPool.load(this, R.raw.d3black, 1);
        a3 = soundPool.load(this, R.raw.a3, 1);
        a3Black = soundPool.load(this, R.raw.a3black, 1);
        b3 = soundPool.load(this, R.raw.b3, 1);

        c4 = soundPool.load(this, R.raw.c4, 1);
        c4Black = soundPool.load(this, R.raw.c4black, 1);
        e4 = soundPool.load(this, R.raw.e4, 1);
        f4 = soundPool.load(this, R.raw.f4, 1);
        f4Black = soundPool.load(this, R.raw.f4black, 1);
        g4 = soundPool.load(this, R.raw.g4, 1);
        g4Black = soundPool.load(this, R.raw.g4black, 1);
        d4 = soundPool.load(this, R.raw.d4, 1);
        d4Black = soundPool.load(this, R.raw.d4black, 1);
        a4 = soundPool.load(this, R.raw.a4, 1);
        a4Black = soundPool.load(this, R.raw.a4black, 1);
        b4 = soundPool.load(this, R.raw.b4, 1);

        c5 = soundPool.load(this, R.raw.c5, 1);
        c5Black = soundPool.load(this, R.raw.c5black, 1);
        e5 = soundPool.load(this, R.raw.e5, 1);
        f5 = soundPool.load(this, R.raw.f5, 1);
        f5Black = soundPool.load(this, R.raw.f5black, 1);
        g5 = soundPool.load(this, R.raw.g5, 1);
        g5Black = soundPool.load(this, R.raw.g5black, 1);
        d5 = soundPool.load(this, R.raw.d5, 1);
        d5Black = soundPool.load(this, R.raw.d5black, 1);
        a5 = soundPool.load(this, R.raw.a5, 1);
        a5Black = soundPool.load(this, R.raw.a5black, 1);
        b5 = soundPool.load(this, R.raw.b5, 1);

        c6 = soundPool.load(this, R.raw.c6, 1);
        c6Black = soundPool.load(this, R.raw.c6black, 1);
        e6 = soundPool.load(this, R.raw.e6, 1);
        f6 = soundPool.load(this, R.raw.f6, 1);
        f6Black = soundPool.load(this, R.raw.f6black, 1);
        g6 = soundPool.load(this, R.raw.g6, 1);
        g6Black = soundPool.load(this, R.raw.g6black, 1);
        d6 = soundPool.load(this, R.raw.d6, 1);
        d6Black = soundPool.load(this, R.raw.d6black, 1);
        a6 = soundPool.load(this, R.raw.a6, 1);
        a6Black = soundPool.load(this, R.raw.a6black, 1);
        b6 = soundPool.load(this, R.raw.b6, 1);

        c7 = soundPool.load(this, R.raw.c7, 1);
        c7Black = soundPool.load(this, R.raw.c7black, 1);
        e7 = soundPool.load(this, R.raw.e7, 1);
        f7 = soundPool.load(this, R.raw.f7, 1);
        f7Black = soundPool.load(this, R.raw.f7black, 1);
        g7 = soundPool.load(this, R.raw.g7, 1);
        g7Black = soundPool.load(this, R.raw.g7black, 1);
        d7 = soundPool.load(this, R.raw.d7, 1);
        d7Black = soundPool.load(this, R.raw.d7black, 1);
        a7 = soundPool.load(this, R.raw.a7, 1);
        a7Black = soundPool.load(this, R.raw.a7black, 1);
        b7 = soundPool.load(this, R.raw.b7, 1);

    }

    private void setListener() {
        buttonC3.setOnClickListener(this);
        C3black.setOnClickListener(this);
        buttonD3.setOnClickListener(this);
        buttonD3Black.setOnClickListener(this);
        buttonE3.setOnClickListener(this);
        buttonF3.setOnClickListener(this);
        buttonF3Black.setOnClickListener(this);
        buttonG3.setOnClickListener(this);
        buttonG3Black.setOnClickListener(this);
        buttonA3.setOnClickListener(this);
        buttonA3Black.setOnClickListener(this);
        buttonB3.setOnClickListener(this);


        buttonC4.setOnClickListener(this);
        C4black.setOnClickListener(this);
        buttonD4.setOnClickListener(this);
        buttonD4Black.setOnClickListener(this);
        buttonE4.setOnClickListener(this);
        buttonF4.setOnClickListener(this);
        buttonF4Black.setOnClickListener(this);
        buttonG4.setOnClickListener(this);
        buttonG4Black.setOnClickListener(this);
        buttonA4.setOnClickListener(this);
        buttonA4Black.setOnClickListener(this);
        buttonB4.setOnClickListener(this);


        buttonC5.setOnClickListener(this);
        C5black.setOnClickListener(this);
        C5black.setOnClickListener(this);
        buttonD5.setOnClickListener(this);
        buttonD5Black.setOnClickListener(this);
        buttonE5.setOnClickListener(this);
        buttonF5.setOnClickListener(this);
        buttonF5Black.setOnClickListener(this);
        buttonG5.setOnClickListener(this);
        buttonG5Black.setOnClickListener(this);
        buttonA5.setOnClickListener(this);
        buttonA5Black.setOnClickListener(this);
        buttonB5.setOnClickListener(this);


        buttonC6.setOnClickListener(this);
        C6black.setOnClickListener(this);
        buttonD6.setOnClickListener(this);
        buttonD6Black.setOnClickListener(this);
        buttonE6.setOnClickListener(this);
        buttonF6.setOnClickListener(this);
        buttonF6Black.setOnClickListener(this);
        buttonG6.setOnClickListener(this);
        buttonG6Black.setOnClickListener(this);
        buttonA6.setOnClickListener(this);
        buttonA6Black.setOnClickListener(this);
        buttonB6.setOnClickListener(this);


        buttonC7.setOnClickListener(this);
        C7black.setOnClickListener(this);
        buttonD7.setOnClickListener(this);
        buttonD7Black.setOnClickListener(this);
        buttonE7.setOnClickListener(this);
        buttonF7.setOnClickListener(this);
        buttonF7Black.setOnClickListener(this);
        buttonG7.setOnClickListener(this);
        buttonG7Black.setOnClickListener(this);
        buttonA7.setOnClickListener(this);
        buttonA7Black.setOnClickListener(this);
        buttonB7.setOnClickListener(this);


    }

    public void play(View view) {

    }

    public void record(View view) {
       onRecord(mStartRecording);
       if(mStartRecording){
           recordButton.setText("finish");
       }
       else{
           recordButton.setText("Record");
       }

       mStartRecording = !mStartRecording;
    }

    private void startRecording() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

        switch (recordingno) {
         case 1 :
                  mediaRecorder.setOutputFile(mFilename1);
                  recordingno++;
                  if(recordingno==7)
                      recordingno=1;
                  break;
            case 2 :
                      mediaRecorder.setOutputFile(mFilename2);
                      recordingno++;
                      if(recordingno==7)
                          recordingno=1;
                      break;
            case 3 :
                mediaRecorder.setOutputFile(mFilename3);
                recordingno++;
                if(recordingno==7)
                    recordingno=1;
                break;
            case 4 :
                mediaRecorder.setOutputFile(mFilename4);
                recordingno++;
                if(recordingno==7)
                    recordingno=1;
                break;
            case 5:
                mediaRecorder.setOutputFile(mFilename5);
                recordingno++;
                if(recordingno==7)
                    recordingno=1;
                break;
            case 6 :
                mediaRecorder.setOutputFile(mFilename6);
                recordingno++;
                if(recordingno==7)
                    recordingno=1;
                break;

        }
        SharedPreferences.Editor editor= (SharedPreferences.Editor) getSharedPreferences("FILENO",MODE_PRIVATE);
        editor.putInt("fileno",recordingno);
        editor.commit();

        mediaRecorder.setAudioSource(MediaRecorder.AudioEncoder.AMR_NB);

        try{
            mediaRecorder.prepare();
        }
        catch (IOException e){
            Log.e("prepare is fail", "startRecording: Failed ");
        }
        mediaRecorder.start();
    }

    private void stopRecording(){
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder=null;
    }

    private  void onRecord(boolean start){
        if(start){
            startRecording();
        }
        else {
            stopRecording();
        }
        if(recordingno ==1){
    Toast recordingmsg =    Toast.makeText(getApplicationContext(), "song" + 6 + "saved", Toast.LENGTH_SHORT);
    recordingmsg.show();
        }
        else {
            int temprecordingno=recordingno-1;
            Toast recordingmsg =    Toast.makeText(getApplicationContext(), "song" + temprecordingno + "saved", Toast.LENGTH_SHORT);
            recordingmsg.show();
        }
    }
}
