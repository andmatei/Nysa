package nysa.nysa_20.model;

import nysa.nysa_20.service.localPersistance.MainLocalPersistenceService;

public class AccountHolder {

    private static DoctorAccount account;
    private static boolean  isEmpty = true;

    private AccountHolder(){
    }

    private static final class SingletonHolder{
        private static AccountHolder SINGLETON = new AccountHolder();
    }

    public static AccountHolder getInstance(){
        return SingletonHolder.SINGLETON;
    }

    public static void setAccount(DoctorAccount account){
        SingletonHolder.SINGLETON.account = account;
        AccountHolder.isEmpty = false;

    }

    public static DoctorAccount getAccount(){
        return SingletonHolder.SINGLETON.account;
    }

    public static boolean isEmpty(){
       return isEmpty;
    }

    public static void disconnect(){
        isEmpty = true;
        MainLocalPersistenceService.deleteCurrentAccount();
    }

    public static void initiatePersistance(){

    }

    public static void finishedPersistance(){

    }

}
