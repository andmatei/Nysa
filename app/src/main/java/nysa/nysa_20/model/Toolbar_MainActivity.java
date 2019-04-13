package nysa.nysa_20.model;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;

import nysa.nysa_20.R;
import nysa.nysa_20.activity.MainActivity;
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
        layoutInflater.inflate(R.layout.toolbar_main_activity, this, true);
    }

    private void BindViews() {
        homeButton =  findViewById(R.id.eyeSymptomsButton);
        accountButton = findViewById(R.id.painSymptomsButton);
        chatBotButton =  findViewById(R.id.pain);
        libraryButton =  findViewById(R.id.respiratorySymptomsButton);
        historyButton =  findViewById(R.id.skinSymptomsButton);
    }

    private void ToolBarSetup (){

        homeButton.setImageResource(R.drawable.home);

        homeButton.setOnClickListener(ev -> MainActivity.setCurrentTab(0));

        accountButton.setOnClickListener(ev -> MainActivity.setCurrentTab(1));

        libraryButton.setOnClickListener(ev -> MainActivity.setCurrentTab(2));

        historyButton.setOnClickListener(ev -> MainActivity.setCurrentTab(3));

        chatBotButton.setOnClickListener(ev -> ActivityShiftService.toChatBotActivity(activity));

    }

    public void updateToolbarImageResources(int position){

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
