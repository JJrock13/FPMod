package frostPrimeMod.cards.common.animalCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.util.CardInfo;

import java.util.ArrayList;

import static frostPrimeMod.BasicMod.makeID;

public class BunsBeautyCard extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "BUNS_BEAUTY_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            1, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.ALL_ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 3;

    public BunsBeautyCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
        setMagic(MAGIC, UPG_MAGIC);
        setBlock(BLOCK, UPG_BLOCK);
        this.keywords.add("frostprimethespire:AnimalCard");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
        for (AbstractMonster curM : monsters){
            if ((curM.intent == AbstractMonster.Intent.ATTACK) || (curM.intent == AbstractMonster.Intent.ATTACK_BUFF) || (curM.intent == AbstractMonster.Intent.ATTACK_DEBUFF) || (curM.intent == AbstractMonster.Intent.ATTACK_DEFEND)){
                this.addToBot(new ApplyPowerAction(curM, p, new WeakPower(curM, magicNumber, false)));
            } else {
                this.addToBot(new ApplyPowerAction(curM, p, new VulnerablePower(curM, magicNumber, false)));
            }
        }
        this.addToBot(new GainBlockAction(p, p, block));
    }

}
