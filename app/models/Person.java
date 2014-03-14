package models;
import javax.persistence.*;
import play.db.ebean.Model;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Person extends Model{
    @Id
    public Long id;

    public Long ean;
    public String firstname;
    public String lastname;
    public String email;
    public String number;
    public String notes;

    public Person() {}

    public Person(Long ean, String firstname, String lastname, String email, String number, String notes) {
        this.ean = ean;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.number = number;
        this.notes = notes;
    }


    public String toString() {
        return String.format("%s %s - %s, %s", firstname, lastname, email, number);
    }

    private static Contacts contactList = new Contacts();

    static {
        contactList.list.add(new Person(1234L, "Arthur", "Leung", "arthurleung@yahoo.ca", "1991", "First contact"));
        contactList.list.add(new Person(1123L, "John", "Jones", "jj@yahoo.ca", "1909", "Second contact"));
        contactList.list.add(new Person(1112L, "Tally", "Ho", "tally@ho.ca", "808", "First contact"));
    }

    public static Set<Person> findAll() {
        return new HashSet<Person>(contactList.list);
    }

    public static Person findByEan(Long ean) {
        for (Person candidate : contactList.list) {
            if (candidate.ean.equals(ean)) {
                return candidate;
            }
        }
        return null;
    }

    public static boolean remove(Person contact) {
        return contactList.list.remove(contact);
    }

    public static void add(Person contact) {
        contactList.list.add(contact);
    }



}
