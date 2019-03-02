package at.nachrichten.newsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import at.nachrichten.newsapp.article.Article;
import at.nachrichten.newsapp.async.TickerHandlerShortArticle;
import at.nachrichten.newsapp.database.DBHandler;
import at.nachrichten.newsapp.listener.DragListenerHome;
import at.nachrichten.newsapp.listener.TouchListener;
import at.nachrichten.newsapp.messages.Messages;
import at.nachrichten.newsapp.speak.Speak;
import at.nachrichten.newsapp.utils.Utils;

//TODO: exception handling full project
//TODO: set up news
public class Home extends MainActivity {
    private Speak speak;
    private Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        speak = new Speak(context,0);
        this.setTitle(doSpeakEntryText(this));

        /*Load content for ticker in own thread*/
        new TickerHandlerShortArticle().execute();

        appendOnClickListenerOnTestViews();
        sizeTextViewTextHeight();
        ((ImageView) findViewById(R.id.navigationComponent)).setVisibility(View.INVISIBLE);
    }


    public void sizeTextViewTextHeight(){
        ((TextView) findViewById(R.id.Info)).setTextSize(Utils.getScreenHeight(this)/50);
        ((TextView) findViewById(R.id.News)).setTextSize(Utils.getScreenHeight(this)/50);
        ((TextView) findViewById(R.id.Bookmarks)).setTextSize(Utils.getScreenHeight(this)/50);
        ((TextView) findViewById(R.id.Ticker)).setTextSize(Utils.getScreenHeight(this)/50);
    }
    public void appendOnClickListenerOnTestViews() {
        ((TextView) findViewById(R.id.Info)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.handleSpeaking(getString(R.string.introduction), speak);
            }
        });
        ((TextView) findViewById(R.id.News)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, News.class));
            }
        });
        ((TextView) findViewById(R.id.Bookmarks)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Bookmarks.class));
            }
        });
        ((TextView) findViewById(R.id.Ticker)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Ticker.class));
            }
        });
    }
}