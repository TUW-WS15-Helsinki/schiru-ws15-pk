class IntTree {

    private class Node {

        int elem;
        Node left = null;
        Node right = null;

        Node(int elem) {
            this.elem = elem;
        }

        void add(int elem) {
            if (elem < this.elem) {
                if (left == null) {
                    left = new Node(elem);
                } else {
                    left.add(elem);
                }
            } else if (elem > this.elem) {
                if (right == null) {
                    right = new Node(elem);
                } else {
                    right.add(elem);
                }
            }
        }

        boolean isLeaf() {
            return left == null && right == null;
        }

        // counts its sub nodes excluding itself
        int countNodes() {
            return (left != null ? left.countNodes() + 1 : 0) + (right != null ? right.countNodes() + 1 : 0);
        }

        int countLeaves() {
            if (isLeaf()) {
                return 1;
            }

            return (left != null ? left.countLeaves() : 0) + (right != null ? right.countLeaves() : 0);
        }

        int height() {
            int heightLeft = left != null ? left.height() + 1: 0;
            int heightRight = right != null ? right.height() + 1 : 0;

            return Math.max(heightLeft, heightRight);
        }

        void printLeaves() {
            if (isLeaf()) {
                System.out.println(this.elem + "");
            } else {
                if (left != null) left.printLeaves();
                if (right != null) right.printLeaves();
            }
        }

        void printInOrderUp() {
            if (left != null) left.printInOrderUp();
            System.out.println(this.elem);
            if (right != null) right.printInOrderUp();
        }

        void printInOrderUpSub(int elem) {
            if (elem == this.elem) {
                printInOrderUp();
            } else if (elem < this.elem && left != null) {
                left.printInOrderUp();
            } else if (elem > this.elem && right != null) {
                right.printInOrderUp();
            }
        }

        void printInOrderDown() {
            if (right != null) right.printInOrderDown();
            System.out.println(this.elem);
            if (left != null) left.printInOrderDown();
        }
    }

    private Node root = null;

    public void add(int elem) {
        if (empty()) {
            root = new Node(elem);
        }

        else root.add(elem);
    }

    public boolean empty() {
        return this.root == null;
    }

    public int countNodes() {
        if (empty()) return 0;
        else return root.countNodes() + 1;
    }

    public int countLeaves() {
        if (empty()) return 0;
        return root.countLeaves();
    }

    public int height() {
        if (empty()) return 0;
        return root.height() + 1;
    }

    public void printLeaves() {
        if (!empty())
            root.printLeaves();
    }

    public void printInOrderUp() {
        if (!empty()) {
            root.printInOrderUp();
        }
    }

    public void printInOrderUpSub(int elem) {
        if (!empty()) {
            root.printInOrderUpSub(elem);
        }
    }

    public void printInOrderDown() {
        if (!empty()) {
            root.printInOrderDown();
        }
    }
}