package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {

    private int maxAmmo, currentAmmo;

    abstract protected Fireable createBullet();

    public Firearm(int currentAmmo, int maxAmmo){
        this.currentAmmo = currentAmmo;
        this.maxAmmo = maxAmmo;
    }

    public Firearm(int maxAmmo){
        this.currentAmmo = maxAmmo;
        this.maxAmmo = maxAmmo;
    }
    public int getAmmo(){
        return currentAmmo;
    }
    public int getMaxAmmo(){ return maxAmmo;}
    public void reload(int newAmmo){
        currentAmmo += newAmmo;
        if(currentAmmo > maxAmmo){
            currentAmmo = maxAmmo;
        }
    }
    public Fireable fire(){
        if(currentAmmo == 0){
            return null;
        }
        currentAmmo -= 1;
        return createBullet();
    }
}
