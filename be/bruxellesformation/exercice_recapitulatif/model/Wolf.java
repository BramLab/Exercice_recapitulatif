package be.bruxellesformation.exercice_recapitulatif.model;

public class Wolf extends Monster{

    public Wolf() {
        setCharacterBonusEndurance(0);
        setCharacterBonusForce(0);
        setLeather(getDice4().roll());
        setGold(0);

        computeAndSetForceModifier( getForce() + getCharacterBonusForce() );
        computeAndSetEnduranceModifier( getEndurance() + getCharacterBonusEndurance() );
        setOriginalHealthPoints( getEndurance() + getEnduranceModifier() );
        setLifetimeHealthPoints(getOriginalHealthPoints());
    }

    @Override
    public String displayString() {
        return " W ";
    }

    @Override
    public String toString() {
        return "Wolf{} " + super.toString();
    }
}
