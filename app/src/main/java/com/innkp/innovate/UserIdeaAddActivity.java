package com.innkp.innovate;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.innkp.innovate.dto.ImageDto;

import java.util.ArrayList;
import java.util.List;


public class UserIdeaAddActivity extends BaseActivity {
    private static final String TAG = UserIdeaAddActivity.class.getName();
    private Toolbar mToolbar;
    public final static int IDEA_INNOVATE = 1;
    public final static int IDEA_COMPLAINTS = 2;
    public final static int IDEA_FEELING = 3;
    public final static String IDEA = "idea";
    private GridView mPictureGridView;
    private ImageAdapter mImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_idea_view);
        initActionBar();
        initViews();
    }

    private int getIdeaType() {
        return getIntent().getIntExtra(IDEA, IDEA_INNOVATE);
    }

    private void initViews() {
        mPictureGridView = (GridView) findViewById(R.id.add_idea_picture);
        mImageAdapter = new ImageAdapter();
        mPictureGridView.setAdapter(mImageAdapter);
    }

    private void initActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_add);
        TextView toolbar_name = (TextView) findViewById(R.id.toolbar_name);
        switch (getIdeaType()) {
            case IDEA_INNOVATE:
                toolbar_name.setText(getString(R.string.add_innovate));
                break;
            case IDEA_COMPLAINTS:
                toolbar_name.setText(getString(R.string.add_complaints));
                break;
            case IDEA_FEELING:
                toolbar_name.setText(getString(R.string.add_feeling));
                break;
            default:
                break;
        }
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_return);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.text_release_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "1111");
            }
        });
    }

    public class ImageAdapter extends BaseAdapter {
        private ArrayList<ImageDto> mList = new ArrayList<ImageDto>();

        @Override
        public int getCount() {
            return mList.size() + 1;
        }

        @Override
        public ImageDto getItem(int position) {
            if (position == 0) return null;
            return mList.get(position - 1);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (getCount() == 0) {
                return null;
            }
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.idea_image_item, parent, false);
                holder.addIcon = convertView.findViewById(R.id.idea_add_icon);
                holder.image = (ImageView) convertView.findViewById(R.id.idea_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (position == 0) {
                holder.addIcon.setVisibility(View.VISIBLE);
                holder.image.setVisibility(View.GONE);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Intent intent = new Intent(getActivity(), CalendarInfoActivity.class);
//                        startActivityForResult(intent, 1);
                    }
                });

            } else {
                holder.addIcon.setVisibility(View.GONE);
                holder.image.setVisibility(View.VISIBLE);
                final ImageDto imageDto = mList.get(position - 1);
                holder.image.setImageBitmap(imageDto.bitmap);
                holder.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Intent intent = new Intent(getActivity(), CalendarInfoActivity.class);
//                        intent.putExtra("calendar_id", calendar.id);
//                        startActivityForResult(intent, 2);
                    }
                });
            }
            return convertView;
        }

        public void setList(List<ImageDto> items) {
            mList.clear();
            mList.addAll(items);
            notifyDataSetChanged();
        }
    }

    private class ViewHolder {
        private View addIcon;
        private ImageView image;
    }
}
