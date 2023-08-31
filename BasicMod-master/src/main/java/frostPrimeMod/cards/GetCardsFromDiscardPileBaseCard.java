package frostPrimeMod.cards;

import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.util.CardInfo;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class GetCardsFromDiscardPileBaseCard extends ChooseOneBaseOption {

    private ArrayList<String> IDs;

    public GetCardsFromDiscardPileBaseCard(CardInfo cardInfo) {
        super(cardInfo);
        IDs = new ArrayList<String>();
    }

    protected void setIDs(ArrayList<String> inputs){
        IDs = inputs;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }
    public void onChoseThisOption() {
        Iterator var2 = AbstractDungeon.player.discardPile.group.iterator();
        while (var2.hasNext()) {
            AbstractCard c = (AbstractCard) var2.next();
            if (isMatch(c.cardID)) {
                this.addToBot(new DiscardToHandAction(c));
            }
        }
        ChooseAgain(AbstractDungeon.player, AbstractDungeon.getRandomMonster());
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
