package frostPrimeMod.cards.common.streamCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.powers.FollowersPower;
import frostPrimeMod.relics.starter.TwitchStreamerRelic;
import frostPrimeMod.util.CardInfo;

import static frostPrimeMod.frostPrimeMod.makeID;

public class EndOfStreamFatigueCard extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "END_OF_STREAM_FATIGUE_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            -1, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.ALL, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);


    public EndOfStreamFatigueCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = this.energyOnUse;

        if (p.hasRelic("Chemical X")) {
            count += 2;
            p.getRelic("Chemical X").flash();
        }
        for (int i = 0; i < count; i ++) {
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, -1)));
            if (p.hasRelic(makeID("TWITCH_STREAMER_RELIC"))){
                ((TwitchStreamerRelic)p.getRelic(makeID("TWITCH_STREAMER_RELIC"))).addSubscribers(1);
            }
        }
        if (upgraded) {
            if (p.hasRelic(makeID("TWITCH_STREAMER_RELIC"))) {
                ((TwitchStreamerRelic) p.getRelic(makeID("TWITCH_STREAMER_RELIC"))).addSubscribers(1);
            }
        }
        p.energy.use(EnergyPanel.totalCount);
        this.addToBot(new ApplyPowerAction(p, p, new FollowersPower(p, 2)));
    }

}
