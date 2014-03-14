package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arthur on 13/03/14.
 */
public class Contacts {
    public String name;
    public List<Person> list = new ArrayList<>();

    public String toString() {
        return name;
    }
}
