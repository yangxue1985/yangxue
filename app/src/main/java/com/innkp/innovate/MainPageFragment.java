package com.innkp.innovate;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
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

import com.innkp.innovate.UI.PagingListView;
import com.innkp.innovate.dto.BaseResource;
import com.innkp.innovate.Utils.MockUtils;
import com.innkp.innovate.dto.VideoDetailInfo;

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
//        mPagingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                VideoDetailInfo info = MockUtils.mockData(VideoDetailInfo.class);
//                CourseActivity.start(getActivity(), info);
//            }
//        });
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
                holder.preview = convertView.findViewById(R.id.resource_preview);
                holder.picture = (ImageView) convertView.findViewById(R.id.resource_pic);
                holder.title = (TextView) convertView.findViewById(R.id.resource_title);
                holder.played = (TextView) convertView.findViewById(R.id.resource_played);
                holder.play = (ImageView) convertView.findViewById(R.id.resource_play);
                holder.author_icon = (ImageView) convertView.findViewById(R.id.resource_author_icon);
                holder.author = (TextView) convertView.findViewById(R.id.resource_author_name);
                holder.share = (ImageView) convertView.findViewById(R.id.resource_share);
                holder.praised_count = (TextView) convertView.findViewById(R.id.resource_praised_count);
                holder.message_count = (TextView) convertView.findViewById(R.id.resource_message_count);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final BaseResource base = mList.get(position);
            if (base != null) {
//                holder.title.setText(base.title);
//                holder.played.setText(base.played + "");
//                holder.author.setText(base.author);
//                holder.praised_count.setText(base.praised + "");
//                holder.message_count.setText(base.message + "");
                holder.preview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        VideoDetailInfo info = MockUtils.mockData(VideoDetailInfo.class);
                        CourseActivity.start(getActivity(), info);
                    }
                });
                holder.play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        VideoDetailInfo info = MockUtils.mockData(VideoDetailInfo.class);
                        CourseActivity.start(getActivity(), info);
                    }
                });
                holder.author_icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
                holder.share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT, "www.baidu.com");
                        startActivity(Intent.createChooser(intent, getResources().getString(R.string.share_to)));
                    }
                });

            }
            return convertView;
        }


        private class ViewHolder {
            private View preview;
            private ImageView picture;
            private TextView title;
            private TextView played;
            private ImageView play;
            private ImageView author_icon;
            private TextView author;
            private ImageView share;
            private TextView praised_count;
            private TextView message_count;
        }
    }
}
