package sample;

import java.time.LocalDate;
import java.util.ArrayList;

public class Competition {
    private String name;
    private LocalDate competitionDate;
    private ArrayList<Member> participants;
    private ArrayList<Integer> participantTime;

    public Competition() {}

    public Competition(String Name, LocalDate CompetitionDate,
                       ArrayList<Member> Participants,
                       ArrayList<Integer> ParticipantTime) {
        name = Name;
        competitionDate = CompetitionDate;
        participants = Participants;
        participantTime = ParticipantTime;
    }

    public String getName() { return name; }

    public LocalDate getDate() {
        return competitionDate;
    }

    public ArrayList<Member> getParticipants() { return participants; }

    public ArrayList<Integer> getParticipantTime() { return participantTime; }

    public String toString() { return name + " " + competitionDate; }
}
