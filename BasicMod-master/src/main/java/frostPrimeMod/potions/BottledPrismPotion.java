package frostPrimeMod.potions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.CardHelper;
import frostPrimeMod.actions.PrismAction;
import frostPrimeMod.frostCharacter.FrostCharacter;

import static frostPrimeMod.frostPrimeMod.makeID;

public class BottledPrismPotion extends BasePotion{
    private static final String ID = makeID("BOTTLED_PRISM_POTION");
    private static final Color LIQUID_COLOR = CardHelper.getColor(232, 46, 45);
    private static final Color HYBRID_COLOR = CardHelper.getColor(153, 253, 103);
    private static final Color SPOTS_COLOR = null;
    public BottledPrismPotion() {
        super(ID, 1, PotionRarity.RARE, PotionSize.FAIRY, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
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
        this.addToBot(new PrismAction(potency));
    }
}
