package frostPrimeMod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static frostPrimeMod.frostPrimeMod.makeID;

public class HumanShieldPower extends GangPower{
    public static final String POWER_ID = makeID("HUMAN_SHIELD_POWER");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private boolean triggered;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public HumanShieldPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        triggered = false;

    }

    @Override
    public int onAttacked(DamageInfo damageInfo, int damage){
        damage = super.onAttacked(damageInfo, damage);
        FollowersPower power = null;
        if (damage > 0){
            if (owner.hasPower(makeID("FOLLOWERS_POWER"))){
                power = ((FollowersPower)owner.getPower(makeID("FOLLOWERS_POWER")));
            } else {
                return damage;
            }
            if (damage <= owner.currentBlock) {
                return damage;
            }
            if (power.amount > 0){
                int numSubs = power.amount;
                int originalDamage = damage;
                damage -= owner.currentBlock;
                damage -= numSubs;
                if (damage < 0){
                    damage = owner.currentBlock;
                } else {
                    damage += owner.currentBlock;
                }
                this.addToBot(new ApplyPowerAction(owner, owner, new FollowersPower(owner, -1 * (originalDamage - owner.currentBlock))));
                return damage;
            } else {
                return damage;
            }
        } else {
            return damage;
        }
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
