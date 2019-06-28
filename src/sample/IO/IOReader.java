package sample.IO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Competition;
import sample.Constants;
import sample.Disciplines.Discipline;
import sample.Disciplines.DisciplineList;
import sample.Member;
import sample.MemberList;
import sample.CompetitionList;
import sample.User.Trainer;
import sample.User.TrainerList;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class IOReader {
    private ObservableList<Member> memberFiles;

    private DisciplineList disciplineList;
    private TrainerList trainerList;

    public IOReader() {
        memberFiles = FXCollections.observableArrayList();
        disciplineList = new DisciplineList();
        trainerList = new TrainerList();
    }

    public void loadMembersFromFile() throws FileNotFoundException {
        File directory = new File(Constants.MEMBER_PATH);
        File[] files = directory.listFiles();

        if (files == null) {
            throw new FileNotFoundException("File not found");
        }

        for (File file : files) {
            memberFiles.add(readMemberFile(file.toString()));
        }
    }

    public void loadMemberTypes() {
        for (Member member : memberFiles) {
            if (member.isCompetetive() && !member.isDeficit()) {
                MemberList.competitiveMembers.add(member);
            }

            if (member.getBalance() > 0) {
                MemberList.membersInDeficit.add(member);
            } else {
                MemberList.members.add(member);
            }
        }
    }

    public void loadCompetitionsFromFile() throws FileNotFoundException {
        File directory = new File(Constants.COMPETITION_PATH);
        File[] files = directory.listFiles();

        if (files == null) {
            throw new FileNotFoundException("File not found");
        }

        for (File file : files) {
            CompetitionList.competitions.add(readCompetitionFile(file.toString()));
        }
    }

    public Member readMemberFile(String memberId) {
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

        // Read in member data
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
            Discipline chosenDiscipline = new Discipline() {};

            // Find trainer based on trainer name
            for (Trainer trainerIndex : trainerList.getTrainers()) {
                if (trainerIndex.getName().equalsIgnoreCase(trainer)) {
                    chosenTrainer = trainerIndex;
                    break;
                }
            }

            // Find discipline based on discipline name
            for (Discipline disciplineIndex : disciplineList.getDisciplines()) {
                if (disciplineIndex.toString().equalsIgnoreCase(discipline)) {
                    chosenDiscipline = disciplineIndex;
                    break;
                }
            }

            memberToReturn = new Member(name, chosenDiscipline, birthday, startDate,
                    competitive, active, email, phoneNumber, balance, chosenTrainer);
            memberToReturn.setId(id);
            fileReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("IOREADER ERROR: " + ex);
        }

        return memberToReturn;
    }

    private Competition readCompetitionFile(String competitionName) {

        Competition competition = new Competition();
        try {
            File file = new File(competitionName);
            Scanner fileReader = new Scanner(file);

            String name = fileReader.nextLine();
            LocalDate competitionDate = LocalDate.parse(fileReader.nextLine());
            String[] participantsAsStr = fileReader.nextLine().split("\\[|, |\\]");
            String[] participantTimesAsStr = fileReader.nextLine().split("\\[|, |\\]");

            // NOTE: we start at 1 because the way we split makes index 0
            // an empty string for some reason
            ArrayList<Member> participants = new ArrayList<>();
            for (int i = 1; i < participantsAsStr.length; i++) {
                participants.add(readMemberFile(Constants.MEMBER_PATH + participantsAsStr[i]));
            }

            // Applies as mentioned above
            ArrayList<Integer> participantTimes = new ArrayList<>();
            for (int i = 1; i < participantTimesAsStr.length; i++) {
                participantTimes.add(Integer.parseInt(participantTimesAsStr[i]));
            }

            competition = new Competition(name, competitionDate, participants
                    , participantTimes);

            fileReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: " + ex);
        }

        return competition;
    }
}
