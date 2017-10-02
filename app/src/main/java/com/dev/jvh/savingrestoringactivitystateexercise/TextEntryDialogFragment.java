package com.dev.jvh.savingrestoringactivitystateexercise;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by JochemVanHespen on 10/2/2017.
 */

public class TextEntryDialogFragment extends DialogFragment {

    public interface TextEntryDialogListener {
        public void onDialogPositiveClick (DialogFragment dialogFragment, String text);
        public void onDialogNegativeClick (DialogFragment dialogFragment);
    }

    TextEntryDialogListener textEntryDialogListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            textEntryDialogListener = (TextEntryDialogListener) context;
        } catch (ClassCastException cce)
        {
            throw new ClassCastException(context.toString() + " must implement TextEntryDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View entryDialog = inflater.inflate(R.layout.textentry_dialog,null);

        builder.setView(entryDialog)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) entryDialog.findViewById(R.id.editText);
                        textEntryDialogListener.onDialogPositiveClick(TextEntryDialogFragment.this,
                                editText.getText().toString());
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textEntryDialogListener.onDialogNegativeClick(TextEntryDialogFragment.this);
            }
        });
        return builder.create();
    }
}
