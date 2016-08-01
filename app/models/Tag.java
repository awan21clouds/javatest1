package models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "tag", strict = false)
public class Tag {
    @Element(name = "name")
    public String name;
}
