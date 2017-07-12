package innovate.innkp.com.innkp;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CourseFragment extends Fragment {
    private static final String TAG = CourseFragment.class.getName();
    private static CourseFragment fragment = new CourseFragment();


    static CourseFragment newInstance() {
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_page, container, false);
        Log.d(TAG, "CourseFragment created.");
        return view;
    }

}
