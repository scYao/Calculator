package com.shijiu.calculator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shijiu.calculator.R;
import com.shijiu.calculator.bean.GridBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class GridAdapter extends BaseAdapter {
    private Context context;
    private List<GridBean> beanList;
    private LayoutInflater inflater;

    public GridAdapter(Context context, List<GridBean> beanList) {
        this.context = context;
        this.beanList = beanList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int i) {
        return beanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        GridHoldr gridHoldr;
        if (view == null){
            gridHoldr = new GridHoldr();
            view = inflater.inflate(R.layout.gird_item,viewGroup,false);
            gridHoldr.imageView = view.findViewById(R.id.id_grid_image);
            gridHoldr.name = view.findViewById(R.id.id_grid_text1);
            gridHoldr.content = view.findViewById(R.id.id_grid_text2);
            view.setTag(gridHoldr);
        }else {
            gridHoldr = (GridHoldr) view.getTag();
        }

        GridBean bean = beanList.get(i);
        if (bean != null) {
            gridHoldr.imageView.setImageResource(bean.getImage());
            gridHoldr.name.setText(bean.getName());
            gridHoldr.content.setText(bean.getContent());
        }
        return view;
    }

    class GridHoldr {
        ImageView imageView;
        TextView name;
        TextView content;

    }
}
