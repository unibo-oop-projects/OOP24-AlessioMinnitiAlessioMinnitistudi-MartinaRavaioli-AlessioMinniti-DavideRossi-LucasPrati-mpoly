package it.unibo.monopoly.model.gameboard.impl.chance_comunity.impl;

import it.unibo.monopoly.model.gameboard.impl.chance_comunity.api.Command;

public abstract class BaseCommand implements Command {

    private String keyword;

    @Override
    public String getKeyWord() {
        return this.keyword;
    }

}
