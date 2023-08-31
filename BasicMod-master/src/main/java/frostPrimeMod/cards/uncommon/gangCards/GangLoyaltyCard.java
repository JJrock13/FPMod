package frostPrimeMod.cards.uncommon.gangCards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.cards.ChooseOneBaseCard;
import frostPrimeMod.cards.chooseOneOptions.gangLoyalty.GangLoyaltyClawGangOption;
import frostPrimeMod.cards.chooseOneOptions.gangLoyalty.GangLoyaltyClothedMoleRatGangOption;
import frostPrimeMod.cards.chooseOneOptions.gangLoyalty.GangLoyaltyCumGangOption;
import frostPrimeMod.cards.chooseOneOptions.gangLoyalty.GangLoyaltyMILFGangOption;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.patches.AbstractCard_Class_Patch;
import frostPrimeMod.util.CardInfo;

import java.util.ArrayList;

import static frostPrimeMod.frostPrimeMod.makeID;

public class GangLoyaltyCard extends ChooseOneBaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "GANG_LOYALTY_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            1, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final int MAGIC = 5;
    private static final int UPG_MAGIC = 3;
    public AbstractPlayer p;
    public AbstractMonster m;

    public GangLoyaltyCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
        setMagic(MAGIC, UPG_MAGIC);
        AbstractCard_Class_Patch.isGangCard.set(this,true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.p = p;
        this.m = m;
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
        stanceChoices.add(new GangLoyaltyCumGangOption());
        stanceChoices.add(new GangLoyaltyMILFGangOption());
        stanceChoices.add(new GangLoyaltyClothedMoleRatGangOption());
        stanceChoices.add(new GangLoyaltyClawGangOption());
        setChoices(stanceChoices);
        super.use(p, m);
    }

}
