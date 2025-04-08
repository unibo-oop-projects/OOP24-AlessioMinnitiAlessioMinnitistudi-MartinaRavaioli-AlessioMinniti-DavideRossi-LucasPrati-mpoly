package it.unibo.monopoly.controller.api;


import java.util.List;
import it.unibo.monopoly.utils.PlayerSetup;

public interface MainMenuLogic {
    void onClickStart(List<PlayerSetup> players);
    void onClickShowRules();
}
