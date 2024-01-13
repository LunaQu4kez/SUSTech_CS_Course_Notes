package Problem03_06;

import java.util.ArrayList;

public class Player {
    private final String account = generateAccount();
    private String password;
    private Mail mail;
    private PhoneNumber phoneNumber;
    ArrayList<Pokemon> pokemons = new ArrayList<>();

    public Player(PhoneNumber phoneNumber, String password) {
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public Player(Mail mail, String password) {
        this.password = password;
        this.mail = mail;
    }

    public Player(Mail mail, PhoneNumber phoneNumber, String password) {
        this.password = password;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }

    public boolean checkIdentity(Mail mail, String password){
        if (mail.getMail().equals(this.mail.getMail()) && password.equals(this.password)){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIdentity(PhoneNumber phoneNumber, String password){
        if (phoneNumber.getPhoneNumber().equals(this.phoneNumber.getPhoneNumber()) && password.equals(this.password)){
            return true;
        } else {
            return false;
        }
    }

    public boolean setMail(PhoneNumber phoneNumber, String password, Mail mail){
        if (!checkIdentity(phoneNumber, password)){
            return false;
        } else {
            this.mail = mail;
            return true;
        }
    }

    public boolean setPhoneNumber(Mail mail, String password, PhoneNumber phoneNumber){
        if (!checkIdentity(mail, password)){
            return false;
        } else {
            this.phoneNumber = phoneNumber;
            return true;
        }
    }

    public String getAccount() {
        return account;
    }

    public Mail getMail() {
        return mail;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public String generateAccount(){
        return "1234567";
    }

    public boolean changePassword(PhoneNumber phoneNumber, String oldPassword, String newPassword){
        if (!checkIdentity(phoneNumber, oldPassword)){
            return false;
        } else {
            this.password = newPassword;
            return true;
        }
    }

    public boolean changePassword(Mail mail,String oldPassword, String newPassword){
        if (!checkIdentity(mail, oldPassword)){
            return false;
        } else {
            this.password = newPassword;
            return true;
        }
    }

    public void addPokemon(Pokemon pokemon){
        this.pokemons.add(pokemon);
    }

}

class Mail{
    public String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Mail(String mail) {
        this.mail = mail;
    }
}

class PhoneNumber{
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
