package com.edx.shell.android.tipcalc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Constantes
    private static final int DEFAULT_TIP_PERCENTAGE = 10;

    // Components
    private EditText edtTotal;
    private Button btnCalculate;
    private EditText edtPropina;

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
        btnCalculate = (Button) findViewById(R.id.btn_calculate);
        edtPropina = (EditText) findViewById(R.id.edt_propina);
    }

    private void setonClickListeners() {
        btnCalculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_calculate:
                hideKeyboard();
                calculateTip();
                break;
            default:
                break;
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
            double propina = total * (porcentaje / 100d);

            String strPorcentaje = String.format(getString(R.string.propina), propina);
            edtPropina.setVisibility(View.VISIBLE);
            edtPropina.setText(strPorcentaje);
        }
    }

    private int obtenerPorcentajePropina() {
        return DEFAULT_TIP_PERCENTAGE;
    }

    /**
     * Función para ocultar el teclado, al dar clic en el botón "Calcular"
     */
    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputMethodManager.hideSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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
