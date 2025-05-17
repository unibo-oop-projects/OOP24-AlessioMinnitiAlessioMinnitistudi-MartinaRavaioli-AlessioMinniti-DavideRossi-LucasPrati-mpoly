package it.unibo.monopoly.utils;

/**
 * node for the linked lists.
 * @param <T>
*/
public class Node<T> {

    private T value;
    private Node<T> nextNode;
    /**
     * constructor.
     * @param value
    */
    public Node(final T value) {
        this.value = value;
        this.nextNode = null;
    }
    /**
     * get the value.
     * @return T
    */
    public T getValue() {
        return this.value;
    }
    /**
     * get the next node.
     * @return Node
    */
    public Node<T> getNextNode() {
        return new Node<>(this.nextNode.getValue());
    }
    /**
     * set the value.
     * @param value
    */
    public void setValue(final T value) {
        this.value = value;
    }
    /**
     * set the next node.
     * @param node
    */
    public void setNextNode(final Node<T> node) {
        this.nextNode = new Node<>(node.getValue());
    }
}
