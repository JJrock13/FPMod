package frostPrimeMod.cards.common.gangCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.powers.CumGangPower;
import frostPrimeMod.util.CardInfo;

import java.util.ArrayList;
import java.util.Iterator;

import static frostPrimeMod.BasicMod.makeID;

public class FindAFriendCard extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "FIND_A_FRIEND_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            0, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);

    private ArrayList<String> IDs;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public FindAFriendCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
        setMagic(MAGIC, UPG_MAGIC); //Sets the card's Block and how much it increases when upgraded.
        this.keywords.add("frostprimethespire:luckfars");
        IDs = new ArrayList<String>();
        IDs.add(makeID("CLAW_GANG_CARD"));
        IDs.add(makeID("CLOTHED_MOLE_RAT_GANG_CARD"));
        IDs.add(makeID("CLOTHED_MOLE_RAT_GANG_SLAM_CARD"));
        IDs.add(makeID("CUM_GANG_CARD"));
        IDs.add(makeID("CUM_GANG_SLAM_CARD"));
        IDs.add(makeID("MILF_GANG_CARD"));
        IDs.add(makeID("MILF_GANG_SLAM_CARD"));
        IDs.add(makeID("GANG_GANG_CARD"));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = magicNumber;
        Iterator var2 = AbstractDungeon.player.drawPile.group.iterator();
        while (var2.hasNext()) {
            AbstractCard c = (AbstractCard) var2.next();
            if (isMatch(c.cardID)) {
                if (p.hand.size() == 10) {
                    p.drawPile.moveToDiscardPile(c);
                    p.createHandIsFullDialog();
                } else {
                    p.drawPile.moveToHand(c, p.drawPile);
                    var2 = AbstractDungeon.player.drawPile.group.iterator();
                }
                AbstractDungeon.player.hand.refreshHandLayout();
                count --;
                if (count <= 0){
                    return;
                }
            }
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
