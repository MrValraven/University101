package EDA1.Hashtables;

public class StoredElement<AnyType> {
    
    public String key;
    public AnyType element;

    public StoredElement(String key, AnyType element) {
        this.key = key;
        this.element = element;
    }

}
