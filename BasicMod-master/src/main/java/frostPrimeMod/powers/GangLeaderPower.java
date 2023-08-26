package frostPrimeMod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static frostPrimeMod.BasicMod.makeID;

public class GangLeaderPower extends BasePower{
    public static final String POWER_ID = makeID("GANG_LEADER_POWER");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public GangLeaderPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void atEndOfTurn(boolean isPlayer){
        if (isPlayer) {
            AbstractPlayer p = (AbstractPlayer) this.owner;
            for (int i = 0; i < amount; i ++) {
                this.addToBot(new ApplyPowerAction(p, p, new CumGangPower(p, 1)));
                this.addToBot(new ApplyPowerAction(p, p, new MILFGangPower(p, 1)));
                this.addToBot(new ApplyPowerAction(p, p, new ClothedMoleRatGangPower(p, 1)));
                this.addToBot(new ApplyPowerAction(p, p, new ClawGangPower(p, 1)));
            }
            if (p.getPower(makeID("GANG_LORD_POWER")) != null) {
                for(int i = 0; i < p.getPower(makeID("GANG_LORD_POWER")).amount; i ++) {
                    this.addToBot(new ApplyPowerAction(p, p, new CumGangPower(p, 1)));
                    this.addToBot(new ApplyPowerAction(p, p, new MILFGangPower(p, 1)));
                    this.addToBot(new ApplyPowerAction(p, p, new ClothedMoleRatGangPower(p, 1)));
                    this.addToBot(new ApplyPowerAction(p, p, new ClawGangPower(p, 1)));
                }
            }
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }


}
