package frostPrimeMod.relics.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.powers.ClawGangPower;
import frostPrimeMod.powers.ClothedMoleRatGangPower;
import frostPrimeMod.powers.CumGangPower;
import frostPrimeMod.powers.MILFGangPower;
import frostPrimeMod.relics.BaseRelic;

import static frostPrimeMod.BasicMod.makeID;

public class NaturalLeaderRelic extends BaseRelic {
    private static final String NAME = "NATURAL_LEADER_RELIC"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.RARE; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.


    public NaturalLeaderRelic() {
        super(ID, NAME, FrostCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void atBattleStart(){
        super.atBattleStart();
        AbstractCreature p = AbstractDungeon.player;
        if (AbstractDungeon.isPlayerInDungeon()) {
            this.addToBot(new ApplyPowerAction(p, p, new CumGangPower(p, 2)));
            this.addToBot(new ApplyPowerAction(p, p, new MILFGangPower(p, 2)));
            this.addToBot(new ApplyPowerAction(p, p, new ClothedMoleRatGangPower(p, 2)));
            this.addToBot(new ApplyPowerAction(p, p, new ClawGangPower(p, 2)));
        }
    }
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

}
