package it.unibo.monopoly.resources;

public class Node<T> {

    T value;
    Node<T> nextNode;

    public Node(T value) {
        this.value = value;
    }
}
