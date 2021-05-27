package ofek.yariv.differentalarmclock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmDetailsActivity extends Activity {

    private AlarmDBHelper dbHelper = new AlarmDBHelper(this);

    private AlarmModel alarmDetails;

    private TimePicker timePicker;
    private EditText edtName;
    private CustomSwitch chkWeekly;
    private CustomSwitch chkSunday;
    private CustomSwitch chkMonday;
    private CustomSwitch chkTuesday;
    private CustomSwitch chkWednesday;
    private CustomSwitch chkThursday;
    private CustomSwitch chkFriday;
    private CustomSwitch chkSaturday;
    private TextView txtToneSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_details);

        getActionBar().setTitle("Create New Alarm");
        getActionBar().setDisplayHomeAsUpEnabled(true);

        timePicker = (TimePicker) findViewById(R.id.alarm_details_time_picker);
        edtName = (EditText) findViewById(R.id.alarm_details_name);
        chkWeekly = (CustomSwitch) findViewById(R.id.alarm_details_repeat_weekly);
        chkSunday = (CustomSwitch) findViewById(R.id.alarm_details_repeat_sunday);
        chkMonday = (CustomSwitch) findViewById(R.id.alarm_details_repeat_monday);
        chkTuesday = (CustomSwitch) findViewById(R.id.alarm_details_repeat_tuesday);
        chkWednesday = (CustomSwitch) findViewById(R.id.alarm_details_repeat_wednesday);
        chkThursday = (CustomSwitch) findViewById(R.id.alarm_details_repeat_thursday);
        chkFriday = (CustomSwitch) findViewById(R.id.alarm_details_repeat_friday);
        chkSaturday = (CustomSwitch) findViewById(R.id.alarm_details_repeat_saturday);
        txtToneSelection = (TextView) findViewById(R.id.alarm_label_tone_selection);


        long id = getIntent().getExtras().getLong("id");

        if (id == -1) {
            alarmDetails = new AlarmModel();
            ImageView check=(ImageView)findViewById(R.id.checktone);
            check.setVisibility(View.INVISIBLE);
        } else {
            alarmDetails = dbHelper.getAlarm(id);

            timePicker.setCurrentMinute(alarmDetails.timeMinute);
            timePicker.setCurrentHour(alarmDetails.timeHour);

            edtName.setText(alarmDetails.name);

            chkWeekly.setChecked(alarmDetails.repeatWeekly);
            chkSunday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.SUNDAY));
            chkMonday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.MONDAY));
            chkTuesday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.TUESDAY));
            chkWednesday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.WEDNESDAY));
            chkThursday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.THURSDAY));
            chkFriday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.FRDIAY));
            chkSaturday.setChecked(alarmDetails.getRepeatingDay(AlarmModel.SATURDAY));

            //txtToneSelection.setText(RingtoneManager.getRingtone(this, alarmDetails.alarmTone).getTitle(this));
        }

        final LinearLayout ringToneContainer = (LinearLayout) findViewById(R.id.alarm_ringtone_container);
        ringToneContainer.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startGenreActivity(-1);
            }
        });
    }
    public void startGenreActivity(long id) {
        Intent intent = new Intent(this, GenreActivity.class);
        //intent.putExtra("id", id);
        startActivityForResult(intent, 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            ImageView check=(ImageView)findViewById(R.id.checktone);
            check.setVisibility(View.VISIBLE);


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.alarm_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                break;
            }
            case R.id.action_save_alarm_details: {
                updateModelFromLayout();

                AlarmManagerHelper.cancelAlarms(this);

                if (alarmDetails.id < 0) {
                    dbHelper.createAlarm(alarmDetails);
                } else {
                    dbHelper.updateAlarm(alarmDetails);
                }
                AlarmManagerHelper.setAlarms(this);



                if (GenreActivity.alarmsoundscheck == 0 &&
                        GenreActivity.electriccheck == 0 &&
                        GenreActivity.oldiescheck == 0 &&
                        GenreActivity.hiphopcheck == 0 &&
                        GenreActivity.popcheck == 0 &&
                        GenreActivity.rockcheck == 0) {

                    Context context = getApplicationContext();
                    CharSequence text = "No genre selected - Default sound selected";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    Calendar rightNow2 = Calendar.getInstance();
                    int hour2 = rightNow2.get(Calendar.HOUR_OF_DAY);
                    Calendar minrightNow2 = Calendar.getInstance();
                    int min2 = minrightNow2.get(Calendar.MINUTE);
                    Context context7 = getApplicationContext();
                    CharSequence text7 = "Alarm set for " + (alarmDetails.timeHour - hour2) + " hours and " + (alarmDetails.timeMinute - min2) + " minutes from now";
                    int duration7 = Toast.LENGTH_SHORT;
                    Toast toast7 = Toast.makeText(context7, text7, duration7);
                    Context context4 = getApplicationContext();
                    CharSequence text4 = "Alarm set for " + (alarmDetails.timeMinute - min2) + " minutes from now";
                    int duration4 = Toast.LENGTH_SHORT;
                    Toast toast4 = Toast.makeText(context4, text4, duration4);
                    Context context2 = getApplicationContext();
                    CharSequence text2 = "Alarm set for "+(alarmDetails.timeHour - hour2)+" hours from now" ;
                    int duration2 = Toast.LENGTH_SHORT;
                    Toast toast2 = Toast.makeText(context2, text2, duration2);
                    Context context1 = getApplicationContext();
                    CharSequence text1 = "Alarm set for "+(alarmDetails.timeHour - hour2)+" hour from now" ;
                    int duration1 = Toast.LENGTH_SHORT;
                    Toast toast1 = Toast.makeText(context1, text1, duration1);
                    Context context3 = getApplicationContext();
                    CharSequence text3 = "Alarm set for "+(alarmDetails.timeMinute - min2)+" minute from now" ;
                    int duration3 = Toast.LENGTH_SHORT;
                    Toast toast3 = Toast.makeText(context3, text3, duration3);
                    Context context5 = getApplicationContext();
                    CharSequence text5 = "Alarm set for " + (alarmDetails.timeHour - hour2) + " hour and " + (alarmDetails.timeMinute - min2) + " minutes from now";
                    int duration5 = Toast.LENGTH_SHORT;
                    Toast toast5 = Toast.makeText(context5, text5, duration5);
                    Context context6 = getApplicationContext();
                    CharSequence text6 = "Alarm set for " + (alarmDetails.timeHour - hour2) + " hours and " + (alarmDetails.timeMinute - min2) + " minute from now";
                    int duration6 = Toast.LENGTH_SHORT;
                    Toast toast6 = Toast.makeText(context6, text6, duration6);
                    Context context8 = getApplicationContext();
                    CharSequence text8 = "Alarm set for " + (alarmDetails.timeHour - hour2) + " hour and " + (alarmDetails.timeMinute - min2) + " minute from now";
                    int duration8 = Toast.LENGTH_SHORT;
                    Toast toast8 = Toast.makeText(context8, text8, duration8);
                    Context context9 = getApplicationContext();
                    CharSequence text9 = "Alarm set for " + ((alarmDetails.timeHour - hour2)-1) + " hour and " + ((alarmDetails.timeMinute - min2)+60) + " minute from now";
                    int duration9 = Toast.LENGTH_SHORT;
                    Toast toast9 = Toast.makeText(context9, text9, duration9);
                    Context context10 = getApplicationContext();
                    CharSequence text10 = "Alarm set for " + ((alarmDetails.timeHour - hour2)-1) + " hours and " + ((alarmDetails.timeMinute - min2)+60) + " minute from now";
                    int duration10 = Toast.LENGTH_SHORT;
                    Toast toast10 = Toast.makeText(context10, text10, duration10);
                    Context context11 = getApplicationContext();
                    CharSequence text11 = "Alarm set for " + ((alarmDetails.timeHour - hour2)-1) + " hour and " + ((alarmDetails.timeMinute - min2)+60) + " minutes from now";
                    int duration11 = Toast.LENGTH_SHORT;
                    Toast toast11 = Toast.makeText(context11, text11, duration11);
                    Context context12 = getApplicationContext();
                    CharSequence text12 = "Alarm set for " + ((alarmDetails.timeHour - hour2)-1) + " hours and " + ((alarmDetails.timeMinute - min2)+60) + " minutes from now";
                    int duration12 = Toast.LENGTH_SHORT;
                    Toast toast12 = Toast.makeText(context12, text12, duration12);
                    Context context13 = getApplicationContext();
                    CharSequence text13 = "Alarm set for "  + ((alarmDetails.timeMinute - min2)+60) + " minute from now";
                    int duration13 = Toast.LENGTH_SHORT;
                    Toast toast13 = Toast.makeText(context13, text13, duration13);
                    Context context14 = getApplicationContext();
                    CharSequence text14 = "Alarm set for "  + ((alarmDetails.timeMinute - min2)+60) + " minutes from now";
                    int duration14 = Toast.LENGTH_SHORT;
                    Toast toast14 = Toast.makeText(context14, text14, duration14);
                    Context context15 = getApplicationContext();
                    CharSequence text15 = "Alarm set for 1 day from now";
                    int duration15 = Toast.LENGTH_SHORT;
                    Toast toast15 = Toast.makeText(context15, text15, duration15);
                    Context context16 = getApplicationContext();
                    CharSequence text16 = "Alarm set for " + (((alarmDetails.timeHour - hour2)-1)+24) + " hours and " + ((alarmDetails.timeMinute - min2)+60) + " minutes from now";
                    int duration16 = Toast.LENGTH_SHORT;
                    Toast toast16 = Toast.makeText(context16, text16, duration16);
                    Context context17 = getApplicationContext();
                    CharSequence text17 = "Alarm set for " + ((alarmDetails.timeHour - hour2)+24) + " hours and " + (alarmDetails.timeMinute - min2) + " minute from now";
                    int duration17 = Toast.LENGTH_SHORT;
                    Toast toast17 = Toast.makeText(context17, text17, duration17);
                    Context context18 = getApplicationContext();
                    CharSequence text18 = "Alarm set for "+((alarmDetails.timeHour - hour2)+24)+" hours from now" ;
                    int duration18 = Toast.LENGTH_SHORT;
                    Toast toast18 = Toast.makeText(context18, text18, duration18);


                    if (alarmDetails.timeMinute==min2&&alarmDetails.timeHour-hour2==1)
                    {
                        toast1.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeMinute==min2&&alarmDetails.timeHour-hour2>1)
                    {
                        toast2.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour==hour2&&alarmDetails.timeMinute-min2==1)
                    {
                        toast3.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour==hour2&&alarmDetails.timeMinute-min2>1)
                    {
                        toast4.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2==1&&alarmDetails.timeMinute-min2>1)
                    {
                        toast5.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2>1&&alarmDetails.timeMinute-min2==1)
                    {
                        toast6.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2>1&&alarmDetails.timeMinute-min2>1)
                    {
                        toast7.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2==1&&alarmDetails.timeMinute-min2==1)
                    {
                        toast8.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2==2&&alarmDetails.timeMinute-min2==-59)
                    {
                        toast9.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2>2&&alarmDetails.timeMinute-min2==-59)
                    {
                        toast10.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2==2&&alarmDetails.timeMinute<min2)
                    {
                        toast11.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2>2&&alarmDetails.timeMinute<min2)
                    {
                        toast12.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2==1&&alarmDetails.timeMinute-min2==-59)
                    {
                        toast13.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2==1&&alarmDetails.timeMinute<min2)
                    {
                        toast14.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour==hour2&&alarmDetails.timeMinute==min2)
                    {
                        toast15.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour<=hour2&&alarmDetails.timeMinute<min2)
                    {
                        toast16.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour<=hour2&&alarmDetails.timeMinute>min2)
                    {
                        toast17.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour<=hour2&&alarmDetails.timeMinute==min2)
                    {
                        toast18.show();
                        toast.show();
                    }



                    setResult(RESULT_OK);

                } else {

                    Context context = getApplicationContext();
                    CharSequence text = "Please make sure your media volume is high! ";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    Calendar rightNow2 = Calendar.getInstance();
                    int hour2 = rightNow2.get(Calendar.HOUR_OF_DAY);
                    Calendar minrightNow2 = Calendar.getInstance();
                    int min2 = minrightNow2.get(Calendar.MINUTE);
                    Context context7 = getApplicationContext();
                    CharSequence text7 = "Alarm set for " + (alarmDetails.timeHour - hour2) + " hours and " + (alarmDetails.timeMinute - min2) + " minutes from now";
                    int duration7 = Toast.LENGTH_SHORT;
                    Toast toast7 = Toast.makeText(context7, text7, duration7);
                    Context context4 = getApplicationContext();
                    CharSequence text4 = "Alarm set for " + (alarmDetails.timeMinute - min2) + " minutes from now";
                    int duration4 = Toast.LENGTH_SHORT;
                    Toast toast4 = Toast.makeText(context4, text4, duration4);
                    Context context2 = getApplicationContext();
                    CharSequence text2 = "Alarm set for "+(alarmDetails.timeHour - hour2)+" hours from now" ;
                    int duration2 = Toast.LENGTH_SHORT;
                    Toast toast2 = Toast.makeText(context2, text2, duration2);
                    Context context1 = getApplicationContext();
                    CharSequence text1 = "Alarm set for "+(alarmDetails.timeHour - hour2)+" hour from now" ;
                    int duration1 = Toast.LENGTH_SHORT;
                    Toast toast1 = Toast.makeText(context1, text1, duration1);
                    Context context3 = getApplicationContext();
                    CharSequence text3 = "Alarm set for "+(alarmDetails.timeMinute - min2)+" minute from now" ;
                    int duration3 = Toast.LENGTH_SHORT;
                    Toast toast3 = Toast.makeText(context3, text3, duration3);
                    Context context5 = getApplicationContext();
                    CharSequence text5 = "Alarm set for " + (alarmDetails.timeHour - hour2) + " hour and " + (alarmDetails.timeMinute - min2) + " minutes from now";
                    int duration5 = Toast.LENGTH_SHORT;
                    Toast toast5 = Toast.makeText(context5, text5, duration5);
                    Context context6 = getApplicationContext();
                    CharSequence text6 = "Alarm set for " + (alarmDetails.timeHour - hour2) + " hours and " + (alarmDetails.timeMinute - min2) + " minute from now";
                    int duration6 = Toast.LENGTH_SHORT;
                    Toast toast6 = Toast.makeText(context6, text6, duration6);
                    Context context8 = getApplicationContext();
                    CharSequence text8 = "Alarm set for " + (alarmDetails.timeHour - hour2) + " hour and " + (alarmDetails.timeMinute - min2) + " minute from now";
                    int duration8 = Toast.LENGTH_SHORT;
                    Toast toast8 = Toast.makeText(context8, text8, duration8);
                    Context context9 = getApplicationContext();
                    CharSequence text9 = "Alarm set for " + ((alarmDetails.timeHour - hour2)-1) + " hour and " + ((alarmDetails.timeMinute - min2)+60) + " minute from now";
                    int duration9 = Toast.LENGTH_SHORT;
                    Toast toast9 = Toast.makeText(context9, text9, duration9);
                    Context context10 = getApplicationContext();
                    CharSequence text10 = "Alarm set for " + ((alarmDetails.timeHour - hour2)-1) + " hours and " + ((alarmDetails.timeMinute - min2)+60) + " minute from now";
                    int duration10 = Toast.LENGTH_SHORT;
                    Toast toast10 = Toast.makeText(context10, text10, duration10);
                    Context context11 = getApplicationContext();
                    CharSequence text11 = "Alarm set for " + ((alarmDetails.timeHour - hour2)-1) + " hour and " + ((alarmDetails.timeMinute - min2)+60) + " minutes from now";
                    int duration11 = Toast.LENGTH_SHORT;
                    Toast toast11 = Toast.makeText(context11, text11, duration11);
                    Context context12 = getApplicationContext();
                    CharSequence text12 = "Alarm set for " + ((alarmDetails.timeHour - hour2)-1) + " hours and " + ((alarmDetails.timeMinute - min2)+60) + " minutes from now";
                    int duration12 = Toast.LENGTH_SHORT;
                    Toast toast12 = Toast.makeText(context12, text12, duration12);
                    Context context13 = getApplicationContext();
                    CharSequence text13 = "Alarm set for "  + ((alarmDetails.timeMinute - min2)+60) + " minute from now";
                    int duration13 = Toast.LENGTH_SHORT;
                    Toast toast13 = Toast.makeText(context13, text13, duration13);
                    Context context14 = getApplicationContext();
                    CharSequence text14 = "Alarm set for "  + ((alarmDetails.timeMinute - min2)+60) + " minutes from now";
                    int duration14 = Toast.LENGTH_SHORT;
                    Toast toast14 = Toast.makeText(context14, text14, duration14);
                    Context context15 = getApplicationContext();
                    CharSequence text15 = "Alarm set for 1 day from now";
                    int duration15 = Toast.LENGTH_SHORT;
                    Toast toast15 = Toast.makeText(context15, text15, duration15);
                    Context context16 = getApplicationContext();
                    CharSequence text16 = "Alarm set for " + (((alarmDetails.timeHour - hour2)-1)+24) + " hours and " + ((alarmDetails.timeMinute - min2)+60) + " minutes from now";
                    int duration16 = Toast.LENGTH_SHORT;
                    Toast toast16 = Toast.makeText(context16, text16, duration16);
                    Context context17 = getApplicationContext();
                    CharSequence text17 = "Alarm set for " + ((alarmDetails.timeHour - hour2)+24) + " hours and " + (alarmDetails.timeMinute - min2) + " minute from now";
                    int duration17 = Toast.LENGTH_SHORT;
                    Toast toast17 = Toast.makeText(context17, text17, duration17);
                    Context context18 = getApplicationContext();
                    CharSequence text18 = "Alarm set for "+((alarmDetails.timeHour - hour2)+24)+" hours from now" ;
                    int duration18 = Toast.LENGTH_SHORT;
                    Toast toast18 = Toast.makeText(context18, text18, duration18);



                    if (alarmDetails.timeMinute==min2&&alarmDetails.timeHour-hour2==1)
                    {
                        toast1.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeMinute==min2&&alarmDetails.timeHour-hour2>1)
                    {
                        toast2.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour==hour2&&alarmDetails.timeMinute-min2==1)
                    {
                        toast3.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour==hour2&&alarmDetails.timeMinute-min2>1)
                    {
                        toast4.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2==1&&alarmDetails.timeMinute-min2>1)
                    {
                        toast5.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2>1&&alarmDetails.timeMinute-min2==1)
                    {
                        toast6.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2>1&&alarmDetails.timeMinute-min2>1)
                    {
                        toast7.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2==1&&alarmDetails.timeMinute-min2==1)
                    {
                        toast8.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2==2&&alarmDetails.timeMinute-min2==-59)
                    {
                        toast9.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2>2&&alarmDetails.timeMinute-min2==-59)
                    {
                        toast10.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2==2&&alarmDetails.timeMinute<min2)
                    {
                        toast11.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2>2&&alarmDetails.timeMinute<min2)
                    {
                        toast12.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2==1&&alarmDetails.timeMinute-min2==-59)
                    {
                        toast13.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour-hour2==1&&alarmDetails.timeMinute<min2)
                    {
                        toast14.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour==hour2&&alarmDetails.timeMinute==min2)
                    {
                        toast15.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour<=hour2&&alarmDetails.timeMinute<min2)
                    {
                        toast16.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour<=hour2&&alarmDetails.timeMinute>min2)
                    {
                        toast17.show();
                        toast.show();
                    }
                    else if (alarmDetails.timeHour<=hour2&&alarmDetails.timeMinute==min2)
                    {
                        toast18.show();
                        toast.show();
                    }


                    setResult(RESULT_OK);

                }



                finish();

            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateModelFromLayout() {
        alarmDetails.timeMinute = timePicker.getCurrentMinute().intValue();
        alarmDetails.timeHour = timePicker.getCurrentHour().intValue();
        alarmDetails.name = edtName.getText().toString();
        alarmDetails.repeatWeekly = chkWeekly.isChecked();
        alarmDetails.setRepeatingDay(AlarmModel.SUNDAY, chkSunday.isChecked());
        alarmDetails.setRepeatingDay(AlarmModel.MONDAY, chkMonday.isChecked());
        alarmDetails.setRepeatingDay(AlarmModel.TUESDAY, chkTuesday.isChecked());
        alarmDetails.setRepeatingDay(AlarmModel.WEDNESDAY, chkWednesday.isChecked());
        alarmDetails.setRepeatingDay(AlarmModel.THURSDAY, chkThursday.isChecked());
        alarmDetails.setRepeatingDay(AlarmModel.FRDIAY, chkFriday.isChecked());
        alarmDetails.setRepeatingDay(AlarmModel.SATURDAY, chkSaturday.isChecked());
        alarmDetails.isEnabled = true;
    }

}