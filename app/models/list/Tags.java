package models.list;

import models.Tag;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "tags", strict = false)
public class Tags {
    @ElementList(inline = true, name = "tag")
    public List<Tag> items;
}
