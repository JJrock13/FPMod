package frostPrimeMod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static frostPrimeMod.BasicMod.makeID;

public class HampterHidePower extends GangPower{
    public static final String POWER_ID = makeID("HAMPTER_HIDE_POWER");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private boolean triggered;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public HampterHidePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        triggered = false;
    }

    @Override
    public int onAttacked(DamageInfo damageInfo, int damage){
        damage = super.onAttacked(damageInfo, damage);
        if (damage > owner.currentBlock) {
            if ((this.amount > 0) && !triggered) {
                this.addToTop(new ApplyPowerAction(owner, owner, new IntangiblePlayerPower(owner, 1)));
                triggered = true;
            }
            reducePower(1);
        }
        return damage;
    }
    @Override
    public void reducePower(int amount){
        super.reducePower(amount);
        if (this.amount <= 0){
            this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
    }

    @Override
    public void atStartOfTurn() {
        if (triggered){
            triggered = false;
        } else {
            reducePower(1);
        }
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }


}
