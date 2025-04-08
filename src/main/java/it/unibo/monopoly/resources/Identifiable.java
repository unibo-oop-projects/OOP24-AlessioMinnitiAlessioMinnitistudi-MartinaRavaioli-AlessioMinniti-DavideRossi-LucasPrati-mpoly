package it.unibo.monopoly.resources;

public interface Identifiable<E> {
    E getID();

    void setID(int value);
}
