package net.thumbtack.netexam.response;

import java.util.Objects;

public class GetSettingResponse {
    private int maxNameLength;
    private int minPasswordLength;
    private int minAnswers;
    private int minQuestionsCountPerExam;
    private int minTime;

    public GetSettingResponse() {
    }

    public GetSettingResponse(int maxNameLength, int minPasswordLength, int minAnswers, int minQuestionsCountPerExam, int minTime) {
        this.maxNameLength = maxNameLength;
        this.minPasswordLength = minPasswordLength;
        this.minAnswers = minAnswers;
        this.minQuestionsCountPerExam = minQuestionsCountPerExam;
        this.minTime = minTime;
    }

    public GetSettingResponse(int maxNameLength, int minPasswordLength) {
        this.maxNameLength = maxNameLength;
        this.minPasswordLength = minPasswordLength;

    }

    public int getMaxNameLength() {
        return maxNameLength;
    }

    public void setMaxNameLength(int maxNameLength) {
        this.maxNameLength = maxNameLength;
    }

    public int getMinPasswordLength() {
        return minPasswordLength;
    }

    public void setMinPasswordLength(int minPasswordLength) {
        this.minPasswordLength = minPasswordLength;
    }

    public int getMinAnswers() {
        return minAnswers;
    }

    public void setMinAnswers(int minAnswers) {
        this.minAnswers = minAnswers;
    }

    public int getMinQuestionsCountPerExam() {
        return minQuestionsCountPerExam;
    }

    public void setMinQuestionsCountPerExam(int minQuestionsCountPerExam) {
        this.minQuestionsCountPerExam = minQuestionsCountPerExam;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetSettingResponse)) return false;
        GetSettingResponse that = (GetSettingResponse) o;
        return getMaxNameLength() == that.getMaxNameLength() &&
                getMinPasswordLength() == that.getMinPasswordLength() &&
                getMinAnswers() == that.getMinAnswers() &&
                getMinQuestionsCountPerExam() == that.getMinQuestionsCountPerExam() &&
                getMinTime() == that.getMinTime();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getMaxNameLength(), getMinPasswordLength(), getMinAnswers(), getMinQuestionsCountPerExam(), getMinTime());
    }

    @Override
    public String toString() {
        return "GetSettingResponse{" +
                "maxNameLength=" + maxNameLength +
                ", minPasswordLength=" + minPasswordLength +
                ", minAnswers=" + minAnswers +
                ", minQuestionsCountPerExam=" + minQuestionsCountPerExam +
                ", minTime=" + minTime +
                '}';
    }
}
