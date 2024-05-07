package arcadeGame.gameComponents;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;
import arcadeGame.gameComponents.imageManagers.Direction;
import arcadeGame.gameComponents.imageManagers.GameImage;
import arcadeGame.gameComponents.spriteAnimations.DisplaySprite;
import arcadeGame.gameComponents.spriteAnimations.PlayerJumpSprite;

public class Player extends Actor {
    private static final float SPEED_MULTIPLIER = 8f / 5f;
    private static final int DOWNWARD_ACCELERATION = 75;
    private static final int NATURAL_FALL_SPEED = 7;
    private static final int NATURAL_FALL_ACCELERATION = 300;
    private static final int FLY_JUMP_SPEED = 17;
    private static final int FLY_PASSIVE_SPEED = 250;
    private static final int HORIZONTAL_ACCELERATION = 100;
    private static final int FLY_COOL_DOWN = 30;
    private static final double IMAGE_SCALE = 2.0;
    private static final double IMAGE_OFFSET = 0.5;

    private final double horizontalSpeed;
    private int flyCoolDownTimer = 0;
    private final double downwardPushAcceleration;
    private final double naturalFallMaxSpeed;
    private final double naturalFallAcceleration;
    private final double flyJumpSpeed;
    private final double flyPassiveSpeed;
    private final double flyMaxSpeed;
    private final double maxHorizontalAccelerationChange;


    public Player(double startX, double startY, double width, double height) {
        super(startX, startY, width, height, GameImage.PLAYER);

        dir = Direction.RIGHT;
        horizontalSpeed = width * DEFAULT_SPEED * SPEED_MULTIPLIER;
        downwardPushAcceleration = width / DOWNWARD_ACCELERATION;
        naturalFallMaxSpeed = width / NATURAL_FALL_SPEED;
        naturalFallAcceleration = width / NATURAL_FALL_ACCELERATION;
        flyJumpSpeed = width / FLY_JUMP_SPEED;
        flyPassiveSpeed = width / FLY_PASSIVE_SPEED;
        flyMaxSpeed = width / NATURAL_FALL_SPEED;
        maxHorizontalAccelerationChange = width / HORIZONTAL_ACCELERATION;
    }

    public void update(Map<Integer, Boolean> keys, List<Tile> tiles, List<DisplaySprite> sprites) {
        flyCoolDownTimer--;
        super.update(tiles);
        handleKeyAction(keys, sprites);
    }

    boolean findKey(Map<Integer, Boolean> keys, int val) {
        return keys.getOrDefault(val, false);
    }

    public CollisionResult handleCollisions(Enemy enemy) {
        CollisionResult collisionType = CollisionResult.NONE;
        if (collidesWith(enemy)) {
            if (y + height * 3 / 4 - vy < enemy.getY() - enemy.getVy()) {
                collisionType = CollisionResult.PLAYER_WON;
            } else {
                collisionType = CollisionResult.ENEMY_WON;
            }
        }
        return collisionType;
    }

    public void loseLife() {
        setSpikeCollision(false);
    }

    void handleKeyAction(Map<Integer, Boolean> keys, List<DisplaySprite> sprites) {
        handleXControls(keys);
        handleYControls(keys, sprites);
    }

    private void handleYControls(Map<Integer, Boolean> keys, List<DisplaySprite> sprites) {
        boolean pressingUp = findKey(keys, KeyEvent.VK_UP) || findKey(keys, KeyEvent.VK_W);
        boolean pressingDown = findKey(keys, KeyEvent.VK_DOWN) || findKey(keys, KeyEvent.VK_S);
        if (pressingUp && !pressingDown) {
            upEffect(sprites);
        } else if (pressingDown && !pressingUp) {
            downEffect();
        } else {
            passiveEffect();
        }
    }

    private void passiveEffect() {
        if (vy < naturalFallMaxSpeed) {
            vy += Math.min(naturalFallMaxSpeed - vy, naturalFallAcceleration);
        }
    }

    private void upEffect(List<DisplaySprite> sprites) {
        if (flyCoolDownTimer <= 0 && vy > naturalFallAcceleration) {
            vy = -flyJumpSpeed;
            flyCoolDownTimer = FLY_COOL_DOWN;
            sprites.add(new PlayerJumpSprite(x, y + height, width));
        } else if (vy > -flyMaxSpeed) {
            vy += Math.max(-flyPassiveSpeed, -flyMaxSpeed - vy);
        }
    }

    private void downEffect() {
        vy += downwardPushAcceleration;
    }

    private void handleXControls(Map<Integer, Boolean> keys) {
        int desiredVelocity = 0;
        if (findKey(keys, KeyEvent.VK_RIGHT) || findKey(keys, KeyEvent.VK_D))
            desiredVelocity += (int) this.horizontalSpeed;

        if (findKey(keys, KeyEvent.VK_LEFT) || findKey(keys, KeyEvent.VK_A))
            desiredVelocity -= (int) this.horizontalSpeed;

        double vxMod = (desiredVelocity - vx) / APPROACH_FACTOR;
        if (vxMod > 0) {
            vxMod = Math.min(vxMod, maxHorizontalAccelerationChange);
        } else {
            vxMod = Math.max(vxMod, -maxHorizontalAccelerationChange);
        }
        vx += vxMod;

        if (this.vx > 0) {
            this.dir = Direction.RIGHT;
        }

        if (this.vx < 0) {
            this.dir = Direction.LEFT;
        }
    }

    public void drawActor(Graphics2D g2) {
        drawImage(g2, IMAGE_SCALE, IMAGE_OFFSET, true);
    }

    @Override
    protected boolean isHero() {
        return true;
    }

    public enum CollisionResult {
        NONE, PLAYER_WON, ENEMY_WON
    }

}
