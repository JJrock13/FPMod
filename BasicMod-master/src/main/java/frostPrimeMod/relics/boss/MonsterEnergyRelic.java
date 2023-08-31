package frostPrimeMod.relics.boss;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.relics.BaseRelic;

import static frostPrimeMod.frostPrimeMod.makeID;

public class MonsterEnergyRelic extends BaseRelic {
    private static final String NAME = "MONSTER_ENERGY_RELIC"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.SOLID; //The sound played when the relic is clicked.

    private boolean HighEnergyTurn;
    public MonsterEnergyRelic() {
        super(ID, NAME, FrostCharacter.Enums.CARD_COLOR, RARITY, SOUND);
        HighEnergyTurn = false;
    }


    @Override
    public void atTurnStart() {
        super.atTurnStart();
        if (!HighEnergyTurn){
            ++AbstractDungeon.player.energy.energyMaster;
            HighEnergyTurn = true;
        } else {
            --AbstractDungeon.player.energy.energyMaster;
            HighEnergyTurn = false;
        }
    }

    @Override
    public void onVictory() {
        super.onVictory();
        if (HighEnergyTurn){
            HighEnergyTurn = false;
            --AbstractDungeon.player.energy.energyMaster;
        }

    }
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
