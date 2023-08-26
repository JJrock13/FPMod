package frostPrimeMod.cards.rare.gangCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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

public class PlayingCatchUpCard extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "PLAYING_CATCH_UP_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            0, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public PlayingCatchUpCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
        setMagic(MAGIC, UPG_MAGIC);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int cumAmount = (p.getPower(makeID("CUM_GANG_POWER")) != null)? p.getPower(makeID("CUM_GANG_POWER")).amount : 0;
        int MILFAmount = (p.getPower(makeID("MILF_GANG_POWER")) != null)? p.getPower(makeID("MILF_GANG_POWER")).amount : 0;
        int ClawAmount = (p.getPower(makeID("CLAW_GANG_POWER")) != null)? p.getPower(makeID("CLAW_GANG_POWER")).amount : 0;
        int CMRAmount = (p.getPower(makeID("CLOTHED_MOLE_RAT_GANG_POWER")) != null)? p.getPower(makeID("CLOTHED_MOLE_RAT_GANG_POWER")).amount : 0;

        if (((cumAmount < MILFAmount) || (MILFAmount == 0)) && ((cumAmount < ClawAmount) || (ClawAmount == 0)) && ((cumAmount < CMRAmount) || (CMRAmount == 0)) && (cumAmount > 0)){
            this.addToBot(new ApplyPowerAction(p, p, new CumGangPower(p, magicNumber)));
        } else if (((MILFAmount < ClawAmount) || (ClawAmount == 0)) && ((MILFAmount < CMRAmount) || (CMRAmount == 0)) && (MILFAmount > 0)){
            this.addToBot(new ApplyPowerAction(p, p, new MILFGangPower(p, magicNumber)));
        } else if (((CMRAmount < ClawAmount) || (ClawAmount == 0)) && (CMRAmount > 0)){
            this.addToBot(new ApplyPowerAction(p, p, new ClothedMoleRatGangPower(p, magicNumber)));
        } else if (ClawAmount > 0){
            this.addToBot(new ApplyPowerAction(p, p, new ClawGangPower(p, magicNumber)));
        }
    }
}
