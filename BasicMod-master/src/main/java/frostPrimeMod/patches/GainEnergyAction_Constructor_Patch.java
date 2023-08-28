package frostPrimeMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;

@SpirePatch(
        clz = GainEnergyAction.class,
        method = SpirePatch.CONSTRUCTOR
)
public class GainEnergyAction_Constructor_Patch {
    public static int energyGainedThisTurn = 0;
    public static void Prefix(GainEnergyAction self, int ___energyGain){
        GainEnergyAction_Constructor_Patch.energyGainedThisTurn += ___energyGain;
    }
}
