package be.bruxellesformation.exercice_recapitulatif.model;

public abstract class Hero extends Personage {

    /*@Override
    public  void fight(Personage monster) {// How to force parameter to be a monster? Personage type is too generic.
        if (monster instanceof Monster m) {
            this.fight(m);
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
    }*/

    @Override
    public String toString() {
        return "Hero{} " + super.toString();
    }

}
