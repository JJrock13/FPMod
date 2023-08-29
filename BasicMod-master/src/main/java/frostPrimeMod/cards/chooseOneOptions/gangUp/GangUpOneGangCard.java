package frostPrimeMod.cards.chooseOneOptions.gangUp;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.cards.ChooseOneBaseCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.util.CardInfo;

import java.util.ArrayList;

import static frostPrimeMod.BasicMod.makeID;

public class GangUpOneGangCard extends ChooseOneBaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "GANG_UP_ONE_GANG_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            -2, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );
    public AbstractMonster m;
    public AbstractPlayer p;

    public static final String ID = makeID(cardInfo.baseId);


    public GangUpOneGangCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
    }
    public GangUpOneGangCard(AbstractPlayer p, AbstractMonster m) {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
        this.p = p;
        this.m = m;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new GangUpCumGangOption());
        stanceChoices.add(new GangUpMILFGangOption());
        stanceChoices.add(new GangUpClothedMoleRatGangOption());
        stanceChoices.add(new GangUpClawGangOption());
        setChoices(stanceChoices);
        super.use(p, m);
    }



}
