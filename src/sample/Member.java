package sample;

import sample.User.Trainer;
import java.time.LocalDate;
import java.util.Random;

public class Member {
    private String id;
    private String name;
    private String discipline;
    private LocalDate birthday;
    private LocalDate startDate;
    private boolean competitive;
    private boolean active;
    private boolean senority;
    private String email;
    private String phoneNumber;
    private boolean deficit;
    private int balance;
    private Trainer appointedTrainer;

    public Member() {}

    public Member(String Name, String Discipline, LocalDate Birthday,
                  LocalDate StartDate, boolean Competitive, boolean Active,
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

        int age = LocalDate.now().getYear() - birthday.getYear();

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

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getStartDate() {
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

    public void setId(String value) { id = value; }

    public void setName(String value) {
        name = value;
    }

    public void setDiscipline(String value) {
        discipline = value;
    }

    public void setEmail(String value) {
        email = value;
    }

    public void setPhoneNumber(String value) {
        phoneNumber = value;
    }

    public void setAppointedTrainer(Trainer value) {
        appointedTrainer = value;
    }
}
