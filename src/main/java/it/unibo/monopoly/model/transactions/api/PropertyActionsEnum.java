package it.unibo.monopoly.model.transactions.api;

/**
 *  An enum that contains all possible types of actions that 
 * can be executed on a Property (so, to be clear, not a {@link Special} tile).
 * These actions may have an effect on the {@link Property} object as well as its
 * associated {@link TitleDeed}. The enum is only used to parameterize the type of
 * actions available, implementation is left to the user.
 */
public enum PropertyActionsEnum {
    /**
     * sell {@link TitleDeed} action type.
     */
    SELL("sell"),
    /**
     * buy {@link TitleDeed} action type.
     */
    BUY("buy"),
    /**
     * buy house action type.
     */
    BUYHOUSE("buy house"),
    /**
     * buy hotel action type.
     */
    BUYHOTEL("buy hotel"),
    /**
     * sell house action type.
     */
    SELLHOUSE("sell house"),
    /**
     * sell hotel naction type.
     */
    SELLHOTEL("sell hotel"),
    /**
     * pay rent for {@link TitleDeed}.
     */
    PAYRENT("pay rent");

    private final String name;

    PropertyActionsEnum(final String name) {
        this.name = name;
    }

    /**
     * Get a name description of the enum type.
     * @return a {@code String} representing the name
     */
    public String getActionName() {
        return this.name;
    }
}
