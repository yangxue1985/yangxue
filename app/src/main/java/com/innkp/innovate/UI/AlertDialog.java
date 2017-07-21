package com.innkp.innovate.UI;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.innkp.innovate.R;

public class AlertDialog extends Dialog {

    private static final String TAG = AlertDialog.class.getSimpleName();
    private String mAlertMessage;

    public AlertDialog(Context context, String message) {
        super(context, R.style.MyDialog);
        mAlertMessage = message;
    }

    public void onSubmit() {
        this.dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog);
        TextView alert = (TextView) findViewById(R.id.alert_message);
        alert.setText(mAlertMessage);
        TextView cancel = (TextView) findViewById(R.id.alert_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        TextView close = (TextView) findViewById(R.id.alert_quit);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
                dismiss();
            }
        });
    }
}
