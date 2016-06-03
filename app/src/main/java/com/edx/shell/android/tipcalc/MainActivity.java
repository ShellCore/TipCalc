package com.edx.shell.android.tipcalc;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.act_about:
                about();
        }
        return super.onOptionsItemSelected(item);
    }

    private void about() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(TipCalcApp.ABOUT_URL));
        startActivity(intent);
    }
}
