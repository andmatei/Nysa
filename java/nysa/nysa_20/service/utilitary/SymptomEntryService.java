package nysa.nysa_20.service.utilitary;

import java.util.HashMap;

import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.model.SymptomEntry;

public class SymptomEntryService {
    public static boolean isTodayLastEntry(){
        Account account = AccountHolder.getAccount();
        HashMap<java.time.LocalDate, SymptomEntry> map  = account.getHistoryMap();
        if(map.containsKey(java.time.LocalDate.now()))
            return true;
        else
            return false;
    }
}
