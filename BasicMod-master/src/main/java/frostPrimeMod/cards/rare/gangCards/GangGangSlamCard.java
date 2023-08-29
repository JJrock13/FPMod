package frostPrimeMod.cards.rare.gangCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.cards.uncommon.gangCards.ClothedMoleRatGangSlamCard;
import frostPrimeMod.cards.uncommon.gangCards.CumGangSlamCard;
import frostPrimeMod.cards.uncommon.gangCards.MILFGangSlamCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.patches.AbstractCard_Class_Patch;
import frostPrimeMod.util.CardInfo;

import static frostPrimeMod.BasicMod.makeID;

public class GangGangSlamCard extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "GANG_GANG_SLAM_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            3, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            AbstractCard.CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            AbstractCard.CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            AbstractCard.CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);


    public GangGangSlamCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
        AbstractCard_Class_Patch.isGangCard.set(this,true);
        setCostUpgrade(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard newCumSlam = new CumGangSlamCard();
        AbstractCard newMILFSlam = new MILFGangSlamCard();
        AbstractCard newCMRSlam = new ClothedMoleRatGangSlamCard();
        if (p.hand.group.size() < 10) {
            p.hand.addToHand(newCumSlam);
        }
        if (p.hand.group.size() < 10) {
            p.hand.addToHand(newMILFSlam);
        }
        if (p.hand.group.size() < 10) {
            p.hand.addToHand(newCMRSlam);
        }
        newCMRSlam.isEthereal = true;
        newCMRSlam.exhaustOnUseOnce = true;
        newCMRSlam.rawDescription += " NL Exhaust, Ethereal";
        newCMRSlam.update();
        newCMRSlam.setCostForTurn(0);
        newMILFSlam.isEthereal = true;
        newMILFSlam.exhaustOnUseOnce = true;
        newMILFSlam.rawDescription += " NL Exhaust, Ethereal";
        newMILFSlam.setCostForTurn(0);
        newCumSlam.isEthereal = true;
        newCumSlam.exhaustOnUseOnce = true;
        newCumSlam.rawDescription += " NL Exhaust, Ethereal";
        newCumSlam.setCostForTurn(0);


    }
}
