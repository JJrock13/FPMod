package frostPrimeMod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import frostPrimeMod.relics.starter.TwitchStreamerRelic;

import static frostPrimeMod.frostPrimeMod.makeID;

public class IDontNeedEmPower extends GangPower implements TwitchStreamerRelic.SubscriberObserver {
    public static final String POWER_ID = makeID("I_DONT_NEED_EM_POWER");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public IDontNeedEmPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        if (((AbstractPlayer)owner).hasRelic(makeID("TWITCH_STREAMER_RELIC"))){
            ((TwitchStreamerRelic)((AbstractPlayer)owner).getRelic(makeID("TWITCH_STREAMER_RELIC"))).register(this);
        }
    }



    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }


    @Override
    public void countUpdate(int count) {
        if (count < 0) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, ((-1 * count) * amount)), 1));
        }
    }
}
