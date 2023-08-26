package frostPrimeMod.cards.common.streamCards;

import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.powers.FollowersPower;
import frostPrimeMod.relics.starter.TwitchStreamerRelic;
import frostPrimeMod.util.CardInfo;

import static frostPrimeMod.BasicMod.makeID;

public class CrowdfundedCard extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "CROWDFUNDED_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            2, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);

    public CrowdfundedCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
        this.keywords.add("frostprimethespire:luckfars");
        setExhaust(true);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int numGold = 0;
        int divisor = 3;
        if (upgraded){
            divisor = 2;
        }
        if (p.hasPower(makeID("FOLLOWERS_POWER"))){
            numGold = ((FollowersPower)p.getPower(makeID("FOLLOWERS_POWER"))).amount / divisor;
        }
        if (numGold <= 0){
            numGold = 1;
        }
        this.addToBot(new GainGoldAction(numGold));
    }

}
