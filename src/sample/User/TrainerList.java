package sample.User;

import java.util.ArrayList;

public class TrainerList {
    private ArrayList<Trainer> trainers;

    public TrainerList() {
        trainers = new ArrayList<>();

        Trainer niklas = new Trainer(0, "Niklas", "1234", 1);
        Trainer frederik = new Trainer(1, "Frederik", "1234", 1);
        Trainer mikael = new Trainer(2, "Mikael", "1234", 1);

        trainers.add(niklas);
        trainers.add(frederik);
        trainers.add(mikael);
    }

    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }
}
