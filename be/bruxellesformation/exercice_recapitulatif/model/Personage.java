package be.bruxellesformation.exercice_recapitulatif.model;

import java.util.Objects;

public abstract class Personage {

    //region properties

    // Gold and leather are assets of monsters while they live, and are
    // transferred to, and hoarded by, heroes when they kill a monster.
    private int endurance;
    private int force;
    private int originalHealthPoints;// via delegates?
    private int lifetimeHealthPoints;
    private int enduranceModifier;
    private int forceModifier;

    // Next are set in Human/Dwarf/Wolf/Orc/Dragon implementation, not here.
    protected int characterBonusEndurance;
    protected int characterBonusForce;
    protected int gold;
    protected int leather;
    private final Dice dice4 = new Dice(1,4);
    private final Dice dice6 = new Dice(1,6);
    private int coordinateX;
    private int coordinateY;

    //endregion

    protected Personage() {
        setEndurance(dice6.highest3From4Rolls());
        setForce(dice6.highest3From4Rolls());
    }

    public  void fight(Personage personage) {// How to force parameter to be a monster? Personage type is too generic.
        if (this instanceof Hero && personage instanceof Monster monster) {
            this.fight(monster);
        } else if (this instanceof Monster && personage instanceof Hero hero) {
            this.fight(hero);
        }
    }
    private void fight(Hero hero) {
        if (hero.getLifetimeHealthPoints() > 0)
            hero.getHit(hit());
        else {
            // nothing particular... no gains for monster when killing opponent, except staying alive?};
        }
    }
    private void fight(Monster monster) {
        if (monster.getLifetimeHealthPoints() > 0){
            monster.getHit(hit());
            if (monster.getLifetimeHealthPoints()<=0){
                setGold(getGold()+monster.getGold());
                setLeather(getLeather() + monster.getLeather());
            }
        }
    }
    // When hero hits monster, monster looses HP
    // When HealthPoints <= zero, monster dies.

    public int hit(){
        int fourSidedThrow = dice4.roll();
        return fourSidedThrow + getForceModifier();
    }

    public void getHit(int hitForce){
        setLifetimeHealthPoints(getLifetimeHealthPoints() - hitForce);
    }

    //region helper method(s)
    private int modifier(int originalScore){
        return (originalScore>=15?2:(originalScore>=10?1:(originalScore>=5?0:-1)));
    }
    public Dice getDice4() {
        return dice4;
    }
    public Dice getDice6() {
        return dice6;
    }

    public abstract String displayString();

    //endregion

    // region local getters, setters & helper method(s)
    public int getEndurance() {
        return endurance;
    }
    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getForce() {
        return force;
    }
    public void setForce(int force){
        this.force = force;
    }

    public int getOriginalHealthPoints() {
        return originalHealthPoints;
    }
    public void setOriginalHealthPoints(int originalHealthPoints){
        this.originalHealthPoints = originalHealthPoints;
    }

    public int getEnduranceModifier() {
        return enduranceModifier;
    }
    public void computeAndSetEnduranceModifier(int endurance) {
        this.enduranceModifier = modifier(endurance);
    }

    public int getForceModifier() {
        return forceModifier;
    }
    public void computeAndSetForceModifier(int force) {
        this.forceModifier = modifier(force);
    }

    public int getLifetimeHealthPoints() {
        return lifetimeHealthPoints;
    }
    public void setLifetimeHealthPoints(int lifetimeHealthPoints) {
        this.lifetimeHealthPoints = lifetimeHealthPoints;
    }

    public int getCoordinateX() {
        return coordinateX;
    }
    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }
    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    // endregion

    // region getters/setter used in specialised classes or abstract
    public int getCharacterBonusEndurance() {
        return characterBonusEndurance;
    }
    public void setCharacterBonusEndurance(int characterBonusEndurance){
        this.characterBonusEndurance = characterBonusEndurance;
    }

    public int getCharacterBonusForce() {
        return characterBonusForce;
    }
    public void setCharacterBonusForce(int characterBonusForce){
        this.characterBonusForce = characterBonusForce;
    }

    public int getLeather() {
        return leather;
    }
    public void setLeather(int leather) {
        this.leather = leather;
    }

    public int getGold() {
        return gold;
    }
    public void setGold(int gold){
        this.gold = gold;
    }

    // endregion

    // region tostring, equals, hashcode
    @Override
    public String toString() {
        return "Personage{" +
                "endurance=" + endurance +
                ", force=" + force +
                ", originalHealthPoints=" + originalHealthPoints +
                ", lifetimeHealthPoints=" + lifetimeHealthPoints +
                ", enduranceModifier=" + enduranceModifier +
                ", forceModifier=" + forceModifier +
                ", characterBonusEndurance=" + characterBonusEndurance +
                ", characterBonusForce=" + characterBonusForce +
                ", gold=" + gold +
                ", leather=" + leather +
                ", dice4=" + dice4 +
                ", dice6=" + dice6 +
                ", coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personage personage = (Personage) o;
        return endurance == personage.endurance && force == personage.force && originalHealthPoints == personage.originalHealthPoints && enduranceModifier == personage.enduranceModifier && forceModifier == personage.forceModifier && characterBonusEndurance == personage.characterBonusEndurance && characterBonusForce == personage.characterBonusForce && gold == personage.gold && leather == personage.leather;
    }

    @Override
    public int hashCode() {
        return Objects.hash(endurance, force, originalHealthPoints, enduranceModifier, forceModifier, characterBonusEndurance, characterBonusForce, gold, leather);
    }
    //endregion

}
