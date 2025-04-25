package it.unibo.monopoly.model.gameboard.impl;

public class Pair<X,Y> {

    private final X a;
    private final Y b;

    public Pair(X a, Y b){
        this.a = a;
        this.b = b;
    }

    public X a(){
        return this.a;
    }

    public Y b(){
        return this.b;
    }
    
}
