package com.ymy.suiyue.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ymy.suiyue.R;
import com.ymy.suiyue.bean.QuestionBean;
import com.ymy.suiyue.util.TimeUtils;
import java.util.List;
/**
 * Created by ymy on 2017/2/24.
 * 发现——问答主界面的适配器
 */
public class Find_Question_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private LayoutInflater inflater;
    private Context context;
    private List<QuestionBean> questionList;

    public Find_Question_Adapter(Context context, List<QuestionBean> questionList) {
        this.context = context;
        this.questionList = questionList;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.question_item,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //Log.e("111111",""+questionList.get(position));
        ViewHolder viewHolder = (ViewHolder) holder;
       // viewHolder.getWorks_body().setOnClickListener(this);

               Glide.with(context)
               .load(questionList.get(position).getUser_info1().getAvatar())
               .into(viewHolder.getQuestion_avatar());

        if(questionList.get(position).getUser_info1().getIndentity_tag()!=null){
            //Log.e("111111",""+questionList.get(position).getUser_info1().getIndentity_tag());
            viewHolder.getQuestion_identity_tag().setText(questionList.get(position).getUser_info1().getIndentity_tag());
        }
        viewHolder.getQuestion_name().setText(questionList.get(position).getUser_info1().getNickname());
        viewHolder.getQuestion_time().setText(TimeUtils.getStandardDate(questionList.get(position).getQ_create_time()));
     if (questionList.get(position).getWorks()!=null){
         if(TextUtils.isEmpty(questionList.get(position).getWorks().getId())){
             viewHolder.getWorks_body().setVisibility(View.GONE);
         }else {

             //Log.e("00000", "" + questionList.get(position).getWorks());
             Glide.with(context)
                     .load(questionList.get(position).getWorks().getCover_photo())
                     .into(viewHolder.getWorks_cover_photo());
             viewHolder.getWorks_tilte().setText(questionList.get(position).getWorks().getTitle());
             viewHolder.getWorks_category().setText(questionList.get(position).getWorks().getCategory());
             viewHolder.getQuestion_like_num().setText(questionList.get(position).getWorks().getLike_num());
             viewHolder.getQuestion_play_num().setText(questionList.get(position).getWorks().getPlay_num());
         }
     }
       viewHolder.getQuestion().setText(questionList.get(position).getQuestion());


        viewHolder.getQuestion_name1().setText(questionList.get(position).getUser_info1().getNickname());
        if(questionList.get(position).getUser_info1().getIndentity_tag()!=null){
            viewHolder.getQuestion_identity_tag1().setText(questionList.get(position).getUser_info1().getIndentity_tag());
        }

        Glide.with(context)
                .load(questionList.get(position).getUser_info2().getAvatar())
                .into(viewHolder.getAnswer_avatar());
        viewHolder.getAnswer_nickname().setText(questionList.get(position).getUser_info2().getNickname());
        viewHolder.getAnswer_identity_tag().setText(questionList.get(position).getUser_info2().getIndentity_tag());
        viewHolder.getAnswer_time().setText(TimeUtils.getStandardDate(questionList.get(position).getA_create_time()));
        viewHolder.getQuestion_listen_num().setText(questionList.get(position).getListen_num());
        viewHolder.getAnswer_audio_long().setText(questionList.get(position).getAudio_long());
        viewHolder.itemView.setTag(questionList.get(position));
        // viewHolder.getWorks_body().setTag(questionList.get(position).getWorks().getId());
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }



    /***
     * 模板类
     */
    public class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView question_avatar,works_cover_photo,answer_avatar;
        TextView answer_audio_long,question_listen_num,answer_time,answer_identity_tag,answer_nickname,question_like_num,question_play_num,question_identity_tag1,question,question_time,
                question_identity_tag,works_tilte,works_category,question_name,question_name1;
        LinearLayout works_body;
        public ViewHolder(View itemView) {

            super(itemView);
            works_body = (LinearLayout) itemView.findViewById(R.id.works_body);//是否显示作品那一块
            question_avatar = (ImageView) itemView.findViewById(R.id.question_avatar);
            works_cover_photo = (ImageView) itemView.findViewById(R.id.works_cover_photo);
            answer_avatar = (ImageView) itemView.findViewById(R.id.answer_avatar);
            answer_audio_long = (TextView) itemView.findViewById(R.id.answer_audio_long);
            question_listen_num = (TextView) itemView.findViewById(R.id.question_listen_num);
            answer_time = (TextView) itemView.findViewById(R.id.answer_time);
            answer_identity_tag = (TextView) itemView.findViewById(R.id.answer_identity_tag);
            answer_nickname = (TextView) itemView.findViewById(R.id.answer_nickname);
            question_like_num = (TextView) itemView.findViewById(R.id.question_like_num);
            question_identity_tag1 = (TextView) itemView.findViewById(R.id.question_identity_tag1);
            question = (TextView) itemView.findViewById(R.id.question);
            question_time = (TextView) itemView.findViewById(R.id.question_time);
            question_identity_tag = (TextView) itemView.findViewById(R.id.question_identity_tag);
            works_tilte = (TextView) itemView.findViewById(R.id.works_tilte);
            works_category = (TextView) itemView.findViewById(R.id.works_category);
            question_name = (TextView) itemView.findViewById(R.id.question_name);
            question_name1 = (TextView) itemView.findViewById(R.id.question_name1);
            question_play_num = (TextView) itemView.findViewById(R.id.question_play_num);

        }

        public LinearLayout getWorks_body() {
            return works_body;
        }

        public TextView getQuestion_name1() {
            return question_name1;
        }

        public ImageView getQuestion_avatar() {
            return question_avatar;
        }

        public ImageView getWorks_cover_photo() {
            return works_cover_photo;
        }

        public ImageView getAnswer_avatar() {
            return answer_avatar;
        }

        public TextView getAnswer_audio_long() {
            return answer_audio_long;
        }

        public TextView getQuestion_listen_num() {
            return question_listen_num;
        }

        public TextView getAnswer_time() {
            return answer_time;
        }

        public TextView getAnswer_identity_tag() {
            return answer_identity_tag;
        }

        public TextView getAnswer_nickname() {
            return answer_nickname;
        }

        public TextView getQuestion_like_num() {
            return question_like_num;
        }

        public TextView getQuestion_play_num() {
            return question_play_num;
        }

        public TextView getQuestion_identity_tag1() {
            return question_identity_tag1;
        }

        public TextView getQuestion() {
            return question;
        }

        public TextView getQuestion_time() {
            return question_time;
        }

        public TextView getQuestion_identity_tag() {
            return question_identity_tag;
        }

        public TextView getWorks_tilte() {
            return works_tilte;
        }

        public TextView getWorks_category() {
            return works_category;
        }

        public TextView getQuestion_name() {
            return question_name;
        }
    }
    /***
     * 下面方法是实现work_body()(作品)的点击事件(需要回调);
     *
     */
    public  interface OnWorkClickListener{
        void onWorkClick(View view,QuestionBean questionBean);
    }
    private OnWorkClickListener workClickListener = null;
    public void setmOnworkClidckListener(OnWorkClickListener workClickListener){
        this.workClickListener = workClickListener;
    }
    @Override
    public void onClick(View v) {
       /* if(workClickListener!=null){
            workClickListener.onWorkClick(v,(QuestionBean) v.getTag());
        }*/
        if(onItemClickListener != null){
            onItemClickListener.onItemClick(v,(QuestionBean) v.getTag());
        }

    }
    /***
     * 下面方法是实现item的点击事件(需要回调);
     *
     */
    public interface OnItemClickListener{
        void onItemClick(View view,QuestionBean questionBean);


    }
    private OnItemClickListener onItemClickListener = null;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

}
