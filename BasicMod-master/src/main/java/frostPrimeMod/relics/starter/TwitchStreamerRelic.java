package frostPrimeMod.relics.starter;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import frostPrimeMod.frostCharacter.FrostCharacter;
import frostPrimeMod.powers.FollowersPower;
import frostPrimeMod.relics.BaseRelic;

import java.util.ArrayList;

import static frostPrimeMod.frostPrimeMod.makeID;

public class TwitchStreamerRelic extends BaseRelic {
    private static final String NAME = "TWITCH_STREAMER_RELIC"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    private boolean selfGold;

    public interface SubscriberObserver{
        public void countUpdate(int count);
    }

    private ArrayList<SubscriberObserver> observers;
    private int goldCounter;

    public TwitchStreamerRelic() {
        super(ID, NAME, FrostCharacter.Enums.CARD_COLOR, RARITY, SOUND);
        observers = new ArrayList<>();
        counter = 0;
        goldCounter = 0;
        selfGold = false;
    }
    public TwitchStreamerRelic(String name, String ID, RelicTier rarity) {
        super(ID, name, FrostCharacter.Enums.CARD_COLOR, rarity, SOUND);
        observers = new ArrayList<>();
        counter = 0;
        goldCounter = 0;
        selfGold = false;
    }

    @Override
    public void onEquip(){
        super.onEquip();
        goldCounter = AbstractDungeon.player.gold;

    }

    @Override
    public void onObtainCard(AbstractCard c) {
        if (c.cardID.equals(makeID("SPONSERED_BY_RAID_SHADOW_LEGENDS_CARD"))) {
            AbstractDungeon.player.gold += 75;
            flash();
        }

    }
    @Override
    public void atBattleStart() {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FollowersPower(AbstractDungeon.player, counter)));
    }
    public void register(SubscriberObserver newObserver){
        observers.add(newObserver);
    }

    @Override
    public void onVictory(){
        observers = new ArrayList<>();
    }

    @Override
    public void onGainGold(){
//        int newGold = AbstractDungeon.player.gold - goldCounter;
//        int goldGained = (int)(((double)newGold) * (((double)counter) * 0.002));
//        goldCounter = AbstractDungeon.player.gold;
//        if (this.selfGold){
//            selfGold = false;
//        } else {
//            if (goldGained > 0) {
//                this.flash();
//                this.selfGold = true;
//                this.addToBot(new GainGoldAction(goldGained));
//            }
//        }
    }

    public int getSubscriberCount() {
        return counter;
    }

    public void addSubscribers(int amount){
        this.flash();
        if (amount > 0){
            if (AbstractDungeon.player.hasRelic(makeID("MARKETABLE_FACE_RELIC"))){
                amount += 1;
            }
            AbstractDungeon.player.gold += (amount * 2);
        }
        counter += amount;
        if (counter < 0){
            counter = 0;
        }
        description = getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
        updateObservers(amount);
    }

    private void updateObservers(int increase){
        for (SubscriberObserver so : observers){
            so.countUpdate(increase);
        }
    }

    @Override
    public String getUpdatedDescription() {
        if (counter <= 0){
            return DESCRIPTIONS[0] + "0" + DESCRIPTIONS[1];
        }
        return DESCRIPTIONS[0] + counter + DESCRIPTIONS[1];
    }

}
