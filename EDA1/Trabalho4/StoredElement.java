package Trabalho4;

public class StoredElement<AnyType> {
    
    public String key;
    public AnyType element;

    public StoredElement(String key, AnyType element) {
        this.key = key;
        this.element = element;
    }
}
