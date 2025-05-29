package it.unibo.monopoly.utils.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * node for the linked lists.
 * @param <T> type of the node
*/
@SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Node is internal and safe to expose in this context")
public class Node<T> {

    private T value; /**value. */
    private Node<T> nextNode; /**next node. */
    /**
     * constructor.
     * @param value value of the node
    */
    public Node(final T value) {
        this.value = value;
        this.nextNode = null;
    }
    /**
     * constructor.
     * @param value value of the node
     * @param nextNode the next node linked to this one
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
     * @param value value of the node
    */
    public void setValue(final T value) {
        this.value = value;
    }
    /**
     * set the next node.
     * @param node node of the list
    */
    public void setNextNode(final Node<T> node) {
        this.nextNode = node;
    }
}
