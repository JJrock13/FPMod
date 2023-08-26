package frostPrimeMod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import frostPrimeMod.cards.Basic.SugarGlideCard;
import frostPrimeMod.cards.common.animalCards.*;
import frostPrimeMod.cards.rare.animalCards.HampterInTheShadowsCard;
import frostPrimeMod.cards.uncommon.animalCards.BaybladeCard;
import frostPrimeMod.cards.uncommon.animalCards.GliderTagTeamCard;
import frostPrimeMod.cards.uncommon.animalCards.HampterHideCard;
import frostPrimeMod.cards.uncommon.animalCards.HardyBoyStompCard;
import frostPrimeMod.relics.starter.TwitchStreamerRelic;

import java.util.ArrayList;

import static frostPrimeMod.BasicMod.makeID;

public class TheyCameForThePetsPower extends BasePower{
    public static final String POWER_ID = makeID("THEY_CAME_FOR_THE_PETS_POWER");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    private ArrayList<String> animalCards;
    //The only thing this controls is the color of the number on the power icon.
    //Turn based powers are white, non-turn based powers are red or green depending on if they're a buff or debuff.
    //For a power to actually decrease/go away on its own they do it themselves.
    //Look at powers that do this like VulnerablePower and DoubleTapPower.

    public TheyCameForThePetsPower(AbstractCreature owner, int amount) {
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
    }


    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        super.onAfterCardPlayed(usedCard);
        boolean animalCardPlayed = false;

        if (animalCardPlayed){
            this.addToBot(new ApplyPowerAction(owner, owner, new FollowersPower(owner, amount)));
        }
    }

    @Override
    public void atEndOfRound(){
        this.addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        if (this.amount == 0) {
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
    }
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

}
