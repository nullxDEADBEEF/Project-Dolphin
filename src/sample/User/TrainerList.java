package sample.User;

import java.util.ArrayList;

public class TrainerList {
    private Trainer niklas = new Trainer(0, "Niklas", "1234", 1);
    private Trainer frederik = new Trainer(1, "Frederik", "1234", 1);
    private Trainer mikael = new Trainer(2, "Mikael", "1234", 1);

    public ArrayList<Trainer> trainers = new ArrayList<Trainer>();

    public TrainerList() {
        trainers.add(niklas);
        trainers.add(frederik);
        trainers.add(mikael);
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }
}
