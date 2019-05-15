package sample.IO;

import sample.Member;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IOWriter {
    public static void writeFile(Member member) {
        try {
            FileWriter writer =
                    new FileWriter("data/" + member.getId());
            PrintWriter printWriter = new PrintWriter(writer);

            printWriter.println(member.getId());
            printWriter.println(member.getName());
            printWriter.println(member.getDiscipline());
            printWriter.println(member.getBirthday().getEditor().getCharacters().toString());
            printWriter.println(member.getStartDate().getEditor().getCharacters().toString());
            printWriter.println(member.isCompetetive());
            printWriter.println(member.isActive());
            printWriter.println(member.isSenority());
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

    public static void deleteFile(String fileName) {
        File file = new File("data/" + fileName);
        file.delete();
    }
}
