package frostPrimeMod.cards.chooseOneOptions.selloutToChat;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.cards.ChooseOneBaseOption;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.relics.starter.TwitchStreamerRelic;
import frostPrimeMod.util.CardInfo;

import static frostPrimeMod.BasicMod.makeID;

public class SelloutToChatForTheMemesOption extends ChooseOneBaseOption {

    private final static CardInfo cardInfo = new CardInfo(
            "SELLOUT_TO_CHAT_FOR_THE_MEMES_OPTION", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            -2, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;
    private AbstractPlayer p;
    private AbstractMonster m;

    public SelloutToChatForTheMemesOption() {
        super(cardInfo);
        setMagic(MAGIC, UPG_MAGIC);
    }

    public SelloutToChatForTheMemesOption(AbstractPlayer p, AbstractMonster m) {
        super(cardInfo);
        this.p = p;
        this.m = m;
        setMagic(MAGIC, UPG_MAGIC);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.p = p;
        this.m = m;
        onChoseThisOption();
    }
    public void onChoseThisOption() {
        if (p.hasRelic(makeID("TWITCH_STREAMER_RELIC"))){
            ((TwitchStreamerRelic)p.getRelic(makeID("TWITCH_STREAMER_RELIC"))).addSubscribers(magicNumber);
        }
        ChooseAgain(p, m);
    }

}
