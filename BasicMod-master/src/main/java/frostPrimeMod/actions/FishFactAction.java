package frostPrimeMod.actions;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.ui.DialogWord;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.SpeechBubble;
import com.megacrit.cardcrawl.vfx.SpeechTextEffect;

import java.util.Random;

public class FishFactAction extends AbstractGameAction {
    public TextureAtlas.AtlasRegion fish1 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("frostPrimeResources/animations/fish1.png"),0,0,720,720);
    public TextureAtlas.AtlasRegion fish2 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("frostPrimeResources/animations/fish2.png"),0,0,512,299);
    public TextureAtlas.AtlasRegion[] fishies = {
            fish1,
            fish2
    };

    public String[] text = {
            "Fish Fact: The strongest female hammerhead shark swims in the middle of the pack."
    };
    public float duration = 4f;
    @Override
    public void update() {

        float fishStartX = -300f;
        float fishStartY = 1100f;
        float fishMidX = 800f;
        float fishEndX = 1800f;
        float fishEndY = 2100f;

        Random rand = new Random();
        int randInt = rand.nextInt(fishies.length);

        AbstractGameEffect fish = new VfxBuilder(fishies[randInt],fishStartX,fishStartY,2f)
                .setScale(.5f)
                .moveX(fishStartX,fishMidX,VfxBuilder.Interpolations.LINEAR)
                .whenComplete(vfxBuilder -> textBubble(vfxBuilder))
                .andThen(duration)
                .andThen(2f)
                .moveX(fishMidX,fishEndX,VfxBuilder.Interpolations.POW2IN)
                .moveY(fishStartY,fishEndY,VfxBuilder.Interpolations.POW2IN)
                .rotateTo(0,45,VfxBuilder.Interpolations.EXP5OUT)
                .build();

        AbstractDungeon.effectsQueue.add(fish);
        this.isDone = true;
    }
    public void textBubble(VfxBuilder vfx) {
        Random rand = new Random();
        int randInt = rand.nextInt(text.length);
        AbstractDungeon.effectsQueue.add(new SpeechBubble(vfx.x + 150, vfx.y + 0f, duration, "", true));
        AbstractDungeon.effectsQueue.add(new SpeechTextEffect(vfx.x + 200 + 170, vfx.y + 174f, duration, text[randInt], DialogWord.AppearEffect.BUMP_IN));

    }
}
