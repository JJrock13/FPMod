package frostPrimeMod.cards;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.powers.TakingSecondsPower;
import frostPrimeMod.util.CardInfo;

import static frostPrimeMod.frostPrimeMod.makeID;

public abstract class ChooseOneBaseOption extends BaseCard{

    public ChooseOneBaseCard ParentCard;
    public CardType parentType;
    public ChooseOneBaseOption(CardInfo cardInfo) {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
    }

    protected void ChooseAgain(AbstractPlayer p, AbstractMonster m){

        new UseCardAction(this, m);
        if (ParentCard != null) {
            ParentCard.type = parentType;
        }
        if (p.hasPower(makeID("TAKING_SECONDS_POWER"))){
            ((TakingSecondsPower)p.getPower(makeID("TAKING_SECONDS_POWER"))).repeat(this);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}
