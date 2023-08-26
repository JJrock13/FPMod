package frostPrimeMod.cards.chooseOneOptions.gangUp;

import frostPrimeMod.cards.GetCardsFromDiscardPileBaseCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.util.CardInfo;

import java.util.ArrayList;

import static frostPrimeMod.BasicMod.makeID;

public class GangUpClothedMoleRatGangOption extends GetCardsFromDiscardPileBaseCard {

    private final static CardInfo cardInfo = new CardInfo(
            "GANG_UP_CLOTHED_MOLE_RAT_GANG_OPTION", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            -2, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);

    public GangUpClothedMoleRatGangOption() {
        super(cardInfo);
        ArrayList<String> IDs = new ArrayList<>();
        IDs.add(makeID("CLOTHED_MOLE_RAT_GANG_CARD"));
        IDs.add(makeID("CLOTHED_MOLE_RAT_GANG_SLAM_CARD"));
        super.setIDs(IDs);
    }
}
