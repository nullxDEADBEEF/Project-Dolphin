package sample.User;

import java.time.LocalDate;

public class Member {
    private String id;
    private String name;
    private String discipline;
    private LocalDate birthday;
    private LocalDate startDate;
    private boolean competetive;
    private boolean active;
    private boolean senority;
    private String email;
    private String phoneNumber;
    private boolean deficit;
    private int balance;
    private Trainer appointedTrainer;

    public Member(String Id, String Name, String Discipline, LocalDate Birthday,
                  LocalDate StartDate, boolean Competetive, boolean Active,
                  boolean Senority, String Email, String PhoneNumber,
                  boolean Deficit, int Balance, Trainer AppointedTrainer) {

        id = Id;
        name = Name;
        discipline = Discipline;
        birthday = Birthday;
        startDate = StartDate;
        competetive = Competetive;
        active = Active;
        senority = Senority;
        email = Email;
        phoneNumber = PhoneNumber;
        deficit = Deficit;
        balance = Balance;
        appointedTrainer = AppointedTrainer;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDiscipline() {
        return discipline;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public boolean isCompetetive() {
        return competetive;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isSenority() {
        return senority;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isDeficit() {
        return deficit;
    }

    public int getBalance() {
        return balance;
    }

    public Trainer getAppointedTrainer() {
        return appointedTrainer;
    }
}
