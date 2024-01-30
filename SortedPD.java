package prog02;

import java.io.*;

/**
 * This is an implementation of PhoneDirectory that uses a sorted
 * array to store the entries.
 *
 * @author 
 */
public class SortedPD extends ArrayBasedPD {
    /** Find an entry in the directory.
     @param name The name to be found
     @return The index of the entry with that name or, if it is not
     there, the index where it should be added.
     */
    protected int find (String name) {
//        for (int i = 0; i < size; i++)
//            if (theDirectory[i].getName().equals(name))
//                return i;
//
//        return size;
        int low = 0;
        int high = size;

        while (low < high) {
            int middle = (low + high)/2;
            if (theDirectory[middle].getName().compareTo(name) < 0) {
                low = middle + 1;
            }
            else {
                high = middle;
            }
        }
        return high;
    }

    /** Determine if name is located at index.
     @param index The index to be checked.
     @param name The name that might be located at that index.
     @return true if a DirectoryEntry with that name is located at
     that index.
     */
    protected boolean found (int index, String name) {
        return index < size && theDirectory[index].getName().equals(name);
    }

    /** Add an entry to the directory.
     @param index The index at which to add the entry to theDirectory.
     @param newEntry The new entry to add.
     @return The DirectoryEntry that was just added.
     */
    protected DirectoryEntry add (int index, DirectoryEntry newEntry) {
//        if (size == theDirectory.length)
//            reallocate();
//        theDirectory[size] = theDirectory[index];
//        theDirectory[index] = newEntry;
//        size++;
//        return newEntry;
        if (size == theDirectory.length)
            reallocate();
        for (int i = size; i > index; i--) {
            theDirectory[i] = theDirectory[i - 1];
        }
        theDirectory[index] = newEntry;
        size++;
        return newEntry;

    }

    /** Remove an entry from the directory.
     @param index The index in theDirectory of the entry to remove.
     @return The DirectoryEntry that was just removed.
     */
    protected DirectoryEntry remove (int index) {
        DirectoryEntry entry = theDirectory[index];
        for (int i = index; i < size - 1; i++) {
            theDirectory[i] = theDirectory[i + 1];
            }
        size--;
        return entry;
    }



}
