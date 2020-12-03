package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm {

    public Gun(int currentAmmo, int maxAmmo){
        super(currentAmmo,maxAmmo);
    }

    @Override
    protected Fireable createBullet() {
        return new Bullet();
    }


}
