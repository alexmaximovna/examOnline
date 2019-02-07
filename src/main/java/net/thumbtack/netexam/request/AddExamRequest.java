package net.thumbtack.netexam.request;

import net.thumbtack.netexam.validator.FieldsValueMatch;

import java.util.Objects;

@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "name",
                fieldMatch = "semester",
                message = "Error with exam name or/and semester"
        )
})
public class AddExamRequest {

    private String name;
    private String semester;

    public AddExamRequest() {
    }

    public AddExamRequest(String name, String semester) {
        this.name = name;
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddExamRequest)) return false;
        AddExamRequest that = (AddExamRequest) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getSemester(), that.getSemester());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getSemester());
    }

    @Override
    public String toString() {
        return "AddExamRequest{" +
                "name='" + name + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }
}
