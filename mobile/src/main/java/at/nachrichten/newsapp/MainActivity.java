package at.nachrichten.newsapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import at.nachrichten.newsapp.listener.TouchListener;
import at.nachrichten.newsapp.utils.Utils;

/**
 * Created by Harald on 08.12.2017.
 */

public abstract class MainActivity extends Activity {

    public TouchListener touchListener;

    public void MainActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String doSpeakEntryText(Context context) {
        Activity currActivity = (Activity) context;
        Context currContext = context;
        String content = " ";

        if (currActivity instanceof Home) {
            content = chooseHomeEntry(currContext);
        } else if (currActivity instanceof Ticker) {
            content = chooseTickerEntry(currContext);
        } else if (currActivity instanceof TickerFullArticle) {
            content = chooseTickerFullArticleEntry(currContext);
        } else if (currActivity instanceof Login) {
            content = "Info";
        } else if (currActivity instanceof Bookmarks) {
            content = chooseBookMarksEntry(currContext);
        } else if (currActivity instanceof BookmarksFullArticle) {
            content = chooseBookMarksFullArticleEntry(currContext);
        } else if (currActivity instanceof News) {
            content = chooseNewsEntry(currContext);
        } else if (currActivity instanceof NewsShortArticle) {
            content = chooseNewsShortArticleEntry(currContext);
        } else if (currActivity instanceof NewsFullArticle) {
            content = chooseNewsFullArticleEntry(currContext);
        }
        return content;
    }

    public String chooseHomeEntry(Context context) {
        if (Utils.HOME_ENTRY) {
            return context.getString(R.string.HOME_ENTRY);
        } else {
            Utils.HOME_ENTRY = true;
            return context.getString(R.string.app_first_use);
        }
    }

    public String chooseTickerEntry(Context context) {
        if (Utils.TICKER_ENTRY) {
            return context.getString(R.string.TICKER_ENTRY);
        } else {
            Utils.TICKER_ENTRY = true;
            return context.getString(R.string.TICKER_FIRST_USE_SWIPE_DOWN);
        }
    }

    public String chooseTickerFullArticleEntry(Context context) {
        if (Utils.TICKER_FULL_ARTICLE_ENTRY) {
            return context.getString(R.string.TICKER_FULL_ARTICLE_ENTRY);
        } else {
            Utils.TICKER_FULL_ARTICLE_ENTRY = true;
            return context.getString(R.string.TICKER_FULL_ARTICLE_FIRST_USE_SWIPE_DOWN);
        }
    }

    public String chooseBookMarksEntry(Context context) {
        if (Utils.BOOKMARKS_ENTRY) {
            return context.getString(R.string.BOOKMARKS_ENTRY);
        } else {
            Utils.BOOKMARKS_ENTRY = true;
            return context.getString(R.string.BOOKMARKS_FIRST_USE_SWIPE_DOWN);
        }
    }

    public String chooseBookMarksFullArticleEntry(Context context) {
        if (Utils.BOOKMARKS_FULLARTIKEL_ENTRY) {
            return context.getString(R.string.BOOKMARKS_FULLARTIKEL_ENTRY);
        } else {
            Utils.BOOKMARKS_FULLARTIKEL_ENTRY = true;
            return context.getString(R.string.BOOKMARKS__FULL_ARTICLE_FIRST_USE_SWIPE_DOWN);
        }
    }

    public String chooseNewsEntry(Context context) {
        if (Utils.NEWS_ENTRY) {
            return context.getString(R.string.NEWS_ENTRY);
        } else {
            Utils.NEWS_ENTRY = true;
            return context.getString(R.string.NEWS_FIRST_USE_SWIPE_DOWN);
        }
    }

    public String chooseNewsShortArticleEntry(Context context) {
        if (Utils.NEWS_SHORT_ENTRY) {
            return context.getString(R.string.NEWS_SHORT_ENTRY);
        } else {
            Utils.NEWS_SHORT_ENTRY = true;
            return context.getString(R.string.NEWS_SHORT_FIRST_USE_SWIPE_DOWN);
        }
    }

    public String chooseNewsFullArticleEntry(Context context) {
        if (Utils.NEWS_FULL_ARTICLE_ENTRY) {
            return context.getString(R.string.NEWS_FULL_ARTICLE_ENTRY);
        } else {
            Utils.NEWS_FULL_ARTICLE_ENTRY = true;
            return context.getString(R.string.NEWS_FULL_ARTICLE_FIRST_USE_SWIPE_DOWN);
        }
    }
}
