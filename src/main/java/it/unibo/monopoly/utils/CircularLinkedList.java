package it.unibo.monopoly.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * circular linked list.
 * @param <T>
*/
public final class CircularLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    /**
     * constructor.
    */
    public CircularLinkedList() {
        this.head = null;
        this.tail = null;
    }
    /**
     * return the head.
     * @return value
    */
    public T getHead() {
        return this.head.getValue();
    }
    /**
     * add a node.
     * @param value
    */
    public void addNode(final T value) {
        final Node<T> newNode = new Node<>(value);
        if (this.head == null) {
        // Lista vuota: inizializza head e tail, e collega a sé stessa
        this.head = newNode;
        this.tail = newNode;
        newNode.setNextNode(newNode);
        } else {
        // Aggiunta in coda
        this.tail.setNextNode(newNode);
        newNode.setNextNode(this.head);
        this.tail = newNode;
        }
    }
    /**
     * convert to a list.
     * @return List
    */
    public List<T> toList() {
        final List<T> list = new ArrayList<>();
        if (this.head == null) {
            return list;
        }
        Node<T> current = this.head;
        do {
            if (!list.contains(current.getValue())) {
                list.add(current.getValue());
            }
            current = current.getNextNode();
        } while (!current.equals(this.head));
        return list;
    }

    /**
     * get the next node.
     * @param value
     * @return T
    */
    public T giveNextNode(final T value) {
        Node<T> currentNode = this.head;

        while (currentNode != null) {
            if (currentNode.getValue().equals(value)) {
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
                if (currentNode.getValue().equals(searchValue)) {
                    return true;
                }
                currentNode = currentNode.getNextNode();
            } while (!currentNode.equals(this.head));
            return false;
        }
    }
    /**
     * delete a node.
     * @param valueToDelete
    */
    public void deleteNode(final T valueToDelete) {
        if (this.head == null) { // the list is empty
            return;
        }
        Node<T> currentNode = this.head;
        do {
            final Node<T> nextNode = currentNode.getNextNode();
            if (nextNode.getValue().equals(valueToDelete)) {
                if (this.tail.equals(this.head)) { // the list has only one single element
                    this.head = null;
                    this.tail = null;
                } else {
                    currentNode.setNextNode(nextNode.getNextNode());
                    if (this.head.equals(nextNode)) { //we're deleting the head
                        this.head = this.head.getNextNode();
                    }
                    if (this.tail.equals(nextNode)) { //we're deleting the tail
                        this.tail = currentNode;
                    }
                }
                break;
            }
            currentNode = nextNode;
        } while (!currentNode.equals(this.head));
    }

}
