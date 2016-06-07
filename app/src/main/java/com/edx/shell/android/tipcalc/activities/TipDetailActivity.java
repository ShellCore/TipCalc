package com.edx.shell.android.tipcalc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.edx.shell.android.tipcalc.R;

public class TipDetailActivity extends AppCompatActivity {

    // Constantes
    public static final String TOTAL_KEY = "total";
    public static final String TIP_KEY = "tip";
    public static final String TIMESTAMP_KEY = "timestamp";

    // Components
    private TextView txtTotal;
    private TextView txtTip;
    private TextView txtTimestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_detail);
        initComponents();
        getIntentValues();

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initComponents() {
        txtTotal = (TextView) findViewById(R.id.txt_total);
        txtTip = (TextView) findViewById(R.id.txt_tip);
        txtTimestamp = (TextView) findViewById(R.id.txt_timestamp);

    }

    private void getIntentValues() {
        Intent intent = getIntent();
        String strTotal = String.format(getString(R.string.total), intent.getDoubleExtra(TOTAL_KEY, 0d));
        txtTotal.setText(strTotal);
        String strTip = String.format(getString(R.string.total), intent.getDoubleExtra(TIP_KEY, 0d));
        txtTip.setText(strTip);
        txtTimestamp.setText(intent.getStringExtra(TIMESTAMP_KEY));
    }
}
