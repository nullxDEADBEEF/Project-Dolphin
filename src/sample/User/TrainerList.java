package sample.User;

import java.util.ArrayList;

public class TrainerList {
    private static Trainer niklas = new Trainer(0, "Niklas", "1234", 1);
    private static Trainer frederik = new Trainer(1, "Frederik", "1234", 1);
    private static Trainer mikael = new Trainer(2, "Mikael", "1234", 1);

    private static ArrayList<Trainer> trainers = new ArrayList<Trainer>();

    public static void loadTrainers() {
        trainers.add(niklas);
        trainers.add(frederik);
        trainers.add(mikael);
    }

    public static ArrayList<Trainer> getTrainers() {
        return trainers;
    }
}
