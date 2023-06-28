package be.bruxellesformation.exercice_recapitulatif.model;

public class Dwarf extends Hero {
    public Dwarf() {
        setCharacterBonusEndurance(2);
        setCharacterBonusForce(0);
        setLeather(0);
        setGold(0);

        computeAndSetEnduranceModifier( getEndurance() + getCharacterBonusEndurance() );
        computeAndSetForceModifier( getForce() + getCharacterBonusForce() );
        setOriginalHealthPoints( getEndurance() + getEnduranceModifier() );
        setLifetimeHealthPoints(getOriginalHealthPoints());
    }

    @Override
    public String displayString() {
        return (char)27 + "[31m" //red
                + "(D)"
                + (char)27 + "[30m"; //back to black
    }

    @Override
    public String toString() {
        return "Dwarf{} " + super.toString();
    }

}