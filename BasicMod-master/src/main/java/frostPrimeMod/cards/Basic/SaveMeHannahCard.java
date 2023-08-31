package frostPrimeMod.cards.Basic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.powers.FollowersPower;
import frostPrimeMod.util.CardInfo;

import static frostPrimeMod.frostPrimeMod.makeID;

public class SaveMeHannahCard extends BaseCard {

    private final static CardInfo cardInfo = new CardInfo(
            "SAVE_ME_HANNAH", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            2, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);


    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 5;
    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    public SaveMeHannahCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
        this.keywords.add("frostprimethespire:luckfars");
        setBlock(BLOCK, UPG_BLOCK); //Sets the card's Block and how much it increases when upgraded.
        setMagic(MAGIC, UPG_MAGIC);
        this.selfRetain = true;
    }

    @Override
    public void onRetained(){
        this.addToBot(new GainBlockAction(AbstractDungeon.player,  this.magicNumber));
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        this.addToBot(new ApplyPowerAction(p, p, new FollowersPower(p, 1)));
    }
}
