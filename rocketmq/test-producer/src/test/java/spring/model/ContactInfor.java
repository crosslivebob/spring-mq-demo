package spring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class ContactInfor {
    private String address;
    private String phone;
//    @Autowired
    private Student student;

//    @Autowired
//    public ContactInfor(Student student) {
//        this.student = student;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ContactInfor{" +
                "address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
