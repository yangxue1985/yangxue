package innovate.innkp.com.innkp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import innovate.innkp.com.innkp.Utils.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = CourseFragment.class.getName();
    private Toolbar mToolbar;
    private MainPageFragment mMainPageFragment;
    private CourseFragment mCourseFragment;
    private SocialFragment mSocialFragment;
    private MineFragment mMineFragment;
    private Fragment mCurrentFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (mMainPageFragment == null)
                        mMainPageFragment = MainPageFragment.newInstance();
                    setTabFragment(mMainPageFragment);
                    return true;
                case R.id.navigation_course:
                    if (mCourseFragment == null)
                        mCourseFragment = CourseFragment.newInstance();
                    setTabFragment(mCourseFragment);
                    return true;
                case R.id.navigation_social:
                    if (mSocialFragment == null)
                        mSocialFragment = SocialFragment.newInstance();
                    setTabFragment(mSocialFragment);
                    return true;
                case R.id.navigation_mine:
                    if (mMineFragment == null)
                        mMineFragment = MineFragment.newInstance();
                    setTabFragment(mMineFragment);
                    return true;
            }
            return false;
        }
    };

    public void setTabFragment(Fragment fragment) {
        if (mCurrentFragment == null || !fragment.getClass().equals(mCurrentFragment.getClass())) {
            try {
                if (!fragment.isAdded()) {
                    FragmentManager fManager = getFragmentManager();
                    FragmentTransaction ft = fManager.beginTransaction();
                    ft.replace(R.id.fragment_view, fragment);
                    ft.commit();
                    mCurrentFragment = fragment;
                }
            } catch (Exception e) {
                Log.i(TAG, "setFragment", e);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initActionBar();
        initFragment();
    }

    private void initActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_return);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
            }
        });

    }

    private void initFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        mMainPageFragment = MainPageFragment.newInstance();
        ft.add(R.id.fragment_view, mMainPageFragment);
        ft.commit();
        mCurrentFragment = mMainPageFragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_edit:
//                Toast.makeText(this, "action_edit", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.action_share:
//                Toast.makeText(this, "action_share", Toast.LENGTH_SHORT).show();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
