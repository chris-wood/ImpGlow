package cawcode.cwood.impglow;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "cawcode.cwood.impglow.MESSAGE"; // key for intent object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    public void sendMessage(View view) {
//        // Do something in response to button
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivityForResult(intent, 10); // matches code below, TODO: define an enum to capture these codes
//    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 10:
                System.out.println("HERE MOFO");
                if (resultCode == RESULT_OK) {
                    Bundle res = data.getExtras();
                    String deviceId = res.getString("device_id");
                    String deviceUrl = res.getString("device_url");

                    // DEBUG
                    System.out.println(deviceId + " - " + deviceUrl);

                    LinearLayout ll = (LinearLayout) findViewById(R.id.imp_list);
                    TextView idView = new TextView(this);
                    idView.setWidth(LinearLayout.LayoutParams.FILL_PARENT);
                    idView.setText(deviceId.toCharArray(), 0, deviceId.length());
                    ll.addView(idView);

                    TextView urlView = new TextView(this);
                    urlView .setWidth(LinearLayout.LayoutParams.FILL_PARENT);
                    urlView .setText(deviceUrl.toCharArray(), 0, deviceUrl.length());
                    ll.addView(urlView);
                } else {
                    System.out.println("Result code was not RESULT_OK");
                }
                break;
        }
    }

    public void registerImp(View view) {
        Intent intent = new Intent(this, ImpRegistrationActivity.class);
        startActivityForResult(intent, 10); // matches code below, TODO: define an enum to capture these codes
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            openSettings();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
