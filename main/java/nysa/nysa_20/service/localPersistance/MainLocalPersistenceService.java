package nysa.nysa_20.service.localPersistance;

import java.io.File;
import java.io.IOException;

import nysa.nysa_20.model.AccountHolder;
import nysa.nysa_20.service.localPersistance.dao.DoctorAccountLocalDao;

public class MainLocalPersistenceService {

        private MainLocalPersistenceService(){}


         private static File ACCOUNT_fILE;

         public static void setAccountFile(File file ){
             ACCOUNT_fILE = file;
         }




        public static void persistCurrentAccount(){


            DoctorAccountLocalDao doctorAccountLocalDao = new DoctorAccountLocalDao(ACCOUNT_fILE);
            if(isAnyAccountPersisted()){
                deleteCurrentAccount();
            }
            try {
                doctorAccountLocalDao.persistCurrentAccount();
                System.out.println("Current Account has been persisted: " + AccountHolder.getAccount());
            } catch (IOException e) {
                e.printStackTrace();
            }



        }

        public static boolean isAnyAccountPersisted(){

            DoctorAccountLocalDao doctorAccountLocalDao = new DoctorAccountLocalDao(ACCOUNT_fILE);

            try {
                return doctorAccountLocalDao.isAnyAccountPersisted();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return false;
        }

        public static void deleteCurrentAccount(){

            DoctorAccountLocalDao doctorAccountLocalDao = new DoctorAccountLocalDao(ACCOUNT_fILE);
            try {
                doctorAccountLocalDao.deleteCurrentAccount();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void retrievePersistedAccount(){

            DoctorAccountLocalDao doctorAccountLocalDao = new DoctorAccountLocalDao(ACCOUNT_fILE);

            try {
                doctorAccountLocalDao.retrieveCurrentAccount();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }



        }







