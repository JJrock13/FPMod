package frostPrimeMod.cards.chooseOneOptions.sugarGlide;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.cards.ChooseOneBaseOption;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.util.CardInfo;

import static frostPrimeMod.frostPrimeMod.makeID;

public class SugarGlideBugsOption extends ChooseOneBaseOption {

    private final static CardInfo cardInfo = new CardInfo(
            "SUGAR_GLIDE_BUGS_OPTION", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            -2, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 3;
    AbstractPlayer p;
    AbstractMonster m;

    public SugarGlideBugsOption() {
        super(cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);
        this.keywords.add("frostprimethespire:stray_mutt_bones(Oliver)");
    }

    public SugarGlideBugsOption(AbstractPlayer p, AbstractMonster m) {
        super(cardInfo);
        this.p = p;
        this.m = m;
        setDamage(DAMAGE, UPG_DAMAGE);
        this.keywords.add("frostprimethespire:stray_mutt_bones(Oliver)");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }
    public void onChoseThisOption() {
        calculateCardDamage(m);
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        ChooseAgain(p, m);
    }

}
