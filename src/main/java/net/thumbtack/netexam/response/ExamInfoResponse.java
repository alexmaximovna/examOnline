package net.thumbtack.netexam.response;

import java.util.Objects;

public class ExamInfoResponse {
    private int id;
    private String name;
    private Integer semester;

    public ExamInfoResponse() {
    }

    public ExamInfoResponse(int id, String name, int semester) {
        this.id = id;
        this.name = name;
        this.semester = semester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (!(o instanceof ExamInfoResponse)) return false;
        ExamInfoResponse that = (ExamInfoResponse) o;
        return getId() == that.getId() &&
                getSemester() == that.getSemester() &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getSemester());
    }

    @Override
    public String toString() {
        return "ExamInfoResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", semester=" + semester +
                '}';
    }
}
