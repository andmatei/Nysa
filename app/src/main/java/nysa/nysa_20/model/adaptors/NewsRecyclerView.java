package nysa.nysa_20.model.adaptors;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nysa.nysa_20.R;
import nysa.nysa_20.service.connectivity.LocationService;


public class NewsRecyclerView extends RecyclerView.Adapter<NewsRecyclerView.ViewHolder> {


    public Context mContext;
    public JSONArray mData = new JSONArray();

    public NewsRecyclerView(Context mContext) {
        this.mContext = mContext;
       //TODO: get mDATA
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            final JSONObject positionData = mData.getJSONObject(position);
            holder.TagName.setText(positionData.getString("tagname"));
            holder.Title.setText(positionData.getString("title"));
            holder.Description.setText(positionData.getString("description"));
           /* holder.ReadBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent i = new Intent(mContext, com.nysa.Article.class);
                        i.putExtra("article_data", positionData.getString("article_data"));
                        mContext.startActivity(i);
                    } catch (JSONException e) {
                        Toast.makeText(mContext, "Couldn't load article...", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            });*/
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mData.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TagName;
        TextView Title;
        TextView Description;
        Button ReadBtn;
        ConstraintLayout Container;

        public ViewHolder(View itemView) {
            super(itemView);
            Container = itemView.findViewById(R.id.newsContainer);
            TagName = itemView.findViewById(R.id.tagName);
            Title= itemView.findViewById(R.id.newsTitle);
            Description = itemView.findViewById(R.id.newsDescription);
            ReadBtn = itemView.findViewById(R.id.readMore);
        }
    }
}
