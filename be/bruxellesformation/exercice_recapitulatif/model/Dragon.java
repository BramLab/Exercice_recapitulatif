package be.bruxellesformation.exercice_recapitulatif.model;

public class Dragon extends Monster {

    public Dragon() {
        setCharacterBonusEndurance(1);
        setCharacterBonusForce(0);
        setLeather(getDice4().roll());
        setGold(getDice6().roll());

        computeAndSetForceModifier( getForce() + getCharacterBonusForce() );
        computeAndSetEnduranceModifier( getEndurance() + getCharacterBonusEndurance() );
        setOriginalHealthPoints( getEndurance() + getEnduranceModifier() );
        setLifetimeHealthPoints(getOriginalHealthPoints());
    }

    @Override
    public String displayString() {
        return " D ";
    }

    @Override
    public String toString() {
        return "Dragon{} " + super.toString();
    }

}
