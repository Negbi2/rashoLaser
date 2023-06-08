package gameStufs;

import static org.lwjgl.glfw.GLFW.*;

public class MouseListener {
    private double scrollX, scrollY;
    private double xPos, yPos, lastX, lastY;

    private boolean isDragging;
    private boolean[] isPressed = new boolean[3];

    private MouseListener() {
        this.scrollX = 0;
        this.scrollY = 0;
        this.xPos = 0;
        this.yPos = 0;
        this.lastX = 0;
        this.lastY = 0;
    }

    private static MouseListener instance;

    public static MouseListener getInstance() {
        if (instance == null)
            instance = new MouseListener();
        return instance;
    }


    // callbacks
    public static void mouseCallback(long window, double xPos, double yPos) {
        getInstance().lastX = getInstance().xPos;
        getInstance().lastY = getInstance().yPos;

        getInstance().xPos = xPos;
        getInstance().yPos = yPos;

        getInstance().isDragging =
                getInstance().isPressed[0] ||
                getInstance().isPressed[1] ||
                getInstance().isPressed[2];
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (button < getInstance().isPressed.length) {
            if (action == GLFW_PRESS)
                getInstance().isPressed[button] = true;
            else if (action == GLFW_RELEASE) {
                getInstance().isPressed[button] = false;
                getInstance().isDragging = false;
            }
        }
    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        getInstance().scrollX = xOffset;
        getInstance().scrollY = yOffset;
    }

    public static void endFrame() {
        getInstance().scrollY = 0;
        getInstance().scrollX = 0;
        getInstance().lastY = getInstance().yPos;
        getInstance().lastX = getInstance().xPos;
    }


    // getters
    public static float getX() {
        return (float) getInstance().xPos;
    }

    public static float getY() {
        return (float) getInstance().yPos;
    }

    public static float getDx() {
        return (float) (getInstance().lastX - getInstance().xPos);
    }

    public static float getDy() {
        return (float) (getInstance().lastY - getInstance().yPos);
    }

    public static float getScrollX() {
        return (float) getInstance().scrollX;
    }

    public static float getScrollY() {
        return (float) getInstance().scrollY;
    }

    public static boolean isDragging() {
        return getInstance().isDragging;
    }

    public static boolean isPressed(int button) {
        if (button < getInstance().isPressed.length)
            return getInstance().isPressed[button];
        return false;
    }
}
