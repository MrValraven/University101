public class SimpleHashtable<AnyType> {

    private String[] hashtable;

    public SimpleHashtable() {
        hashtable = new String[10];
    }

    public void put(String key, String value) {
        int hashedKey = hashKey(key);
        if(hashtable[hashedKey] != null) {
            System.out.println("position occupied");
        }

        else {
            hashtable[hashedKey] = value;
        }
    }

    public String get(String key) {
        int hashedKey = hashKey(key);

        return hashtable[hashedKey];
    }

    private int hashKey(String key) {
        return key.length() % hashtable.length;
    }

    public void printHashtable() {
        for(int i = 0; i < hashtable.length; i++) {
            System.out.println(hashtable[i]);
        }
    }
}