package gameStufs;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {

    private boolean[] isPressed = new boolean[350];

    private KeyListener(){}

    // singleton
    private static KeyListener instance;
    public static KeyListener getInstance() {
        if (instance == null)
            instance = new KeyListener();
        return instance;
    }

    public static void keyCallBack(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS)
            getInstance().isPressed[key] = true;
        else if (action == GLFW_RELEASE)
            getInstance().isPressed[key] = false;
    }

    public static boolean isKeyPressed(int keyCode) {
        return getInstance().isPressed[keyCode];
    }
}
