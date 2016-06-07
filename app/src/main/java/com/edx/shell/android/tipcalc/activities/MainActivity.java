package com.edx.shell.android.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.edx.shell.android.tipcalc.R;
import com.edx.shell.android.tipcalc.TipCalcApp;
import com.edx.shell.android.tipcalc.fragments.TipHistoryListFragment;
import com.edx.shell.android.tipcalc.listeners.TipHistoryListFragmentListener;
import com.edx.shell.android.tipcalc.models.TipRecord;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Variables
    private TipHistoryListFragmentListener fragmentListener;

    // Constantes
    private static final int DEFAULT_TIP_PERCENTAGE = 10;
    private static final int TIP_STEP_CHANGE = 1;

    // Components
    private EditText edtTotal;
    private EditText edtPropina;
    private EditText edtPorcentaje;
    private Button btnCalculate;
    private Button btnIncrease;
    private Button btnDecrease;
    private Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        setonClickListeners();
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
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeComponents() {
        edtTotal = (EditText) findViewById(R.id.edt_total);
        edtPropina = (EditText) findViewById(R.id.edt_propina);
        edtPorcentaje = (EditText) findViewById(R.id.edt_tip_percentage);

        btnCalculate = (Button) findViewById(R.id.btn_calculate);
        btnIncrease = (Button) findViewById(R.id.btn_increase);
        btnDecrease = (Button) findViewById(R.id.btn_decrease);
        btnClear = (Button) findViewById(R.id.btn_clear);

        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frg_list);
        fragment.setRetainInstance(true);
        fragmentListener = fragment;
    }

    private void setonClickListeners() {
        btnCalculate.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        btnDecrease.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_calculate:
                hideKeyboard();
                calculateTip();
                break;
            case R.id.btn_increase:
                hideKeyboard();
                cambiarCambioPropina(TIP_STEP_CHANGE);
                break;
            case R.id.btn_decrease:
                hideKeyboard();
                cambiarCambioPropina(-TIP_STEP_CHANGE);
                break;
            case R.id.btn_clear:
                hideKeyboard();
                fragmentListener.clearList();
            default:
                break;
        }
    }

    private void cambiarCambioPropina(int change) {
        int porcentaje = obtenerPorcentajePropina();
        porcentaje += change;
        if (porcentaje > 0) {
            edtPorcentaje.setText(String.valueOf(porcentaje));
        }
    }

    /**
     * Función para calcular el valor de la propina, de acuerdo al total capturado
     */
    private void calculateTip() {
        String strTotal = edtTotal.getText().toString().trim();
        if (!strTotal.isEmpty()) {
            double total = Double.parseDouble(strTotal);
            int porcentaje = obtenerPorcentajePropina();

            TipRecord tipRecord = new TipRecord(total, porcentaje, new Date());

            String strPorcentaje = String.format(getString(R.string.propina), tipRecord.getTip());
            fragmentListener.addToList(tipRecord);
            edtPropina.setVisibility(View.VISIBLE);
            edtPropina.setText(strPorcentaje);
        }
    }

    private int obtenerPorcentajePropina() {
        int porcentaje = DEFAULT_TIP_PERCENTAGE;
        String strPorcentaje = edtPorcentaje.getText().toString().trim();
        if (!strPorcentaje.isEmpty()) {
            porcentaje = Integer.parseInt(strPorcentaje);
        } else {
            edtPorcentaje.setText(String.valueOf(porcentaje));
        }
        return porcentaje;
    }

    /**
     * Función para ocultar el teclado, al dar clic en el botón "Calcular"
     */
    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            if (getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (NullPointerException e) {
            Log.e(getLocalClassName(), Log.getStackTraceString(e));
        }
    }

    /**
     * Función para mostrar al usuario información acerca del programador de la aplicación
     */
    private void about() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(TipCalcApp.ABOUT_URL));
        startActivity(intent);
    }


}
