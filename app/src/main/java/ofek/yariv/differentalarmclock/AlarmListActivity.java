package ofek.yariv.differentalarmclock;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class AlarmListActivity extends ListActivity {

    private AlarmListAdapter mAdapter;
    private AlarmDBHelper dbHelper = new AlarmDBHelper(this);
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        requestWindowFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_alarm_list);

        mAdapter = new AlarmListAdapter(this, dbHelper.getAlarms());

        setListAdapter(mAdapter);
        ListView list = (ListView) findViewById(android.R.id.list);
        if (list.getAdapter().getCount()==0)
        {
            ImageView plus = (ImageView)findViewById(R.id.plus);
            TextView noalarmstext = (TextView)findViewById(R.id.noalarmstext);
            plus.setVisibility(View.VISIBLE);
            noalarmstext.setVisibility(View.VISIBLE);
        }
        else
        {
            ImageView plus = (ImageView)findViewById(R.id.plus);
            TextView noalarmstext = (TextView)findViewById(R.id.noalarmstext);
            plus.setVisibility(View.INVISIBLE);
            noalarmstext.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.alarm_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_new_alarm: {
                GenreActivity.rockcheck=0;
                GenreActivity.popcheck=0;
                GenreActivity.electriccheck=0;
                GenreActivity.oldiescheck=0;
                GenreActivity.hiphopcheck=0;
                GenreActivity.alarmsoundscheck=0;
                startAlarmDetailsActivity(-1);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            mAdapter.setAlarms(dbHelper.getAlarms());
            mAdapter.notifyDataSetChanged();
            ListView list = (ListView) findViewById(android.R.id.list);
            if (list.getAdapter().getCount()==0)
            {
                ImageView plus = (ImageView)findViewById(R.id.plus);
                TextView noalarmstext = (TextView)findViewById(R.id.noalarmstext);
                plus.setVisibility(View.VISIBLE);
                noalarmstext.setVisibility(View.VISIBLE);
            }
            else
            {
                ImageView plus = (ImageView)findViewById(R.id.plus);
                TextView noalarmstext = (TextView)findViewById(R.id.noalarmstext);
                plus.setVisibility(View.INVISIBLE);
                noalarmstext.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void setAlarmEnabled(long id, boolean isEnabled) {
        AlarmManagerHelper.cancelAlarms(this);

        AlarmModel model = dbHelper.getAlarm(id);
        model.isEnabled = isEnabled;
        dbHelper.updateAlarm(model);

        AlarmManagerHelper.setAlarms(this);
    }

    public void startAlarmDetailsActivity(long id) {
        Intent intent = new Intent(this, AlarmDetailsActivity.class);
        intent.putExtra("id", id);
        startActivityForResult(intent, 0);
    }

    public void deleteAlarm(long id) {


        final long alarmId =id;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog show = builder.setMessage("Please confirm")
                .setTitle("Delete set?")
                .setCancelable(true)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Cancel Alarms
                        AlarmManagerHelper.cancelAlarms(mContext);
                        //Delete alarm from DB by id

                        dbHelper.deleteAlarm(alarmId);

                        //Refresh the list of the alarms in the adaptor

                        mAdapter.setAlarms(dbHelper.getAlarms());

                        //Notify the adapter the data has changed
                        mAdapter.notifyDataSetChanged();
                        //Set the alarms
                        ListView list = (ListView) findViewById(android.R.id.list);
                        if (list.getAdapter().getCount()==0)
                        {
                            ImageView plus = (ImageView)findViewById(R.id.plus);
                            TextView noalarmstext = (TextView)findViewById(R.id.noalarmstext);
                            plus.setVisibility(View.VISIBLE);
                            noalarmstext.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            ImageView plus = (ImageView)findViewById(R.id.plus);
                            TextView noalarmstext = (TextView)findViewById(R.id.noalarmstext);
                            plus.setVisibility(View.INVISIBLE);
                            noalarmstext.setVisibility(View.INVISIBLE);
                        }
                    }
                }).show();
    }
}