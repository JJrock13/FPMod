package frostPrimeMod.cards.uncommon.gangCards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.patches.AbstractCard_Class_Patch;
import frostPrimeMod.relics.starter.TwitchStreamerRelic;
import frostPrimeMod.util.CardInfo;

import static frostPrimeMod.frostPrimeMod.makeID;

public class GangInfluenceCard extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "GANG_INFLUENCE_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            1, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);


    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = -2;

    public GangInfluenceCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
        setMagic(MAGIC, UPG_MAGIC); //Sets the card's Block and how much it increases when upgraded.
        exhaust = true;
        AbstractCard_Class_Patch.isGangCard.set(this,true);
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int numCards = 0;
        numCards += (p.getPower(makeID("CUM_GANG_POWER")) != null)? p.getPower(makeID("CUM_GANG_POWER")).amount : 0;
        numCards += (p.getPower(makeID("MILF_GANG_POWER")) != null)? p.getPower(makeID("MILF_GANG_POWER")).amount : 0;
        numCards += (p.getPower(makeID("CLAW_GANG_POWER")) != null)? p.getPower(makeID("CLAW_GANG_POWER")).amount : 0;
        numCards += (p.getPower(makeID("CLOTHED_MOLE_RAT_GANG_POWER")) != null)? p.getPower(makeID("CLOTHED_MOLE_RAT_GANG_POWER")).amount : 0;
        for (AbstractRelic r : p.relics){
            if (r.relicId.equals(makeID("TWITCH_STREAMER_RELIC"))){
                ((TwitchStreamerRelic) r).addSubscribers((int)(numCards / magicNumber));
            }
        }
    }

}
