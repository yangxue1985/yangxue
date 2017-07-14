package innovate.innkp.com.innkp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class PatentCourseActivity extends BaseActivity {
    private static final String TAG = PatentCourseActivity.class.getName();
    private Toolbar mToolbar;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.patent_course_view);
        initActionBar();
        mTextView = (TextView) findViewById(R.id.patent_course_text);
        mTextView.setText("id=" + getIntent().getIntExtra("index", -1));

    }

    private void initActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_patent_course);
        TextView toolbar_name=(TextView)findViewById(R.id.toolbar_name);
        toolbar_name.setText(getString(R.string.patent_course));
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
