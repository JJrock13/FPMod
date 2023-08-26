package frostPrimeMod.cards.common.gangCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.powers.ClawGangPower;
import frostPrimeMod.powers.ClothedMoleRatGangPower;
import frostPrimeMod.powers.CumGangPower;
import frostPrimeMod.powers.MILFGangPower;
import frostPrimeMod.util.CardInfo;

import static frostPrimeMod.BasicMod.makeID;

public class MILFGangCard extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "MILF_GANG_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            1, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);


    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;

    private static final int MAGIC = 2;

    private static final int UPG_MAGIC = 1;

    public MILFGangCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
        setBlock(BLOCK, UPG_BLOCK); //Sets the card's Block and how much it increases when upgraded.
        setMagic(MAGIC, UPG_MAGIC);
        this.keywords.add("frostprimethespire:markenfreemanview");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int numCards = 0;
        if (p.hasPower(makeID("MILF_GANG_POWER"))){
            numCards = p.getPower(makeID("MILF_GANG_POWER")).amount;
        }
        this.addToBot(new GainBlockAction(p, (block + (magicNumber * numCards))));
        this.addToBot(new ApplyPowerAction(p, p, new MILFGangPower(p, 1)));
        if (p.getPower(makeID("GANG_LORD_POWER")) != null) {
            for(int i = 0; i < p.getPower(makeID("GANG_LORD_POWER")).amount; i ++) {
                this.addToBot(new ApplyPowerAction(p, p, new MILFGangPower(p, 1)));
            }
        }
        if (p.getPower(makeID("JOINING_FORCES_POWER")) != null) {
            this.addToBot(new ApplyPowerAction(p, p, new ClothedMoleRatGangPower(p, 1)));
            this.addToBot(new ApplyPowerAction(p, p, new ClawGangPower(p, 1)));
            this.addToBot(new ApplyPowerAction(p, p, new CumGangPower(p, 1)));
        }
    }

}
