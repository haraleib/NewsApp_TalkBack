package at.nachrichten.newsapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import org.w3c.dom.Text;

import at.nachrichten.newsapp.Bookmarks;
import at.nachrichten.newsapp.BookmarksFullArticle;
import at.nachrichten.newsapp.Home;
import at.nachrichten.newsapp.Login;
import at.nachrichten.newsapp.News;
import at.nachrichten.newsapp.NewsFullArticle;
import at.nachrichten.newsapp.NewsShortArticle;
import at.nachrichten.newsapp.R;
import at.nachrichten.newsapp.Ticker;
import at.nachrichten.newsapp.TickerFullArticle;
import at.nachrichten.newsapp.speak.Speak;

/**
 * Created by Harald on 10.01.2018.
 */

public class Utils {

    public static boolean HOME_ENTRY = false;
    public static boolean TICKER_ENTRY = false;
    public static boolean TICKER_FULL_ARTICLE_ENTRY = false;
    public static boolean LOGIN_ENTRY = false;
    public static boolean NEWS_ENTRY = false;
    public static boolean NEWS_SHORT_ENTRY = false;
    public static boolean NEWS_FULL_ARTICLE_ENTRY = false;
    public static boolean BOOKMARKS_ENTRY = false;
    public static boolean BOOKMARKS_FULLARTIKEL_ENTRY = false;
    public static boolean APP_FIRST_USE_DONE = false;
    public static int getIdFromTextView(Context context, String textViewName){
        return context.getResources().getIdentifier(textViewName, "id", context.getPackageName());
    }
    public static View getRootView(Activity activity){
        return activity.findViewById(android.R.id.content);
    }

    public static boolean isTextView(View rootView, int id){
        return rootView.findViewById(id) instanceof TextView ? true : false;
    }

    public static boolean classEqualsOtherClass(Context context, Class clazz){
        return context.getClass().equals(clazz) ? true: false;
    }

    public static void goToActivity(Context context, Class clazz){
        Intent intent = new Intent(context, clazz);
        ((Activity)context).startActivity(intent);
    }

    public static int getScreenHeight(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public static int getScreenWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static void doSpeakEntryText(Context context, Speak speak){
        Activity currActivity = (Activity) context;
        Context currContext = context;

        if (currActivity instanceof Home) {
           chooseHomeEntry(currContext, speak);
        } else if (currActivity instanceof Ticker) {
            chooseTickerEntry(currContext, speak);
        } else if (currActivity instanceof TickerFullArticle) {
            chooseTickerFullArticleEntry(currContext, speak);
        } else if (currActivity instanceof Login) {
            speak.speak(currContext.getString(R.string.info_text));
        } else if (currActivity instanceof Bookmarks) {
            chooseBookMarksEntry(currContext, speak);
        } else if (currActivity instanceof BookmarksFullArticle) {
            chooseBookMarksFullArticleEntry(currContext, speak);
        } else if (currActivity instanceof News) {
            chooseNewsEntry(currContext, speak);
        } else if (currActivity instanceof NewsShortArticle) {
            chooseNewsShortArticleEntry(currContext, speak);
        } else if (currActivity instanceof NewsFullArticle) {
            chooseNewsFullArticleEntry(currContext, speak);
        }
    }

    public static void doSpeakInfoText(Context context, Speak speak){
        Activity currActivity = (Activity) context;
        Context currContext = context;

        if (currActivity instanceof Home) {
            Utils.handleSpeaking(context.getString(R.string.app_first_use), speak);
        } else if (currActivity instanceof Ticker) {
            Utils.handleSpeaking(context.getString(R.string.TICKER_FIRST_USE_SWIPE_DOWN), speak);
        } else if (currActivity instanceof TickerFullArticle) {
            Utils.handleSpeaking(context.getString(R.string.TICKER_FULL_ARTICLE_FIRST_USE_SWIPE_DOWN), speak);
        } else if (currActivity instanceof Login) {
            speak.speak(currContext.getString(R.string.info_text));
        } else if (currActivity instanceof Bookmarks) {
            Utils.handleSpeaking(context.getString(R.string.BOOKMARKS_FIRST_USE_SWIPE_DOWN), speak);
        } else if (currActivity instanceof BookmarksFullArticle) {
            Utils.handleSpeaking(context.getString(R.string.BOOKMARKS__FULL_ARTICLE_FIRST_USE_SWIPE_DOWN), speak);
        } else if (currActivity instanceof News) {
            Utils.handleSpeaking(context.getString(R.string.NEWS_FIRST_USE_SWIPE_DOWN), speak);
        } else if (currActivity instanceof NewsShortArticle) {
            Utils.handleSpeaking(context.getString(R.string.NEWS_SHORT_FIRST_USE_SWIPE_DOWN), speak);
        } else if (currActivity instanceof NewsFullArticle) {
            Utils.handleSpeaking(context.getString(R.string.NEWS_FULL_ARTICLE_FIRST_USE_SWIPE_DOWN), speak);
        }
    }


    public static void chooseHomeEntry(Context context, Speak speak){
        if(Utils.HOME_ENTRY){
            Utils.handleSpeaking(context.getString(R.string.HOME_ENTRY), speak);
        }else{
            Utils.handleSpeaking(context.getString(R.string.app_first_use), speak);
            Utils.HOME_ENTRY = true;
        }
    }
    public static void chooseTickerEntry(Context context, Speak speak) {
        if (Utils.TICKER_ENTRY) {
            Utils.handleSpeaking(context.getString(R.string.TICKER_ENTRY), speak);
        } else {
            Utils.handleSpeaking(context.getString(R.string.TICKER_FIRST_USE_SWIPE_DOWN), speak);
            Utils.TICKER_ENTRY = true;
        }
    }

    public static void chooseTickerFullArticleEntry(Context context, Speak speak) {
        if (Utils.TICKER_FULL_ARTICLE_ENTRY) {
            Utils.handleSpeaking(context.getString(R.string.TICKER_FULL_ARTICLE_ENTRY), speak);
        } else {
            Utils.handleSpeaking(context.getString(R.string.TICKER_FULL_ARTICLE_FIRST_USE_SWIPE_DOWN), speak);
            Utils.TICKER_FULL_ARTICLE_ENTRY = true;
        }
    }

    public static void chooseBookMarksEntry(Context context, Speak speak) {
        if (Utils.BOOKMARKS_ENTRY) {
            Utils.handleSpeaking(context.getString(R.string.BOOKMARKS_ENTRY), speak);
        } else {
            Utils.handleSpeaking(context.getString(R.string.BOOKMARKS_FIRST_USE_SWIPE_DOWN), speak);
            Utils.BOOKMARKS_ENTRY = true;
        }
    }

    public static void chooseBookMarksFullArticleEntry(Context context, Speak speak) {
        if (Utils.BOOKMARKS_FULLARTIKEL_ENTRY) {
            Utils.handleSpeaking(context.getString(R.string.BOOKMARKS_FULLARTIKEL_ENTRY), speak);;
        } else {
            Utils.handleSpeaking(context.getString(R.string.BOOKMARKS__FULL_ARTICLE_FIRST_USE_SWIPE_DOWN), speak);
            Utils.BOOKMARKS_FULLARTIKEL_ENTRY = true;
        }
    }

    public static void chooseNewsEntry(Context context, Speak speak) {
        if (Utils.NEWS_ENTRY) {
            Utils.handleSpeaking(context.getString(R.string.NEWS_ENTRY), speak);
        } else {
            Utils.handleSpeaking(context.getString(R.string.NEWS_FIRST_USE_SWIPE_DOWN), speak);
            Utils.NEWS_ENTRY = true;
        }
    }

    public static void chooseNewsShortArticleEntry(Context context, Speak speak) {
        if (Utils.NEWS_SHORT_ENTRY) {
            Utils.handleSpeaking(context.getString(R.string.NEWS_SHORT_ENTRY), speak);
        } else {
            Utils.handleSpeaking(context.getString(R.string.NEWS_SHORT_FIRST_USE_SWIPE_DOWN), speak);
            Utils.NEWS_SHORT_ENTRY = true;
        }
    }

    public static void chooseNewsFullArticleEntry(Context context, Speak speak) {
        if (Utils.NEWS_FULL_ARTICLE_ENTRY) {
            Utils.handleSpeaking(context.getString(R.string.NEWS_FULL_ARTICLE_ENTRY), speak);
        } else {
            Utils.handleSpeaking(context.getString(R.string.NEWS_FULL_ARTICLE_FIRST_USE_SWIPE_DOWN), speak);
            Utils.NEWS_FULL_ARTICLE_ENTRY = true;
        }
    }

    public static void handleSpeaking(String speakText, Speak speak) {
        if (!speakText.isEmpty()) {
            if (speak.isSpeaking()) {
                speak.stopReading();
            } else {
                speak.speak(speakText);
            }
        }
    }

}
