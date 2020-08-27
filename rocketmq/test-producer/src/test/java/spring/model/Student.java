package spring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class Student {
    private long id;
    private String name;
    @Autowired
    private ContactInfor contactInfor;

//    @Autowired
//    public Student(ContactInfor contactInfor) {
//        this.contactInfor = contactInfor;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContactInfor getContactInfor() {
        return contactInfor;
    }

    public void setContactInfor(ContactInfor contactInfor) {
        this.contactInfor = contactInfor;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactInfor=" + contactInfor +
                '}';
    }

    public void test() {
        System.out.println("student test");
    }
}
