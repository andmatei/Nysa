package nysa.nysa_20.model;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;

import nysa.nysa_20.R;
import nysa.nysa_20.activity.AccountSettingsActivity;
import nysa.nysa_20.activity.ChatBotActivity;
import nysa.nysa_20.activity.LibraryActivity;
import nysa.nysa_20.activity.MainActivity;
import nysa.nysa_20.activity.SymptomTrackActivity;
import nysa.nysa_20.service.utilitary.ActivityShiftService;
import nysa.nysa_20.service.utilitary.PermissionService;

public class Tool_bar extends ConstraintLayout {
    private Context context;
    private Activity activity;
    private LayoutInflater layoutInflater;
    public ImageButton HomeBtn;
    public ImageButton PersonBtn;
    public ImageButton AddBtn;
    public ImageButton LibraryBtn;
    public ImageButton GraphBtn;

    public Tool_bar(Context mContext, AttributeSet attrs) {
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
        HomeBtn = (ImageButton) findViewById(R.id.homeButton);
        PersonBtn = (ImageButton) findViewById(R.id.accountButton);
        AddBtn = (ImageButton) findViewById(R.id.chatBotButton);
        LibraryBtn = (ImageButton) findViewById(R.id.libraryButton);
        GraphBtn = (ImageButton) findViewById(R.id.historyButton);
    }

    private void ToolBarSetup (){
        if(activity instanceof MainActivity) {
            HomeBtn.setImageResource(R.drawable.home);
        } else if(activity instanceof  LibraryActivity) {
            LibraryBtn.setImageResource(R.drawable.menu);
        } else if(activity instanceof AccountSettingsActivity) {
            PersonBtn.setImageResource(R.drawable.person);
        } else{
            GraphBtn.setImageResource(R.drawable.graph);
        }
        if(!(activity instanceof MainActivity)) {
            HomeBtn.setOnClickListener(ev -> ActivityShiftService.toMainActivity(activity));
        }
        if(!(activity instanceof AccountSettingsActivity)) {
            PersonBtn.setOnClickListener(ev -> ActivityShiftService.toAccountSettingsActivity(activity));
        }

        if(!(activity instanceof LibraryActivity)) {
            LibraryBtn.setOnClickListener(ev -> ActivityShiftService.toLibraryActivity(activity));
        }
        if(!(activity instanceof SymptomTrackActivity)) {
            GraphBtn.setOnClickListener(ev -> ActivityShiftService.toSymptomTrackActivity(activity));
        }
        if(!(activity instanceof ChatBotActivity)) {
            AddBtn.setOnClickListener(ev -> ActivityShiftService.toChatBotActivity(activity));
        }
    }
}
