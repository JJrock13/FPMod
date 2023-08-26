package frostPrimeMod.cards.chooseOneOptions.gangUp;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.cards.ChooseOneBaseOption;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.util.CardInfo;

import java.util.Iterator;

import static frostPrimeMod.BasicMod.makeID;

public class GangUpAllGangsOption extends ChooseOneBaseOption {

    private final static CardInfo cardInfo = new CardInfo(
            "GANG_UP_ALL_GANGS_OPTION", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            -2, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);

    private AbstractPlayer p;
    private AbstractMonster m;

    public GangUpAllGangsOption() {
        super(cardInfo);
    }

    public GangUpAllGangsOption(AbstractPlayer p, AbstractMonster m) {
        super(cardInfo);
        this.p = p;
        this.m = m;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }
    public void onChoseThisOption() {
        Iterator var2 = AbstractDungeon.player.discardPile.group.iterator();
        boolean cumGangFound = false;
        boolean MILFGangFound = false;
        boolean clothedMoleRatGangFound = false;
        boolean clawGangFound = false;
        AbstractCard cumCard = null;
        AbstractCard MILFCard= null;
        AbstractCard moleCard= null;
        AbstractCard clawCard= null;

        while(var2.hasNext()) {
            AbstractCard c = (AbstractCard)var2.next();
            if ((c.cardID.equals(makeID("CUM_GANG_CARD")) || (c.cardID.equals(makeID("CUM_GANG_SLAM_CARD")))) && (!cumGangFound)){
                cumCard = c;
                cumGangFound = true;
            }
            if ((c.cardID.equals(makeID("MILF_GANG_CARD")) || (c.cardID.equals(makeID("MILF_GANG_SLAM_CARD")))) && (!MILFGangFound)){
                MILFCard = c;
                MILFGangFound = true;
            }
            if ((c.cardID.equals(makeID("CLOTHED_MOLE_RAT_GANG_CARD")) || (c.cardID.equals(makeID("CLOTHED_MOLE_RAT_GANG_SLAM_CARD")))) && (!clothedMoleRatGangFound)){
                moleCard = c;
                clothedMoleRatGangFound = true;
            }
            if (c.cardID.equals(makeID("CLAW_GANG_CARD")) && (!clawGangFound)){
                clawCard = c;
                clawGangFound = true;
            }
        }
        if (cumCard != null) {
            this.addToBot(new DiscardToHandAction(cumCard));
        }
        if (MILFCard != null) {
            this.addToBot(new DiscardToHandAction(MILFCard));
        }
        if (moleCard != null) {
            this.addToBot(new DiscardToHandAction(moleCard));
        }
        if (clawCard != null) {
            this.addToBot(new DiscardToHandAction(clawCard));
        }
        ChooseAgain(p, m);
    }

}
