package sample.User;

public class Trainer extends User {
    public Trainer(int ID, String Name, String Password, int Clearance) {
        id = ID;
        name = Name;
        password = Password;
        clearance = Clearance;
    }

    public Trainer() {}

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public void idGenerator() {
    }

    public void setClearance(int clearanceLevel) {

    }

    public int getClearance() {
        return clearance;
    }
}
