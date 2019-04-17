package nysa.nysa_20.service.localPersistance.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;

public class AccountLocalDao {
    private File file;


    public AccountLocalDao(File file) {
        this.file = file;
    }

    public boolean isAnyAccountPersisted() throws IOException, ClassNotFoundException {
        try(  FileInputStream fileInputStream = new FileInputStream(file);
              InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
              BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ){
            if(bufferedReader.readLine()==null)
                return false;
            return true;
         }
    }

    public void persistCurrentAccount() throws IOException {
        Account curentAccount = AccountHolder.getAccount();
        try(FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ){
            objectOutputStream.writeObject(curentAccount);
        }
    }





    public void deleteCurrentAccount() throws IOException {

        try(FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ){
            objectOutputStream.flush();
        }


    }

    public void retrieveCurrentAccount() throws IOException, ClassNotFoundException {
       try(FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
       ){
           Account account = (Account) objectInputStream.readObject();
           AccountHolder.setAccount(account);

       }
    }
}
