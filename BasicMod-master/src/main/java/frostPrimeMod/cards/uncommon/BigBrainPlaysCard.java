package frostPrimeMod.cards.uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.cards.ChooseOneBaseCard;
import frostPrimeMod.cards.chooseOneOptions.bigBrainPlays.BigBrainPlaysDefenseOption;
import frostPrimeMod.cards.chooseOneOptions.bigBrainPlays.BigBrainPlaysOffenseOption;
import frostPrimeMod.cards.chooseOneOptions.sugarGlide.SugarGlideBabyOption;
import frostPrimeMod.cards.chooseOneOptions.sugarGlide.SugarGlideBugsOption;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.util.CardInfo;

import java.util.ArrayList;

import static frostPrimeMod.BasicMod.makeID;

public class BigBrainPlaysCard extends ChooseOneBaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "BIG_BRAIN_PLAYS_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            1, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;


    public BigBrainPlaysCard() {
        super(cardInfo);
        setMagic(MAGIC, UPG_MAGIC);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new BigBrainPlaysDefenseOption(p, m));
        stanceChoices.add(new BigBrainPlaysOffenseOption(p, m));
        setChoices(stanceChoices);
        super.use(p, m);
    }
}
