import java.util.List;

public class City {
    private final String id;
    private final List<Float> coords;

    public City(String id, List<Float> coords) {
        this.id = id;
        this.coords = coords;
    }
    public String getId() {
        return id;
    }

    public List<Float> getCoords() {
        return coords;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        City other = (City) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }

}
