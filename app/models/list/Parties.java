package models.list;

import models.Party;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "parties", strict = false)
public class Parties {
    @ElementList(inline = true, name = "party")
    public List<Party> items;
}
