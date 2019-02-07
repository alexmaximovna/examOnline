package net.thumbtack.netexam.response;

import java.util.List;
import java.util.Objects;

public class GroupsResponse {
    private String nameGroup;
    private List<StudentsResponse> studentList;

    public GroupsResponse() {

    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public List<StudentsResponse> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentsResponse> studentList) {
        this.studentList = studentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupsResponse)) return false;
        GroupsResponse groups = (GroupsResponse) o;
        return Objects.equals(getNameGroup(), groups.getNameGroup()) &&
                Objects.equals(getStudentList(), groups.getStudentList());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNameGroup(), getStudentList());
    }

    @Override
    public String toString() {
        return "GroupsResponse{" +
                "nameGroup='" + nameGroup + '\'' +
                ", studentList=" + studentList +
                '}';
    }
}
