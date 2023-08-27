package frostPrimeMod.actions;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import frostPrimeMod.BasicMod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class AndrewAttackAction extends AbstractGameAction {
    private boolean hit = false;
    private int damage;
    public int counterMax;

    public TextureAtlas.AtlasRegion andrewFace = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("basicmod/andrewface.png"),0,0,157,230);

    public AndrewAttackAction(AbstractCreature source, int dmg, int magic) {
        this.damage = dmg;
        this.source = source;
        this.counterMax = magic;

        boolean playedMusic = false;

        Sfx storm = new Sfx("basicmod/sounds/storm.ogg");
        storm.play(5);

    }
    public void update() {
        AbstractGameEffect storm = new VfxBuilder(andrewFace, source.hb.cX + 600, source.hb.cY + 200, 2f)
                .whenStarted(vfxBuilder -> stormEffect(vfxBuilder))
                .setAlpha(.6f)
                .fadeOutFromAlpha(2f,.6f)
                .setScale(3)
                .build();
        AbstractDungeon.effectsQueue.add(storm);
        this.isDone = true;
    }
    public void stormEffect(VfxBuilder vfx){
        Random rand = new Random();
        float randFloat;
        int randInt;
        ArrayList<AbstractCreature> attackOrder = new ArrayList<>();
        ArrayList<AbstractCreature> enemiesLeft = (ArrayList<AbstractCreature>) BasicMod.aliveMonsters.clone();

        while (attackOrder.size() < BasicMod.aliveMonsters.size() * counterMax){
            randInt = rand.nextInt(enemiesLeft.size());
            attackOrder.add(enemiesLeft.get(randInt));
            int numRandInt = 0;
            for (int i = 0; i < attackOrder.size(); i++){
                if (attackOrder.get(i).equals(enemiesLeft.get(randInt))){
                    numRandInt ++;
                }
            }
            if (numRandInt >= counterMax){
                enemiesLeft.remove(randInt);
            }
        }

        for (AbstractCreature c : attackOrder) {
            this.addToBot(new VFXAction(new LightningEffect(c.drawX, c.drawY)));
            this.addToBot(new VFXAction(new FlashAtkImgEffect(c.hb.cX, c.hb.cY, this.attackEffect)));
            this.addToBot(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
            this.addToBot(new DamageAction(c, new DamageInfo(AbstractDungeon.player, this.damage),AttackEffect.LIGHTNING));
            randFloat = (rand.nextInt(50) / 100f);
            this.addToBot(new WaitAction(randFloat));
        }
    }
}