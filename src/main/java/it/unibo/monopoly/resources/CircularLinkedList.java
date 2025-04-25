package it.unibo.monopoly.resources;

public class CircularLinkedList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;

    public void addNode(T value) {
        Node<T> newNode = new Node<T>(value);
    
        if (head == null) {
            head = newNode;
        } else {
            tail.nextNode = newNode;
        }
    
        tail = newNode;
        tail.nextNode = head;
    }

    public boolean containsNode(T searchValue) {
        Node<T> currentNode = head;
    
        if (head == null) {
            return false;
        } else {
            do {
                if (currentNode.value == searchValue) {
                    return true;
                }
                currentNode = currentNode.nextNode;
            } while (currentNode != head);
            return false;
        }
    }

    public void deleteNode(T valueToDelete) {
        Node<T> currentNode = head;
        if (head == null) { // the list is empty
            return;
        }
        do {
            Node<T> nextNode = currentNode.nextNode;
            if (nextNode.value == valueToDelete) {
                if (tail == head) { // the list has only one single element
                    head = null;
                    tail = null;
                } else {
                    currentNode.nextNode = nextNode.nextNode;
                    if (head == nextNode) { //we're deleting the head
                        head = head.nextNode;
                    }
                    if (tail == nextNode) { //we're deleting the tail
                        tail = currentNode;
                    }
                }
                break;
            }
            currentNode = nextNode;
        } while (currentNode != head);
    }

}
