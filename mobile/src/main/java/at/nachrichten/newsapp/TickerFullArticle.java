package at.nachrichten.newsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import at.nachrichten.newsapp.async.TickerHandlerFullArticle;
import at.nachrichten.newsapp.listener.DragListenerContent;
import at.nachrichten.newsapp.listener.TouchListener;
import at.nachrichten.newsapp.speak.Speak;
import at.nachrichten.newsapp.utils.Utils;

/**
 * Created by Harald on 07.12.2017.
 */

public class TickerFullArticle extends MainActivity {

    private Speak speak;
    private Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticker);

        this.setTitle(doSpeakEntryText(this));
        speak = new Speak(context,0);
        ((ImageView) findViewById(R.id.navigationComponent)).setVisibility(View.INVISIBLE);
        TickerHandlerFullArticle.setTextViewToSetContent(findViewById(R.id.ArticleTextView));

        findViewById(R.id.Back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Ticker.class));
            }
        });
        findViewById(R.id.Info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.doSpeakInfoText(context, speak);
            }
        });
        findViewById(R.id.ArticleTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.handleSpeaking(((TextView)findViewById(R.id.ArticleTextView)).getText().toString(), speak);
            }
        });
        setBackAndInfoTextViewHeight();
        sizeTextViewTextHeight();
    }

    public void sizeTextViewTextHeight(){
        ((TextView) findViewById(R.id.ArticleTextView)).setTextSize(Utils.getScreenHeight(this)/75);
    }

    public int sizeTextViewHeight(){
        return Utils.getScreenHeight(this)/6;
    }

    public float sizeBackTextViewTextHeight() {
        return Utils.getScreenHeight(this)/65;
    }

    public void setBackAndInfoTextViewHeight() {
        ((TextView) findViewById(R.id.Back)).setHeight(sizeTextViewHeight());
        ((TextView) findViewById(R.id.Back)).setMinHeight(sizeTextViewHeight());
        ((TextView) findViewById(R.id.Back)).setTextSize(sizeBackTextViewTextHeight());
        ((TextView) findViewById(R.id.Info)).setHeight(sizeTextViewHeight());
        ((TextView) findViewById(R.id.Info)).setMinHeight(sizeTextViewHeight());
        ((TextView) findViewById(R.id.Info)).setTextSize(sizeBackTextViewTextHeight());
    }
}
