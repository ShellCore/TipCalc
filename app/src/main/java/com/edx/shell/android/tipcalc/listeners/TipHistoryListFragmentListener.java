package com.edx.shell.android.tipcalc.listeners;

import com.edx.shell.android.tipcalc.models.TipRecord;

public interface TipHistoryListFragmentListener {
    void addToList(TipRecord record);
    void clearList();
}
