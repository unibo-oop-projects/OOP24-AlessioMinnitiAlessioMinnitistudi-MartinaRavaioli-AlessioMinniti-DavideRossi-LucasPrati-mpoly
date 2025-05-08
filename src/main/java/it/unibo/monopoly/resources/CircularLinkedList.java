package it.unibo.monopoly.resources;

/**
 * circular linked list.
 * @param <T>
*/
public final class CircularLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    /**
     * add a node.
     * @param value
    */
    public void addNode(final T value) {
        final Node<T> newNode = new Node<>(value);
        if (this.head == null) {
            this.head = newNode;
        } else {
            tail.setNextNode(newNode);
        }
        this.tail = newNode;
        this.tail.setNextNode(this.head);
    }

    /**
     * get the next node.
     * @param value
     * @return T
    */
    public T giveNextNode(final T value) {
        Node<T> currentNode = this.head;

        while (currentNode != null) {
            if (currentNode.getValue() == value) {
                return currentNode.getNextNode().getValue();
            }
            currentNode = currentNode.getNextNode();
        }

        return null;
    }
    /**
     * control if contains a node.
     * @param searchValue
     * @return boolean
    */
    public boolean containsNode(final T searchValue) {
        Node<T> currentNode = this.head;
        if (this.head == null) {
            return false;
        } else {
            do {
                if (currentNode.getValue() == searchValue) {
                    return true;
                }
                currentNode = currentNode.getNextNode();
            } while (currentNode != this.head);
            return false;
        }
    }
    /**
     * delete a node.
     * @param valueToDelete
    */
    public void deleteNode(final T valueToDelete) {
        Node<T> currentNode = this.head;
        if (this.head == null) { // the list is empty
            return;
        }
        do {
            final Node<T> nextNode = currentNode.getNextNode();
            if (nextNode.getValue() == valueToDelete) {
                if (this.tail == this.head) { // the list has only one single element
                    this.head = null;
                    this.tail = null;
                } else {
                    currentNode.setNextNode(nextNode.getNextNode());
                    if (this.head == nextNode) { //we're deleting the head
                        this.head = this.head.getNextNode();
                    }
                    if (this.tail == nextNode) { //we're deleting the tail
                        this.tail = currentNode;
                    }
                }
                break;
            }
            currentNode = nextNode;
        } while (currentNode != this.head);
    }

}
