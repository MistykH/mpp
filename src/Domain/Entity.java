package Domain;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class Entity<ID> {

    private ID id;

    public Entity(ID id) {
        this.id = id;
    }
    public Entity(){}


    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public void setEntity(Entity<ID> e){
        this.id=e.getId();
    }


    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
