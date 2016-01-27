class CharList {

    private class ListNode {
        char elem;
        ListNode next = null;
        ListNode prev = null;

        ListNode(char elem, ListNode next, ListNode prev) {
            this.elem = elem;
            this.next = next;
            this.prev = prev;
        }

        int getElem() {
            return this.elem;
        }
        ListNode getNext() {
            return this.next;
        }
        ListNode getPrev() {
            return this.prev;
        }

        void add(char elem) {
            if (this.next == null)
                this.next = new ListNode(elem, null, this);
            else
                this.next.add(elem);
        }

        public String toString() {
            return this.elem + ((this.next == null) ? "-|" : "->" + this.next);
        }

    }

    private ListNode head = null;

    public boolean empty() {
        return this.head == null;
    }

    public void add(char elem) {
        if (empty()) {
            head = new ListNode(elem, null, null);
        } else {
            head.add(elem);
        }
    }

    public boolean search(char value) {
        if (empty()) return false;

        ListNode currentNode = head;
        do {
            if (currentNode.getElem() == value)
                return true;
        } while((currentNode = currentNode.getNext()) != null);

        return false;
    }

    public boolean insert(char value) {
        if (empty()) {
            add(value);
            return true;
        }

        // DA DIESE METHODE BOOLEAN ZURUECKGIBT, GEHE ICH DAVON
        // AUS, DASS BEI EINEM DOPPELTEN ELEMENT FALSE ZURUECKGELIEFERT WERDEN SOLL.

        // Case 1: value < head
        if (value < head.getElem()) {
            ListNode newHead = new ListNode(value, head, null);
            head.prev = newHead;
            head = newHead;

            return true;
        }

        // Case 2: prev < value < next
        ListNode prev = head;
        ListNode next = head.getNext();

        while (true) {
            // Case 2 Edge Case: List does only contain one element
            // or no matching element was found
            if (next == null) {
                head.add(value);

                return true;
            }

            // Case 2 Regular Case: prev < value < next
            if (prev.getElem() < value && value < next.getElem()) {
                // Insert in between
                ListNode newElem = new ListNode(value, next, prev);
                prev.next = newElem;
                next.prev = newElem;

                return true;
            }

            // If value is a duplicate, return false.
            if (value == prev.getElem() || value == next.getElem()) {
                return false;
            }

            prev = next;
            next = next.getNext();
        }
    }

    public boolean remove(char value) {
        if (empty()) return false;

        ListNode currentNode = head;
        do {
            if (currentNode.getElem() == value) {
                ListNode prev = currentNode.getPrev();
                ListNode next = currentNode.getNext();

                // Edge case -> head is being removed
                if (prev == null) {
                    head = next;
                } else {
                    prev.next = next;
                }
                if (next != null) next.prev = prev;
            }
        } while((currentNode = currentNode.getNext()) != null);

        return false;
    }

    public String toString() {
        return "[" + this.head + "]";
    }
}
