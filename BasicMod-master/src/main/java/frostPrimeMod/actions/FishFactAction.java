package frostPrimeMod.actions;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class FishFactAction extends AbstractGameAction {
    public TextureAtlas.AtlasRegion fish1 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("basicmod/animations/fish1.png"),0,0,720,720);

    @Override
    public void update() {
        float fishStartX = -200f;
        float fishStartY = 1000f;
        float fishMidX = 800f;
        float fishEndX = 1800f;
        float fishEndY = 2000f;
        AbstractGameEffect fish = new VfxBuilder(fish1,fishStartX,fishStartY,2f)
                .setScale(.75f)
                .moveX(fishStartX,fishMidX,VfxBuilder.Interpolations.LINEAR)
                .whenComplete(vfxBuilder -> textBubble(vfxBuilder))
                .andThen(3f)
                .andThen(2f)
                .moveX(fishMidX,fishEndX,VfxBuilder.Interpolations.POW2IN)
                .moveY(fishStartY,fishEndY,VfxBuilder.Interpolations.POW2IN)
                .rotateTo(0,45,VfxBuilder.Interpolations.EXP5OUT)
                .build();

        AbstractDungeon.effectsQueue.add(fish);
        this.isDone = true;
    }
    public void textBubble(VfxBuilder vfx){
        AbstractGameEffect textBubble = new VfxBuilder(ImageMaster.SPEECH_BUBBLE_IMG, vfx.x + 500f, vfx.y, .5f)
                .setScale(0)
                .scale(0,1)
                .andThen(2.5f)
                .andThen(.5f)
                .setScale(1)
                .scale(1,0)
                .build();
        AbstractDungeon.effectsQueue.add(textBubble);
    }
}
