package it.unibo.monopoly.resources;


public class CircularLinkedList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;

    public void addNode(T value) {
        Node<T> newNode = new Node<>(value);
    
        if (head == null) {
            head = newNode;
        } else {
            tail.setNextNode(newNode);
        }
    
        tail = newNode;
        tail.setNextNode(head);
    }

    public T giveNextNode(T value){
        Node<T> currentNode = head;

        while(currentNode != null){
            if(currentNode.getValue()==value){
                return currentNode.getNextNode().getValue();
            }
            currentNode = currentNode.getNextNode();
        }

        return null;
    }

    public boolean containsNode(T searchValue) {
        Node<T> currentNode = head;
    
        if (head == null) {
            return false;
        } else {
            do {
                if (currentNode.getValue() == searchValue) {
                    return true;
                }
                currentNode = currentNode.getNextNode();
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
            Node<T> nextNode = currentNode.getNextNode();
            if (nextNode.getValue() == valueToDelete) {
                if (tail == head) { // the list has only one single element
                    head = null;
                    tail = null;
                } else {
                    currentNode.setNextNode(nextNode.getNextNode());
                    if (head == nextNode) { //we're deleting the head
                        head = head.getNextNode();
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
