package it.unibo.monopoly.resources;

public class Node<T> {

    private T value;
    private Node<T> nextNode;

    public Node(T value) {
        this.value = value;
    }

    public T getValue(){
        return this.value;
    }

    public Node<T> getNextNode(){
        return this.nextNode;
    }

    public void setValue(T value){
        this.value=value;
    }

    public void setNextNode(Node<T> node){
        this.nextNode=node;
    }
}
