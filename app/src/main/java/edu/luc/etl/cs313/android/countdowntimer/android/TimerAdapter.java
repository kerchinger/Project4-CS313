package edu.luc.etl.cs313.android.countdowntimer.android;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.media.MediaPlayer; // added from here to
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.media.MediaPlayer.OnCompletionListener;
import android.content.Context; // here for all of the MediaPlayer imports
import java.lang.Object;

import edu.luc.etl.cs313.android.countdowntimer.R;
import edu.luc.etl.cs313.android.countdowntimer.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.countdowntimer.model.TimerModelFacade;
import edu.luc.etl.cs313.android.countdowntimer.model.ConcreteTimerModelFacade;


/**
 * Created by kyleerchinger on 11/1/16.
 */

public class TimerAdapter extends Activity implements TimerUIUpdateListener {

    private static String TAG = "countdowntimer-android-activity";

    /**
     * The state-based dynamic model.
     */
    private TimerModelFacade model;

    protected void setModel(final TimerModelFacade model) {
        this.model = model;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inject dependency on view so this adapter receives UI events
        setContentView(R.layout.activity_main);
        // inject dependency on model into this so model receives UI events
        this.setModel(new ConcreteTimerModelFacade());
        // inject dependency on this into model to register for UI updates
        model.setUIUpdateListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        model.onStart();
    }

    // TODO remaining lifecycle methods
    /**
     * Updates the seconds and minutes in the UI.
     *
     * @param time
     */
    public void updateTime(final int time) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final TextView tvS = (TextView) findViewById(R.id.seconds);
                tvS.setText(Integer.toString(time/10) + Integer.toString(time %10));
            }
        });
    }


    /**
     * Updates the state name in the UI.
     * @param stateId
     */
    public void updateState(final int stateId) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(() -> {
            final TextView stateName = (TextView) findViewById(R.id.stateName); // so you can;t see this in the app, as of right now, but its there
            stateName.setText(getString(stateId));
        });
    }

    public void updateButton( final int buttonID){
        runOnUiThread(() -> {
            final TextView stateName = (TextView) findViewById(R.id.counterButton); // so you can;t see this in the app, as of right now, but its there
            stateName.setText(getString(buttonID));
        });
    }

    MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    public void ringAlarm(boolean b) {
        if (mediaPlayer.isPlaying()) { // basically like b being false, we don't need it because every time we enter the method the mediaplayer will stop. and then start if b is true
            mediaPlayer.stop();
        } else if (b == true){
            mediaPlayer.reset();
            final Context context = getApplicationContext();


            //Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

            try {
                mediaPlayer.setDataSource(context, defaultRingtoneUri);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
                //mediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
                mediaPlayer.prepare();
                mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
                mediaPlayer.start();

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    // forward event listener methods to the model
    public void onButtonPress(final View view) {
        model.onButtonPress();
    }
}
