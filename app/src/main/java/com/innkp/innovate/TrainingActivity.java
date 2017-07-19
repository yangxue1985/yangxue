package com.innkp.innovate;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class TrainingActivity extends BaseActivity {
    private static final String TAG = TrainingActivity.class.getName();
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training);
        initActionBar();
    }

    private void initActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_sub);
        TextView toolbar_name=(TextView)findViewById(R.id.toolbar_name);
        toolbar_name.setText(getString(R.string.title_training));
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_return);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
