package be.bruxellesformation.exercice_recapitulatif.model;

public class Orc extends Monster {

    public Orc() {
        setCharacterBonusEndurance(0);
        setCharacterBonusForce(1);
        setLeather(0);
        setGold(getDice6().roll());

        computeAndSetForceModifier( getForce() + getCharacterBonusForce() );
        computeAndSetEnduranceModifier( getEndurance() + getCharacterBonusEndurance() );
        setOriginalHealthPoints( getEndurance() + getEnduranceModifier() );
        setLifetimeHealthPoints(getOriginalHealthPoints());
    }

    @Override
    public String displayString() {
        return " O ";
    }

    @Override
    public String toString() {
        return "Orc{} " + super.toString();
    }

}
