package frostPrimeMod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import frostPrimeMod.actions.MulliganAction;

import static frostPrimeMod.frostPrimeMod.makeID;

public class MulliganPower extends BasePower{
    public static final String POWER_ID = makeID("MULLIGAN_POWER");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public MulliganPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }



    @Override
    public void atStartOfTurnPostDraw() {
        super.atStartOfTurnPostDraw();
        for (int i = 0; i < this.amount; i ++) {
            this.addToBot(new MulliganAction(this.owner, this.amount));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }


}
