package frostPrimeMod.cards;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.powers.TakingSecondsPower;
import frostPrimeMod.relics.boss.PowerfulInfluenceRelic;
import frostPrimeMod.util.CardInfo;

import java.util.ArrayList;
import java.util.Iterator;

import static frostPrimeMod.frostPrimeMod.makeID;

public abstract class ChooseOneBaseCard extends BaseCard{


    private ArrayList<AbstractCard> stanceChoices;

    public ChooseOneBaseCard(CardInfo cardInfo) {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
    }

    protected void setChoices(ArrayList<AbstractCard> stanceChoices){
        this.stanceChoices = stanceChoices;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator var4 = stanceChoices.iterator();

        while(var4.hasNext()) {
            AbstractCard c = (AbstractCard)var4.next();
            if (this.upgraded) {
                c.upgrade();
            }
            c.calculateCardDamage(m);
            ((ChooseOneBaseOption)c).ParentCard = this;
            ((ChooseOneBaseOption)c).parentType = this.type;
        }
        this.type = CardType.STATUS;
        if (AbstractDungeon.player.hasRelic(makeID("POWERFUL_INFLUENCE_RELIC"))){
            if (!((PowerfulInfluenceRelic)AbstractDungeon.player.getRelic(makeID("POWERFUL_INFLUENCE_RELIC"))).UsedThisFight) {
                var4 = stanceChoices.iterator();

                while (var4.hasNext()) {
                    AbstractCard c = (AbstractCard) var4.next();
                    c.onChoseThisOption();
                }
                ((PowerfulInfluenceRelic)AbstractDungeon.player.getRelic(makeID("POWERFUL_INFLUENCE_RELIC"))).UsedThisFight = true;
                return;
            }
        }
        if (p.hasPower(makeID("TAKING_SECONDS_POWER"))){
            ((TakingSecondsPower)p.getPower(makeID("TAKING_SECONDS_POWER"))).setLastChoices(stanceChoices);
        }
        this.addToBot(new ChooseOneAction(stanceChoices));
    }
}
