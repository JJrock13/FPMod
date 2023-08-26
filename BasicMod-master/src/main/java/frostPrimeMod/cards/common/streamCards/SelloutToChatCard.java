package frostPrimeMod.cards.common.streamCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Offering;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.Byrd;
import frostPrimeMod.cards.ChooseOneBaseCard;
import frostPrimeMod.cards.chooseOneOptions.selloutToChat.SelloutToChatForTheCashOption;
import frostPrimeMod.cards.chooseOneOptions.selloutToChat.SelloutToChatForTheMemesOption;
import frostPrimeMod.cards.chooseOneOptions.sugarGlide.SugarGlideBabyOption;
import frostPrimeMod.cards.chooseOneOptions.sugarGlide.SugarGlideBugsOption;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.powers.FollowersPower;
import frostPrimeMod.util.CardInfo;

import java.util.ArrayList;

import static frostPrimeMod.BasicMod.makeID;

public class SelloutToChatCard extends ChooseOneBaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "SELLOUT_TO_CHAT_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            0, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 25;
    private static final int UPG_MAGIC = 10;


    public SelloutToChatCard() {
        super(cardInfo);
        upgradesDescription = true;
        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);
    }
    public void use(AbstractPlayer p, AbstractMonster m){
        this.addToBot(new DamageAction(p, new DamageInfo(new Byrd(0, 0), 4), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new SelloutToChatForTheCashOption(p, m));
        stanceChoices.add(new SelloutToChatForTheMemesOption(p, m));
        setChoices(stanceChoices);
        super.use(p, m);
        this.addToBot(new ApplyPowerAction(p, p, new FollowersPower(p, 1)));
    }
}
