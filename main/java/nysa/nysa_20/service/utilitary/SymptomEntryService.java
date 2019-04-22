package nysa.nysa_20.service.utilitary;

import android.widget.TextView;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
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
    private SymptomEntryService() {
    }

    public static boolean isTodayLastEntry(Account account) {
        HashMap<java.time.LocalDate, SymptomEntry> map = account.getHistoryMap();
        if (map.containsKey(java.time.LocalDate.now()))
            return true;
        else
            return false;
    }

    public static int getKeyByValue(String value, HashMap<Integer, String> map) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue()))
                return entry.getKey();
        }
        return 0;
    }

    public static int getScore(SymptomEntry symptomEntry) {
        int score = 0;
        score += symptomEntry.getSymptomsPainEntry()
                .stream()
                .mapToInt(s -> s.getSymptomRating() * 5)
                .sum();

        score += symptomEntry.getSymptomsRespirationEntry()
                .stream()
                .mapToInt(s -> s.getSymptomRating() * 5)
                .sum();
        score += symptomEntry.getSymptomsSightEntry()
                .stream()
                .mapToInt(s -> s.getSymptomRating() * 5)
                .sum();
        score += symptomEntry.getSymptomsSkinEntry()
                .stream()
                .mapToInt(s -> s.getSymptomRating() * 5)
                .sum();


        return score;
    }

    public static int getScore(LocalDate date,Account account) {
        HashMap<java.time.LocalDate, SymptomEntry> map = account.getHistoryMap();

        int score = 0;
        if (map.containsKey(date)) {
            SymptomEntry symptomEntry = map.get(date);
            score += symptomEntry.getSymptomsPainEntry()
                    .stream()
                    .mapToInt(s -> s.getSymptomRating() * 5)
                    .sum();

            score += symptomEntry.getSymptomsRespirationEntry()
                    .stream()
                    .mapToInt(s -> s.getSymptomRating() * 5)
                    .sum();
            score += symptomEntry.getSymptomsSightEntry()
                    .stream()
                    .mapToInt(s -> s.getSymptomRating() * 5)
                    .sum();
            score += symptomEntry.getSymptomsSkinEntry()
                    .stream()
                    .mapToInt(s -> s.getSymptomRating() * 5)
                    .sum();

        }


        return score;
    }

    public static SymptomEntry getTodaySymptomEntry(Account account) {
        HashMap<java.time.LocalDate, SymptomEntry> map = account.getHistoryMap();

        if (isTodayLastEntry(account)) {
            return map.get(java.time.LocalDate.now());
        }
        return null;
    }

    public static String getEyesightSymptoms(SymptomEntry entry) {
        StringBuilder rez = new StringBuilder();
        List<Symptom> symptomList = entry.getSymptomsSightEntry();
        for (Symptom symptom : symptomList) {
            rez.append(symptom.getSymptoms().stream()
                    .collect(Collectors.joining(", " + "\n")));
            rez.append("\n");
        }

        return rez.toString();
    }

    public static String getRespirationSymptoms(SymptomEntry entry) {
        StringBuilder rez = new StringBuilder();
        List<Symptom> symptomList = entry.getSymptomsRespirationEntry();
        for (Symptom symptom : symptomList) {
            rez.append(symptom.getSymptoms().stream()
                    .collect(Collectors.joining(", " + "\n")));
            rez.append("\n");
        }

        return rez.toString();
    }

    public static String getPainSymptoms(SymptomEntry entry) {
        StringBuilder rez = new StringBuilder();
        List<Symptom> symptomList = entry.getSymptomsPainEntry();
        for (Symptom symptom : symptomList) {
            rez.append(symptom.getSymptoms().stream()
                    .collect(Collectors.joining(", " + "\n")));
            rez.append("\n");
        }

        return rez.toString();
    }

    public static String getSkinSymptoms(SymptomEntry entry) {
        StringBuilder rez = new StringBuilder();
        List<Symptom> symptomList = entry.getSymptomsSkinEntry();
        for (Symptom symptom : symptomList) {
            rez.append(symptom.getSymptoms().stream()
                    .collect(Collectors.joining(", " + "\n")));
            rez.append("\n");
        }

        return rez.toString();
    }

    public static String getEyesightDeclaration(SymptomEntry entry) {
        StringBuilder rez = new StringBuilder();
        List<Symptom> symptomList = entry.getSymptomsSightEntry();
        for (Symptom symptom : symptomList) {
            rez.append(symptom.getSymptomDeclaration());
            rez.append("\n");

        }
        return rez.toString();
    }

    public static String getRespirationDeclaration(SymptomEntry entry) {
        StringBuilder rez = new StringBuilder();
        List<Symptom> symptomList = entry.getSymptomsRespirationEntry();
        for (Symptom symptom : symptomList) {
            rez.append(symptom.getSymptomDeclaration());
            rez.append("\n");

        }
        return rez.toString();
    }

    public static String getPainDeclaration(SymptomEntry entry) {
        StringBuilder rez = new StringBuilder();
        List<Symptom> symptomList = entry.getSymptomsPainEntry();
        for (Symptom symptom : symptomList) {
            rez.append(symptom.getSymptomDeclaration());
            rez.append("\n");

        }
        return rez.toString();
    }

    public static String getSkinDeclaration(SymptomEntry entry) {
        StringBuilder rez = new StringBuilder();
        List<Symptom> symptomList = entry.getSymptomsSkinEntry();
        for (Symptom symptom : symptomList) {
            rez.append(symptom.getSymptomDeclaration());
            rez.append("\n");

        }
        return rez.toString();
    }

    public static int[] getSymptomsCountInAPeriod(LocalDate beginLocalDate, LocalDate endLocalDate,Account account) {
        int sightSymptomsCount = 0;
        int respiratorySymptomsCount = 0;
        int painSymptomsCount = 0;
        int skinSymptomsCount = 0;


        HashMap<LocalDate, SymptomEntry> map = account.getHistoryMap();
        LocalDate currentDate = beginLocalDate;

        while (isLocalDate1BeforeLocalDate2(currentDate,endLocalDate)) {


            if (map.containsKey(currentDate)) {
                SymptomEntry symptomEntry = map.get(currentDate);

                sightSymptomsCount += symptomEntry.getSymptomsSightEntry().stream()
                        .mapToInt(s -> s.getSymptoms().size())
                        .sum();

                respiratorySymptomsCount += symptomEntry.getSymptomsRespirationEntry().stream()
                        .mapToInt(s -> s.getSymptoms().size())
                        .sum();
                painSymptomsCount += symptomEntry.getSymptomsPainEntry().stream()
                        .mapToInt(s -> s.getSymptoms().size())
                        .sum();
                skinSymptomsCount += symptomEntry.getSymptomsSkinEntry().stream()
                        .mapToInt(s -> s.getSymptoms().size())
                        .sum();
            }

            currentDate = LocalDate.parse(currentDate.toString()).plusDays(1);

        }
        int[] values = new int[4];
        values[0] = sightSymptomsCount;
        values[1] = respiratorySymptomsCount;
        values[2] = painSymptomsCount;
        values[3] = skinSymptomsCount;

        return values;
    }

    public static boolean isLocalDate1BeforeLocalDate2(LocalDate localDate1, LocalDate localDate2) {
        if (localDate1.isAfter(localDate2)) {
            return false;
        }
        return true;
    }
}
