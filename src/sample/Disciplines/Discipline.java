package sample.Disciplines;

import sample.Member;

import java.time.LocalTime;
import java.util.ArrayList;

public abstract class Discipline {
    public ArrayList<Member> participants;
    public ArrayList<LocalTime> participantTimes;

    public ArrayList<Member> getParticipants() {
        return participants;
    }

    public ArrayList<LocalTime> getParticipantTimes() {
        return participantTimes;
    }

    public String toString() {
        return "Discipline";
    }
}
