package frostPrimeMod.cards.uncommon.gangCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.purple.FlurryOfBlows;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.patches.AbstractCard_Class_Patch;
import frostPrimeMod.util.CardInfo;

import java.util.ArrayList;

import static frostPrimeMod.BasicMod.makeID;

public class GangSheepCard extends BaseCard {

    private final static CardInfo cardInfo = new CardInfo(
            "GANG_SHEEP_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            0, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;
    private ArrayList<String> IDs;

    public GangSheepCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
        this.keywords.add("frostprimethespire:luckfars");
        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it increases when upgraded.
        AbstractCard_Class_Patch.isGangCard.set(this,true);
        IDs = new ArrayList<String>();
        IDs.add(makeID("CLAW_GANG_CARD"));
        IDs.add(makeID("CLOTHED_MOLE_RAT_GANG_CARD"));
        IDs.add(makeID("CLOTHED_MOLE_RAT_GANG_SLAM_CARD"));
        IDs.add(makeID("CUM_GANG_CARD"));
        IDs.add(makeID("CUM_GANG_SLAM_CARD"));
        IDs.add(makeID("MILF_GANG_CARD"));
        IDs.add(makeID("MILF_GANG_SLAM_CARD"));
        IDs.add(makeID("GANG_GANG_CARD"));
        IDs.add(makeID("GANG_GANG_SLAM_CARD"));
    }

    @Override
    public void triggerOnCardPlayed(AbstractCard c){
        if (isMatch(c.cardID)){
            this.addToBot(new DiscardToHandAction(this));
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

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }
}
