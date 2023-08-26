package frostPrimeMod.cards.uncommon.streamCards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.NecronomicurseEffect;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.util.CardInfo;

import static frostPrimeMod.BasicMod.makeID;

public class SponseredByRaidShadowLegendsCard extends BaseCard implements OnObtainCard {

    private final static CardInfo cardInfo = new CardInfo(
            "SPONSERED_BY_RAID_SHADOW_LEGENDS_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            -2, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int MAGIC = 75;



    public SponseredByRaidShadowLegendsCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
        setMagic(MAGIC);
        setEthereal(true);

    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = "There's no going back.";
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }


    public void onRemoveFromMasterDeck() {

        AbstractDungeon.effectsQueue.add(new NecronomicurseEffect(new SponseredByRaidShadowLegendsCard(), (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));

    }

    @Override
    public void onObtainCard() {

    }
}
