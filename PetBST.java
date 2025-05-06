public class PetBST {
    private BSTNode root;

    private class BSTNode {
        private Pet pet;
        private BSTNode left, right;

        public BSTNode(Pet pet) {
            this.pet = pet;
            this.left = null;
            this.right = null;
        }
    }

    public PetBST() {
        root = null;
    }

    public void insert(Pet pet) {
        root = insertRec(root, pet);
    }

    private BSTNode insertRec(BSTNode root, Pet pet) {
        // If the tree is empty, return a new node
        if (root == null) {
            return new BSTNode(pet);
        }

        // Otherwise, recur down the tree
        if (pet.getId() < root.pet.getId()) {
            root.left = insertRec(root.left, pet);
        } else if (pet.getId() > root.pet.getId()) {
            root.right = insertRec(root.right, pet);
        }

        // Return the (unchanged) node pointer
        return root;
    }

    public Pet search(int id) {
        return searchRec(root, id);
    }

    private Pet searchRec(BSTNode root, int id) {
        // Base Cases: root is null or id is present at root
        if (root == null || root.pet.getId() == id) {
            return (root == null) ? null : root.pet;
        }

        // Id is greater than root's id
        if (root.pet.getId() < id) {
            return searchRec(root.right, id);
        }

        // Id is smaller than root's id
        return searchRec(root.left, id);
    }

    public boolean delete(int id) {
        Pet petToFind = search(id);
        if (petToFind == null) {
            return false;
        }
        
        root = deleteRec(root, id);
        return true;
    }

    private BSTNode deleteRec(BSTNode root, int id) {
        // Base case
        if (root == null) {
            return root;
        }

        // Recursive calls for ancestors of node to be deleted
        if (id < root.pet.getId()) {
            root.left = deleteRec(root.left, id);
        } else if (id > root.pet.getId()) {
            root.right = deleteRec(root.right, id);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get the inorder successor
            root.pet = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.pet.getId());
        }

        return root;
    }

    private Pet minValue(BSTNode root) {
        Pet minv = root.pet;
        while (root.left != null) {
            minv = root.left.pet;
            root = root.left;
        }
        return minv;
    }

    // Method to get all pets in order (sorted by ID)
    public CustomLinkedList<Pet> getInOrderList() {
        CustomLinkedList<Pet> list = new CustomLinkedList<>();
        inOrderTraversal(root, list);
        return list;
    }

    private void inOrderTraversal(BSTNode node, CustomLinkedList<Pet> list) {
        if (node != null) {
            inOrderTraversal(node.left, list);
            list.add(node.pet);
            inOrderTraversal(node.right, list);
        }
    }
}