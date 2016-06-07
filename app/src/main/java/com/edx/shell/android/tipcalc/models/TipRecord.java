package com.edx.shell.android.tipcalc.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TipRecord {
    private double bill;
    private int tipPercentage;
    private Date timeStamp;

    public TipRecord() {
    }

    public TipRecord(double bill, int tipPercentage, Date timeStamp) {
        this.bill = bill;
        this.tipPercentage = tipPercentage;
        this.timeStamp = timeStamp;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public int getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(int tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Función que devuelve el valor de la propina, de acuerdo al total y el porecntaje de propina
     * @return
     */
    public double getTip() {
        return bill * (tipPercentage / 100d);
    }

    /**
     * Función que devuelve la fecha en la que se realizó la petición en formato 'yyyy MMM dd, HH:mm'
     * @return
     */
    public String getDateFormatted() {
        return new SimpleDateFormat("yyyy MMM dd, HH:mm")
                .format(timeStamp);
    }
}
