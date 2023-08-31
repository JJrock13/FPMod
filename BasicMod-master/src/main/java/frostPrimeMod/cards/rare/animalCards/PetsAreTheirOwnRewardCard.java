package frostPrimeMod.cards.rare.animalCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.cards.Basic.SugarGlideCard;
import frostPrimeMod.cards.common.animalCards.*;
import frostPrimeMod.cards.uncommon.animalCards.*;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.powers.PetsAreTheirOwnRewardPower;
import frostPrimeMod.util.CardInfo;

import java.util.ArrayList;

import static frostPrimeMod.frostPrimeMod.makeID;

public class PetsAreTheirOwnRewardCard extends BaseCard {

    private final static CardInfo cardInfo = new CardInfo(
            "PETS_ARE_THEIR_OWN_REWARD_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            3, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;
    private ArrayList<String> animalCards;

    public PetsAreTheirOwnRewardCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
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
        animalCards.add(IllGiveYouATreatCard.ID);
        animalCards.add(LivingInAZooCard.ID);
        animalCards.add(PetsAreTheirOwnRewardCard.ID);
        animalCards.add(TasteTheWildCard.ID);
        animalCards.add(TheyCameForThePetsCard.ID);
        animalCards.add(TooCuteCard.ID);

        this.keywords.add("frostprimethespire:AnimalCard");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> cardsInHand = AbstractDungeon.player.hand.group;
        for (AbstractCard card : cardsInHand){
            boolean animalCardPlayed = false;
            for (String s : animalCards){
                if (card.cardID.equals(s)){
                    animalCardPlayed = true;
                    this.flash();
                }
            }
            if (animalCardPlayed) {
                card.setCostForTurn(card.cost - magicNumber);
            }
        }
        this.addToBot(new ApplyPowerAction(p, p, new PetsAreTheirOwnRewardPower(p, magicNumber)));
    }
}
