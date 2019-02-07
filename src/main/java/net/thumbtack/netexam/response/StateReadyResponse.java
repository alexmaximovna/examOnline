package net.thumbtack.netexam.response;

import net.thumbtack.netexam.model.ShowDetails;

import java.util.Objects;

public class StateReadyResponse {
    private int questionsCountPerExam;
    private int timeInMinutes;
    private ShowDetails showDetails;

    public StateReadyResponse() {
    }

    public StateReadyResponse(int questionsCountPerExam, int timeInMinutes, ShowDetails showDetails) {
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
        if (!(o instanceof StateReadyResponse)) return false;
        StateReadyResponse that = (StateReadyResponse) o;
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
        return "StateReadyResponse{" +
                "questionsCountPerExam=" + questionsCountPerExam +
                ", timeInMinutes=" + timeInMinutes +
                ", showDetails=" + showDetails +
                '}';
    }
}
