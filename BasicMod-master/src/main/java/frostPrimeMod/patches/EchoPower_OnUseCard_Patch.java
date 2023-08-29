package frostPrimeMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.EchoPower;
import frostPrimeMod.cards.ChooseOneBaseOption;

@SpirePatch(
        clz = EchoPower.class,
        method = "onUseCard"
)
public class EchoPower_OnUseCard_Patch {
    public static SpireReturn<Void> Prefix(EchoPower self, AbstractCard card, UseCardAction action){
        if (card instanceof ChooseOneBaseOption){
            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }
}
