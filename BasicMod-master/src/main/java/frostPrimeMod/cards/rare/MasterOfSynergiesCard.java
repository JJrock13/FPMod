package frostPrimeMod.cards.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.powers.FollowersPower;
import frostPrimeMod.relics.starter.TwitchStreamerRelic;
import frostPrimeMod.util.CardInfo;

import java.util.ArrayList;

import static frostPrimeMod.BasicMod.makeID;

public class MasterOfSynergiesCard extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "MASTER_OF_SYNERGIES_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            3, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);



    public MasterOfSynergiesCard() {
        super(cardInfo);
        setExhaust(true);
        setCostUpgrade(2);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(makeID("FOLLOWERS_POWER"))){
            int numSubsToGain = 0;
            this.damage = (((FollowersPower)p.getPower(makeID("FOLLOWERS_POWER"))).amount / 4);
            addToBot(new DamageAllEnemiesAction(p, this.damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            if (p.hasPower(makeID("CLAW_GANG_POWER"))){
                numSubsToGain += p.getPower(makeID("CLAW_GANG_POWER")).amount;
            }
            if (p.hasPower(makeID("CUM_GANG_POWER"))){
                numSubsToGain += p.getPower(makeID("CUM_GANG_POWER")).amount;
            }
            if (p.hasPower(makeID("CLOTHED_MOLE_RAT_GANG_POWER"))){
                numSubsToGain += p.getPower(makeID("CLOTHED_MOLE_RAT_GANG_POWER")).amount;
            }
            if (p.hasPower(makeID("MILF_GANG_POWER"))){
                numSubsToGain += p.getPower(makeID("MILF_GANG_POWER")).amount;
            }
            ((TwitchStreamerRelic)p.getRelic(makeID("TWITCH_STREAMER_RELIC"))).addSubscribers(numSubsToGain);
        }
        ArrayList<AbstractCard> cardsInHand = AbstractDungeon.player.hand.group;
        int blockNum = 0;
        for (AbstractCard card : cardsInHand) {
            if (card.keywords.contains("frostprimethespire:animalcard")){
                blockNum += 5;
            }
        }
        this.addToBot(new GainBlockAction(p, blockNum));
        this.addToBot(new ApplyPowerAction(p, p, new FollowersPower(p, 4)));
    }
}
