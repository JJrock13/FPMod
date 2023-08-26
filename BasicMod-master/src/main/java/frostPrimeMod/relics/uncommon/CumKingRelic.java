package frostPrimeMod.relics.uncommon;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.SneckoEye;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.relics.BaseRelic;

import static frostPrimeMod.BasicMod.makeID;

public class CumKingRelic extends BaseRelic {
    private static final String NAME = "CUM_KING_RELIC"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.UNCOMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked.


    public CumKingRelic() {
        super(ID, NAME, FrostCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onEquip() {
        super.onEquip();
        AbstractDungeon.player.masterHandSize += 1;
    }

    @Override
    public void onUnequip() {
        super.onUnequip();
        AbstractDungeon.player.masterHandSize -= 1;
    }

}
