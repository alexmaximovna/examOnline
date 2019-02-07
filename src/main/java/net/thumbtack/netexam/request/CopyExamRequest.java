package net.thumbtack.netexam.request;

import java.util.Objects;

public class CopyExamRequest {
    private int sourceId;
    private String name;
    private int semester;

    public CopyExamRequest() {
    }

    public CopyExamRequest(int sourceId, String name, int semester) {
        this.sourceId = sourceId;
        this.name = name;
        this.semester = semester;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CopyExamRequest)) return false;
        CopyExamRequest that = (CopyExamRequest) o;
        return getSourceId() == that.getSourceId() &&
                getSemester() == that.getSemester() &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getSourceId(), getName(), getSemester());
    }

    @Override
    public String toString() {
        return "CopyExamRequest{" +
                "sourceId=" + sourceId +
                ", name='" + name + '\'' +
                ", semester=" + semester +
                '}';
    }
}
