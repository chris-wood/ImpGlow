package cawcode.cwood.impglow;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class ImpRegistrationActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imp_registration);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_imp_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void submitRegistrationData(View view) {
        EditText idTextView = (EditText) findViewById(R.id.device_id_field);
        EditText urlTextView = (EditText) findViewById(R.id.device_url_field);

        Bundle stringData = new Bundle();
        stringData.putString("device_id", idTextView.getText().toString());
        stringData.putString("device_url", urlTextView.getText().toString());

        Intent intent = new Intent();
        intent.putExtras(stringData);
        setResult(RESULT_OK, intent);

        finish();
    }
}
