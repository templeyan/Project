package com.ymy.suiyue.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ymy.suiyue.R;
import com.ymy.suiyue.bean.FindPeopleIF;
import com.ymy.suiyue.constants.InterfaceUri;

import java.util.List;

/**
 * find——hot页面前三个数据的适配器
 */


public class Find_hot_Adapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
      private LayoutInflater inflater;
    private Context context;
    private List<FindPeopleIF> findPeopleIFs;
    private String flag;

    public Find_hot_Adapter1(List<FindPeopleIF> findPeopleIFs, Context context,String flag) {
        this.findPeopleIFs = findPeopleIFs;
        this.context = context;
        this.flag =flag;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //第一步：通过布局加载器去获取那个布局
        View view = inflater.inflate(R.layout.fragment_hot_item1,null);



        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.gethot_item_name().setText(findPeopleIFs.get(position).getNickname());
        if (flag.equals(InterfaceUri.find_hot_screamlist3)){
            viewHolder.gethot_item_identity().setText(findPeopleIFs.get(position).getScore());
        }else {
            viewHolder.gethot_item_identity().setText(findPeopleIFs.get(position).getIdentity());
        }
        if(flag.equals(InterfaceUri.find_hot_musicstar6)||flag.equals(InterfaceUri.find_hot_newmusic6)){
            viewHolder.gethot_tvrank().setVisibility(View.GONE);
        }
        Glide.with(context)
                .load(findPeopleIFs.get(position).getAvatar())
                .into(viewHolder.gethot_item_iv());
    }

    @Override
    public int getItemCount() {
        if(flag.equals(InterfaceUri.find_hot_musicstar6)){
            return 6;
        }
        return findPeopleIFs.size();
    }
    /***
     * 模板类
     */
    public class ViewHolder extends  RecyclerView.ViewHolder{
       ImageView hot_item_iv;
        TextView hot_item_name,hot_item_identity,hot_tvrank;

        public ViewHolder(View itemView) {

            super(itemView);
            hot_item_iv = (ImageView) itemView.findViewById(R.id.hot_item_iv);
            hot_item_name = (TextView) itemView.findViewById(R.id.hot_item_name);
            hot_tvrank = (TextView) itemView.findViewById(R.id.hot_tvrank);
            hot_item_identity = (TextView) itemView.findViewById(R.id.hot_item_identity);
        }
        public ImageView gethot_item_iv() {
                return hot_item_iv;
            }
        public TextView gethot_item_name() {
            return hot_item_name;
        }
        public TextView gethot_item_identity() {
            return hot_item_identity;
        }
        public TextView gethot_tvrank() {
            return hot_tvrank;
        }

    }
}
