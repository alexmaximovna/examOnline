package net.thumbtack.netexam.response;

import java.util.List;
import java.util.Objects;

public class StudentsPassExamResponse {
    private String name;
    private List<GroupsResponse> groupList;

    public StudentsPassExamResponse() {
    }

    public StudentsPassExamResponse(String name, List<GroupsResponse> groupList) {
        this.name = name;
        this.groupList = groupList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupsResponse> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupsResponse> groupList) {
        this.groupList = groupList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentsPassExamResponse)) return false;
        StudentsPassExamResponse that = (StudentsPassExamResponse) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getGroupList(), that.getGroupList());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getGroupList());
    }

    @Override
    public String toString() {
        return "StudentsPassExamResponse{" +
                "name='" + name + '\'' +
                ", groupList=" + groupList +
                '}';
    }
}

