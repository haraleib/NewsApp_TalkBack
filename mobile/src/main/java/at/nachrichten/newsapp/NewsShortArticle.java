package at.nachrichten.newsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
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

public class NewsShortArticle extends MainActivity {
    private List<TextView> shortArticle;
    private static List<String> shortArticleHeader;
    private static String categoriesToLoad;
    private DBHandler db;
    private Speak speak;
    private Context context = this;
    public static int articleAdded = 0;

    public static List<String> getShortArticleHeader() {
        return shortArticleHeader;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticker);
        if(articleAdded == 0){
            this.setTitle(doSpeakEntryText(this));
        } else if(articleAdded == 1){
            this.setTitle("Artikel wurde in die Merkliste hinzugefügt." + doSpeakEntryText(context));
            articleAdded=0;
        }

        speak = new Speak(context,0);
        ((ImageView) findViewById(R.id.navigationComponent)).setVisibility(View.INVISIBLE);

        findViewById(R.id.Back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, News.class));
            }
        });
        findViewById(R.id.Info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.doSpeakInfoText(context, speak);
            }
        });
        setBackAndInfoTextViewHeight();

        categoriesToLoad = News.getCategoryChoosed();

        createNewsFeed();
       appendTextViewsToLayout();

    }

    private void appendTextViewsToLayout() {
        LinearLayout contentLayout = (LinearLayout) findViewById(R.id.content);
        Iterator<TextView> iterNewsFeed = shortArticle.iterator();
        int i = 0;
        while (iterNewsFeed.hasNext()) {
            if(i==0) {
                assignToLoadingArticleView(iterNewsFeed);
                ((TextView) findViewById(R.id.ArticleTextView)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(String header : NewsShortArticle.getShortArticleHeader()){
                            if(header.equals(((TextView) v).getText().toString())){
                                NewsFullArticle.setHeaderFulArticleToLoad(header);
                            }
                        }
                        startActivity(new Intent(context, NewsFullArticle.class));
                    }
                });
            }
            if(i==2) {
                TextView textView = iterNewsFeed.next();
                textView.setId(R.id.NewsShortArticleTextView2);
                contentLayout.addView(textView);
                ((TextView) findViewById(R.id.NewsShortArticleTextView2)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(String header : NewsShortArticle.getShortArticleHeader()){
                            if(header.equals(((TextView) v).getText().toString())){
                                NewsFullArticle.setHeaderFulArticleToLoad(header);
                            }
                        }
                        startActivity(new Intent(context, NewsFullArticle.class));
                    }
                });
            }
            if(i==3) {
                TextView textView = iterNewsFeed.next();
                textView.setId(R.id.NewsShortArticleTextView3);
                contentLayout.addView(textView);
                ((TextView) findViewById(R.id.NewsShortArticleTextView3)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(String header : NewsShortArticle.getShortArticleHeader()){
                            if(header.equals(((TextView) v).getText().toString())){
                                NewsFullArticle.setHeaderFulArticleToLoad(header);
                            }
                        }
                        startActivity(new Intent(context, NewsFullArticle.class));
                    }
                });
            }

            i++;
        }
    }

    private void createNewsFeed() {
        db = new DBHandler(this);
        List<Article> articles = db.getAllArticles();
        shortArticleHeader = new ArrayList<String>();
        shortArticle = new ArrayList<TextView>();
        int i = 0;
        for(Article article : articles){
            if(article.getCategoryEng().equals(categoriesToLoad) || article.getCategoryGer().equals(categoriesToLoad)) {
                if(i == 3) {
                    return;
                }
                shortArticleHeader.add(article.getDate() + "\n" + article.getHeader() + "\n");
                TextView textViewToAdd = createNextTextView();
                textViewToAdd.setText(article.getDate() + "\n" + article.getHeader() + "\n");
                textViewToAdd.setTextSize(sizeTextViewTextHeight());
                shortArticle.add(textViewToAdd);
                i++;
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

    private void assignToLoadingArticleView( Iterator<TextView> iterNewsFeed){
        ((TextView) findViewById(R.id.ArticleTextView)).setText(iterNewsFeed.next().getText());
        ((TextView) findViewById(R.id.ArticleTextView)).setTextSize(sizeTextViewTextHeight());
        ((TextView) findViewById(R.id.ArticleTextView)).setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        ((TextView) findViewById(R.id.ArticleTextView)).setTextColor(Color.BLACK);
    }

    public float sizeTextViewTextHeight(){
        return Utils.getScreenHeight(this)/65;
    }

    public float sizeBackTextViewTextHeight() {
        return Utils.getScreenHeight(this)/65;
    }
    public int sizeTextViewHeight() {
        return Utils.getScreenHeight(this) / 5;
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
