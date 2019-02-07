package net.thumbtack.netexam.response;

import java.util.Objects;

public class CopyExamResponse {
    private int copyId;
    private String name;
    private int semester;

    public CopyExamResponse() {
    }

    public CopyExamResponse(int copyId, String name, int semester) {
        this.copyId = copyId;
        this.name = name;
        this.semester = semester;
    }

    public int getCopyId() {
        return copyId;
    }

    public void setCopyId(int copyId) {
        this.copyId = copyId;
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
        if (!(o instanceof CopyExamResponse)) return false;
        CopyExamResponse that = (CopyExamResponse) o;
        return getCopyId() == that.getCopyId() &&
                getSemester() == that.getSemester() &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCopyId(), getName(), getSemester());
    }

    @Override
    public String toString() {
        return "CopyExamResponse{" +
                "copyId=" + copyId +
                ", name='" + name + '\'' +
                ", semester=" + semester +
                '}';
    }
}
