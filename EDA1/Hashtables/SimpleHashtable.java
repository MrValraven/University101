package EDA1.Hashtables;

public class SimpleHashtable<AnyType> {

    private StoredElement<AnyType>[] hashtable;

    public SimpleHashtable() {
        hashtable = new StoredElement[10];
    }

    public void put(String key, AnyType element) {
        int hashedKey = hashKey(key);

        if (isOccupied(hashedKey)) {
            int stopIndex = hashedKey;
            if (hashedKey == hashtable.length - 1) {
                hashedKey = 0;
            }

            else {
                hashedKey++;
            }

            while (isOccupied(hashedKey) && hashedKey != stopIndex) {
                hashedKey = (hashedKey + 1) % hashtable.length;
            }
        }

        if (isOccupied(hashedKey)) {
            System.out.println("position occupied");
        }

        else {
            hashtable[hashedKey] = new StoredElement<AnyType>(key, element);
        }
    }

    public AnyType get(String key) {
        int hashedKey = findKey(key);

        if (hashedKey == -1) {
            return null;
        }

        return hashtable[hashedKey].element;
    }

    private int hashKey(String key) {
        return key.length() % hashtable.length;
    }

    private int findKey(String key) {
        int hashedKey = hashKey(key);

        if (hashtable[hashedKey] != null && hashtable[hashedKey].key.equals(key)) {
            return hashedKey;
        }

        int stopIndex = hashedKey;

        if (hashedKey == hashtable.length - 1) {
            hashedKey = 0;
        }

        else {
            hashedKey++;
        }

        while (hashedKey != stopIndex && hashtable[hashedKey] != null && !hashtable[hashedKey].key.equals(key)) {
            hashedKey = (hashedKey + 1) % hashtable.length;
        }

        if (stopIndex == hashedKey) {
            return -1;
        }

        else  {
            return hashedKey;
        }

    }

    private boolean isOccupied(int index) {
        return hashtable[index] != null;
    }

    public void printHashtable() {
        for (int i = 0; i < hashtable.length; i++) {
            if(hashtable[i] == null) {
                System.out.println("empty");
            }

            else {
                System.out.println("Position " + i + ": " + hashtable[i].element);
            }
            
        }
    }
}