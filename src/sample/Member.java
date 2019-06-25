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
    private boolean seniority;
    private String email;
    private String phoneNumber;
    private boolean deficit;
    private int balance;
    private Trainer appointedTrainer;
    private LocalDate paymentDate;

    private final int seniorOver65DiscountedPrice =
            (1600 - (1600 / 100 * 25));
    private final int activeUnder18Price = 1000;
    private final int activeOver18Price = 1600;
    private final int passiveMemberPrice = 500;

    private int age;

    private Random random = new Random();

    public Member() {}

    public Member(String Name, String Discipline, LocalDate Birthday,
                  LocalDate StartDate, boolean Competitive, boolean Active,
                  String Email, String PhoneNumber, int Balance, Trainer AppointedTrainer) {

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

        paymentDate = startDate;

        age = LocalDate.now().getYear() - birthday.getYear();

        seniority = age > 18;

        determinePayment();

        setDeficit(isBalanceNegative(balance));
    }

    private boolean isBalanceNegative(int balance) {
        return balance > 0;
    }

    private void determinePayment() {
        if (LocalDate.now().equals(paymentDate)) {
            if (active && seniority && age >= 60) {
                balance += seniorOver65DiscountedPrice;
            } else if (active && seniority) {
                balance += activeOver18Price;
            } else if (active) {
                balance += activeUnder18Price;
            } else {
                balance += passiveMemberPrice;
            }

            findNewPaymentDate();
        }
    }

    // adds a year to the paymentDate, so the price is not added
    // everytime we start the program
    private void findNewPaymentDate() {
        if (LocalDate.now().getYear() == paymentDate.getYear()) {
            paymentDate = paymentDate.plusYears(1);
        }
    }

    public void generateId() {
        id = String.valueOf(random.nextInt());
    }

    public void addToBalance(int amount) {
        balance -= amount;
    }

    public void subFromBalance(int amount) {
        balance += amount;
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

    public LocalDate getBirthday() { return birthday; }

    public LocalDate getStartDate() {
        return startDate;
    }

    public boolean isCompetetive() {
        return competitive;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isSeniority() {
        return seniority;
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

    public void setDeficit(boolean value) {
        deficit = value;
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
