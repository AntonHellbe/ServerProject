package org.demo.model;
import org.springframework.data.annotation.Id;
/**
 * Created by Anton on 2016-04-06.
 */

public class User {


    private String firstName;
    private String lastName;
    private String rfid;
    private TimeSamples[] samples;

    @Id
    private String id;

    public User(String firstName, String lastName, String rfid, String id, TimeSamples[] samples){
        this.firstName = firstName;
        this.lastName = lastName;
        this.rfid = rfid;
        this.id = id;
        this.samples = samples;
    }

    public boolean checkIdentity(String name) {
        if(name.equals(this.firstName)) {
            return true;
        }
        return false;
    }

    public boolean checkRfid(String rfid) {
        if(this.rfid.equals(rfid)) {
            return true;
        }

        return false;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public String getRfid() {
        return rfid;
    }

    public TimeSamples[] getSamples() {
        return samples;
    }

    public static void main(String[] args) {
        User p1 = new User("Anton", "Hellbe", "345", "678");
        TimeSamples samples = new TimeSamples();
        samples.newTimeSample();

        System.out.println(p1.getSamples());

    }

}
