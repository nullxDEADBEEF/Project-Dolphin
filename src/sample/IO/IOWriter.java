package sample.IO;

import sample.Constants;
import sample.Member;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class IOWriter {
    public static void writeFile(Member member) {
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
            System.out.println("ERROR: " + e);
        }
    }

    public static void deleteFile(Member member) {
        File file = new File(Constants.MEMBER_PATH + member.getId());
        file.delete();
    }

    public static void deleteCompetitionFile(String fileName) {
        File file = new File(Constants.COMPETITION_PATH + fileName);
        file.delete();
    }
}
