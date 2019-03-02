package at.nachrichten.newsapp.listener;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import at.nachrichten.newsapp.Home;
import at.nachrichten.newsapp.R;
import at.nachrichten.newsapp.messages.Messages;
import at.nachrichten.newsapp.utils.Utils;

/**
 * Created by Harald on 11.01.2018.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import at.nachrichten.newsapp.Home;
import at.nachrichten.newsapp.R;
import at.nachrichten.newsapp.utils.Utils;

public class DragListenerLogin extends DragListener {

    public DragListenerLogin(Context context) {
        super(context);
    }

        @Override
        public boolean onDrag(View v, DragEvent event) {
            //View v = current View
            int action = event.getAction();
            View rootView = Utils.getRootView(getActivity());
            View navigatonComponent = (View) event.getLocalState();

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundColor(Color.GRAY);

                    if (rootView.getResources().getResourceEntryName(v.getId()).equals(Messages.LOGIN_EMAIL_VIEW)){
                        getSpeak().speak(getContext().getString(R.string.prompt_email));
                    }else if(rootView.getResources().getResourceEntryName(v.getId()).equals(Messages.LOGIN_PASSWORD_VIEW)){
                        getSpeak().speak(getContext().getString(R.string.prompt_password));
                    }else if(rootView.getResources().getResourceEntryName(v.getId()).equals(Messages.LOGIN_SIGN_IN_BUTTON_VIEW)){
                        getSpeak().speak(getContext().getString(R.string.action_sign_in));
                    }

                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.home_screen_border));
                    break;
                case DragEvent.ACTION_DROP:

                    if (rootView.getResources().getResourceEntryName(v.getId()).equals("email")){
                        getSpeak().speak(getContext().getString(R.string.prompt_email));
                        v.requestFocus();
                    }else if(rootView.getResources().getResourceEntryName(v.getId()).equals("password")){
                        getSpeak().speak(getContext().getString(R.string.prompt_password));
                        v.requestFocus();
                    }else if(rootView.getResources().getResourceEntryName(v.getId()).equals("email_sign_in_button")){
                        getSpeak().speak(getContext().getString(R.string.action_sign_in));
                        v.requestFocus();
                    }

                        navigatonComponent.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:

                    navigatonComponent.setVisibility(View.VISIBLE);
                default:
                    break;
            }
            return true;
        }
}

