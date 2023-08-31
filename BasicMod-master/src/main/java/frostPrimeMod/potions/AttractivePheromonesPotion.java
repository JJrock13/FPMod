package frostPrimeMod.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.CardHelper;
import frostPrimeMod.actions.PheromonesAction;
import frostPrimeMod.frostCharacter.FrostCharacter;


import static frostPrimeMod.frostPrimeMod.makeID;

public class AttractivePheromonesPotion extends BasePotion{
    private static final String ID = makeID("ATTRACTIVE_PHEROMONES_POTION");
    private static final Color LIQUID_COLOR = CardHelper.getColor(140, 58, 180);
    private static final Color HYBRID_COLOR = CardHelper.getColor(177, 61, 160);
    private static final Color SPOTS_COLOR = null;

    public AttractivePheromonesPotion() {
        super(ID, 1, PotionRarity.UNCOMMON, PotionSize.EYE, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
        playerClass = FrostCharacter.Enums.FROST_PRIME;
    }
    @Override
    public String getDescription() {
        if (potency == 1) {
            return potionStrings.DESCRIPTIONS[0];
        }
        else return potionStrings.DESCRIPTIONS[1];
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        this.addToBot(new PheromonesAction(potency));
    }

    @Override
    public void update() {

    }


}
