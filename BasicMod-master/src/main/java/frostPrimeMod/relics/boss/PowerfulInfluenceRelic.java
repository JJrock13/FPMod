package frostPrimeMod.relics.boss;

import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.relics.BaseRelic;

import static frostPrimeMod.frostPrimeMod.makeID;

public class PowerfulInfluenceRelic extends BaseRelic {
    private static final String NAME = "POWERFUL_INFLUENCE_RELIC"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.SOLID; //The sound played when the relic is clicked.

    public boolean UsedThisFight;
    public PowerfulInfluenceRelic() {
        super(ID, NAME, FrostCharacter.Enums.CARD_COLOR, RARITY, SOUND);
        UsedThisFight = false;
    }

    @Override
    public void atPreBattle() {
        super.atPreBattle();
        UsedThisFight = false;
    }
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
