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
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import innovate.innkp.com.innkp.UI.PagingListView;
import innovate.innkp.com.innkp.dto.BaseResource;

public class MainPageFragment extends Fragment {
    private static final String TAG = MainPageFragment.class.getName();
    private static MainPageFragment fragment = new MainPageFragment();
    private PagingListView mPagingList;

    static MainPageFragment newInstance() {
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page, container, false);
        Log.d(TAG,"MainPageFragment created.");
        initViews();
        return view;
    }
    public void initViews(View view){
        mPagingList = (PagingListView) view.findViewById(R.id.resource_list);
//        mPagingList.setHeaderCanLoadMore(false);
//        mPagingList.setFooterRootViewVisibility(View.GONE);
        mAppointmentAdapter = new AppointmentAdapter();
        mPagingList.setAdapter(mAppointmentAdapter);
        mPagingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), AppointmentInfoActivity.class);
                intent.putExtra(Constants.KEY_APPOINTMENT_ID, mAppointmentAdapter.getItem(i).id);
                intent.putExtra(Constants.KEY_ORIGINAL_ID, mAppointmentAdapter.getItem(i).originalId);
                getContext().startActivity(intent);
            }
        });
        mPagingList.setOnLoadListener(new PagingListView.OnLoadMoreListener() {
            @Override
            public void onFooterLoadMore() {
                mListener.onLoadMore(mAppointmentAdapter.getCount());
            }

            @Override
            public void onHeaderLoadMore() {
            }
        });
    }
    public class AppointmentAdapter extends BaseAdapter {

        private List<BaseResource> mList = new ArrayList<BaseResource>();

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
                convertView = inflater.inflate(R.layout.agenda_item_row, parent, false);
                holder.dividerLine = convertView.findViewById(R.id.divider_line);
                holder.date = (TextView) convertView.findViewById(R.id.top_date);
                holder.layout = convertView.findViewById(R.id.agenda_item_layout);
                holder.period = (TextView) convertView.findViewById(R.id.agenda_period);
                holder.calendarName = (TextView) convertView.findViewById(R.id.agenda_calendar_name);
                holder.ordersList = (TextView) convertView.findViewById(R.id.agenda_order_list);
                holder.customers = (TextView) convertView.findViewById(R.id.agenda_customers);
                FontUtils.setFont(getContext(), holder.date, FontUtils.FONT_ROBOTO_MEDIUM);
                FontUtils.setFont(getContext(), holder.period, FontUtils.FONT_ROBOTO_MEDIUM);
                FontUtils.setFont(getContext(), holder.calendarName, FontUtils.FONT_ROBOTO_REGULAR);
                FontUtils.setFont(getContext(), holder.ordersList, FontUtils.FONT_ROBOTO_MEDIUM);
                FontUtils.setFont(getContext(), holder.customers, FontUtils.FONT_ROBOTO_REGULAR);
                holder.checkIn = (ImageView) convertView.findViewById(R.id.agenda_check_in);
                holder.decline = (ImageView) convertView.findViewById(R.id.agenda_decline);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final AppointmentDTO appointment = mList.get(position);
            CalendarDTO c = getCalendar(appointment.calendarId);
            if (c != null) {
                holder.layout.setBackgroundColor(c.color);
                int textColor = Utils.getTextColor(getContext(), c.color);
                holder.calendarName.setTextColor(textColor);
                holder.period.setTextColor(textColor);
                holder.customers.setTextColor(textColor);
                holder.ordersList.setTextColor(textColor);
                if (c.name != null) {
                    holder.calendarName.setText(c.name);
                }
            }
            holder.period.setText(getContext().getResources().getString(R.string.start_to_end,
                    Constants.appointmentTimeFormat1.format(appointment.startTime), Constants.appointmentTimeFormat1.format(appointment.endTime)));
            if (appointment.name != null) {
                holder.ordersList.setText(appointment.name);
            }
            if (appointment.checkedIn) {
                holder.checkIn.setVisibility(VISIBLE);
            } else {
                holder.checkIn.setVisibility(GONE);
            }
            if (appointment.declined) {
                holder.decline.setVisibility(VISIBLE);
            } else {
                holder.decline.setVisibility(GONE);
            }
            if (appointment.customers != null) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < appointment.customers.size(); i++) {
                    for (com.clover.sdk.v3.customers.Customer customer : mCustomers) {
                        if (appointment.customers.get(i).id.equals(customer.getId())) {
                            sb.append(Utils.getCustomerName(customer));
                            if (i != appointment.customers.size() - 1) sb.append(",");
                            break;
                        }
                    }
                }
                holder.customers.setText(sb.toString().trim());
            }
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            calendar.setTimeInMillis(appointment.startTime);
            if (!mDays.contains(new MyDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)))) {
                holder.dividerLine.setVisibility(VISIBLE);
                if (mDays.size() == 0) {
                    holder.date.setTextColor(getContext().getResources().getColor(R.color.setting_menu_checked));
                } else {
                    holder.date.setTextColor(getContext().getResources().getColor(R.color.gray_6));
                }
                holder.date.setText(Constants.appointmentTimeFormat3.format(appointment.startTime));
                mDays.add(new MyDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)));
            } else {
                holder.dividerLine.setVisibility(GONE);
                holder.date.setText("");
            }
            return convertView;
        }


        private class ViewHolder {
            private View layout;
            private View dividerLine;
            private TextView date;
            private TextView period;
            private TextView calendarName;
            private TextView ordersList;
            private TextView customers;
            private ImageView decline;
            private ImageView checkIn;
        }
    }
}
