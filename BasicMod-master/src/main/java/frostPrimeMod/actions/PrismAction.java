package frostPrimeMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import frostPrimeMod.cards.ChooseOneBaseOption;
import frostPrimeMod.patches.AbstractCard_Class_Patch;

import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

public class PrismAction extends AbstractGameAction {
    public boolean retrieveCard = false;
    public int amount = 0;
    public PrismAction(int potency){
        this.amount = potency;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }
    @Override
    public void update() {
        ArrayList generatedCards;
        generatedCards = this.generateCardChoices();
        if (amount > 1) {
            for (Object c : generatedCards) {
                ((AbstractCard)c).upgrade();
            }
        }
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, CardRewardScreen.TEXT[1], true);
            this.tickDuration();
        } else {
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();

                    disCard.current_x = -1000.0F * Settings.xScale;

                    AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(disCard, (float) Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }
                this.retrieveCard = true;
            }
            this.tickDuration();
        }
    }

    public ArrayList<AbstractCard> generateCardChoices(){
        Random rand = new Random();
        ArrayList<AbstractCard> returnArray = new ArrayList<AbstractCard>();
        while (returnArray.size() != 3){
            boolean dupe = false;
            AbstractCard tmp = AbstractDungeon.returnTrulyRandomCardInCombat();
            ArrayList<AbstractCard> allCards = CardLibrary.getAllCards();
            int randInt = rand.nextInt(allCards.size());
            while ((allCards.get(randInt) instanceof ChooseOneBaseOption) || (allCards.get(randInt).rarity == AbstractCard.CardRarity.BASIC)){
                randInt = rand.nextInt(allCards.size());
            }
            tmp = allCards.get(randInt);

            Iterator i = returnArray.iterator();

            while (i.hasNext()){
                AbstractCard c = (AbstractCard) i.next();
                if (c.cardID.equals(tmp.cardID)){
                    dupe = true;
                    break;
                }
            }

            if (!dupe){
                returnArray.add(tmp.makeCopy());
            }
        }
        return returnArray;
    }
}
