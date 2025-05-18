package it.unibo.monopoly.resources;

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
     * constructor.
     * @param value
     * @param nextNode
    */
    public Node(final T value, final Node<T> nextNode) {
        this.value = value;
        this.nextNode = nextNode;
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
        return this.nextNode;
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
        this.nextNode = node;
    }
}
