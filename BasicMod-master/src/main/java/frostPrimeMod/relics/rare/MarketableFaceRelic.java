package frostPrimeMod.relics.rare;

import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.relics.BaseRelic;

import static frostPrimeMod.frostPrimeMod.makeID;

public class MarketableFaceRelic extends BaseRelic {
    private static final String NAME = "MARKETABLE_FACE_RELIC"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.RARE; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.


    public MarketableFaceRelic() {
        super(ID, NAME, FrostCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


}
