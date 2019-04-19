package nysa.nysa_20.model.main_activity_fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nysa.nysa_20.R;
import nysa.nysa_20.activity.MainActivity;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.Symptom;
import nysa.nysa_20.model.SymptomEntry;
import nysa.nysa_20.service.utilitary.SymptomEntryService;
import nysa.nysa_20.service.utilitary.TimeUtilitaryClass;

public class FragmentSymptomTrack extends Fragment {
    public FragmentSymptomTrack(){}
    private static View view;
    private static CompactCalendarView calendarView;
    private static TextView monthTextView;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM - YYYY");
    private static Map<LocalDate, SymptomEntry> map = AccountHolder.getAccount().getHistoryMap();
    private static TextView discomfortLevelTextView;
    private static TextView eyesightTextView;
    private static TextView respirationTextView;
    private static TextView painTextView;
    private static TextView skinTextView;
    private static ConstraintLayout selectedDataLayout;
    private static TextView eyesightDeclarationTextView;
    private static TextView respirationDeclarationTextView;
    private static TextView painDeclarationTextView;
    private static TextView skinDeclarationTextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_symptom_track, container, false);

        initComponents();
        setupPieChart();
        return view;

    }

    private void initComponents() {

        initComponentsReferences();

        prepareCalendar();
        setupPieChart();
    }

    private void initComponentsReferences() {
        calendarView = view.findViewById(R.id.calendarView);
        monthTextView = view.findViewById(R.id.monthTextView);
        discomfortLevelTextView = view.findViewById(R.id.discomfortLevelSelectedTextView);
        eyesightTextView = view.findViewById(R.id.eyesightSymptomsTextView_Tracker);
        respirationTextView = view.findViewById(R.id.respirationSymptomsTextView_Tracker);
        painTextView = view.findViewById(R.id.painSymptomsTextView_Tracker);
        skinTextView = view.findViewById(R.id.skinSymptomsTextView_Tracker);
        selectedDataLayout = view.findViewById(R.id.selectedDate_data);
        eyesightDeclarationTextView = view.findViewById(R.id.eyesightDeclarationTextView_Tracker);
        respirationDeclarationTextView = view.findViewById(R.id.respirationDeclarationTextView_Tracker);
        painDeclarationTextView = view.findViewById(R.id.painDeclarationTextView_Tracker);
        skinDeclarationTextView = view.findViewById(R.id.skinDeclarationTextView_Tracker);
    }

    private void prepareCalendar() {




        calendarView.setUseThreeLetterAbbreviation(true);
        monthTextView.setText(TimeUtilitaryClass.getCurrentMonthYearFormat());


        for(Map.Entry<LocalDate, SymptomEntry> entry : map.entrySet()){
            long epochDay =entry.getKey().toEpochDay()*86400000;
            int scoreDay = SymptomEntryService.getScore(entry.getKey());
            Event event = new Event(Color.RED,epochDay,scoreDay);
            calendarView.addEvent(event);

        }


        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                takeActionOnDayClicked(dateClicked);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                monthTextView.setText(dateFormat.format(firstDayOfNewMonth));
            }
        });
    }

    private static void takeActionOnDayClicked(Date date){
        LocalDate localDate = TimeUtilitaryClass.convertToLocalDate(date);
        if(map.containsKey(localDate)){
              selectedDataLayout.setVisibility(View.VISIBLE);
              SymptomEntry entry = map.get(localDate);

              int discomfortLevel = SymptomEntryService.getScore(entry);
              discomfortLevelTextView.setText(String.valueOf(discomfortLevel));

              prepareSightSymptomsAndDeclaration(entry);
              prepareRespirationSymptomsAndDeclaration(entry);
              preparePainSymptomsAndDeclaration(entry);
              prepareSkinSymptomsAndDeclaration(entry);
        }
        else{
            selectedDataLayout.setVisibility(View.GONE);
        }
    }

    private static void prepareSightSymptomsAndDeclaration(SymptomEntry entry){
        eyesightTextView.setText(SymptomEntryService.getEyesightSymptoms(entry));
        String eyesightDeclaration = SymptomEntryService.getEyesightDeclaration(entry);
        eyesightDeclarationTextView.setText("Declaration : "+eyesightDeclaration);

    }
    private static void prepareRespirationSymptomsAndDeclaration(SymptomEntry entry){
        respirationTextView.setText(SymptomEntryService.getRespirationSymptoms(entry));
        String respirationDeclaration = SymptomEntryService.getRespirationDeclaration(entry);
        respirationDeclarationTextView.setText("Declaration : "+respirationDeclaration);

    }

    private static void preparePainSymptomsAndDeclaration(SymptomEntry entry){
        painTextView.setText(SymptomEntryService.getPainSymptoms(entry));
        String painDeclaration = SymptomEntryService.getPainDeclaration(entry);
        painDeclarationTextView.setText("Declaration : "+painDeclaration);


    }

    private static void prepareSkinSymptomsAndDeclaration(SymptomEntry entry) {
        skinTextView.setText(SymptomEntryService.getSkinSymptoms(entry));
        String skinDeclaration = SymptomEntryService.getSkinDeclaration(entry);
        skinDeclarationTextView.setText("Declaration : "+skinDeclaration);
    }

    private static void setupPieChart(){
        PieChart chart  = view.findViewById(R.id.chart);
        chart.setUsePercentValues(true);;



        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(25,0));
        values.add(new Entry(26,1));
        values.add(new Entry(27,2));
        values.add(new Entry(28,3));
        values.add(new Entry(29,4));



        PieDataSet dataSet = new PieDataSet(values,"The proportion of symptoms in categories");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setDrawValues(true);

        PieData data = new PieData();
        data.addDataSet(dataSet);
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);


        chart.setData(data);
        chart.setDescription("");
        chart.invalidate();

    }


}
