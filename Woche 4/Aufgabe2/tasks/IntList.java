public class IntList {

    private class ListNode {
        int elem;
        ListNode next = null;
        int prevSum = 0;

        ListNode(int elem, ListNode next) {
            this.elem = elem;
            this.next = next;
        }

        int getElem() {
            return this.elem;
        }

        ListNode getNext() {
            return this.next;
        }

        void add(int elem) {
            if(next == null) {
                next = new ListNode(elem, null);
                next.prevSum = this.prevSum + this.elem;
            }
            else
                next.add(elem);
        }

        void calculatePrevSum(int prevSum, int prevElem) {
            this.prevSum = prevSum + prevElem;

            if(this.next != null)
                this.next.calculatePrevSum(this.prevSum, this.elem);
        }

        // returns new head
        ListNode reverseR(ListNode previous) {
            ListNode nextElem = this.next;
            this.next = previous;

            return nextElem != null ? nextElem.reverseR(this) : this;
        }

        public String toString() {
            return this.elem + "[" + prevSum + "]" + ((this.next == null) ? "-|" : "->" + this.next);
        }

    }

    private ListNode head = null;

    public boolean empty() {
        return this.head == null;
    }

    public void add(int elem) {
        if(head == null)
            head = new ListNode(elem, null);
        else
            head.add(elem);
    }

    public boolean search(int value) {
        if (head == null) return false;

        ListNode currentNode = head;
        do{
            if (currentNode.getElem() == value)
                return true;
        } while((currentNode = currentNode.getNext()) != null);

        return false;
    }

    public void reverseI() {
        if (head == null || head.getNext() == null) return;

        ListNode previous = head;
        ListNode current = head.getNext();
        head.next = null; // Current head will be the new last element.
        ListNode next;

        do {
            next = current.getNext();

            current.next = previous;

            previous = current;
        } while (next != null && (current = next) != null);

        head = current;

        head.prevSum = 0;
        head.getNext().calculatePrevSum(head.prevSum, head.getElem());
    }

    public void reverseR() {
        if (head == null || head.getNext() == null) return;

        ListNode next = head.getNext();
        head.next = null; // Head will be the new last element.
        head = next.reverseR(head);

        head.prevSum = 0;
        head.getNext().calculatePrevSum(head.prevSum, head.getElem());
    }

    public String toString() {
        return "[" + this.head + "]";
    }
}