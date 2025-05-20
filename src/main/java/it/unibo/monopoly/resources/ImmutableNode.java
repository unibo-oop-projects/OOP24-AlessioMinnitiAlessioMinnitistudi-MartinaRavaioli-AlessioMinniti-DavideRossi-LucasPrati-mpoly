package it.unibo.monopoly.resources;

public final class ImmutableNode<T> {
    private final T value;
    private final Node<T> next;

    public ImmutableNode(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

    public T getValue() {
        return this.value;
    }

    public Node<T> getNext() {
        return this.next;
    }
}
