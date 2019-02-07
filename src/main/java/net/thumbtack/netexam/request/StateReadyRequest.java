package net.thumbtack.netexam.request;

import net.thumbtack.netexam.model.ShowDetails;

import java.util.Objects;

public class StateReadyRequest {
    private int questionsCountPerExam;
    private int timeInMinutes;
    private ShowDetails showDetails;

    public StateReadyRequest() {
    }

    public StateReadyRequest(int questionsCountPerExam, int timeInMinutes, ShowDetails showDetails) {
        this.questionsCountPerExam = questionsCountPerExam;
        this.timeInMinutes = timeInMinutes;
        this.showDetails = showDetails;
    }

    public int getQuestionsCountPerExam() {
        return questionsCountPerExam;
    }

    public void setQuestionsCountPerExam(int questionsCountPerExam) {
        this.questionsCountPerExam = questionsCountPerExam;
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public ShowDetails getShowDetails() {
        return showDetails;
    }

    public void setShowDetails(ShowDetails showDetails) {
        this.showDetails = showDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StateReadyRequest)) return false;
        StateReadyRequest that = (StateReadyRequest) o;
        return getQuestionsCountPerExam() == that.getQuestionsCountPerExam() &&
                getTimeInMinutes() == that.getTimeInMinutes() &&
                getShowDetails() == that.getShowDetails();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getQuestionsCountPerExam(), getTimeInMinutes(), getShowDetails());
    }

    @Override
    public String toString() {
        return "StateReadyRequest{" +
                "questionsCountPerExam=" + questionsCountPerExam +
                ", timeInMinutes=" + timeInMinutes +
                ", showDetails=" + showDetails +
                '}';
    }
}
