package com.ymy.suiyue.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ymy.suiyue.R;
import com.ymy.suiyue.bean.FindMusicClassification;
import com.ymy.suiyue.bean.WorkListOne;

import java.util.List;

/**
 * Created by ymy on 2017/2/21.
 * 歌曲榜的适配器
 */

public class Find_WorkListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<WorkListOne> listWorkListOnes;
    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的
    private View mFooterView;

    public View getmFooterView() {
        return mFooterView;
    }

    public void setmFooterView(View mFooterView) {
        this.mFooterView = mFooterView;
        notifyItemInserted(getItemCount()-1);
    }

    //
    /** 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    * */
    @Override
    public int getItemViewType(int position) {
        if(position==getItemCount()-1){
            return  TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    public Find_WorkListAdapter(List<WorkListOne> listWorkListOnes, Context context) {
        this.listWorkListOnes = listWorkListOnes;
        this.context = context;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //创建View，如果是HeaderView或者是FooterView，直接在Holder中返回
        if(mFooterView != null && viewType == TYPE_FOOTER){
            return new ViewHolder(mFooterView);
        }
        //第一步：通过布局加载器去获取那个布局
        View view = inflater.inflate(R.layout.worklist_item,null);

        return new Find_WorkListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_NORMAL){
            if(holder instanceof ViewHolder) {
                //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了(这里没有header)
                ((ViewHolder) holder).getworklist_item_NM().setText(listWorkListOnes.get(position).getNickname());
                ((ViewHolder) holder).getworklist_item_Text().setText(listWorkListOnes.get(position).getTitle());
                ((ViewHolder) holder).getworklist_item_Rank().setText("No." + (position + 1));//设置名次
                Glide.with(context)
                        .load(listWorkListOnes.get(position).getCover_photo())
                        .into(((ViewHolder) holder).getworklist_item_IV());
                return;
            }
            return;
        }else if(getItemViewType(position) == TYPE_FOOTER){
            return;
        }

    }
        /*Find_WorkListAdapter.ViewHolder viewHolder = (Find_WorkListAdapter.ViewHolder) holder;
        Log.e("----",""+listWorkListOnes.size()+"sssss"+position);*/


        /*Glide.with(context)
                .load(R.mipmap.ic_launcher)
                .into(viewHolder.gethot_MCIV());*/
            // Log.e("2222",""+findMusicCfs.get(position).getCover_photo());


    //返回View中Item的个数，这个时候，总的个数应该是ListView中Item的个数加上HeaderView和FooterView
    @Override
    public int getItemCount() {
        if (mFooterView != null) {
            return listWorkListOnes.size() + 1;
        }else {
            return listWorkListOnes.size();
        }

    }
    /***
     * 模板类
     */
    public class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView worklist_item_IV;
        TextView worklist_item_Rank,worklist_item_Text,worklist_item_NM;

        public ViewHolder(View itemView) {

            super(itemView);
            if (itemView == mFooterView){
                return;
            }
            worklist_item_IV = (ImageView) itemView.findViewById(R.id.worklist_item_IV);
            worklist_item_Rank = (TextView) itemView.findViewById(R.id.worklist_item_Rank);
            worklist_item_Text = (TextView) itemView.findViewById(R.id.worklist_item_Text);
            worklist_item_NM = (TextView) itemView.findViewById(R.id.worklist_item_NM);

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
}
