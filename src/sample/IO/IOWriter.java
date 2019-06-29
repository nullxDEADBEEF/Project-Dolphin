package sample.IO;

import sample.Competition;
import sample.Constants;
import sample.Member;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// Write member and competition data into a file
public class IOWriter {

    public IOWriter() {}

    /**
     * writes the members data into a file(their id)
     * @param member the members data to write
     */
    public void writeFile(Member member) {
        try {
            FileWriter writer =
                    new FileWriter(Constants.MEMBER_PATH + member.getId());
            PrintWriter printWriter = new PrintWriter(writer);

            printWriter.println(member.getId());
            printWriter.println(member.getName());
            printWriter.println(member.getDiscipline());
            printWriter.println(member.getBirthday());
            printWriter.println(member.getStartDate());
            printWriter.println(member.isCompetetive());
            printWriter.println(member.isActive());
            printWriter.println(member.isSeniority());
            printWriter.println(member.getEmail());
            printWriter.println(member.getPhoneNumber());
            printWriter.println(member.isDeficit());
            printWriter.println(member.getBalance());
            printWriter.println(member.getAppointedTrainer());

            printWriter.close();
        } catch (IOException e) {
            System.out.println("IOWRITER ERROR: " + e);
        }
    }

    /**
     * writes the competition data into a file(swim type + date)
     * @param competition the competition data to write
     */
    public void writeFile(Competition competition) {
        try {
            FileWriter writer =
                    new FileWriter(Constants.COMPETITION_PATH +
                            competition.getName() + " " + competition.getDate().toString() + ".txt");

            PrintWriter printWriter = new PrintWriter(writer);

            printWriter.println(competition.getName());
            printWriter.println(competition.getDate());

            printWriter.print("[");
            for (int i = 0; i < competition.getParticipants().size() - 1; i++) {
                printWriter.print(competition.getParticipants().get(i).getId() + ", ");
            }
            printWriter.print(competition.getParticipants().get(competition.getParticipants().size() - 1).getId());
            printWriter.println("]");

            printWriter.print("[");
            for (int i = 0; i < competition.getParticipantTime().size() - 1; i++) {
                printWriter.print(competition.getParticipantTime().get(i) + ", ");
            }
            printWriter.print(competition.getParticipantTime()
                    .get(competition.getParticipantTime().size() - 1));
            printWriter.println("]");

            printWriter.close();
        } catch (IOException e) {
            System.out.println("IOWRITER RROR: " + e);
        }
    }

    /**
     * deletes a member from the system
     * @param member the member to delete
     */
    public void deleteFile(Member member) {
        File file = new File(Constants.MEMBER_PATH + member.getId());
        if (file.delete()) {
            System.out.println(member.getId() + " was deleted successfully");
        }
    }

    /**
     * deletes a competition from the system
     * @param competition the competition to delete
     */
    public void deleteCompetitionFile(Competition competition) {
        File file = new File(Constants.COMPETITION_PATH + competition.getName());
        if (file.delete()) {
            System.out.println(competition + " was deleted successfully");
        }
    }
}
