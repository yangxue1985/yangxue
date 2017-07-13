package innovate.innkp.com.innkp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import innovate.innkp.com.innkp.UI.PagingListView;
import innovate.innkp.com.innkp.dto.BaseResource;

public class MainPageFragment extends Fragment {
    private static final String TAG = MainPageFragment.class.getName();
    private static MainPageFragment fragment = new MainPageFragment();
    private PagingListView mPagingList;
    private BaseResourceAdapter mBaseResourceAdapter;

    static MainPageFragment newInstance() {
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page, container, false);
        Log.d(TAG, "MainPageFragment created.");
        initViews(view);
        return view;
    }

    public void initViews(View view) {
        mPagingList = (PagingListView) view.findViewById(R.id.resource_list);
        mPagingList.setHeaderCanLoadMore(false);
        mPagingList.setFooterCanLoadMore(false);
        mPagingList.setFooterRootViewVisibility(View.GONE);
        mPagingList.setHeaderRootViewVisibility(View.GONE);
        mBaseResourceAdapter = new BaseResourceAdapter();
        mPagingList.setAdapter(mBaseResourceAdapter);
        mPagingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent = new Intent(getActivity(), PatentCourseActivity.class);
                intent.putExtra("index", i);
                startActivity(intent);
            }
        });
        mPagingList.setOnLoadListener(new PagingListView.OnLoadMoreListener() {
            @Override
            public void onFooterLoadMore() {
            }

            @Override
            public void onHeaderLoadMore() {
            }
        });
    }

    public class BaseResourceAdapter extends BaseAdapter {

        private List<BaseResource> mList = new ArrayList<BaseResource>();

        public BaseResourceAdapter() {
            super();
            for (int i = 0; i < 15; i++) {
                BaseResource b = new BaseResource();
                mList.add(b);
            }
        }

        public int getCount() {
            return mList.size();
        }

        public BaseResource getItem(int position) {
            return mList.get(position);
        }

        public long getItemId(int position) {
            return position;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            if (getCount() == 0) {
                return null;
            }
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.base_resource_item, parent, false);
                holder.title = (TextView) convertView.findViewById(R.id.base_resource_title);
                holder.text = (TextView) convertView.findViewById(R.id.base_resource_text);
                holder.thumb_view = (ImageView) convertView.findViewById(R.id.base_resource_pic);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
//            final BaseResource base = mList.get(position);
//            if (base != null) {
//                holder.title.setText(getContext().getResources().getString(R.string.app_name));
//            }
            return convertView;
        }


        private class ViewHolder {
            private TextView title;
            private TextView text;
            private ImageView thumb_view;
        }
    }
}
