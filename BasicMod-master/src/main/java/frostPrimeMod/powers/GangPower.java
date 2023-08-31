package frostPrimeMod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;

import static frostPrimeMod.frostPrimeMod.makeID;

public abstract class GangPower extends BasePower{
    public GangPower(String id, PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount) {
        super(id, powerType, isTurnBased, owner, amount);
    }



    private boolean hasGangLordPower(AbstractCreature target){
        if (target.getPower(makeID("GANG_LORD_POWER")) != null){
            return true;
        } else {
            return false;
        }
    }
}
