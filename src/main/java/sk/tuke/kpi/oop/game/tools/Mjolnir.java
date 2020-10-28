package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.oop.game.Reactor;

public class Mjolnir extends Hammer {

    public Mjolnir(){
        super(4);
    }
    public Mjolnir(Reactor reactor){
        super(4,reactor);

    }

}
