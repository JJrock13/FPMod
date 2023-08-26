package frostPrimeMod.cards.common;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.ThunderStrike;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import frostPrimeMod.cards.BaseCard;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.powers.FollowersPower;
import frostPrimeMod.util.CardInfo;

import java.util.Iterator;

import static frostPrimeMod.BasicMod.makeID;

public class ANDREWCard extends BaseCard {

    private final static CardInfo cardInfo = new CardInfo(
            "ANDREW_CARD", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            2, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.ALL_ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.COMMON, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            FrostCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );
//hello
    public static final String ID = makeID(cardInfo.baseId);

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int DAMAGE = 14;
    private static final int UPG_DAMAGE = 6;
    public class AndrewAttackAction extends DamageAllEnemiesAction {
        private boolean hit = false;

        private int baseDamage;
        private boolean firstFrame;
        private boolean utilizeBaseDamage;

        public AndrewAttackAction(AbstractCreature source, int[] amount, DamageInfo.DamageType type, AttackEffect effect, boolean isFast) {
            super(source, amount, type, effect, isFast);
        }

        public AndrewAttackAction(AbstractCreature source, int[] amount, DamageInfo.DamageType type, AttackEffect effect) {
            super(source, amount, type, effect);
        }

        public AndrewAttackAction(AbstractPlayer player, int baseDamage, DamageInfo.DamageType type, AttackEffect effect) {
            super(player, baseDamage, type, effect);
            this.firstFrame = true;
            this.baseDamage = baseDamage;
            this.utilizeBaseDamage = true;
        }

        public void update() {
            int i;
            if (this.firstFrame) {
                boolean playedMusic = false;
                i = AbstractDungeon.getCurrRoom().monsters.monsters.size();
                if (this.utilizeBaseDamage) {
                    this.damage = DamageInfo.createDamageMatrix(this.baseDamage);
                }

                for (AbstractCreature c : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    if (!((AbstractMonster)c).isDying && ((AbstractMonster)c).currentHealth > 0 && !((AbstractMonster)c).isEscaping) {
                            this.addToTop(new VFXAction(new LightningEffect(c.drawX, c.drawY)));
                            this.addToTop(new VFXAction(new FlashAtkImgEffect(c.hb.cX, c.hb.cY, this.attackEffect)));
                            this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
                    }
                }

                this.firstFrame = false;
            }

            this.tickDuration();
            if (this.isDone) {
                Iterator var4 = AbstractDungeon.player.powers.iterator();

                while(var4.hasNext()) {
                    AbstractPower p = (AbstractPower)var4.next();
                    p.onDamageAllEnemies(this.damage);
                }

                int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();

                for(i = 0; i < temp; ++i) {
                    if (!((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).isDeadOrEscaped()) {
                        if (this.attackEffect == AttackEffect.POISON) {
                            ((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).tint.color.set(Color.CHARTREUSE);
                            ((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).tint.changeColor(Color.WHITE.cpy());
                        } else if (this.attackEffect == AttackEffect.FIRE) {
                            ((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).tint.color.set(Color.RED);
                            ((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).tint.changeColor(Color.WHITE.cpy());
                        }

                        ((AbstractMonster)AbstractDungeon.getCurrRoom().monsters.monsters.get(i)).damage(new DamageInfo(this.source, this.damage[i], this.damageType));
                    }
                }

                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                }

                if (!Settings.FAST_MODE) {
                    this.addToTop(new WaitAction(0.1F));
                }
            }

        }
    }

    public ANDREWCard() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.
        setDamage(DAMAGE, UPG_DAMAGE); //Sets the card's damage and how much it increases when upgraded.
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AndrewAttackAction(p, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.LIGHTNING));
        this.addToBot(new ApplyPowerAction(p, p, new FollowersPower(p, 3)));
    }
}
