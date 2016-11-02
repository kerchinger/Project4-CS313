package edu.luc.etl.cs313.android.simplestopwatch.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.media.MediaPlayer; // added from here to
import  android.media.AudioManager;
import  android.media.RingtoneManager;
import android.net.Uri;
import java.io.IOException;
import android.media.MediaPlayer.OnCompletionListener;
import android.content.Context; // here for all of the MediaPlayer imports


import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.common.TimerUIUpdateListener;
import edu.luc.etl.cs313.android.simplestopwatch.common.TimerUIListener;
import edu.luc.etl.cs313.android.simplestopwatch.common.TimerUIUpdateSource;
import edu.luc.etl.cs313.android.simplestopwatch.common.Constants;
import edu.luc.etl.cs313.android.simplestopwatch.model.state.TimerModelFacade;
import edu.luc.etl.cs313.android.simplestopwatch.model.state.ConcreteTimerModelFacade;


/**
 * Created by kyleerchinger on 11/1/16.
 */

public class TimerAdapter extends Activity implements TimerUIUpdateListener {

    private static String TAG = "simpletimer-android-act1vity";

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

    public void ringAlarm(boolean value){
            Uri defaultRingtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            MediaPlayer mediaPlayer = new MediaPlayer();
            Context context = this; // added this to get context to not be red
            try {
                mediaPlayer.setDataSource(context, defaultRingtoneUri);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
                mediaPlayer.prepare();
                mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });

                if (value == true) { // controls whether or not the media player is on or off
                    mediaPlayer.start();}
                else{
                    mediaPlayer.stop();
                }
                }catch(IllegalArgumentException e){
                    e.printStackTrace();
                }catch(SecurityException e){
                    e.printStackTrace();
                }catch(IllegalStateException e){
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }
    }



    /**
     * Updates the state name in the UI.
     * @param stateId
     */
    public void updateState(final int stateId) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(() -> {
            final TextView stateName = (TextView) findViewById(R.id.stateName);
            stateName.setText(getString(stateId));
        });
    }

    // forward event listener methods to the model
    public void onStartStop(final View view) {
        model.onStart();
    }


}
