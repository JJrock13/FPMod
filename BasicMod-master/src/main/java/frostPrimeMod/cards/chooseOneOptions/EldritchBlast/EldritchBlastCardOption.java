package frostPrimeMod.cards.chooseOneOptions.EldritchBlast;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import frostPrimeMod.actions.EldritchBlastCardAction;
import frostPrimeMod.cards.ChooseOneBaseOption;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.util.CardInfo;

import static frostPrimeMod.BasicMod.makeID;

public class EldritchBlastCardOption extends ChooseOneBaseOption {

    private final static CardInfo cardInfo = new CardInfo(
            "ELDRITCH_BLAST_CARD_OPTION", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            -2, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE = 4;
    AbstractPlayer p;
    AbstractMonster m;

    public EldritchBlastCardOption() {
        super(cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);

    }

    public EldritchBlastCardOption(AbstractPlayer p, AbstractMonster m) {
        super(cardInfo);
        this.p = p;
        this.m = m;
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new EldritchBlastCardAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }
    public void onChoseThisOption() {
        calculateCardDamage(m);
        this.addToBot(new EldritchBlastCardAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
        ChooseAgain(p, m);
    }

}
