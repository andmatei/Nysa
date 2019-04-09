package nysa.nysa_20.model;

public class AccountHolder {

    Account account;
    static boolean  isEmpty = true;

    private AccountHolder(){
    }

    private static final class SingletonHolder{
        private static AccountHolder SINGLETON = new AccountHolder();
    }

    public static AccountHolder getInstance(){
        return SingletonHolder.SINGLETON;
    }

    public static void setAccount(Account account){
        SingletonHolder.SINGLETON.account = account;
        AccountHolder.isEmpty = false;

    }

    public static Account getAccount(){
        return SingletonHolder.SINGLETON.account;
    }

    public static boolean isEmpty(){
       return isEmpty;
    }

    public static void disconnect(){
        isEmpty = true;
        //TODO disconnectSequence
    }

}
