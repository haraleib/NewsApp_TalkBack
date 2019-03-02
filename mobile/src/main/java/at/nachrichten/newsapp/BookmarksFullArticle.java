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
import java.util.Iterator;
import java.util.List;

import at.nachrichten.newsapp.article.Article;
import at.nachrichten.newsapp.database.DBHandler;
import at.nachrichten.newsapp.listener.DragListenerContent;
import at.nachrichten.newsapp.listener.TouchListener;
import at.nachrichten.newsapp.speak.Speak;
import at.nachrichten.newsapp.utils.Utils;

import static android.view.View.VISIBLE;

/**
 * Created by Harald on 17.01.2018.
 */

public class BookmarksFullArticle extends MainActivity {

    private List<TextView> fullArticle;
    public static String headerFulArticleToLoad;
    private DBHandler db;
    private Speak speak;
    private Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticker);

        this.setTitle(doSpeakEntryText(this));
        speak = new Speak(context,0);
        setSizeNavigationComponent();

        /*Initialize Listeners*/
        ((ImageView) findViewById(R.id.navigationComponent)).setVisibility(View.INVISIBLE);
        findViewById(R.id.ArticleTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (unsetMarkArticleAsBookmarked()) {
                    Bookmarks.articleAdded = 1;
                    startActivity(new Intent(context, Bookmarks.class));
                } else {
                    startActivity(new Intent(context, Bookmarks.class));
                }
            }
        });
        findViewById(R.id.Info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.doSpeakInfoText(BookmarksFullArticle.this, speak);
            }
        });

        setBackAndInfoTextViewHeight();
        findViewById(R.id.Back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Bookmarks.class));
            }
        });

        createNewsFeed();
        appendTextViewsToLayout();
        sizeTextViewTextHeight();
    }


    public boolean unsetMarkArticleAsBookmarked() {
        db = new DBHandler(this);
        List<Article> articles = db.getAllArticles();
        for (Article article : articles) {
            String headerStart = article.getDate() + "\n" + article.getHeader() + "\n" + article.getData() + "\n";
            headerStart = headerStart.substring(0, 15);
            BookmarksFullArticle.headerFulArticleToLoad = BookmarksFullArticle.headerFulArticleToLoad.substring(0, 15);
            if (headerStart.equals(BookmarksFullArticle.headerFulArticleToLoad)) {
                article.setIsBookMarked("No");
                db.updateArticle(article);
                return true;
            }
        }
        return false;
    }


    private void setSizeNavigationComponent() {
        ImageView navigationComponent = (ImageView) findViewById(R.id.navigationComponent);
        navigationComponent.getLayoutParams().height = Utils.getScreenHeight(this) / 2;
        navigationComponent.getLayoutParams().width = Utils.getScreenWidth(this) / 2;
    }

    private void appendTextViewsToLayout() {
   //     LinearLayout contentLayout = (LinearLayout) findViewById(R.id.content);
        //    Iterator<TextView> iterNewsFeed = fullArticle.iterator();
        //     while (iterNewsFeed.hasNext()) {
        //         contentLayout.addView(iterNewsFeed.next());
        //     }
        if(fullArticle.iterator().hasNext()) {
            TextView textView = (TextView) findViewById(R.id.ArticleTextView);
            textView.setText(fullArticle.iterator().next().getText().toString());
        }
    }

    private void createNewsFeed() {
        db = new DBHandler(this);
        List<Article> articles = db.getAllArticles();
        fullArticle = new ArrayList<TextView>();
        for(Article article : articles){
            String headerStart = article.getDate() + "\n" + article.getHeader() + "\n" + article.getData() + "\n";
            headerStart = headerStart.substring(0, 15);
            headerFulArticleToLoad = headerFulArticleToLoad.substring(0,15);
            if(headerFulArticleToLoad.equals(headerStart)){
                TextView textViewToAdd = createNextTextView();
                textViewToAdd.setText(article.getDate() + "\n" + article.getHeader() + "\n" + article.getData() + "\n");
                textViewToAdd.setTextSize(sizeTextViewTextHeight());
                fullArticle.add(textViewToAdd);
            }
        }
    }

    private TextView createNextTextView() {
        TextView layout = (TextView) findViewById(R.id.ArticleTextView);
        TextView nextView = new TextView(this);
        nextView.setLayoutParams(layout.getLayoutParams());
        nextView.setBackgroundResource(R.drawable.ticker_newsfeed_border);
        nextView.setTextColor(Color.BLACK);
        nextView.setTextSize(sizeTextViewTextHeight());
        nextView.setVisibility(VISIBLE);
        if (nextView.getParent() != null) {
            ((ViewGroup) nextView.getParent()).removeView(nextView);
        }
        return nextView;
    }

    public float sizeTextViewTextHeight(){
        return Utils.getScreenHeight(this)/65;
    }

    public static void setHeaderFulArticleToLoad(String headerFullArticleToLoad) {
        headerFulArticleToLoad = headerFullArticleToLoad;
    }

    public float sizeBackTextViewTextHeight() {
        return Utils.getScreenHeight(this)/65;
    }
    public int sizeTextViewHeight() {
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
