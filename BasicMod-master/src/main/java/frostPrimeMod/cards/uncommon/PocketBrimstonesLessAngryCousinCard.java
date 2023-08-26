package frostPrimeMod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.util.CardInfo;

import static frostPrimeMod.BasicMod.makeID;

public class PocketBrimstonesLessAngryCousinCard extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "POCKET_BRIMSTONES_LESS_ANGRY_COUSIN_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            1, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.ALL, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);


    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public PocketBrimstonesLessAngryCousinCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
        this.keywords.add("frostprimethespire:luckfars");
        setMagic(MAGIC, UPG_MAGIC); //Sets the card's Block and how much it increases when upgraded.
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster mn) {
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber)));
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters){
            this.addToBot(new ApplyPowerAction(m, p, new MetallicizePower(m, 1)));
        }
    }

}
