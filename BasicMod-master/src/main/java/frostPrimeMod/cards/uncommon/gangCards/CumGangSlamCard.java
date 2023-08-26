package frostPrimeMod.cards.uncommon.gangCards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.powers.ClawGangPower;
import frostPrimeMod.powers.ClothedMoleRatGangPower;
import frostPrimeMod.powers.CumGangPower;
import frostPrimeMod.powers.MILFGangPower;
import frostPrimeMod.util.CardInfo;

import static frostPrimeMod.BasicMod.makeID;

public class CumGangSlamCard extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "CUM_GANG_SLAM_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            1, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);

    private int cardsPlayedThisTurn;

    public CumGangSlamCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
        cardsPlayedThisTurn = 0;
        setMagic(0);
    }

    @Override
    public void atTurnStart(){
        super.atTurnStart();
        cardsPlayedThisTurn = 0;
        upDateMagic();
    }

    @Override
    public void triggerOnCardPlayed(AbstractCard card){
        super.triggerOnCardPlayed(card);
        cardsPlayedThisTurn ++;
        upDateMagic();
    }

    private void upDateMagic() {
        this.magicNumber = AbstractDungeon.player.hand.group.size() + cardsPlayedThisTurn;
        if (upgraded){
            this.magicNumber *= 2;
        }
        super.isMagicNumberModified = true;
    }

    @Override
    public void hover(){
        super.hover();
        if (AbstractDungeon.isPlayerInDungeon()) {
            upDateMagic();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.magicNumber, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.addToBot(new ApplyPowerAction(p, p, new CumGangPower(p, 1)));
        if (p.getPower(makeID("GANG_LORD_POWER")) != null) {
            for(int i = 0; i < p.getPower(makeID("GANG_LORD_POWER")).amount; i ++) {
                this.addToBot(new ApplyPowerAction(p, p, new CumGangPower(p, 1)));
            }
        }
        if (p.getPower(makeID("JOINING_FORCES_POWER")) != null) {
            this.addToBot(new ApplyPowerAction(p, p, new MILFGangPower(p, 1)));
            this.addToBot(new ApplyPowerAction(p, p, new ClothedMoleRatGangPower(p, 1)));
            this.addToBot(new ApplyPowerAction(p, p, new ClawGangPower(p, 1)));
        }
    }

}
