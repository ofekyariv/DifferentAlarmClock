package ofek.yariv.differentalarmclock;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class AlarmScreen extends Activity {

    public final String TAG = this.getClass().getSimpleName();

    private WakeLock mWakeLock;


    private static final int WAKELOCK_TIMEOUT = 60 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setup layout
        this.setContentView(R.layout.activity_alarm_screen);

        String name = getIntent().getStringExtra(AlarmManagerHelper.NAME);
        if(name.equals(""))
        {
            TextView wakeup = (TextView) findViewById(R.id.alarm_screen_title);
            wakeup.setAlpha(1.0f);
        }
        else
        {
            TextView wakeup = (TextView) findViewById(R.id.alarm_screen_title);
            wakeup.setAlpha(0.0f);
        }
        int timeHour = getIntent().getIntExtra(AlarmManagerHelper.TIME_HOUR, 0);
        int timeMinute = getIntent().getIntExtra(AlarmManagerHelper.TIME_MINUTE, 0);
        String tone = getIntent().getStringExtra(AlarmManagerHelper.TONE);

        TextView tvName = (TextView) findViewById(R.id.alarm_screen_name);
        tvName.setText(name);

        TextView tvTime = (TextView) findViewById(R.id.alarm_screen_time);
        tvTime.setText(String.format("%02d : %02d", timeHour, timeMinute));

        Button dismissButton = (Button) findViewById(R.id.alarm_screen_button);
        dismissButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                GenreActivity.Selected.stop();
                finish();
            }
        });


                    if (GenreActivity.Selected==null)
                    {
                        GenreActivity.Selected = MediaPlayer.create(AlarmScreen.this,R.raw.alarmsound1);
                    }



                    GenreActivity.Selected.setLooping(true);
                    GenreActivity.Selected.start();
                    if (!GenreActivity.Selected.isPlaying())
                    {
                        if (GenreActivity.alarmsoundscheck == 0)
                        {
                            GenreActivity.Selected = MediaPlayer.create(AlarmScreen.this,R.raw.alarmsound5);
                        }
                        if (GenreActivity.popcheck == 0)
                        {
                            GenreActivity.Selected = MediaPlayer.create(AlarmScreen.this,R.raw.pop5);
                        }
                        if (GenreActivity.rockcheck == 0)
                        {
                            GenreActivity.Selected = MediaPlayer.create(AlarmScreen.this,R.raw.rock5);
                        }
                        if (GenreActivity.hiphopcheck == 0)
                        {
                            GenreActivity.Selected = MediaPlayer.create(AlarmScreen.this,R.raw.hiphop5);
                        }
                        if (GenreActivity.oldiescheck == 0)
                        {
                            GenreActivity.Selected = MediaPlayer.create(AlarmScreen.this,R.raw.oldies5);
                        }
                        if (GenreActivity.electriccheck == 0)
                        {
                            GenreActivity.Selected = MediaPlayer.create(AlarmScreen.this,R.raw.electric5);
                        }
                        GenreActivity.Selected.setLooping(true);
                        GenreActivity.Selected.start();
                    }

        //Ensure wakelock release
        Runnable releaseWakelock = new Runnable() {

            @Override
            public void run() {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

                if (mWakeLock != null && mWakeLock.isHeld()) {
                    mWakeLock.release();
                }
            }
        };

        new Handler().postDelayed(releaseWakelock, WAKELOCK_TIMEOUT);

    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onResume() {
        super.onResume();

        // Set the window to keep screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        // Acquire wakelock
        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        if (mWakeLock == null) {
            mWakeLock = pm.newWakeLock((PowerManager.FULL_WAKE_LOCK | PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), TAG);
        }

        if (!mWakeLock.isHeld()) {
            mWakeLock.acquire();
            Log.i(TAG, "Wakelock aquired!!");
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mWakeLock != null && mWakeLock.isHeld()) {
            mWakeLock.release();
        }
    }
}
