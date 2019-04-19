package nysa.nysa_20.service.utilitary;

import android.widget.TextView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.Symptom;
import nysa.nysa_20.model.SymptomEntry;

public class SymptomEntryService {
    private SymptomEntryService(){}

    public static boolean isTodayLastEntry(){
        Account account = AccountHolder.getAccount();
        HashMap<java.time.LocalDate, SymptomEntry> map  = account.getHistoryMap();
        if(map.containsKey(java.time.LocalDate.now()))
            return true;
        else
            return false;
    }

    public static int getKeyByValue(String value, HashMap<Integer,String> map){
        for(Map.Entry<Integer,String> entry : map.entrySet()){
            if(Objects.equals(value,entry.getValue()))
                return  entry.getKey();
        }
        return 0;
    }

    public static int getScore(SymptomEntry symptomEntry){
        int score = 0;
        score+=symptomEntry.getSymptomsPainEntry()
                .stream()
                .mapToInt(s -> s.getSymptomRating()*5)
                .sum();

        score+=symptomEntry.getSymptomsRespirationEntry()
                .stream()
                .mapToInt(s -> s.getSymptomRating()*5)
                .sum();
        score+=symptomEntry.getSymptomsSightEntry()
                .stream()
                .mapToInt(s -> s.getSymptomRating()*5)
                .sum();
        score+=symptomEntry.getSymptomsSkinEntry()
                .stream()
                .mapToInt(s -> s.getSymptomRating()*5)
                .sum();


        return score;
    }

    public static int getScore(LocalDate date){
        Account account = AccountHolder.getAccount();
        HashMap<java.time.LocalDate, SymptomEntry> map  = account.getHistoryMap();

        int score = 0;
        if(map.containsKey(date)){
            SymptomEntry symptomEntry = map.get(date);
            score+=symptomEntry.getSymptomsPainEntry()
                    .stream()
                    .mapToInt(s -> s.getSymptomRating()*5)
                    .sum();

            score+=symptomEntry.getSymptomsRespirationEntry()
                    .stream()
                    .mapToInt(s -> s.getSymptomRating()*5)
                    .sum();
            score+=symptomEntry.getSymptomsSightEntry()
                    .stream()
                    .mapToInt(s -> s.getSymptomRating()*5)
                    .sum();
            score+=symptomEntry.getSymptomsSkinEntry()
                    .stream()
                    .mapToInt(s -> s.getSymptomRating()*5)
                    .sum();

        }



        return score;
    }

    public static SymptomEntry getTodaySymptomEntry(){
        Account account = AccountHolder.getAccount();
        HashMap<java.time.LocalDate, SymptomEntry> map  = account.getHistoryMap();

        if(isTodayLastEntry()){
            return map.get(java.time.LocalDate.now());
        }
        return null;
    }

    public static String getEyesightSymptoms(SymptomEntry entry){
        StringBuilder rez = new StringBuilder();
        List<Symptom> symptomList = entry.getSymptomsSightEntry();
        for(Symptom symptom : symptomList){
           rez.append( symptom.getSymptoms().stream()
                                 .collect(Collectors.joining(", "+"\n")));
           rez.append("\n");
        }

        return rez.toString();
    }

    public static String getRespirationSymptoms(SymptomEntry entry){
        StringBuilder rez = new StringBuilder();
        List<Symptom> symptomList = entry.getSymptomsRespirationEntry();
        for(Symptom symptom : symptomList){
            rez.append( symptom.getSymptoms().stream()
                    .collect(Collectors.joining(", "+"\n")));
            rez.append("\n");
        }

        return rez.toString();
    }
    public static String getPainSymptoms(SymptomEntry entry){
        StringBuilder rez = new StringBuilder();
        List<Symptom> symptomList = entry.getSymptomsPainEntry();
        for(Symptom symptom : symptomList){
            rez.append( symptom.getSymptoms().stream()
                    .collect(Collectors.joining(", "+"\n")));
            rez.append("\n");
        }

        return rez.toString();
    }
    public static String getSkinSymptoms(SymptomEntry entry){
        StringBuilder rez = new StringBuilder();
        List<Symptom> symptomList = entry.getSymptomsSkinEntry();
        for(Symptom symptom : symptomList){
            rez.append( symptom.getSymptoms().stream()
                    .collect(Collectors.joining(", "+"\n")));
            rez.append("\n");
        }

        return rez.toString();
    }

    public static String getEyesightDeclaration(SymptomEntry entry){
        StringBuilder rez = new StringBuilder();
        List<Symptom> symptomList = entry.getSymptomsSightEntry();
        for(Symptom symptom : symptomList){
            rez.append(symptom.getSymptomDeclaration());
            rez.append("\n");

        }
        return rez.toString();
    }

    public static String getRespirationDeclaration(SymptomEntry entry){
        StringBuilder rez = new StringBuilder();
        List<Symptom> symptomList = entry.getSymptomsRespirationEntry();
        for(Symptom symptom : symptomList){
            rez.append(symptom.getSymptomDeclaration());
            rez.append("\n");

        }
        return rez.toString();
    }

    public static String getPainDeclaration(SymptomEntry entry){
        StringBuilder rez = new StringBuilder();
        List<Symptom> symptomList = entry.getSymptomsPainEntry();
        for(Symptom symptom : symptomList){
            rez.append(symptom.getSymptomDeclaration());
            rez.append("\n");

        }
        return rez.toString();
    }

    public static String getSkinDeclaration(SymptomEntry entry){
        StringBuilder rez = new StringBuilder();
        List<Symptom> symptomList = entry.getSymptomsSkinEntry();
        for(Symptom symptom : symptomList){
            rez.append(symptom.getSymptomDeclaration());
            rez.append("\n");

        }
        return rez.toString();
    }
}
