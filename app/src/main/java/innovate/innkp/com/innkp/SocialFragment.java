package innovate.innkp.com.innkp;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SocialFragment extends Fragment {
    private static final String TAG = SocialFragment.class.getName();
    private static SocialFragment fragment = new SocialFragment();


    static SocialFragment newInstance() {
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.social_page, container, false);
        Log.d(TAG, "SocialFragment created.");
        return view;
    }

}
