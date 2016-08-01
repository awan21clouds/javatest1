package models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "party", strict = false)
public class Party {
    public Party(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Element(name = "first-name")
    public String firstName;

    @Element(name = "last-name")
    public String lastName;
}
