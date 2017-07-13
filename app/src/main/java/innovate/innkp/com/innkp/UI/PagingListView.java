package innovate.innkp.com.innkp.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import innovate.innkp.com.innkp.R;


public class PagingListView extends ListView implements OnScrollListener {

    private final static int FOOTER_LOADING = 1;
    private final static int FOOTER_LOAD_DONE = 2;
    private int mFooterState;

    private View mFooterRootView;
    private ProgressBar mFooterLoadProgressBar;
    private TextView mFooterTextView;

    private final static int HEADER_LOADING = 1;
    private final static int HEADER_LOAD_DONE = 2;
    private int mHeaderState;

    private View mHeaderRootView;
    private ProgressBar mHeaderLoadProgressBar;
    private TextView mHeaderTextView;

    private LayoutInflater mInflater;

    private OnLoadMoreListener mLoadMoreListener;

    public PagingListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PagingListView(Context context) {
        super(context);
        init(context);
    }

    public PagingListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private boolean mFooterCanLoadMore = true;
    private boolean mHeaderCanLoadMore = true;

    public void setFooterCanLoadMore(boolean canFooterLoadMore) {
        mFooterCanLoadMore = canFooterLoadMore;
        if (mFooterCanLoadMore) {
            if (getFooterViewsCount() == 0)
                addFooterView();
        } else {
            if (getFooterViewsCount() == 1 && mFooterRootView != null)
//                if (this.getAdapter() instanceof FooterViewListAdapter) {
                    this.removeFooterView(mFooterRootView);
//                }
        }
    }

    public void setFooterRootViewVisibility(int visibility) {
        mFooterRootView.setVisibility(visibility);
    }
    public void setHeaderRootViewVisibility(int visibility) {
        mHeaderRootView.setVisibility(visibility);
    }
    public void setHeaderCanLoadMore(boolean canHeaderLoadMore) {
        mHeaderCanLoadMore = canHeaderLoadMore;
        if (mHeaderCanLoadMore) {
            if (getHeaderViewsCount() == 0)
                addHeaderView();
        } else {
            if (getHeaderViewsCount() == 1 && mHeaderRootView != null)
                this.removeHeaderView(mHeaderRootView);
        }
    }

    private void init(Context context) {
        mInflater = LayoutInflater.from(context);
        addFooterView();
        addHeaderView();
        setOnScrollListener(this);

    }

    private void addFooterView() {
        mFooterRootView = mInflater.inflate(R.layout.list_more, null);
        mFooterRootView.setVisibility(View.VISIBLE);
        mFooterLoadProgressBar = (ProgressBar) mFooterRootView
                .findViewById(R.id.loading_progress);
        mFooterTextView = (TextView) mFooterRootView
                .findViewById(R.id.load_more);
        mFooterRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFooterCanLoadMore) {
                    if (mFooterState != FOOTER_LOADING) {
                        mFooterState = FOOTER_LOADING;
                        onFooterLoadMore();
                    }
                }
            }
        });

        addFooterView(mFooterRootView);
        mFooterState = FOOTER_LOAD_DONE;
    }

    private void addHeaderView() {
        mHeaderRootView = mInflater.inflate(R.layout.list_more, null);
        mHeaderRootView.setVisibility(View.VISIBLE);
        mHeaderLoadProgressBar = (ProgressBar) mHeaderRootView
                .findViewById(R.id.loading_progress);
        mHeaderTextView = (TextView) mHeaderRootView
                .findViewById(R.id.load_more);
        mHeaderRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHeaderCanLoadMore) {
                    if (mHeaderState != HEADER_LOADING) {
                        mHeaderState = HEADER_LOADING;
                        onFooterLoadMore();
                    }
                }
            }
        });

        addHeaderView(mHeaderRootView);
        mHeaderState = HEADER_LOAD_DONE;
    }

    private int mFirstItemIndex;
    private int mLastItemIndex;
    private int mCount;

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        mFirstItemIndex = firstVisibleItem;
        mLastItemIndex = firstVisibleItem + visibleItemCount - 2;
        mCount = totalItemCount - 2;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        if (mFooterCanLoadMore) {
//            if (mLastItemIndex == mCount && scrollState == SCROLL_STATE_IDLE) {
//                if (mFooterState != FOOTER_LOADING) {
//                    mFooterState = FOOTER_LOADING;
//                    onFooterLoadMore();
//                    changeFooterViewByState();
//                    return;
//                }
//            }
//        }
//        if (mHeaderCanLoadMore) {
//            if (mFirstItemIndex == 0 && scrollState == SCROLL_STATE_IDLE) {
//                if (mHeaderState != HEADER_LOADING) {
//                    mHeaderState = HEADER_LOADING;
//                    onHeaderLoadMore();
//                    changeHeaderViewByState();
//                }
//            }
//        }
    }

    private void changeFooterViewByState() {
        if (mFooterCanLoadMore) {
            switch (mFooterState) {
                case FOOTER_LOADING:
                    mFooterTextView.setText(R.string.refreshing);
                    mFooterTextView.setVisibility(View.VISIBLE);
                    mFooterLoadProgressBar.setVisibility(View.VISIBLE);
                    break;
                case FOOTER_LOAD_DONE:
                    mFooterTextView.setText(R.string.click_load_more);
                    mFooterTextView.setVisibility(View.VISIBLE);
                    mFooterLoadProgressBar.setVisibility(View.GONE);
                    mFooterRootView.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }
    }

    private void changeHeaderViewByState() {
        if (mHeaderCanLoadMore) {
            switch (mHeaderState) {
                case HEADER_LOADING:
                    mHeaderTextView.setText(R.string.refreshing);
                    mHeaderTextView.setVisibility(View.VISIBLE);
                    mHeaderLoadProgressBar.setVisibility(View.VISIBLE);
                    break;
                case HEADER_LOAD_DONE:
                    mHeaderTextView.setText(R.string.click_load_more);
                    mHeaderTextView.setVisibility(View.VISIBLE);
                    mHeaderLoadProgressBar.setVisibility(View.GONE);
                    mHeaderRootView.setVisibility(View.VISIBLE);
                    break;

                default:
                    break;
            }
        }
    }

    public interface OnLoadMoreListener {
        public void onFooterLoadMore();

        public void onHeaderLoadMore();
    }

    public void setOnLoadListener(OnLoadMoreListener loadMoreListener) {
        mLoadMoreListener = loadMoreListener;
    }

    private void onFooterLoadMore() {
        if (mLoadMoreListener != null) {
            mFooterTextView.setText(R.string.click_load_more);
            mFooterTextView.setVisibility(View.VISIBLE);
            mFooterLoadProgressBar.setVisibility(View.VISIBLE);
            mLoadMoreListener.onFooterLoadMore();
        }
    }

    private void onHeaderLoadMore() {
        if (mLoadMoreListener != null) {
            mHeaderTextView.setText(R.string.click_load_more);
            mHeaderTextView.setVisibility(View.VISIBLE);
            mHeaderLoadProgressBar.setVisibility(View.VISIBLE);
            mLoadMoreListener.onHeaderLoadMore();
        }
    }

    public void onFooterLoadMoreComplete() {
        mFooterState = FOOTER_LOAD_DONE;
        changeFooterViewByState();
    }

    public void onHeaderLoadMoreComplete() {
        mHeaderState = HEADER_LOAD_DONE;
        changeHeaderViewByState();
    }

}
