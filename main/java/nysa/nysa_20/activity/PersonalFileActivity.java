package nysa.nysa_20.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
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
import java.util.HashMap;
import java.util.Map;

import nysa.nysa_20.R;
import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.DoctorAccount;
import nysa.nysa_20.model.SymptomEntry;
import nysa.nysa_20.service.utilitary.SymptomEntryService;
import nysa.nysa_20.service.utilitary.TimeUtilitaryClass;

public class PersonalFileActivity extends AppCompatActivity {
    private static CompactCalendarView calendarView;
    private static TextView monthTextView;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM - YYYY");
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
    private static TextView beginPeriodStatement;
    private static TextView endPeriodStatement;
    private static TextView beginPeriod;
    private static TextView endPeriod;
    private static LocalDate beginPeriodLocalDate;
    private static LocalDate endPeriodLocalDate;
    private static PieChart chart;
    private static TextView eyesightValueLegendTextView;
    private static TextView respirationValueLegendTextView;
    private static TextView painValueLegendTextView;
    private static TextView skinValueLegendTextView;
    private static TextView namePatientTextView;
    private static TextView emailPatientTextView;
    private static TextView allergiesTextView;
    private static DoctorAccount account;
    private static HashMap<LocalDate,SymptomEntry> map;
    private static Context context;
    private static Account patientAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_file);
        context = this;

        prepareInitComponents();

        initComponents();
        setupPieChart();

    }

    private void prepareInitComponents(){
        Intent i = this.getIntent();
        int pos = (Integer) i.getExtras().get("PatientPosition");
        account = AccountHolder.getAccount();
        patientAccount = account.getPatients().get(pos);
        map = patientAccount.getHistoryMap();

    }


    private void initComponents() {

        initComponentsReferences();

        prepareCalendar();

        initComponentsFunctionalities();
        setupPieChart();
    }

    private void initComponentsFunctionalities() {
        beginPeriodStatement.setOnClickListener(ev -> onBeginPeriodChange(ev));
        endPeriodStatement.setOnClickListener(ev -> onEndPeriodChange(ev));
        beginPeriod.setText(TimeUtilitaryClass.getLocalDateToStringFormatSymptomTrack(LocalDate.now()));
        endPeriod.setText(TimeUtilitaryClass.getLocalDateToStringFormatSymptomTrack(LocalDate.now()));
        beginPeriodLocalDate = LocalDate.now();
        endPeriodLocalDate = LocalDate.now();
        namePatientTextView.setText(patientAccount.getFirstName()+" "+patientAccount.getLastName());
        emailPatientTextView.setText(patientAccount.getEmail());
        allergiesTextView.setText(getPatientAllergies());

    }

    private String getPatientAllergies() {
        StringBuilder sb = new StringBuilder("");
        HashMap<String,Boolean> allergyMap = patientAccount.getAllergyMap();
        for(Map.Entry<String,Boolean> e :allergyMap.entrySet()){
            if(e.getValue()){
                sb.append(e.getKey()+" ");
            }
        }


        return sb.toString();
    }

    private void onEndPeriodChange(View ev) {

        prepareSelectDate("END");
    }

    private void onBeginPeriodChange(View ev) {

        prepareSelectDate("BEGIN");

    }



    private void initComponentsReferences() {
        calendarView = findViewById(R.id.calendarView_DialogBox);
        monthTextView = findViewById(R.id.monthTextView);
        discomfortLevelTextView = findViewById(R.id.discomfortLevelSelectedTextView);
        eyesightTextView = findViewById(R.id.eyesightSymptomsTextView_Tracker);
        respirationTextView = findViewById(R.id.respirationSymptomsTextView_Tracker);
        painTextView = findViewById(R.id.painSymptomsTextView_Tracker);
        skinTextView = findViewById(R.id.skinSymptomsTextView_Tracker);
        selectedDataLayout = findViewById(R.id.selectedDate_data);
        eyesightDeclarationTextView = findViewById(R.id.eyesightDeclarationTextView_Tracker);
        respirationDeclarationTextView = findViewById(R.id.respirationDeclarationTextView_Tracker);
        painDeclarationTextView = findViewById(R.id.painDeclarationTextView_Tracker);
        skinDeclarationTextView = findViewById(R.id.skinDeclarationTextView_Tracker);
        beginPeriodStatement = findViewById(R.id.beginPeriodStatementTextView_Tracker);
        beginPeriod = findViewById(R.id.beginPeriodTextView_Tracker);
        endPeriodStatement = findViewById(R.id.endPeriodStatementTextView_Tracker);
        endPeriod = findViewById(R.id.endPeriodTextView_Tracker);
        chart = findViewById(R.id.chart);
        eyesightValueLegendTextView = findViewById(R.id.eyesightValueLegendTextView);
        respirationValueLegendTextView = findViewById(R.id.respirationValueLegendTextView);
        painValueLegendTextView = findViewById(R.id.painValueLegendTextView);
        skinValueLegendTextView = findViewById(R.id.skinValueLegendTextView);
        namePatientTextView = findViewById(R.id.namePatientTextViewPersonalFile);
        emailPatientTextView = findViewById(R.id.emailPatientTextViewPersonalFile);
        allergiesTextView = findViewById(R.id.allergiesValuesTextView);


    }

    private void prepareCalendar() {


        calendarView.setUseThreeLetterAbbreviation(true);
        monthTextView.setText(TimeUtilitaryClass.getCurrentMonthYearFormat());


        for (Map.Entry<LocalDate, SymptomEntry> entry : map.entrySet()) {
            long epochDay = entry.getKey().toEpochDay() * 86400000;
            int scoreDay = SymptomEntryService.getScore(entry.getKey(),patientAccount);
            Event event = new Event(Color.RED, epochDay, scoreDay);
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

    private static void takeActionOnDayClicked(Date date) {
        LocalDate localDate = TimeUtilitaryClass.convertToLocalDate(date);
        if (map.containsKey(localDate)) {
            selectedDataLayout.setVisibility(View.VISIBLE);
            SymptomEntry entry = map.get(localDate);

            int discomfortLevel = SymptomEntryService.getScore(entry);
            discomfortLevelTextView.setText(String.valueOf(discomfortLevel));

            prepareSightSymptomsAndDeclaration(entry);
            prepareRespirationSymptomsAndDeclaration(entry);
            preparePainSymptomsAndDeclaration(entry);
            prepareSkinSymptomsAndDeclaration(entry);
        } else {
            selectedDataLayout.setVisibility(View.GONE);
        }
    }

    private static void prepareSightSymptomsAndDeclaration(SymptomEntry entry) {
        eyesightTextView.setText(SymptomEntryService.getEyesightSymptoms(entry));
        String eyesightDeclaration = SymptomEntryService.getEyesightDeclaration(entry);
        eyesightDeclarationTextView.setText("Declaration : " + eyesightDeclaration);

    }

    private static void prepareRespirationSymptomsAndDeclaration(SymptomEntry entry) {
        respirationTextView.setText(SymptomEntryService.getRespirationSymptoms(entry));
        String respirationDeclaration = SymptomEntryService.getRespirationDeclaration(entry);
        respirationDeclarationTextView.setText("Declaration : " + respirationDeclaration);

    }

    private static void preparePainSymptomsAndDeclaration(SymptomEntry entry) {
        painTextView.setText(SymptomEntryService.getPainSymptoms(entry));
        String painDeclaration = SymptomEntryService.getPainDeclaration(entry);
        painDeclarationTextView.setText("Declaration : " + painDeclaration);


    }

    private static void prepareSkinSymptomsAndDeclaration(SymptomEntry entry) {
        skinTextView.setText(SymptomEntryService.getSkinSymptoms(entry));
        String skinDeclaration = SymptomEntryService.getSkinDeclaration(entry);
        skinDeclarationTextView.setText("Declaration : " + skinDeclaration);
    }

    private static void setupPieChart() {

        chart.setUsePercentValues(false);


        ArrayList<Entry> values = new ArrayList<>();
        for(int i=0;i<=3;i++){
            values.add(new Entry(1,i));
        }



        PieDataSet dataSet = new PieDataSet(values, "");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setDrawValues(true);

        PieData data = new PieData();
        data.addDataSet(dataSet);
        data.setValueTextSize(0f);

        chart.animateY(1000, Easing.EasingOption.EaseOutCubic);
        chart.setData(data);
        chart.setDescription("");
        aChangeHasOccured();
        chart.invalidate();

    }

    public static void prepareSelectDate(String typeOfMargin) {

        DatePickerDialog.OnDateSetListener listener;
        Calendar calendar = Calendar.getInstance();


        listener = (view, year, month, dayOfMonth) -> selectDate(year,month,dayOfMonth,typeOfMargin);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(context,android.R.style.Theme_Holo_Light_Dialog_MinWidth,listener,year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();


    }

    public  static void selectDate(int year,int month,int day,String typeOfMargin){
        LocalDate localDate = LocalDate.of(year,month+1,day);
        if(typeOfMargin.equals("BEGIN")){
            if(!localDate.equals(beginPeriodLocalDate)){
                beginPeriodLocalDate = localDate;
                beginPeriod.setText( TimeUtilitaryClass.getLocalDateToStringFormatSymptomTrack(beginPeriodLocalDate));
                aChangeHasOccured();
            }
        }
        else{
            if(typeOfMargin.equals("END")){
                if(!localDate.equals(endPeriodLocalDate)){
                    endPeriodLocalDate = localDate;
                    endPeriod.setText(TimeUtilitaryClass.getLocalDateToStringFormatSymptomTrack(endPeriodLocalDate));
                    aChangeHasOccured();
                }
            }
        }


    }

    private static void aChangeHasOccured() {

        ArrayList<Entry> chartValues = prepareNewChartValues();
        PieDataSet dataSet = new PieDataSet(chartValues, "");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setDrawValues(true);

        PieData data = new PieData();
        data.addDataSet(dataSet);
        data.setValueTextSize(0f);

        chart.animateY(1000, Easing.EasingOption.EaseOutCubic);
        chart.setData(data);
        chart.setDescription("");
        chart.invalidate();

    }



    private static ArrayList<Entry> prepareNewChartValues() {
        ArrayList<Entry> entryValues = new ArrayList<>();
        // eyesight - 0 -red #c12552
        //respiratory - 1 - orange #ff6600
        //pain - 2 - yellow #f5c700
        //skin 3 - green #6a961f

        int[] values = SymptomEntryService.getSymptomsCountInAPeriod(beginPeriodLocalDate,endPeriodLocalDate,patientAccount);
        int sum = values[0]+values[1]+values[2]+values[3];
        if(sum == 0){
            for(int i = 0;i<=3;i++){
                entryValues.add(new Entry(1,i));
                String value = "0% (0)";
                eyesightValueLegendTextView.setText(value);
                respirationValueLegendTextView.setText(value);
                painValueLegendTextView.setText(value);
                skinValueLegendTextView.setText(value);
            }
        }
        else{


            for(int i = 0;i<=3;i++){
                entryValues.add(new Entry(values[i],i));

            }
            eyesightValueLegendTextView.setText((int)((values[0]*100)/sum)+"% ("+values[0]+")");
            respirationValueLegendTextView.setText((int)((values[1]*100)/sum)+"% ("+values[1]+")");
            painValueLegendTextView.setText((int)((values[2]*100)/sum)+"% ("+values[2]+")");
            skinValueLegendTextView.setText((int)((values[3]*100)/sum)+"% ("+values[3]+")");
        }


        return entryValues;

    }
}
