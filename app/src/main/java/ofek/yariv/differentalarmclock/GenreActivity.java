package ofek.yariv.differentalarmclock;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Random;

public class GenreActivity extends Activity {
    MediaPlayer electric1;
    MediaPlayer electric2;
    MediaPlayer electric3;
    MediaPlayer electric4;
    MediaPlayer electric5;
    MediaPlayer electric6;
    MediaPlayer alarmsound1;
    MediaPlayer alarmsound2;
    MediaPlayer alarmsound3;
    MediaPlayer alarmsound4;
    MediaPlayer alarmsound5;
    MediaPlayer alarmsound6;
    MediaPlayer hiphop1;
    MediaPlayer hiphop2;
    MediaPlayer hiphop3;
    MediaPlayer hiphop4;
    MediaPlayer hiphop5;
    MediaPlayer hiphop6;
    MediaPlayer oldies1;
    MediaPlayer oldies2;
    MediaPlayer oldies3;
    MediaPlayer oldies4;
    MediaPlayer oldies5;
    MediaPlayer oldies6;
    MediaPlayer pop1;
    MediaPlayer pop2;
    MediaPlayer pop3;
    MediaPlayer pop4;
    MediaPlayer pop5;
    MediaPlayer pop6;
    MediaPlayer pop7;
    MediaPlayer rock1;
    MediaPlayer rock2;
    MediaPlayer rock3;
    MediaPlayer rock4;
    MediaPlayer rock5;
    MediaPlayer rock6;
    MediaPlayer rock7;
    static MediaPlayer Selected;
    static int alarmsoundscheck =0;
    static int electriccheck =0;
    static int hiphopcheck =0;
    static int oldiescheck =0;
    static int popcheck =0;
    static int rockcheck =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        electric1 = MediaPlayer.create(GenreActivity.this, R.raw.electric1);
        electric2 = MediaPlayer.create(GenreActivity.this,R.raw.electric2);
        electric3 = MediaPlayer.create(GenreActivity.this,R.raw.electric3);
        electric4 = MediaPlayer.create(GenreActivity.this,R.raw.electric4);
        electric5 = MediaPlayer.create(GenreActivity.this,R.raw.electric5);
        electric6 = MediaPlayer.create(GenreActivity.this,R.raw.electric6);
        alarmsound1 = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound1);
        alarmsound2 = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound2);
        alarmsound3 = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound3);
        alarmsound4 = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound4);
        alarmsound5 = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound5);
        alarmsound6 = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound6);
        hiphop1 = MediaPlayer.create(GenreActivity.this,R.raw.hiphop1);
        hiphop2 = MediaPlayer.create(GenreActivity.this,R.raw.hiphop2);
        hiphop3 = MediaPlayer.create(GenreActivity.this,R.raw.hiphop3);
        hiphop4 = MediaPlayer.create(GenreActivity.this,R.raw.hiphop4);
        hiphop5 = MediaPlayer.create(GenreActivity.this,R.raw.hiphop5);
        hiphop6 = MediaPlayer.create(GenreActivity.this,R.raw.hiphop6);
        oldies1 = MediaPlayer.create(GenreActivity.this,R.raw.oldies1);
        oldies2 = MediaPlayer.create(GenreActivity.this,R.raw.oldies2);
        oldies3 = MediaPlayer.create(GenreActivity.this,R.raw.oldies3);
        oldies4 = MediaPlayer.create(GenreActivity.this,R.raw.oldies4);
        oldies5 = MediaPlayer.create(GenreActivity.this,R.raw.oldies5);
        oldies6 = MediaPlayer.create(GenreActivity.this,R.raw.oldies6);
        pop1 = MediaPlayer.create(GenreActivity.this,R.raw.pop1);
        pop2 = MediaPlayer.create(GenreActivity.this,R.raw.pop2);
        pop3 = MediaPlayer.create(GenreActivity.this,R.raw.pop3);
        pop4 = MediaPlayer.create(GenreActivity.this,R.raw.pop4);
        pop5 = MediaPlayer.create(GenreActivity.this,R.raw.pop5);
        pop6 = MediaPlayer.create(GenreActivity.this,R.raw.pop6);
        pop7 = MediaPlayer.create(GenreActivity.this,R.raw.pop7);
        rock1 = MediaPlayer.create(GenreActivity.this,R.raw.rock1);
        rock2 = MediaPlayer.create(GenreActivity.this,R.raw.rock2);
        rock3 = MediaPlayer.create(GenreActivity.this,R.raw.rock3);
        rock4 = MediaPlayer.create(GenreActivity.this,R.raw.rock4);
        rock5 = MediaPlayer.create(GenreActivity.this,R.raw.rock5);
        rock6 = MediaPlayer.create(GenreActivity.this,R.raw.rock6);
        rock7 = MediaPlayer.create(GenreActivity.this,R.raw.rock7);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        Button TryAlarmSounds=(Button)findViewById(R.id.TryAlarmSounds);
        Button TryRock=(Button)findViewById(R.id.TryRock);
        Button TryPop=(Button)findViewById(R.id.TryPop);
        Button TryOldies=(Button)findViewById(R.id.TryOldies);
        Button TryHipHop=(Button)findViewById(R.id.TryHipHop);
        Button TryElectric=(Button)findViewById(R.id.TryElectric);
        final CheckBox checkBoxAlarmsounds=(CheckBox)findViewById(R.id.checkBoxAlarmSounds);
        final CheckBox checkBoxRock=(CheckBox)findViewById(R.id.checkBoxRock);
        final CheckBox checkBoxPop=(CheckBox)findViewById(R.id.checkBoxPop);
        final CheckBox checkBoxOldies=(CheckBox)findViewById(R.id.checkBoxOldies);
        final CheckBox checkBoxHipHop=(CheckBox)findViewById(R.id.checkBoxHipHop);
        final CheckBox checkBoxElectric=(CheckBox)findViewById(R.id.checkBoxElectric);
        Button SetRandomAlarmSound=(Button)findViewById(R.id.SetRandomAlarmSound);
        if (alarmsoundscheck==1)
        {
            checkBoxAlarmsounds.setChecked(true);
        }
        if (electriccheck==1)
        {
            checkBoxElectric.setChecked(true);
        }
        if (hiphopcheck==1)
        {
            checkBoxHipHop.setChecked(true);
        }
        if (oldiescheck==1)
        {
            checkBoxOldies.setChecked(true);
        }
        if (popcheck==1)
        {
            checkBoxPop.setChecked(true);
        }
        if (rockcheck==1)
        {
            checkBoxRock.setChecked(true);
        }
        SetRandomAlarmSound.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                int ralarmsounds = 0;
                int rrock = 0;
                int rpop = 0;
                int rhiphop = 0;
                int relectric = 0;
                int roldies = 0;
                int highestnum;
                if(!checkBoxAlarmsounds.isChecked()&&!checkBoxElectric.isChecked()&&!checkBoxHipHop.isChecked()&&!checkBoxOldies.isChecked()&&!checkBoxPop.isChecked()&&!checkBoxRock.isChecked())
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Please check at least one genre";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else
                {

                if (checkBoxAlarmsounds.isChecked()) {
                    alarmsoundscheck=1;
                    Random randalarmsounds = new Random();
                    ralarmsounds = randalarmsounds.nextInt(5) + 1;
                }
                if (checkBoxRock.isChecked()) {
                    rockcheck=1;
                    Random randrock = new Random();
                    rrock = randrock.nextInt(6) + 1;
                }
                if (checkBoxPop.isChecked()) {
                    popcheck=1;
                    Random randpop = new Random();
                    rpop = randpop.nextInt(6) + 1;
                }
                if (checkBoxHipHop.isChecked()) {
                    hiphopcheck=1;
                    Random randhiphop = new Random();
                    rhiphop = randhiphop.nextInt(5) + 1;
                }
                if (checkBoxElectric.isChecked()) {
                    electriccheck=1;
                    Random randelectric = new Random();
                    relectric = randelectric.nextInt(5) + 1;
                }
                if (checkBoxOldies.isChecked()) {
                    oldiescheck=1;
                    Random randoldies = new Random();
                    roldies = randoldies.nextInt(5) + 1;
                }
                    if(!checkBoxAlarmsounds.isChecked())
                    {
                        alarmsoundscheck=0;
                    }
                    if(!checkBoxElectric.isChecked())
                    {
                        electriccheck=0;
                    }
                    if(!checkBoxHipHop.isChecked())
                    {
                        hiphopcheck=0;
                    }
                    if(!checkBoxOldies.isChecked())
                    {
                        oldiescheck=0;
                    }
                    if(!checkBoxPop.isChecked())
                    {
                        popcheck=0;
                    }
                    if(!checkBoxRock.isChecked())
                    {
                        rockcheck=0;
                    }

                highestnum = Math.max(ralarmsounds, relectric);
                if (highestnum < Math.max(rhiphop, roldies)) {
                    highestnum = Math.max(rhiphop, roldies);
                }
                if (highestnum < Math.max(rpop, rrock)) {
                    highestnum = Math.max(rpop, rrock);
                }
                if (ralarmsounds < highestnum)
                    ralarmsounds = 0;
                if (relectric < highestnum)
                    relectric = 0;
                if (rhiphop < highestnum)
                    rhiphop = 0;
                if (roldies < highestnum)
                    roldies = 0;
                if (rpop < highestnum)
                    rpop = 0;
                if (rrock < highestnum)
                    rrock = 0;
                while (!(((ralarmsounds == highestnum) && (ralarmsounds != relectric) && (ralarmsounds != rhiphop) && (ralarmsounds != roldies) && (ralarmsounds != rpop) && (ralarmsounds != rrock))
                        || ((relectric == highestnum) && (relectric != ralarmsounds) && (relectric != rhiphop) && (relectric != roldies) && (relectric != rpop) && (relectric != rrock))
                        || ((rhiphop == highestnum) && (rhiphop != ralarmsounds) && (rhiphop != relectric) && (rhiphop != roldies) && (rhiphop != rpop) && (rhiphop != rrock))
                        || ((roldies == highestnum) && (roldies != ralarmsounds) && (roldies != relectric) && (roldies != rhiphop) && (roldies != rpop) && (roldies != rrock))
                        || ((rpop == highestnum) && (rpop != ralarmsounds) && (rpop != relectric) && (rpop != rhiphop) && (rpop != roldies) && (rpop != rrock))
                        || ((rrock == highestnum) && (rrock != ralarmsounds) && (rrock != relectric) && (rrock != rhiphop) && (rrock != roldies) && (rrock != rpop)))) {
                    if (ralarmsounds != 0) {
                        Random randalarmsounds = new Random();
                        ralarmsounds = randalarmsounds.nextInt(5) + 1;
                    }
                    if (relectric != 0) {
                        Random randelectric = new Random();
                        relectric = randelectric.nextInt(5) + 1;
                    }
                    if (rhiphop != 0) {
                        Random randhiphop = new Random();
                        rhiphop = randhiphop.nextInt(5) + 1;
                    }
                    if (roldies != 0) {
                        Random randoldies = new Random();
                        roldies = randoldies.nextInt(5) + 1;
                    }
                    if (rpop != 0) {
                        Random randpop = new Random();
                        rpop = randpop.nextInt(6) + 1;
                    }
                    if (rrock != 0) {
                        Random randrock = new Random();
                        rrock = randrock.nextInt(6) + 1;
                    }

                }

                if (ralarmsounds > relectric && ralarmsounds > rhiphop && ralarmsounds > roldies && ralarmsounds > rpop && ralarmsounds > rrock) {
                    if (ralarmsounds == 1) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound1);
                    }
                    if (ralarmsounds == 2) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound2);
                    }
                    if (ralarmsounds == 3) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound3);
                    }
                    if (ralarmsounds == 4) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound4);
                    }
                    if (ralarmsounds == 5) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound5);
                    }
                    if (ralarmsounds == 6) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound6);
                    }
                }
                if (relectric > ralarmsounds && relectric > rhiphop && relectric > roldies && relectric > rpop && relectric > rrock) {
                    if (relectric == 1) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.electric1);
                    }
                    if (relectric == 2) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.electric2);
                    }
                    if (relectric == 3) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.electric3);
                    }
                    if (relectric == 4) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.electric4);
                    }
                    if (relectric == 5) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.electric5);
                    }
                    if (relectric == 6) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.electric6);
                    }
                }
                if (rhiphop > ralarmsounds && rhiphop > relectric && rhiphop > roldies && rhiphop > rpop && rhiphop > rrock) {
                    if (rhiphop == 1) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.hiphop1);
                    }if (rhiphop == 2) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.hiphop2);
                    }if (rhiphop == 3) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.hiphop3);
                    }if (rhiphop == 4) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.hiphop4);
                    }if (rhiphop == 5) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.hiphop5);
                    }if (rhiphop == 6) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.hiphop6);
                    }
                }
                if (roldies > ralarmsounds && roldies > relectric && roldies > rhiphop && roldies > rpop && roldies > rrock) {
                    if (roldies == 1) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.oldies1);
                    }if (roldies == 2) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.oldies2);
                    }if (roldies == 3) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.oldies3);
                    }if (roldies == 4) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.oldies4);
                    }if (roldies == 5) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.oldies5);
                    }if (roldies == 6) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.oldies6);
                    }
                }
                if (rpop > ralarmsounds && rpop > relectric && rpop > roldies && rpop > rhiphop && rpop > rrock) {
                    if (rpop == 1) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.pop1);
                    }if (rpop == 2) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.pop2);
                    }if (rpop == 3) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.pop3);
                    }if (rpop == 4) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.pop4);
                    }if (rpop == 5) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.pop5);
                    }if (rpop == 6) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.pop6);
                    }if (rpop == 7) {
                        Selected = MediaPlayer.create(GenreActivity.this,R.raw.pop7);
                    }
                }
                if (rrock > ralarmsounds && rrock > relectric && rrock > roldies && rrock > rpop && rrock > rhiphop) {
                    if (rrock == 1) {
                        Selected = MediaPlayer.create(GenreActivity.this, R.raw.pop1);
                    }if (rrock == 2) {
                        Selected = MediaPlayer.create(GenreActivity.this, R.raw.pop2);
                    }if (rrock == 3) {
                        Selected = MediaPlayer.create(GenreActivity.this, R.raw.pop3);
                    }if (rrock == 4) {
                        Selected = MediaPlayer.create(GenreActivity.this, R.raw.pop4);
                    }if (rrock == 5) {
                        Selected = MediaPlayer.create(GenreActivity.this, R.raw.pop5);
                    }if (rrock == 6) {
                        Selected = MediaPlayer.create(GenreActivity.this, R.raw.pop6);
                    }if (rrock == 7) {
                        Selected = MediaPlayer.create(GenreActivity.this, R.raw.pop7);
                    }
                }
                    stopPlaying();
                    Context context = getApplicationContext();
                    CharSequence text = "Your alarm sound is ready!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    setResult(RESULT_OK);
                    finish();
            }
            }
        });
            TryElectric.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onClick(View v) {
                    if (electric1.isPlaying())
                    {
                        stopPlaying();
                        electric2.start();
                    }
                    else if (electric2.isPlaying())
                    {
                        stopPlaying();
                        electric3.start();
                    }
                    else if (electric3.isPlaying())
                    {
                        stopPlaying();
                        electric4.start();
                    }
                    else if(electric4.isPlaying())
                    {
                        stopPlaying();
                        electric5.start();
                    }
                    else if(electric5.isPlaying())
                    {
                        stopPlaying();
                        electric6.start();
                    }
                    else if(electric6.isPlaying())
                    {
                        stopPlaying();
                        electric1.start();
                    }
                    else {
                        stopPlaying();
                        electric1.start();
                    }

            }

                }
            );
        TryHipHop.setOnClickListener(new View.OnClickListener() {
                                           @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                           @Override
                                           public void onClick(View v) {
                   if (hiphop1.isPlaying())
                   {
                       stopPlaying();
                       hiphop2.start();
                   }
                   else if (hiphop2.isPlaying())
                   {
                       stopPlaying();
                       hiphop3.start();
                   }
                   else if (hiphop3.isPlaying())
                   {
                       stopPlaying();
                       hiphop4.start();
                   }
                   else if(hiphop4.isPlaying())
                   {
                       stopPlaying();
                       hiphop5.start();
                   }
                   else if(hiphop5.isPlaying())
                   {
                       stopPlaying();
                       hiphop6.start();
                   }
                   else if(hiphop6.isPlaying())
                   {
                       stopPlaying();
                       hiphop1.start();
                   }
                   else {
                       stopPlaying();
                       hiphop1.start();
                   }

               }
            }
        );
        TryAlarmSounds.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (alarmsound1.isPlaying())
                {
                    stopPlaying();
                    alarmsound2.start();
                }
                else if (alarmsound2.isPlaying())
                {
                    stopPlaying();
                    alarmsound3.start();
                }
                else if (alarmsound3.isPlaying())
                {
                    stopPlaying();
                    alarmsound4.start();
                }
                else if(alarmsound4.isPlaying())
                {
                    stopPlaying();
                    alarmsound5.start();
                }
                else if(alarmsound5.isPlaying())
                {
                    stopPlaying();
                    alarmsound6.start();
                }
                else if(alarmsound6.isPlaying())
                {
                    stopPlaying();
                    alarmsound1.start();
                }
                else {
                    stopPlaying();
                    alarmsound1.start();
                }
            }
        });

        TryOldies.setOnClickListener(new View.OnClickListener() {
                                           @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                           @Override
                                           public void onClick(View v) {
           if (oldies1.isPlaying())
           {
               stopPlaying();
               oldies2.start();
           }
                   else if (oldies2.isPlaying())
                   {
                       stopPlaying();
                       oldies3.start();
                   }
                   else if (oldies3.isPlaying())
                   {
                       stopPlaying();
                       oldies4.start();
                   }
                   else if(oldies4.isPlaying())
                   {
                       stopPlaying();
                       oldies5.start();
                   }
                   else if(oldies5.isPlaying())
                   {
                       stopPlaying();
                       oldies6.start();
                   }
                   else if(oldies6.isPlaying())
                   {
                       stopPlaying();
                       oldies1.start();
                   }
                   else {
                       stopPlaying();
                       oldies1.start();
                   }

               }
           }
        );
        TryPop.setOnClickListener(new View.OnClickListener() {
                                           @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                           @Override
                                           public void onClick(View v) {
                   if (pop1.isPlaying())
                   {
                       stopPlaying();
                       pop2.start();
                   }
                   else if (pop2.isPlaying())
                   {
                       stopPlaying();
                       pop3.start();
                   }
                   else if (pop3.isPlaying())
                   {
                       stopPlaying();
                       pop4.start();
                   }
                   else if(pop4.isPlaying())
                   {
                       stopPlaying();
                       pop5.start();
                   }
                   else if(pop5.isPlaying())
                   {
                       stopPlaying();
                       pop6.start();
                   }
                   else if(pop6.isPlaying())
                   {
                       stopPlaying();
                       pop7.start();
                   }
                   else if(pop7.isPlaying())
                   {
                       stopPlaying();
                       pop1.start();
                   }
                   else {
                       stopPlaying();
                       pop1.start();
                   }

               }
           }
        );
        TryRock.setOnClickListener(new View.OnClickListener() {
                                           @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                           @Override
                                           public void onClick(View v) {
               if (rock1.isPlaying())
               {
                   stopPlaying();
                   rock2.start();
               }
               else if (rock2.isPlaying())
               {
                   stopPlaying();
                   rock3.start();
               }
               else if (rock3.isPlaying())
               {
                   stopPlaying();
                   rock4.start();
               }
               else if(rock4.isPlaying())
               {
                   stopPlaying();
                   rock5.start();
               }
               else if(rock5.isPlaying())
               {
                   stopPlaying();
                   rock6.start();
               }
               else if(rock6.isPlaying())
               {
                   stopPlaying();
                   rock7.start();
               }
               else if(rock7.isPlaying())
               {
                   stopPlaying();
                   rock1.start();
               }
               else {
                   stopPlaying();
                   rock1.start();
               }

           }
       }
        );


    }

    public void stopPlaying()
    {if(electric1.isPlaying())
    {
        electric1.stop();
        electric1.reset();
        electric1 = MediaPlayer.create(GenreActivity.this,R.raw.electric1);
    }if(electric2.isPlaying())
    {
        electric2.stop();
        electric2.reset();
        electric2 = MediaPlayer.create(GenreActivity.this,R.raw.electric2);
    }if(electric3.isPlaying())
    {
        electric3.stop();
        electric3.reset();
        electric3 = MediaPlayer.create(GenreActivity.this,R.raw.electric3);
    }if(electric4.isPlaying())
    {
        electric4.stop();
        electric4.reset();
        electric4 = MediaPlayer.create(GenreActivity.this,R.raw.electric4);
    }if(electric5.isPlaying())
    {
        electric5.stop();
        electric5.reset();
        electric5 = MediaPlayer.create(GenreActivity.this,R.raw.electric5);
    }if(electric6.isPlaying())
    {
        electric6.stop();
        electric6.reset();
        electric6 = MediaPlayer.create(GenreActivity.this,R.raw.electric6);
    }if(hiphop1.isPlaying())
    {
        hiphop1.stop();
        hiphop1.reset();
        hiphop1 = MediaPlayer.create(GenreActivity.this,R.raw.hiphop1);
    }if(hiphop2.isPlaying())
    {
        hiphop2.stop();
        hiphop2.reset();
        hiphop2 = MediaPlayer.create(GenreActivity.this,R.raw.hiphop2);
    }if(hiphop3.isPlaying())
    {
        hiphop3.stop();
        hiphop3.reset();
        hiphop3 = MediaPlayer.create(GenreActivity.this,R.raw.hiphop3);
    }if(hiphop4.isPlaying())
    {
        hiphop4.stop();
        hiphop4.reset();
        hiphop4 = MediaPlayer.create(GenreActivity.this,R.raw.hiphop4);
    }if(hiphop5.isPlaying())
    {
        hiphop5.stop();
        hiphop5.reset();
        hiphop5 = MediaPlayer.create(GenreActivity.this,R.raw.hiphop5);
    }if(hiphop6.isPlaying())
    {
        hiphop6.stop();
        hiphop6.reset();
        hiphop6 = MediaPlayer.create(GenreActivity.this,R.raw.hiphop6);
    }if(oldies1.isPlaying())
    {
        oldies1.stop();
        oldies1.reset();
        oldies1 = MediaPlayer.create(GenreActivity.this,R.raw.oldies1);
    }if(oldies2.isPlaying())
    {
        oldies2.stop();
        oldies2.reset();
        oldies2 = MediaPlayer.create(GenreActivity.this,R.raw.oldies2);
    }if(oldies3.isPlaying())
    {
        oldies3.stop();
        oldies3.reset();
        oldies3 = MediaPlayer.create(GenreActivity.this,R.raw.oldies3);
    }if(oldies4.isPlaying())
    {
        oldies4.stop();
        oldies4.reset();
        oldies4 = MediaPlayer.create(GenreActivity.this,R.raw.oldies4);
    }if(oldies5.isPlaying())
    {
        oldies5.stop();
        oldies5.reset();
        oldies5 = MediaPlayer.create(GenreActivity.this,R.raw.oldies5);
    }if(oldies6.isPlaying())
    {
        oldies6.stop();
        oldies6.reset();
        oldies6 = MediaPlayer.create(GenreActivity.this,R.raw.oldies6);
    }if(rock1.isPlaying())
    {
        rock1.stop();
        rock1.reset();
        rock1 = MediaPlayer.create(GenreActivity.this,R.raw.rock1);
    }if(rock2.isPlaying())
    {
        rock2.stop();
        rock2.reset();
        rock2 = MediaPlayer.create(GenreActivity.this,R.raw.rock2);
    }if(rock3.isPlaying())
    {
        rock3.stop();
        rock3.reset();
        rock3 = MediaPlayer.create(GenreActivity.this,R.raw.rock3);
    }if(rock4.isPlaying())
    {
        rock4.stop();
        rock4.reset();
        rock4 = MediaPlayer.create(GenreActivity.this,R.raw.rock4);
    }if(rock5.isPlaying())
    {
        rock5.stop();
        rock5.reset();
        rock5 = MediaPlayer.create(GenreActivity.this,R.raw.rock5);
    }if(rock6.isPlaying())
    {
        rock6.stop();
        rock6.reset();
        rock6 = MediaPlayer.create(GenreActivity.this,R.raw.rock6);
    }if(rock7.isPlaying())
    {
        rock7.stop();
        rock7.reset();
        rock7 = MediaPlayer.create(GenreActivity.this,R.raw.rock7);
    }if(pop1.isPlaying())
    {
        pop1.stop();
        pop1.reset();
        pop1 = MediaPlayer.create(GenreActivity.this,R.raw.pop1);
    }if(pop2.isPlaying())
    {
        pop2.stop();
        pop2.reset();
        pop2 = MediaPlayer.create(GenreActivity.this,R.raw.pop2);
    }if(pop3.isPlaying())
    {
        pop3.stop();
        pop3.reset();
        pop3 = MediaPlayer.create(GenreActivity.this,R.raw.pop3);
    }if(pop4.isPlaying())
    {
        pop4.stop();
        pop4.reset();
        pop4 = MediaPlayer.create(GenreActivity.this,R.raw.pop4);
    }if(pop5.isPlaying())
    {
        pop5.stop();
        pop5.reset();
        pop5 = MediaPlayer.create(GenreActivity.this,R.raw.pop5);
    }if(pop6.isPlaying())
    {
        pop6.stop();
        pop6.reset();
        pop6 = MediaPlayer.create(GenreActivity.this,R.raw.pop6);
    }if(pop7.isPlaying())
    {
        pop7.stop();
        pop7.reset();
        pop7 = MediaPlayer.create(GenreActivity.this,R.raw.pop7);
    }
    if(alarmsound1.isPlaying())
    {
        alarmsound1.stop();
        alarmsound1.reset();
        alarmsound1 = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound1);
    }
        if(alarmsound2.isPlaying())
        {
            alarmsound2.stop();
            alarmsound2.reset();
            alarmsound2 = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound2);
        }
        if(alarmsound3.isPlaying())
        {
            alarmsound3.stop();
            alarmsound3.reset();
            alarmsound3 = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound3);
        }
        if(alarmsound4.isPlaying())
        {
            alarmsound4.stop();
            alarmsound4.reset();
            alarmsound4 = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound4);
        }
        if(alarmsound5.isPlaying())
        {
            alarmsound5.stop();
            alarmsound5.reset();
            alarmsound5 = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound5);
        }
        if(alarmsound6.isPlaying())
        {
            alarmsound6.stop();
            alarmsound6.reset();
            alarmsound6 = MediaPlayer.create(GenreActivity.this,R.raw.alarmsound6);
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_genre, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onPause() {
        super.onPause();
        stopPlaying();
    }
    }
