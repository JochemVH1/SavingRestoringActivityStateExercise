package com.dev.jvh.savingrestoringactivitystateexercise;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements TextEntryDialogFragment.TextEntryDialogListener {

    private final String TEXTVIEW_STATEKEY = "TEXTVIEW_STATEKEY";
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView1);

        if(savedInstanceState != null) if(savedInstanceState.containsKey(TEXTVIEW_STATEKEY))
                textView.setText(savedInstanceState.getString(TEXTVIEW_STATEKEY));

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(TEXTVIEW_STATEKEY,textView.getText().toString());
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialogFragment, String text) {
        textView.setText(text);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialogFragment) {
        Toast.makeText(getApplicationContext(),"Cancelled dialog",Toast.LENGTH_LONG).show();
    }

    public void buttonClicked(View view)
    {
        TextEntryDialogFragment dialogFragment = new TextEntryDialogFragment();
        dialogFragment.show(getFragmentManager(),"Text dialog!");
    }
}
