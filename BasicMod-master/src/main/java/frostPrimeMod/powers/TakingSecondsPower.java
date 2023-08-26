package frostPrimeMod.powers;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import java.util.ArrayList;

import static frostPrimeMod.BasicMod.makeID;

public class TakingSecondsPower extends GangPower{
    public static final String POWER_ID = makeID("TAKING_SECONDS_POWER");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    private ArrayList<AbstractCard> LastChoices;
    private int numRepeats = 0;
    public TakingSecondsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void setLastChoices(ArrayList<AbstractCard> choices){
        LastChoices = choices;
        numRepeats = 0;
    }
    public void repeat(AbstractCard c){
        if (numRepeats < amount){
            if (LastChoices.size() > 1){
                LastChoices.remove(c);
                numRepeats ++;
                this.addToBot(new ChooseOneAction(LastChoices));
            }
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }


}
