package com.nysa;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ArticleRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context mContext;
    private JSONArray mData;

    public ArticleRecyclerView(Context mContext, JSONArray mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getItemViewType(int position) {
        String Type = null;
        try {
            Type = mData.getJSONObject(position).getString("type");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(Type.toLowerCase().equals("heading")) {
            return 0;
        } else if(Type.toLowerCase().equals("paragraph")) {
            return 1;
        }
        return 2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0: return new HeadingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.article_heading, parent, false));
            case 1: return new ParagraphViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.article_paragraph, parent, false));
            case 2: return new ListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        try {
            JSONObject positionData = mData.getJSONObject(position);
            switch (holder.getItemViewType()) {
                case 0:
                    HeadingViewHolder headingViewHolder = (HeadingViewHolder) holder;
                    headingViewHolder.Title.setText(positionData.getString("title"));
                    headingViewHolder.TagName.setText(positionData.getString("tagname"));
                    headingViewHolder.Posted.setText(positionData.getString("posted"));
                    String mAuthor = "By <b>" + positionData.getString("author") + "</b>";
                    headingViewHolder.Authour.setText(Html.fromHtml(mAuthor));
                    headingViewHolder.ExitArticle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Activity activity = (Activity) mContext;
                            activity.finish();
                        }
                    });
                    break;
                case 1:
                    ParagraphViewHolder paragraphViewHolder = (ParagraphViewHolder) holder;
                    paragraphViewHolder.Paragraph.setText(Html.fromHtml(Html.fromHtml(positionData.getString("paragraph")).toString()));
                    paragraphViewHolder.Subtitle.setText(positionData.getString("subtitle"));
                    if(paragraphViewHolder.Subtitle.getText().equals("")) {
                        paragraphViewHolder.Underline.setVisibility(View.GONE);
                        paragraphViewHolder.Subtitle.setVisibility(View.GONE);
                    }
                    break;
                case 2:
                    ListViewHolder listViewHolder = (ListViewHolder) holder;
                    listViewHolder.Title.setText(positionData.getString("title"));
                    if(listViewHolder.Title.getText().equals("")) {
                        listViewHolder.Title.setVisibility(View.GONE);
                    }
                    listViewHolder.List.setText(Html.fromHtml(Html.fromHtml(positionData.getString("list")).toString()));
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mData.length();
    }

    class HeadingViewHolder extends RecyclerView.ViewHolder {

        public TextView TagName;
        public TextView Title;
        public TextView Authour;
        public TextView Posted;
        public ImageButton ExitArticle;


        public HeadingViewHolder(View itemView) {
            super(itemView);
            TagName = (TextView) itemView.findViewById(R.id.TagnameNews);
            Title = (TextView) itemView.findViewById(R.id.titleTextView);
            Authour = (TextView) itemView.findViewById(R.id.authorTextView);
            Posted = (TextView) itemView.findViewById(R.id.postedTextView);
            ExitArticle = (ImageButton) itemView.findViewById(R.id.exitArticle);
        }
    }

    class ParagraphViewHolder extends RecyclerView.ViewHolder {

        public TextView Subtitle;
        public TextView Paragraph;
        public ImageView Underline;

        public ParagraphViewHolder(View itemView) {
            super(itemView);
            Subtitle = (TextView) itemView.findViewById(R.id.SubtitleTextView);
            Paragraph = (TextView) itemView.findViewById(R.id.ParagraphTextView);
            Underline = (ImageView) itemView.findViewById(R.id.SubtitleUnderline);
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        public TextView Title;
        public TextView List;

        public ListViewHolder(View itemView) {
            super(itemView);
            Title = (TextView) itemView.findViewById(R.id.listTitle);
            List = (TextView) itemView.findViewById(R.id.listItem);
        }
    }

}
