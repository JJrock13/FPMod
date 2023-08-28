package frostPrimeMod.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import frostPrimeMod.actions.FishFactAction;
import frostPrimeMod.cards.chooseOneOptions.marineBiologist.AmmoniaCard;

import static frostPrimeMod.BasicMod.makeID;

public class NitrogenCyclePower extends BasePower {
    public static final String POWER_ID = makeID("NITROGEN_CYCLE_POWER");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.
    public NitrogenCyclePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
    public void atStartOfTurn(){
        this.addToBot(new FishFactAction());
        this.addToBot(new MakeTempCardInHandAction(new AmmoniaCard(), amount));
    }
}
