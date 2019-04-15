package nysa.nysa_20.model.adaptors;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import nysa.nysa_20.R;
import nysa.nysa_20.model.Messages;

public class ChatBotActiviytMessageAdaptor extends RecyclerView.Adapter<ChatBotActiviytMessageAdaptor.MessageViewHolder>{

    private List<Messages> mMessageList;
    public ChatBotActiviytMessageAdaptor(List<Messages> mMessageList){
        this.mMessageList=mMessageList;

    }

    @NonNull
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_single_layout_me,parent,false);
        return new MessageViewHolder(v);
    }

    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Messages s = mMessageList.get(position);
        String from = s.getFrom();
        if(from.equals("user")){
            holder.messageText.setBackgroundResource(R.drawable.message_text_background_me);
            holder.messageText.setTextColor(Color.BLACK);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
            params.weight = 1.0f;
            params.gravity = Gravity.RIGHT;
            holder.messageText.setLayoutParams(params);
            holder.container.setPadding(240, 8, 16, 8);

        }
        else
        {
            holder.messageText.setBackgroundResource(R.drawable.message_text_background_bot);
            holder.messageText.setTextColor(Color.WHITE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
            params.weight = 1.0f;
            params.gravity = Gravity.LEFT;
            holder.messageText.setLayoutParams(params);
            holder.container.setPadding(16, 8, 240, 8);
        }
        holder.messageText.setText(s.getMessage());

    }

    public int getItemCount() {
        return mMessageList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView messageText;
        public TextView from;
        public LinearLayout container;
        public MessageViewHolder(View view){
            super(view);
            container = (LinearLayout) view.findViewById(R.id.containerMessage);
            messageText = (TextView) view.findViewById(R.id.message_profile_layout_me);
            container = (LinearLayout) view.findViewById(R.id.containerMessage);
        }
    }
}
