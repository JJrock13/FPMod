package frostPrimeMod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import frostPrimeMod.relics.starter.TwitchStreamerRelic;

import static frostPrimeMod.BasicMod.makeID;

public class AggressiveAudiencePower extends GangPower implements TwitchStreamerRelic.SubscriberObserver {
    public static final String POWER_ID = makeID("AGGRESSIVE_AUDIENCE_POWER");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public AggressiveAudiencePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        if (((AbstractPlayer)owner).hasRelic(makeID("TWITCH_STREAMER_RELIC"))){
            ((TwitchStreamerRelic)((AbstractPlayer)owner).getRelic(makeID("TWITCH_STREAMER_RELIC"))).register(this);
        }
    }

    @Override
    public void onApplyPower(AbstractPower Power, AbstractCreature Target, AbstractCreature Source){
        if (Power.ID.equals(makeID("FOLLOWERS_POWER"))){
            for (int i = 0; i < Power.amount; i ++){
                this.addToBot(new DamageRandomEnemyAction(new DamageInfo(owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
        }
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }


    @Override
    public void countUpdate(int count) {
        for (int i = 0; i < count; i ++){
            this.addToBot(new DamageRandomEnemyAction(new DamageInfo(owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }
}
