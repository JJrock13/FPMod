package frostPrimeMod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import frostPrimeMod.actions.FakeFactAction;
import frostPrimeMod.relics.starter.TwitchStreamerRelic;

import static frostPrimeMod.frostPrimeMod.makeID;
import java.util.Random;

public class MemeCyclePower extends BasePower {
    public static final String POWER_ID = makeID("MEME_CYCLE_POWER");
    private static final PowerType TYPE = PowerType.BUFF;
    public static int counter = 0;
    private static final boolean TURN_BASED = false;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.
    public MemeCyclePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }
    public void atStartOfTurn(){
        Random rand = new Random();

        this.addToBot(new FakeFactAction());
        if (this.counter < (1 + amount - 1)){
            int randInt = rand.nextInt(2);
            if (randInt == 0){
                this.addToBot(new ApplyPowerAction(owner, owner, new VulnerablePower(owner,2, false)));
            }
            else {
                this.addToBot(new ApplyPowerAction(owner, owner, new WeakPower(owner,2, false)));
            }
        }
        else if (this.counter < (3 + amount - 1)){
            int randInt = rand.nextInt(2);
            if (randInt == 0){
                this.addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner,-2)));
            }
            else {
                this.addToBot(new ApplyPowerAction(owner, owner, new DexterityPower(owner,-2)));
            }
        }
        else if (this.counter < (5 + amount - 1)){
            int randInt = rand.nextInt(2);
            if (randInt == 0){
                this.addToBot(new ApplyPowerAction(owner, owner, new HexPower(owner,1)));
            }
            else {
                this.addToBot(new ApplyPowerAction(owner, owner, new ConstrictedPower(owner,owner,5)));
            }
        }
        else {
            this.addToBot(new ApplyPowerAction(owner, owner, new BeatOfDeathPower(owner, 2)));
        }

        this.addToBot(new ApplyPowerAction(owner, owner, new FollowersPower(owner, 5)));
        ((TwitchStreamerRelic)AbstractDungeon.player.getRelic(makeID("TWITCH_STREAMER_RELIC"))).addSubscribers(1);
        this.counter ++;
    }
}
