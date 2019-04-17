package nysa.nysa_20.service.localPersistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.service.localPersistance.dao.AccountLocalDao;

public class MainLocalPersistenceService {

        private MainLocalPersistenceService(){}


         private static File ACCOUNT_fILE;

         public static void setAccountFile(File file ){
             ACCOUNT_fILE = file;
         }




        public static void persistCurrentAccount(){

            AccountHolder.initiatePersistance();
            AccountLocalDao accountLocalDao = new AccountLocalDao(ACCOUNT_fILE);
            if(isAnyAccountPersisted()){
                deleteCurrentAccount();
            }
            try {
                accountLocalDao.persistCurrentAccount();
                System.out.println("Current Account has been persisted: " + AccountHolder.getAccount());
            } catch (IOException e) {
                e.printStackTrace();
            }


            AccountHolder.finishedPersistance();
        }

        public static boolean isAnyAccountPersisted(){

            AccountLocalDao accountLocalDao = new AccountLocalDao(ACCOUNT_fILE);

            try {
                return accountLocalDao.isAnyAccountPersisted();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return false;
        }

        public static void deleteCurrentAccount(){

            AccountLocalDao accountLocalDao = new AccountLocalDao(ACCOUNT_fILE);
            try {
                accountLocalDao.deleteCurrentAccount();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void retrievePersistedAccount(){

            AccountLocalDao accountLocalDao = new AccountLocalDao(ACCOUNT_fILE);

            try {
                accountLocalDao.retrieveCurrentAccount();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }



        }







