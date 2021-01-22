package Hashtables;

import java.util.LinkedList;
import java.util.ListIterator;

public class ChainedHashtable<AnyType> {

    private LinkedList<StoredElement<AnyType>>[] hashtable;

    public ChainedHashtable() {
        hashtable = new LinkedList[10];
        for(int i = 0; i < hashtable.length; i++) {
            hashtable[i] = new LinkedList<StoredElement<AnyType>>();
        }
    }

    public void put(String key, AnyType element) {
        int hashedKey = hashKey(key);

        hashtable[hashedKey].add(new StoredElement<AnyType>(key, element));
    }

    public AnyType get(String key) {
        int hashedKey = hashKey(key);

        ListIterator<StoredElement<AnyType>> iterator = hashtable[hashedKey].listIterator();

        StoredElement<AnyType> element = null;

        while(iterator.hasNext()) {
            element = (StoredElement<AnyType>) iterator.next();

            if(element.key.equals(key)) {
                return element.element;
            }
        }

        return null;
        
    }

    public AnyType remove(String key) {
        int hashedKey = hashKey(key);

        ListIterator<StoredElement<AnyType>> iterator = hashtable[hashedKey].listIterator();

        StoredElement<AnyType> element = null;

        int index = -1;

        while(iterator.hasNext()) {
            element = (StoredElement<AnyType>) iterator.next();
            index++;

            if(element.key.equals(key)) {
                break;
            }
        }

        if(element == null || !element.key.equals(key)) {
            return null;
        }

        else {
            hashtable[hashedKey].remove(index);
            return element.element;
        }

    }
    
    private int hashKey(String key) {
        return Math.abs(key.hashCode() % hashtable.length);
    }

    public void printHashtable() {
        for(int i = 0; i < hashtable.length; i++) {
            if(hashtable[i].isEmpty()) {
                System.out.println("Position " + i + ": empty");
            }

            else {
                System.out.print("Position " + i + ": ");
                ListIterator<StoredElement<AnyType>> iterator = hashtable[i].listIterator();

                while(iterator.hasNext()) {
                    System.out.println(iterator.next().element);
                    System.out.println("->");
                }

                System.out.println("null");
            }
        }
    }
}
