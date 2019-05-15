package sample;

import javafx.scene.control.DatePicker;
import sample.User.Trainer;

import java.util.Calendar;
import java.util.Random;

public class Member {
    private String id;
    private String name;
    private String discipline;
    private DatePicker birthday;
    private DatePicker startDate;
    private boolean competitive;
    private boolean active;
    private boolean senority;
    private String email;
    private String phoneNumber;
    private boolean deficit;
    private int balance;
    private Trainer appointedTrainer;

    public Member(String Name, String Discipline, DatePicker Birthday,
                  DatePicker StartDate, boolean Competitive, boolean Active,
                  String Email, String PhoneNumber, int Balance, Trainer AppointedTrainer) {

        Random random = new Random();

        id = String.valueOf(random.nextInt());
        name = Name;
        discipline = Discipline;
        birthday = Birthday;
        startDate = StartDate;
        competitive = Competitive;
        active = Active;
        email = Email;
        phoneNumber = PhoneNumber;
        balance = Balance;
        appointedTrainer = AppointedTrainer;

        int age =
                Calendar.getInstance().get(Calendar.YEAR) - birthday.getValue().getYear();

        if (age > 18) {
            senority = true;
        } else {
            senority = false;
        }

        // NOTE: assume that there is no deficit upon creation of a member
        deficit = false;
    }

    public String toString() {
        return name;
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

    public DatePicker getBirthday() {
        return birthday;
    }

    public DatePicker getStartDate() {
        return startDate;
    }

    public boolean isCompetetive() {
        return competitive;
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
