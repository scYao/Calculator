package com.shijiu.calculator.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.bean.LoanBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/29 0029.
 */

public class PpDaiAdapter extends RecyclerView.Adapter<PpDaiAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private LayoutInflater inflater;
    private List<LoanBean> beanList;
    private onItemClickListener onItemClickListener;

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null){
            onItemClickListener.onItemClick(view, (int) view.getTag());
        }

    }

    public interface onItemClickListener{
        void onItemClick(View view , int position);
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public PpDaiAdapter(Context context, List<LoanBean> beanList) {
        this.context = context;
        this.beanList = beanList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_loan, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LoanBean bean = beanList.get(position);
        if (bean != null) {
            holder.imageView.setImageResource(bean.getImagePath());
            holder.name.setText(bean.getName());
        }

        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.id_loan_image);
            name = itemView.findViewById(R.id.id_loan_name);
        }
    }

}
