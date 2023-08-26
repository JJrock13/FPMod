package frostPrimeMod.cards.uncommon.gangCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.util.CardInfo;

import java.util.ArrayList;
import java.util.Iterator;

import static frostPrimeMod.BasicMod.makeID;

public class InfightingCard extends BaseCard {

    private final static CardInfo cardInfo = new CardInfo(
            "INFIGHTING_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            1, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 2;
    private ArrayList<String> IDs;

    public InfightingCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it increases when upgraded.
        IDs = new ArrayList<String>();
        IDs.add(makeID("CLAW_GANG_CARD"));
        IDs.add(makeID("CLOTHED_MOLE_RAT_GANG_CARD"));
        IDs.add(makeID("CLOTHED_MOLE_RAT_GANG_SLAM_CARD"));
        IDs.add(makeID("CUM_GANG_CARD"));
        IDs.add(makeID("CUM_GANG_SLAM_CARD"));
        IDs.add(makeID("MILF_GANG_CARD"));
        IDs.add(makeID("MILF_GANG_SLAM_CARD"));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();
        AbstractCard c = null;
        ArrayList<AbstractCard> discards = new ArrayList<>();
        while (var2.hasNext() ) {
            c = (AbstractCard) var2.next();
            if (isMatch(c.cardID)) {
                discards.add(c);
            }
        }
        for (AbstractCard card : discards){
            this.addToBot(new DiscardSpecificCardAction(card));
            this.addToBot(new DamageRandomEnemyAction(new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    private boolean isMatch(String ID){
        for (String s : IDs){
            if (s.equals(ID)){
                return true;
            }
        }
        return false;
    }
}
