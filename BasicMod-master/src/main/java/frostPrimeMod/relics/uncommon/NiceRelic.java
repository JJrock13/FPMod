package frostPrimeMod.relics.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.relics.BaseRelic;

import static frostPrimeMod.BasicMod.makeID;

public class NiceRelic extends BaseRelic {
    private static final String NAME = "NICE_RELIC"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.UNCOMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.SOLID; //The sound played when the relic is clicked.


    public NiceRelic() {
        super(ID, NAME, FrostCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }


    @Override
    public void onEnterRoom(AbstractRoom room) {
        super.onEnterRoom(room);
        if (AbstractDungeon.player.maxHealth != 69){
            AbstractDungeon.player.maxHealth = 69;
        }
    }

}
