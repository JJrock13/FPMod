package frostPrimeMod.cards;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Reboot;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.util.CardInfo;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class DrawSpecificCardsBaseCard extends ChooseOneBaseOption {

    private ArrayList<String> IDs;
    private AbstractPlayer p;
    private AbstractMonster m;
    public DrawSpecificCardsBaseCard(CardInfo cardInfo) {
        super(cardInfo);
        IDs = new ArrayList<String>();
    }

    protected void setIDs(ArrayList<String> inputs){
        IDs = inputs;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.p = p;
        this.m = m;
        onChoseThisOption();
    }
    public void onChoseThisOption() {
        ArrayList<AbstractCard> drawnCards = new ArrayList<>();
        Iterator var2 = AbstractDungeon.player.drawPile.group.iterator();
        AbstractCard c = null;
        for (int i = 0; i < magicNumber; i ++) {
            if (var2.hasNext()){
                c = (AbstractCard) var2.next();
            } else {
                shuffleDiscardIntoDeck();
                var2 = AbstractDungeon.player.drawPile.group.iterator();
                if (var2.hasNext()){
                    c = (AbstractCard) var2.next();
                } else {
                    break;
                }
            }
            if (AbstractDungeon.player.hand.group.size() >= 10){
                AbstractDungeon.player.drawPile.moveToDiscardPile(c);
                var2 = AbstractDungeon.player.drawPile.group.iterator();
            } else {
                AbstractDungeon.player.drawPile.moveToHand(c);
                drawnCards.add(c);
                var2 = AbstractDungeon.player.drawPile.group.iterator();
            }
        }
        for (AbstractCard card : drawnCards){
            if (!isMatch(card.cardID)) {
                this.addToBot(new DiscardSpecificCardAction(card));
            }
        }
        ChooseAgain(AbstractDungeon.player, AbstractDungeon.getRandomMonster());
    }

    private void shuffleDiscardIntoDeck(){
        Iterator var2 = AbstractDungeon.player.discardPile.group.iterator();
        while (var2.hasNext() ) {
            AbstractCard c = (AbstractCard) var2.next();
            AbstractDungeon.player.discardPile.moveToDeck(c, true);
            var2 = AbstractDungeon.player.discardPile.group.iterator();
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
