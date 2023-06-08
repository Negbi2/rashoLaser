package gameStufs;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private final String title;
    private final int height;
    private final int width;

    private long glfwWindow;

    private Window() {
        height = 1080;
        width = 1920;
        title = "cool trazer";
    }

    private static Window instance;

    public static Window getInstance() {
        if (instance == null) instance = new Window();
        return instance;
    }

    private void initializeWindow() {
        // Make an error call back
        GLFWErrorCallback.createPrint(System.err).set();

        // initialize GLFW
        if (!glfwInit())
            throw new IllegalStateException("couldn't start glfw");

        // Configure window hints
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // Make the window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (glfwWindow == NULL)
            throw new IllegalStateException("Couldn't create GLFW window");

        glfwSetCursorPosCallback(glfwWindow, MouseListener::mouseCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallBack);
        // Create the OpenGl context current
        glfwMakeContextCurrent(glfwWindow);
        glfwSwapInterval(4); // Fast ahh swap time

        glfwShowWindow(glfwWindow);

        // very important
        GL.createCapabilities();
    }

    public void start() {
        System.out.println("Hello LWJGL" + Version.getVersion() + "!");
        initializeWindow();


        // Main loop
        while (!glfwWindowShouldClose(glfwWindow)) {
            glfwPollEvents();

            glClearColor(.85f, .0f, .4f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(glfwWindow);
        }

        // Free the memory cause we stay memory safe out here
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}
