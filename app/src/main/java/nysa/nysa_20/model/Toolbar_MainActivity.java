package nysa.nysa_20.model;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.Toast;

import nysa.nysa_20.R;
import nysa.nysa_20.activity.AccountSettingsActivity;
import nysa.nysa_20.activity.ChatBotActivity;
import nysa.nysa_20.activity.LibraryActivity;
import nysa.nysa_20.activity.HomeActivity;
import nysa.nysa_20.activity.MainActivity;
import nysa.nysa_20.activity.SymptomTrackActivity;
import nysa.nysa_20.service.utilitary.ActivityShiftService;

public class Toolbar_MainActivity extends ConstraintLayout {
    private Context context;
    private Activity activity;
    private LayoutInflater layoutInflater;
    public ImageButton homeButton;
    public ImageButton accountButton;
    public ImageButton chatBotButton;
    public ImageButton libraryButton;
    public ImageButton historyButton;

    public Toolbar_MainActivity(Context mContext, AttributeSet attrs) {
        super(mContext, attrs);
        context = mContext;
        activity = (Activity) context;
        Inflate();
        BindViews();
        ToolBarSetup();
    }

    private void Inflate() {
        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.tool_bar, this, true);
    }

    private void BindViews() {
        homeButton =  findViewById(R.id.homeButton);
        accountButton = findViewById(R.id.accountButton);
        chatBotButton =  findViewById(R.id.chatBotButton);
        libraryButton =  findViewById(R.id.libraryButton);
        historyButton =  findViewById(R.id.historyButton);
    }

    private void ToolBarSetup (){

        homeButton.setImageResource(R.drawable.home);

        homeButton.setOnClickListener(ev -> MainActivity.setCurrentTab(0));

        accountButton.setOnClickListener(ev -> MainActivity.setCurrentTab(1));

        libraryButton.setOnClickListener(ev -> MainActivity.setCurrentTab(2));

        historyButton.setOnClickListener(ev -> MainActivity.setCurrentTab(3));

        chatBotButton.setOnClickListener(ev -> ActivityShiftService.toChatBotActivity(activity));

    }

    public void updateToolbarImageResorces(int position){

        switch (position){
            case 0:  homeButton.setImageResource(R.drawable.home);
                     accountButton.setImageResource(R.drawable.noperson);
                     libraryButton.setImageResource(R.drawable.nomenu);
                     historyButton.setImageResource(R.drawable.nograph);

                     return;

            case 1:  homeButton.setImageResource(R.drawable.nohome);
                     accountButton.setImageResource(R.drawable.person);
                     libraryButton.setImageResource(R.drawable.nomenu);
                     historyButton.setImageResource(R.drawable.nograph);
                     return;

            case 2:  homeButton.setImageResource(R.drawable.nohome);
                     accountButton.setImageResource(R.drawable.noperson);
                     libraryButton.setImageResource(R.drawable.menu);
                     historyButton.setImageResource(R.drawable.nograph);
                     return;
            case 3:  homeButton.setImageResource(R.drawable.nohome);
                     accountButton.setImageResource(R.drawable.noperson);
                     libraryButton.setImageResource(R.drawable.nomenu);
                     historyButton.setImageResource(R.drawable.graph);
                     return;


        }
    }
}
