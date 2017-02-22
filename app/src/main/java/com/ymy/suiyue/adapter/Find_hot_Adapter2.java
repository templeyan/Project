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
import com.ymy.suiyue.bean.FindPeopleIF;

import java.util.List;

/**
 * Created by ymy on 2017/2/21.
 */

public class Find_hot_Adapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
      private LayoutInflater inflater;
    private Context context;
    private List<FindMusicClassification> findMusicCfs;

    public Find_hot_Adapter2(List<FindMusicClassification> findMusicCfs, Context context) {
        this.findMusicCfs = findMusicCfs;
        this.context = context;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //第一步：通过布局加载器去获取那个布局
        View view = inflater.inflate(R.layout.fragment_hot_item2,null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
         ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.gethot_MCNM().setText(findMusicCfs.get(position).getHashtag());
        viewHolder.gethot_MCNM().getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        viewHolder.gethot_MCNum().setText(findMusicCfs.get(position).getTotal_play());
        Glide.with(context)
                .load(findMusicCfs.get(position).getCover_photo())
                .into(viewHolder.gethot_MCIV());
        /*Glide.with(context)
                .load(R.mipmap.ic_launcher)
                .into(viewHolder.gethot_MCIV());*/
       // Log.e("2222",""+findMusicCfs.get(position).getCover_photo());
    }

    @Override
    public int getItemCount() {
        return 6;
    }
    /***
     * 模板类
     */
    public class ViewHolder extends  RecyclerView.ViewHolder{
       ImageView hot_MCIV;
        TextView hot_MCNM,hot_MCNum;

        public ViewHolder(View itemView) {

            super(itemView);
            hot_MCIV = (ImageView) itemView.findViewById(R.id.hot_MCIV);
            hot_MCNM = (TextView) itemView.findViewById(R.id.hot_MCNM);
            hot_MCNum = (TextView) itemView.findViewById(R.id.hot_MCNum);
        }
        public ImageView gethot_MCIV() {
                return hot_MCIV;
            }
        public TextView gethot_MCNM() {
            return hot_MCNM;
        }
        public TextView gethot_MCNum() {
            return hot_MCNum;
        }

    }
}
