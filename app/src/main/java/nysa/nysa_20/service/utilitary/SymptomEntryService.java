package nysa.nysa_20.service.utilitary;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;
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
}
