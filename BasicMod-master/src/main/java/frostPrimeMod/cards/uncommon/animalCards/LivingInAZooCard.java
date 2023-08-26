package frostPrimeMod.cards.uncommon.animalCards;

import com.megacrit.cardcrawl.cards.red.DoubleTap;
import com.megacrit.cardcrawl.powers.DoubleTapPower;
import frostPrimeMod.cards.Basic.SugarGlideCard;
import frostPrimeMod.cards.DrawSpecificCardsBaseCard;
import frostPrimeMod.cards.common.animalCards.*;
import frostPrimeMod.cards.rare.animalCards.HampterInTheShadowsCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.util.CardInfo;

import java.util.ArrayList;

import static frostPrimeMod.BasicMod.makeID;

public class LivingInAZooCard extends DrawSpecificCardsBaseCard {

    private final static CardInfo cardInfo = new CardInfo(
            "LIVING_IN_A_ZOO_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            1, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 3;

    public LivingInAZooCard() {
        super(cardInfo);
        setMagic(MAGIC, UPG_MAGIC);
        ArrayList<String> IDs = new ArrayList<>();
        IDs.add(BevinBounceCard.ID);
        IDs.add(BevinsBashCard.ID);
        IDs.add(BladeCronch.ID);
        IDs.add(BunnyTagTeamCard.ID);
        IDs.add(BunsBeautyCard.ID);
        IDs.add(CockroachCrunchCard.ID);
        IDs.add(GoBabyCard.ID);
        IDs.add(GoBugsCard.ID);
        IDs.add(HardyHowlCard.ID);
        IDs.add(BaybladeCard.ID);
        IDs.add(GliderTagTeamCard.ID);
        IDs.add(HampterHideCard.ID);
        IDs.add(HardyBoyStompCard.ID);
        IDs.add(HampterInTheShadowsCard.ID);
        IDs.add(SugarGlideCard.ID);
        super.setIDs(IDs);
        this.keywords.add("frostprimethespire:Memorial");
        this.keywords.add("frostprimethespire:AnimalCard");
    }
}
