package at.nachrichten.newsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import at.nachrichten.newsapp.async.TickerHandlerFullArticle;
import at.nachrichten.newsapp.async.TickerHandlerShortArticle;
import at.nachrichten.newsapp.listener.DragListenerContent;
import at.nachrichten.newsapp.listener.TouchListener;
import at.nachrichten.newsapp.speak.Speak;
import at.nachrichten.newsapp.utils.Utils;

import static android.view.View.VISIBLE;

/**
 * Created by Harald on 06.12.2017.
 */

public class Ticker extends MainActivity {

    private List<TextView> newsFeed;
    private Speak speak;
    private Context context = this;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticker);

        this.setTitle(doSpeakEntryText(this));
        speak = new Speak(context,0);

        ((ImageView) findViewById(R.id.navigationComponent)).setVisibility(View.INVISIBLE);
        findViewById(R.id.Back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Home.class));
            }
        });
        findViewById(R.id.Info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.doSpeakInfoText(context, speak);
            }
        });
        setBackAndInfoTextViewHeight();
        if (TickerHandlerShortArticle.isExecuted()) {
            createNewsFeed();
            findViewById(R.id.ArticleTextView).setVisibility(View.INVISIBLE);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    appendTextViewsToLayout();
                }
            });
        }
    }

    private void appendTextViewsToLayout() {
        LinearLayout contentLayout = (LinearLayout) findViewById(R.id.content);
        Iterator<TextView> iterNewsFeed = newsFeed.iterator();
        int i = 0;
        while (iterNewsFeed.hasNext() && i<3) {
            if(i==0) {
                TextView textView = iterNewsFeed.next();
                textView.setId(R.id.Ticker1);
                contentLayout.addView(textView);
                ((TextView) findViewById(R.id.Ticker1)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String contentKey = ((TextView) v).getText().toString();
                        TickerHandlerFullArticle.setUpUrl(contentKey);
                        new TickerHandlerFullArticle().execute();
                        startActivity(new Intent(context, TickerFullArticle.class));
                    }
                });
            }
            if(i==1) {
                TextView textView = iterNewsFeed.next();
                textView.setId(R.id.Ticker2);
                contentLayout.addView(textView);
                ((TextView) findViewById(R.id.Ticker2)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String contentKey = ((TextView) v).getText().toString();
                        TickerHandlerFullArticle.setUpUrl(contentKey);
                        new TickerHandlerFullArticle().execute();
                        startActivity(new Intent(context, TickerFullArticle.class));
                    }
                });
            }
            if(i==2) {
                TextView textView = iterNewsFeed.next();
                textView.setId(R.id.Ticker3);
                contentLayout.addView(textView);
                ((TextView) findViewById(R.id.Ticker3)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String contentKey = ((TextView) v).getText().toString();
                        TickerHandlerFullArticle.setUpUrl(contentKey);
                        new TickerHandlerFullArticle().execute();
                        startActivity(new Intent(context, TickerFullArticle.class));
                    }
                });
            }
            i++;
        }
    }

    private void createNewsFeed() {
        HashMap<String, String[]> contentMap = TickerHandlerShortArticle.getContentMap();
        Map.Entry<String, String[]> entry;
        Iterator<Map.Entry<String, String[]>> iterContent = contentMap.entrySet().iterator();
        newsFeed = new ArrayList<TextView>();

        int i = 0;
        while (iterContent.hasNext() && i <3) {
            TextView textViewToAdd = createNextTextView();
            entry = iterContent.next();
            textViewToAdd.setText(entry.getValue()[0]);
            newsFeed.add(textViewToAdd);
            i++;
        }
    }

    private TextView createNextTextView() {
        TextView layout = (TextView) findViewById(R.id.ArticleTextView);
        TextView nextView = new TextView(this);
        nextView.setLayoutParams(layout.getLayoutParams());
        nextView.setBackgroundResource(R.drawable.ticker_newsfeed_border);
        nextView.setTextColor(Color.BLACK);
        nextView.setHeight(sizeTextViewHeight());
        nextView.setMinHeight(sizeTextViewHeight());
        nextView.setTextSize(sizeTextViewTextHeight());
        nextView.setVisibility(VISIBLE);
        if (nextView.getParent() != null) {
            ((ViewGroup) nextView.getParent()).removeView(nextView);
        }
        return nextView;
    }

    public float sizeTextViewTextHeight(){
        //    Integer height = ((TextView) findViewById(R.id.ArticleTextView)).getHeight();
        //    Float heightF = (float) height;
        return Utils.getScreenHeight(this)/65;
    }

    public int sizeTextViewHeight(){
        return Utils.getScreenHeight(this)/5;
    }

    public float sizeBackTextViewTextHeight() {
        return Utils.getScreenHeight(this)/75;
    }

    public int sizeBackTextViewHeight() {
        return Utils.getScreenHeight(this) / 6;
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