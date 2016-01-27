public class CharStack {

    private CharStackNode top = null;

    // push an element onto the stack
    public void push(char elem) {
        // The newly created element is the new top.
        // The next element, thus, is the previous top element
        this.top = new CharStackNode(elem, top);
    }

    // pop an element from the stack
    public char pop() {
        char popped = top.getElem();

        // Just referr to the next element on the stack
        // as being the top element now
        top = top.getNext();

        return popped;
    }

    // true if the stack is empty
    public boolean isEmpty() {
        return top == null;
    }
}
