package frostPrimeMod.cards.uncommon.gangCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.patches.AbstractCard_Class_Patch;
import frostPrimeMod.powers.ClawGangPower;
import frostPrimeMod.powers.ClothedMoleRatGangPower;
import frostPrimeMod.powers.CumGangPower;
import frostPrimeMod.powers.MILFGangPower;
import frostPrimeMod.util.CardInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static frostPrimeMod.BasicMod.makeID;

public class ClothedMoleRatGangSlamCard extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "CLOTHED_MOLE_RAT_GANG_SLAM_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            2, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.ALL_ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.UNCOMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 0;
    private Random rand;

    public ClothedMoleRatGangSlamCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
        setDamage(DAMAGE, UPG_DAMAGE);
        AbstractCard_Class_Patch.isGangCard.set(this,true);
        rand = new Random(System.currentTimeMillis());
    }

    private <T> T chooseRandom(List<T> in){
        return in.get(rand.nextInt(in.size()));
    }

    private ArrayList<ApplyPowerAction> selectDebuff(AbstractMonster m){
        ArrayList<ArrayList<ApplyPowerAction>> debuffs = new ArrayList<>();
        ArrayList<ApplyPowerAction> temp = new ArrayList<>();

        temp.add(new ApplyPowerAction(m, AbstractDungeon.player, new VulnerablePower(m, 1, false), 1));
        debuffs.add(temp);

        temp = new ArrayList<>();
        temp.add(new ApplyPowerAction(m, AbstractDungeon.player, new WeakPower(m, 1, false), 1));
        debuffs.add(temp);

        temp = new ArrayList<>();
        temp.add(new ApplyPowerAction(m, AbstractDungeon.player, new StrengthPower(m, -1), -1));
        if (m != null && !m.hasPower("Artifact")) {
            temp.add(new ApplyPowerAction(m, AbstractDungeon.player, new GainStrengthPower(m, 1), 1));
        }
        debuffs.add(temp);
        return chooseRandom(debuffs);
    }
    private ArrayList<ApplyPowerAction> selectBuff(){
        ArrayList<ArrayList<ApplyPowerAction>> buffs = new ArrayList<>();
        ArrayList<ApplyPowerAction> temp = new ArrayList<>();
        temp.add(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
        temp.add(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseStrengthPower(AbstractDungeon.player, 1), 1));
        buffs.add(temp);

        temp = new ArrayList<>();
        temp.add(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new DexterityPower(AbstractDungeon.player, 1), 1));
        temp.add(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new LoseDexterityPower(AbstractDungeon.player, 1), 1));
        buffs.add(temp);

        temp = new ArrayList<>();
        temp.add(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ThornsPower(AbstractDungeon.player, 1), 1));
        buffs.add(temp);

        temp = new ArrayList<>();
        temp.add(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new PlatedArmorPower(AbstractDungeon.player, 1), 1));
        buffs.add(temp);

        return chooseRandom(buffs);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
        int numHits = (p.getPower(makeID("CLOTHED_MOLE_RAT_GANG_POWER")) != null)? (p.getPower(makeID("CLOTHED_MOLE_RAT_GANG_POWER")).amount + 1) : 1;
        for (AbstractMonster curM : monsters){
            if (curM.currentHealth > 0) {
                calculateCardDamage(curM);
                this.addToBot(new DamageAction(curM, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                for (int i = 0; i < numHits; i++) {
                    if (upgraded) {
                        if (rand.nextDouble() < 0.5) {
                            ArrayList<ApplyPowerAction> choice = selectBuff();
                            for (ApplyPowerAction a : choice) {
                                this.addToBot(a);
                            }
                        } else {
                            ArrayList<ApplyPowerAction> choice = selectDebuff(curM);
                            for (ApplyPowerAction a : choice) {
                                this.addToBot(a);
                            }
                        }
                    } else {
                        ArrayList<ApplyPowerAction> choice = selectDebuff(curM);
                        for (ApplyPowerAction a : choice) {
                            this.addToBot(a);
                        }
                    }
                }
                this.addToBot(new ApplyPowerAction(p, p, new ClothedMoleRatGangPower(p, 1)));
                if (p.getPower(makeID("GANG_LORD_POWER")) != null) {
                    for (int i = 0; i < p.getPower(makeID("GANG_LORD_POWER")).amount; i++) {
                        this.addToBot(new ApplyPowerAction(p, p, new ClothedMoleRatGangPower(p, 1)));
                    }
                }
                if (p.getPower(makeID("JOINING_FORCES_POWER")) != null) {
                    this.addToBot(new ApplyPowerAction(p, p, new MILFGangPower(p, 1)));
                    this.addToBot(new ApplyPowerAction(p, p, new ClawGangPower(p, 1)));
                    this.addToBot(new ApplyPowerAction(p, p, new CumGangPower(p, 1)));
                }
            }
        }
    }

}
