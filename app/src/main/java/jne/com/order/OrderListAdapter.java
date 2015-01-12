package jne.com.order;

import android.content.Context;
import android.view.*;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Jne
 * Date: 2015/1/11.
 */
public class OrderListAdapter extends BaseAdapter{
    private Context context;
    private List<Order> orderList;

    public OrderListAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Order order = orderList.get(position);
        if (order == null){
            return null;
        }

        ViewHolder holder = null;
        if (view != null){
            holder = (ViewHolder) view.getTag();
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.show_sql_item, null);

            holder = new ViewHolder();
            holder.dateIdTextView = (TextView) view.findViewById(R.id.dateIdTextView);
            holder.dateCustomTextView = (TextView) view.findViewById(R.id.dateCustomTextView);
            holder.dateOrderPriceTextView = (TextView) view.findViewById(R.id.dateOrderPriceTextView);
            holder.dateCountoryTextView = (TextView) view.findViewById(R.id.dateCountoryTextView);

            view.setTag(holder);
        }

        holder.dateIdTextView.setText(order.id + "");
        holder.dateCustomTextView.setText(order.customName);
        holder.dateOrderPriceTextView.setText(order.orderPrice + "");
        holder.dateCountoryTextView.setText(order.country);

        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public static class ViewHolder{
        public TextView dateIdTextView;
        public TextView dateCustomTextView;
        public TextView dateOrderPriceTextView;
        public TextView dateCountoryTextView;
    }
}
