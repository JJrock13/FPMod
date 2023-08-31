package frostPrimeMod.relics.starter;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.relics.BaseRelic;

import static frostPrimeMod.frostPrimeMod.makeID;

public class ActiveStreamerRelic extends BaseRelic {
    private static final String NAME = "ACTIVE_STREAMER_RELIC"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.
    private TwitchStreamerRelic old;


    public ActiveStreamerRelic() {
        super(ID, NAME, FrostCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }
    @Override
    public void onVictory(){
        this.flash();
        if (AbstractDungeon.player.hasRelic(makeID("TWITCH_STREAMER_RELIC"))){
            ((TwitchStreamerRelic)AbstractDungeon.player.getRelic(makeID("TWITCH_STREAMER_RELIC"))).addSubscribers(1);
        }

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


}
