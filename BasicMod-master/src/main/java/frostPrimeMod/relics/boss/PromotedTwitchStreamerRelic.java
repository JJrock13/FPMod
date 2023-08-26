package frostPrimeMod.relics.boss;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.relics.BaseRelic;
import frostPrimeMod.relics.starter.TwitchStreamerRelic;

import static frostPrimeMod.BasicMod.makeID;

public class PromotedTwitchStreamerRelic extends BaseRelic {
    private static final String NAME = "PROMOTED_TWITCH_STREAMER_RELIC"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.BOSS; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.
    private TwitchStreamerRelic old;


    public PromotedTwitchStreamerRelic() {
        super(ID, NAME, FrostCharacter.Enums.CARD_COLOR, RARITY, SOUND);

    }
    @Override
    public void onObtainCard(AbstractCard c) {
        if (c.cardID.equals(makeID("SPONSERED_BY_RAID_SHADOW_LEGENDS_CARD"))) {
            //AbstractDungeon.player.gold += 75;
            //flash();
        }
    }
    @Override
    public void onVictory(){
        this.flash();
        if (AbstractDungeon.player.hasRelic(makeID("TWITCH_STREAMER_RELIC"))){
            ((TwitchStreamerRelic)AbstractDungeon.player.getRelic(makeID("TWITCH_STREAMER_RELIC"))).addSubscribers(4);
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }


}
