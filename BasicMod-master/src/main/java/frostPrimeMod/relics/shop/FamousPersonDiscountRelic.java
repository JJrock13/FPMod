package frostPrimeMod.relics.shop;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.relics.BaseRelic;
import frostPrimeMod.relics.starter.TwitchStreamerRelic;

import static frostPrimeMod.frostPrimeMod.makeID;

public class FamousPersonDiscountRelic extends BaseRelic {
    private static final String NAME = "FAMOUS_PERSON_DISCOUNT_RELIC"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.SHOP; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    private static final int SubcriberThreshhold = 100;
    public FamousPersonDiscountRelic() {
        super(ID, NAME, FrostCharacter.Enums.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        super.onEnterRoom(room);
        if (room instanceof ShopRoom) {
            this.flash();
            this.pulse = true;
            if (AbstractDungeon.player.hasRelic(makeID("TWITCH_STREAMER_RELIC"))) {
                TwitchStreamerRelic r = ((TwitchStreamerRelic) AbstractDungeon.player.getRelic(makeID("TWITCH_STREAMER_RELIC")));
                if (r.getSubscriberCount() > SubcriberThreshhold){
                    AbstractDungeon.shopScreen.applyDiscount((float)0.4, false);
                }
            }
        } else {
            this.pulse = false;
        }
    }

    @Override
    public void onEquip() {
        super.onEquip();
        if (AbstractDungeon.shopScreen.isActive){
            this.flash();
            this.pulse = true;
            if (AbstractDungeon.player.hasRelic(makeID("TWITCH_STREAMER_RELIC"))) {
                TwitchStreamerRelic r = ((TwitchStreamerRelic) AbstractDungeon.player.getRelic(makeID("TWITCH_STREAMER_RELIC")));
                if (r.getSubscriberCount() > SubcriberThreshhold){
                    AbstractDungeon.shopScreen.applyDiscount((float)0.4, false);
                }
            }
        }
    }
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
}
