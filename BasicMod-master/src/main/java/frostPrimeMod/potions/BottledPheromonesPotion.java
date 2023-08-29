package frostPrimeMod.potions;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import frostPrimeMod.actions.PheromonesAction;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.patches.AbstractCard_Class_Patch;

import java.util.ArrayList;
import java.util.Iterator;

import static frostPrimeMod.BasicMod.makeID;

public class BottledPheromonesPotion extends BasePotion{
    private static final String ID = makeID("BOTTLED_PHEROMONES_POTION");
    private static final Color LIQUID_COLOR = CardHelper.getColor(255, 0, 255);
    private static final Color HYBRID_COLOR = CardHelper.getColor(255, 0, 255);
    private static final Color SPOTS_COLOR = null;

    public BottledPheromonesPotion() {
        super(ID, 1, PotionRarity.UNCOMMON, PotionSize.MOON, LIQUID_COLOR, HYBRID_COLOR, SPOTS_COLOR);
        playerClass = FrostCharacter.Enums.FROST_PRIME;
    }
    @Override
    public String getDescription() {
        if (potency == 1) {
            return potionStrings.DESCRIPTIONS[0];
        }
        else return potionStrings.DESCRIPTIONS[1];
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        this.addToBot(new PheromonesAction(potency));
    }

    @Override
    public void update() {

    }


}
