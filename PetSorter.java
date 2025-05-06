public class PetSorter {
    
    // Enumeration for different sort criteria
    public enum SortCriteria {
        ID, NAME, AGE, SPECIES
    }
    
    /**
     * Binary Insertion Sort implementation for Pet objects
     * This is more efficient than standard insertion sort because it uses
     * binary search to find the correct position to insert elements
     */
    public static CustomLinkedList<Pet> binaryInsertionSort(CustomLinkedList<Pet> pets, SortCriteria criteria) {
        // First convert linked list to array for easier sorting
        Pet[] petsArray = linkedListToArray(pets);
        
        // Perform binary insertion sort
        for (int i = 1; i < petsArray.length; i++) {
            Pet key = petsArray[i];
            int insertionPoint = findInsertionPoint(petsArray, 0, i - 1, key, criteria);
            
            // Shift all elements to the right
            System.arraycopy(petsArray, insertionPoint, petsArray, insertionPoint + 1, i - insertionPoint);
            
            // Insert the key at the correct position
            petsArray[insertionPoint] = key;
        }
        
        // Convert back to linked list
        return arrayToLinkedList(petsArray);
    }
    
    /**
     * Binary Search to find the insertion point for the key
     */
    private static int findInsertionPoint(Pet[] arr, int start, int end, Pet key, SortCriteria criteria) {
        if (start > end) {
            return start;
        }
        
        int mid = start + (end - start) / 2;
        
        int comparison = compareByKey(key, arr[mid], criteria);
        
        if (comparison == 0) {
            return mid + 1;
        } else if (comparison > 0) {
            return findInsertionPoint(arr, mid + 1, end, key, criteria);
        } else {
            return findInsertionPoint(arr, start, mid - 1, key, criteria);
        }
    }
    
    /**
     * Compare two Pet objects based on the specified criteria
     */
    private static int compareByKey(Pet pet1, Pet pet2, SortCriteria criteria) {
        switch (criteria) {
            case ID:
                return Integer.compare(pet1.getId(), pet2.getId());
            case NAME:
                return pet1.getName().compareToIgnoreCase(pet2.getName());
            case AGE:
                return Integer.compare(pet1.getAge(), pet2.getAge());
            case SPECIES:
                return pet1.getSpecies().compareToIgnoreCase(pet2.getSpecies());
            default:
                return 0;
        }
    }
    
    /**
     * Convert linked list to array for easier sorting
     */
    private static Pet[] linkedListToArray(CustomLinkedList<Pet> list) {
        // Count elements in the linked list
        int count = 0;
        Node<Pet> current = list.getHead();
        while (current != null) {
            count++;
            current = current.getNext();
        }
        
        // Create and populate array
        Pet[] array = new Pet[count];
        current = list.getHead();
        for (int i = 0; i < count; i++) {
            array[i] = current.getData();
            current = current.getNext();
        }
        
        return array;
    }
    
    /**
     * Convert array back to linked list
     */
    private static CustomLinkedList<Pet> arrayToLinkedList(Pet[] array) {
        CustomLinkedList<Pet> list = new CustomLinkedList<>();
        for (Pet pet : array) {
            list.add(pet);
        }
        return list;
    }
}