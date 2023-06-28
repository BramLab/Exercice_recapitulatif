package be.bruxellesformation.exercice_recapitulatif.model;

public class Human extends Hero{

    public Human() {
        setCharacterBonusEndurance(1);
        setCharacterBonusForce(1);
        setLeather(0);
        setGold(0);

        computeAndSetForceModifier( getForce() + getCharacterBonusForce() );
        computeAndSetEnduranceModifier( getEndurance() + getCharacterBonusEndurance() );
        setOriginalHealthPoints( getEndurance() + getEnduranceModifier() );
        setLifetimeHealthPoints(getOriginalHealthPoints());

    }

    @Override
    public String displayString() {
        return (char)27 + "[31m" //red
                + "(H)"
                + (char)27 + "[30m"; //back to black
    }

    @Override
    public String toString() {
        return "Human{} " + super.toString();
    }

}
