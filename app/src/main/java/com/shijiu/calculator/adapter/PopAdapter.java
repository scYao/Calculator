package com.shijiu.calculator.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.bean.UnitBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class PopAdapter  extends RecyclerView.Adapter<PopAdapter.ViewHolder> implements View.OnClickListener {
    private List<UnitBean> beanList;
    private Context context;
    private LayoutInflater inflater;

    private OnItemClickListener mOnItemClickListener = null;
    private static final String TAG = "PopAdapter";

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view,(int)view.getTag());
        }
    }

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public PopAdapter(List<UnitBean> beanList, Context context) {
        this.beanList = beanList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_unit,null);
        ViewHolder viewHolder =new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
//        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UnitBean bean = beanList.get(position);
        holder.unit.setText(bean.getUnit());
        if (bean.getImgae() != null){
            Log.e(TAG, "onBindViewHolder: "+bean.toString());
            holder.imageView.setImageResource(bean.getImgae());
        }
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView unit;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            unit = itemView.findViewById(R.id.id_unit);
            imageView = itemView.findViewById(R.id.id_unit_tick);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    imageView.setImageResource(R.mipmap.tick);
//                }
//            });
        }
    }
}
