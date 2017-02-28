package com.ymy.suiyue.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.ymy.suiyue.R;
import com.ymy.suiyue.bean.FindMusicClassification;
import com.ymy.suiyue.bean.WorkListOne;
import com.ymy.suiyue.util.GlideRoundTransform;

import java.util.List;

/**
 * Created by ymy on 2017/2/21.
 * 歌曲榜的适配器
 */

public class Find_WorkListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private LayoutInflater inflater;
    private Context context;
    private List<WorkListOne> listWorkListOnes;
    public static final int TYPE1 = 0;
    public static final int TYPE2 = 1;  //这个是尾布局标识
    private RequestManager glideRequest;

    //
    public Find_WorkListAdapter(List<WorkListOne> listWorkListOnes, Context context) {
        this.listWorkListOnes = listWorkListOnes;
        this.context = context;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position) {
        switch (listWorkListOnes.get(position).getType()){
            case  0:
                return TYPE1;
            case 1:
                return TYPE2;


        }
        return  TYPE1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     View view = null;
        switch (viewType){
            case TYPE1:
                view = inflater.inflate(R.layout.worklist_item,null);
                view.setOnClickListener(this);
                break;
            case  TYPE2:
                view = inflater.inflate(R.layout.find_worklist_foot,null);
                view.setOnClickListener(this);
                break;
        }
        return new ViewHolder(view,viewType);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        glideRequest = Glide.with(context);
        int type = getItemViewType(position);
        switch (type){
            case TYPE1:
                //Log.i("dsddd",""+listWorkListOnes.size()+"-------"+position);
                if(position == 0){
                    viewHolder.getworklist_item_Rank().setBackgroundColor(Color.RED);

                    //Log.i("pppp",""+position);
                }
                viewHolder.getworklist_item_NM().setText(listWorkListOnes.get(position).getNickname());
                viewHolder.getworklist_item_Text().setText(listWorkListOnes.get(position).getTitle());
                viewHolder.getworklist_item_Rank().setText("No." + (position + 1));//设置名次
                glideRequest.load(listWorkListOnes.get(position).getCover_photo()).transform(new GlideRoundTransform(context, 5)).into(((ViewHolder) holder).getworklist_item_IV());
                //这有个问题，不设置的话颜色为红色
                if(position == 7){
                    viewHolder.getworklist_item_Rank().setBackgroundColor(Color.argb(50,80,50,50));
                }
                break;
            case TYPE2:
                break;
        }




            viewHolder.itemView.setTag(listWorkListOnes.get(position));


    }

    @Override
    public int getItemCount() {

            return listWorkListOnes.size();


    }
    /***
     * 模板类
     */
    public class ViewHolder extends  RecyclerView.ViewHolder{
        int viewType;
        ImageView worklist_item_IV;
        TextView worklist_item_Rank,worklist_item_Text,worklist_item_NM;

        public ViewHolder(View itemView,int viewType) {

            super(itemView);
            this.viewType = viewType;
            switch (viewType){
                case  TYPE1:
                worklist_item_IV = (ImageView) itemView.findViewById(R.id.worklist_item_IV);
                worklist_item_Rank = (TextView) itemView.findViewById(R.id.worklist_item_Rank);
                worklist_item_Text = (TextView) itemView.findViewById(R.id.worklist_item_Text);

                worklist_item_NM = (TextView) itemView.findViewById(R.id.worklist_item_NM);
                    break;
                case  TYPE2:
                    break;

            }


        }
        public ImageView getworklist_item_IV() {
            return worklist_item_IV;
        }
        public TextView getworklist_item_Rank() {
            return worklist_item_Rank;
        }
        public TextView getworklist_item_Text() {
            return worklist_item_Text;
        }
        public TextView getworklist_item_NM() {
            return worklist_item_NM;
        }


    }
    /***
     * 下面方法实现RecyclerView的点击事件;
     */
    public interface OnRecyclerViewItemClickListener{
        void onItemClick(View view,WorkListOne workListOne);
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setmOnitemClickListener(OnRecyclerViewItemClickListener listener){
        this.mOnItemClickListener = listener;
    }
    @Override
    public void onClick(View v) {
        if(mOnItemClickListener!= null){
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (WorkListOne)v.getTag());
        }
    }
}
