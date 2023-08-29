package frostPrimeMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch(
        clz = AbstractCard.class,
        method = SpirePatch.CLASS
)
public class AbstractCard_Class_Patch {
    public static SpireField<Boolean> isGangCard = new SpireField<Boolean>(() -> false);
}
