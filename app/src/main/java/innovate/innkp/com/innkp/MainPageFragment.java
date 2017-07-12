package innovate.innkp.com.innkp;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainPageFragment extends Fragment {
    private static final String TAG = MainPageFragment.class.getName();
    private static MainPageFragment fragment = new MainPageFragment();


    static MainPageFragment newInstance() {
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page, container, false);
        Log.d(TAG,"MainPageFragment created.");
        return view;
    }

}
