package com.example.dfily.isusapp.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.dfily.isusapp.R;

/**
 * Created by dfily on 15.11.2017.
 */

public class YesNoDialog extends DialogFragment {

    YesNoClicker clicker;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(R.string.yes_no_string)
                .setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                    clicker.yesClick();
                })
                .setNegativeButton(R.string.no, (dialogInterface, i) -> {
                    clicker.noClick();
                });

        return builder.create();
    }

    public void setClicker(YesNoClicker clicker) {
        this.clicker = clicker;
    }

}
