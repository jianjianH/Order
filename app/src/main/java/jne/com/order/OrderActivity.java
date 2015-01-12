package jne.com.order;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import java.util.List;


public class OrderActivity extends Activity {

    private static final String TAG = "OrderActivity";

    private OrderDao ordersDao;

    private TextView showSQLMsg;

    private ListView showDateListView;

    private List<Order> orderList;

    private OrderListAdapter adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ordersDao = new OrderDao(this);
        ordersDao.initTable();

        initComponent();

        orderList = ordersDao.getAllDate();
        if (orderList != null){
            adapter = new OrderListAdapter(this, orderList);
            showDateListView.setAdapter(adapter);
        }
    }

    private void initComponent(){
        Button insertButton = (Button)findViewById(R.id.insertButton);
        Button deleteButton = (Button)findViewById(R.id.deleteButton);
        Button updateButton = (Button)findViewById(R.id.updateButton);
        Button query1Button = (Button)findViewById(R.id.query1Button);
        Button query2Button = (Button)findViewById(R.id.query2Button);
        Button query3Button = (Button)findViewById(R.id.query3Button);

        SQLBtnOnclickListener onclickListener = new SQLBtnOnclickListener();
        insertButton.setOnClickListener(onclickListener);
        deleteButton.setOnClickListener(onclickListener);
        updateButton.setOnClickListener(onclickListener);
        query1Button.setOnClickListener(onclickListener);
        query2Button.setOnClickListener(onclickListener);
        query3Button.setOnClickListener(onclickListener);

        showSQLMsg = (TextView)findViewById(R.id.showSQLMsg);
        showDateListView = (ListView)findViewById(R.id.showDateListView);
        showDateListView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.show_sql_item, null), null, false);
    }

    public class SQLBtnOnclickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.insertButton:
                    showSQLMsg.setText("新增一条数据：\n添加数据(7, \"Jne\", 700, \"China\")\ninsert into Orders(Id, CustomName, OrderPrice, Country) values (7, \"Jne\", 700, \"China\")");
                    if (ordersDao.insertDate()){
                        orderList.add(new Order(7, "Jne", 700, "China"));
                        adapter.notifyDataSetChanged();
                    }

                    break;

                case R.id.deleteButton:
                    showSQLMsg.setText("删除一条数据：\n删除Id为7的数据\ndelete from Orders where Id = 7");
                    ordersDao.deleteOrder();

                    for (Order order : orderList){
                        if (order.id == 7){
                            orderList.remove(order);
                        }
                    }
                    adapter.notifyDataSetChanged();
                    break;

                case R.id.updateButton:
                    showSQLMsg.setText("修改一条数据：\n将Id为6的数据的OrderPrice修改了800\nupdate Orders set OrderPrice = 800 where Id = 6");
                    ordersDao.updateOrder();

                    for (Order order : orderList){
                        if (order.id == 6){
                            order.orderPrice = 800;
                        }
                    }
                    adapter.notifyDataSetChanged();
                    break;

                case R.id.query1Button:
                    StringBuilder msg = new StringBuilder();
                    msg.append("数据查询：\n此处将用户名为\"Bor\"的信息提取出来\nselect * from Orders where CustomName = 'Bor'");
                    List<Order> borOrders = ordersDao.getBorOrder();
                    for (Order order : borOrders){
                        msg.append("\n(" + order.id + ", " + order.customName + ", " + order.orderPrice + ", " + order.country + ")");
                    }
                    showSQLMsg.setText(msg);
                    break;

                case R.id.query2Button:
                    int chinaCount = ordersDao.getChinaCount();
                    showSQLMsg.setText("统计查询：\n此处查询Country为China的用户总数\nselect count(Id) from Orders where Country = 'China'\ncount = " + chinaCount);
                    break;

                case R.id.query3Button:
                    StringBuilder msg2 = new StringBuilder();
                    msg2.append("比较查询：\n此处查询单笔数据中OrderPrice最高的\nselect Id, CustomName, Max(OrderPrice) as OrderPrice, Country from Orders");
                    Order order = ordersDao.getMaxOrderPrice();
                    msg2.append("\n(" + order.id + ", " + order.customName + ", " + order.orderPrice + ", " + order.country + ")");
                    showSQLMsg.setText(msg2);
                    break;

                default:

                    break;
            }
        }
    }
}
