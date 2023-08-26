package frostPrimeMod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import frostPrimeMod.cards.common.gangCards.ClawGangCard;

import static frostPrimeMod.BasicMod.makeID;

public class ClawGangPower extends GangPower{
    public static final String POWER_ID = makeID("CLAW_GANG_POWER");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public ClawGangPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);

        for (AbstractCard c : AbstractDungeon.player.drawPile.group){
            if (c.cardID.equals(makeID("CLAW_GANG_CARD"))){
                ((ClawGangCard)c).calculateBaseDamage(1);
            }
        }
        for (AbstractCard c : AbstractDungeon.player.hand.group){
            if (c.cardID.equals(makeID("CLAW_GANG_CARD"))){
                ((ClawGangCard)c).calculateBaseDamage(1);
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group){
            if (c.cardID.equals(makeID("CLAW_GANG_CARD"))){
                ((ClawGangCard)c).calculateBaseDamage(1);
            }
        }

    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);
        if (power.ID.equals(makeID("CLAW_GANG_POWER"))){
            for (AbstractCard c : AbstractDungeon.player.drawPile.group){
                if (c.cardID.equals(makeID("CLAW_GANG_CARD"))){
                    ((ClawGangCard)c).calculateBaseDamage(1);
                }
            }
            for (AbstractCard c : AbstractDungeon.player.hand.group){
                if (c.cardID.equals(makeID("CLAW_GANG_CARD"))){
                    ((ClawGangCard)c).calculateBaseDamage(1);
                }
            }
            for (AbstractCard c : AbstractDungeon.player.discardPile.group){
                if (c.cardID.equals(makeID("CLAW_GANG_CARD"))){
                    ((ClawGangCard)c).calculateBaseDamage(1);
                }
            }
        }
    }
}
