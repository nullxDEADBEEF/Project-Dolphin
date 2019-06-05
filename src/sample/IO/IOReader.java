package sample.IO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Constants;
import sample.Member;
import sample.MemberList;
import sample.User.Trainer;
import sample.User.TrainerList;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class IOReader {
    private static ObservableList<Member> memberFiles =
            FXCollections.observableArrayList();

    public static void loadMembers() {
        File directory = new File(Constants.MEMBER_PATH);
        File[] files = directory.listFiles();

        for (File file : files) {
            memberFiles.add(readMemberFile(file.toString()));
        }

        for (Member member : memberFiles) {
            if (member.getBalance() > 0) {
                MemberList.membersInDeficit.add(member);
            } else {
                MemberList.members.add(member);
            }
        }
    }

    public static Member readMemberFile(String memberId) {
        String name;
        String discipline;
        LocalDate birthday;
        LocalDate startDate;
        boolean competitive;
        boolean active;
        String email;
        String phoneNumber;
        int balance;
        String trainer;

        Member memberToReturn = new Member();

        try {
            File file = new File(memberId);
            Scanner fileReader = new Scanner(file);

            String id = fileReader.nextLine();
            name = fileReader.nextLine();
            discipline = fileReader.nextLine();
            birthday = LocalDate.parse(fileReader.nextLine());
            startDate = LocalDate.parse(fileReader.nextLine());
            competitive = Boolean.parseBoolean(fileReader.nextLine());
            active = Boolean.parseBoolean(fileReader.nextLine());
            fileReader.nextLine();
            email = fileReader.nextLine();
            phoneNumber = fileReader.nextLine();
            fileReader.nextLine();
            balance = Integer.parseInt(fileReader.nextLine());
            trainer = fileReader.nextLine();

            Trainer chosenTrainer = new Trainer();

            for (int i = 0; i < TrainerList.getTrainers().size(); i++) {
                if (TrainerList.getTrainers().get(i).getName().equalsIgnoreCase(trainer)) {
                    chosenTrainer = TrainerList.getTrainers().get(i);
                }
            }

            memberToReturn = new Member(name, discipline, birthday, startDate,
                    competitive, active, email, phoneNumber, balance, chosenTrainer);
            memberToReturn.setId(id);

        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: " + ex);
        }

        return memberToReturn;
    }
}
