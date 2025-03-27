// SpeedReductionPellet.java
package iut.gon.agarioclient.model;

import javafx.geometry.Point2D;

public class SpeedReductionPellet extends EffectPellet {
    public SpeedReductionPellet(String id, Point2D position, double mass) {
        super(id, position, mass);
    }

    @Override
    public void applyEffect(Player player) {
        player.setSpeed(player.getSpeed() / 2);
    }
}