package frostPrimeMod.cards.chooseOneOptions.tasteTheWild;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.cards.Basic.SugarGlideCard;
import frostPrimeMod.cards.ChooseOneBaseOption;
import frostPrimeMod.cards.common.animalCards.*;
import frostPrimeMod.cards.rare.animalCards.HampterInTheShadowsCard;
import frostPrimeMod.cards.uncommon.animalCards.BaybladeCard;
import frostPrimeMod.cards.uncommon.animalCards.GliderTagTeamCard;
import frostPrimeMod.cards.uncommon.animalCards.HampterHideCard;
import frostPrimeMod.cards.uncommon.animalCards.HardyBoyStompCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.util.CardInfo;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static frostPrimeMod.BasicMod.makeID;

public class TasteTheWildFormAPackOption extends ChooseOneBaseOption {

    private final static CardInfo cardInfo = new CardInfo(
            "TASTE_THE_WILD_FORM_A_PACK_OPTION", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            -2, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    private AbstractPlayer p;
    private AbstractMonster m;

    private ArrayList<String> animalCards;

    public TasteTheWildFormAPackOption() {
        super(cardInfo);
        setMagic(MAGIC, UPG_MAGIC);
        animalCards = new ArrayList<>();
        animalCards.add(BevinBounceCard.ID);
        animalCards.add(BevinsBashCard.ID);
        animalCards.add(BladeCronch.ID);
        animalCards.add(BunnyTagTeamCard.ID);
        animalCards.add(BunsBeautyCard.ID);
        animalCards.add(CockroachCrunchCard.ID);
        animalCards.add(GoBabyCard.ID);
        animalCards.add(GoBugsCard.ID);
        animalCards.add(HardyHowlCard.ID);
        animalCards.add(BaybladeCard.ID);
        animalCards.add(GliderTagTeamCard.ID);
        animalCards.add(HampterHideCard.ID);
        animalCards.add(HardyBoyStompCard.ID);
        animalCards.add(HampterInTheShadowsCard.ID);
        animalCards.add(SugarGlideCard.ID);
    }

    public TasteTheWildFormAPackOption(AbstractPlayer p, AbstractMonster m) {
        super(cardInfo);
        this.p = p;
        this.m = m;
        setMagic(MAGIC, UPG_MAGIC);
        animalCards = new ArrayList<>();
        animalCards.add(BevinBounceCard.ID);
        animalCards.add(BevinsBashCard.ID);
        animalCards.add(BladeCronch.ID);
        animalCards.add(BunnyTagTeamCard.ID);
        animalCards.add(BunsBeautyCard.ID);
        animalCards.add(CockroachCrunchCard.ID);
        animalCards.add(GoBabyCard.ID);
        animalCards.add(GoBugsCard.ID);
        animalCards.add(HardyHowlCard.ID);
        animalCards.add(BaybladeCard.ID);
        animalCards.add(GliderTagTeamCard.ID);
        animalCards.add(HampterHideCard.ID);
        animalCards.add(HardyBoyStompCard.ID);
        animalCards.add(HampterInTheShadowsCard.ID);
        animalCards.add(SugarGlideCard.ID);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p, this.magicNumber));
    }

    private boolean isAnimalCard(AbstractCard card){
        for (String s : animalCards){
            if (s.equals(card.cardID)){
                return true;
            }
        }
        return false;
    }

    public void onChoseThisOption() {
        ArrayList<AbstractCard> hand = AbstractDungeon.player.hand.group;
        for (AbstractCard c : hand){
            if (isAnimalCard(c)){
                c.setCostForTurn(-9);
            }
        }
        ChooseAgain(p, m);
    }

}
