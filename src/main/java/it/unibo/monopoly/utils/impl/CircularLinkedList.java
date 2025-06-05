package it.unibo.monopoly.utils.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * circular linked list.
 * @param <T> type of the list
*/
public final class CircularLinkedList<T> {
    private Node<T> head; /**head. */
    private Node<T> tail; /**tail. */
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
     * @param value value of the node
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
     * @param value value of the curr node
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
     * @param searchValue value to search
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
     * @param valueToDelete value to delete
    */
    public void deleteNode(final T valueToDelete) {
        if (this.head == null) {
            return; // lista vuota
        }

        Node<T> currentNode = this.head;
        Node<T> prevNode = this.tail; // inizializziamo il precedente come tail per gestire anche il primo nodo

        do {
            if (currentNode.getValue().equals(valueToDelete)) {
                if (this.head == this.tail) {
                    // Un solo nodo nella lista
                    this.head = null;
                    this.tail = null;
                } else {
                    prevNode.setNextNode(currentNode.getNextNode());

                    if (currentNode == this.head) {
                        this.head = currentNode.getNextNode();
                    }

                    if (currentNode == this.tail) {
                        this.tail = prevNode;
                    }
                }
                return; // uscita dopo aver eliminato il nodo
            }
            prevNode = currentNode;
            currentNode = currentNode.getNextNode();
        } while (currentNode != this.head);
    }
    /**
     * clear the list.
     */
    public void clear() {
        this.head = null;
        this.tail = null;
    }
}
