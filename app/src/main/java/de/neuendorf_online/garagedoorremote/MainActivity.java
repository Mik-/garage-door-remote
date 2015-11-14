package de.neuendorf_online.garagedoorremote;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import static de.neuendorf_online.garagedoorremote.DoorFragment.newInstance;

public class MainActivity extends ActionBarActivity {
    public final static String EXTRA_MESSAGE = "de.neuendorf_online.garagedoorremote.MESSAGE";

    private DoorModel door;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.door = new DoorModel("Testdoor", DoorState.INTERMEDIATE, DoorIntent.IDLE, 1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
              return ;
            }

            DoorFragment doorFragment = newInstance(this.door);

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, doorFragment).commit();
        }

        final DynDNS dynDNS = new DynDNS();

        class DynDNSCallback implements DynDNS.Callback {
            public void onUrlResolved() {
                TextView textView = (TextView) findViewById(R.id.dyndns_result);
                textView.setText(dynDNS.getIpAddress());
            }
        }

        dynDNS.resolve("http://dyndns.mn70.de/garage_json.php", new DynDNSCallback());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}