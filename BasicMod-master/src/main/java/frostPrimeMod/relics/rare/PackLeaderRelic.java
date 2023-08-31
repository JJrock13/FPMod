package frostPrimeMod.relics.rare;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.relics.BaseRelic;

import static frostPrimeMod.frostPrimeMod.makeID;

public class PackLeaderRelic extends BaseRelic {
    private static final String NAME = "PACK_LEADER_RELIC"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.RARE; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.HEAVY; //The sound played when the relic is clicked.


    public PackLeaderRelic() {
        super(ID, NAME, FrostCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        super.onPlayCard(c, m);
        if (c.keywords.contains("frostprimethespire:animalcard")){
            AbstractCreature p = AbstractDungeon.player;
            if (AbstractDungeon.isPlayerInDungeon()) {
                this.addToBot(new GainBlockAction(p, 3));
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


}
