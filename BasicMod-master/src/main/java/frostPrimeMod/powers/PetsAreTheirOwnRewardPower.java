package frostPrimeMod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import frostPrimeMod.cards.Basic.SugarGlideCard;
import frostPrimeMod.cards.common.animalCards.*;
import frostPrimeMod.cards.rare.animalCards.HampterInTheShadowsCard;
import frostPrimeMod.cards.rare.animalCards.PetsAreTheirOwnRewardCard;
import frostPrimeMod.cards.rare.animalCards.TasteTheWildCard;
import frostPrimeMod.cards.rare.animalCards.TooCuteCard;
import frostPrimeMod.cards.uncommon.animalCards.*;

import java.util.ArrayList;

import static frostPrimeMod.BasicMod.makeID;

public class PetsAreTheirOwnRewardPower extends BasePower{
    public static final String POWER_ID = makeID("PETS_ARE_THEIR_OWN_REWARD_POWER");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    private ArrayList<String> animalCards;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public PetsAreTheirOwnRewardPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
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
    }

    public void onCardDraw(AbstractCard card) {
        boolean animalCardPlayed = false;
        for (String s : animalCards){
            if (card.cardID.equals(s)){
                animalCardPlayed = true;
                this.flash();
            }
        }
        if (animalCardPlayed) {
            card.setCostForTurn(card.cost - amount);
        }

    }




    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}
