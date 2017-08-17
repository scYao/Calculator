package com.shijiu.calculator.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.bean.CalculateBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class CalculatorAdapter extends RecyclerView.Adapter<CalculatorAdapter.ViewHolder> {
    private List<CalculateBean> beanList;
    private Context context;
    private LayoutInflater inflater;

    public CalculatorAdapter(List<CalculateBean> beanList, Context context) {
        this.beanList = beanList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_calculate_detail,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

       CalculateBean bean = beanList.get(position);
        if (bean != null) {
            holder.order_number.setText(bean.getOrder_number());
            holder.total.setText(String.format("%.2f", Double.parseDouble(bean.getTotal())));
            holder.invest.setText(String.format("%.2f", Double.parseDouble(bean.getInvest())));
            holder.rate.setText(String.format("%.2f", Double.parseDouble(bean.getRate())));
        }

    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView order_number;
        TextView total;
        TextView invest;
        TextView rate;


        public ViewHolder(View itemView) {
            super(itemView);

            order_number = itemView.findViewById(R.id.id_order_number);
            total = itemView.findViewById(R.id.id_total);
            invest = itemView.findViewById(R.id.id_invest);
            rate = itemView.findViewById(R.id.id_rate);
        }
    }
}
