package ba.sum.fsre.studentpraksa.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
    @Column(unique = true, nullable = false, length = 50)
    private String name;
    @Column(unique = true, nullable = false, length = 50)
    private String school;
    @Column(unique = true, nullable = false, length = 50)
    private String link;

    public Student(int id, String name, String school, String link) {

        this.id = id;
        this.name = name;
        this.school = school;
        this.link = link;
    }
    public Student( ) {

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @ManyToMany(mappedBy = "myStudent")
    Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
