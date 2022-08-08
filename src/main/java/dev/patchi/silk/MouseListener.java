package dev.patchi.silk;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;

public class MouseListener {

    private static MouseListener instance;

    private double scrollX, scrollY;
    private double xPos, yPos, lastY, lastX;
    private boolean mouseButtonPressed[] = new boolean[GLFW_MOUSE_BUTTON_LAST+1];
    private boolean isDragging;

    private MouseListener() {

        this.scrollX = 0;
        this.scrollY = 0;
        this.xPos = 0;
        this.yPos = 0;
        this.lastX = 0;
        this.lastY = 0;

    }

    public static MouseListener get(){
        if (instance == null) {
            instance = new MouseListener();
        }

        return instance;
    }

    public static void mousePosCallback(long window, double xpos, double ypos) {

        get().lastX = get().xPos;
        get().lastY = get().yPos;

        get().xPos = xpos;
        get().yPos = ypos;

        for (boolean b : get().mouseButtonPressed) {
            if(b) {
                get().isDragging = true;
                break;
            }
        }

    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {

        if(action == GLFW_PRESS) {
            if(button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = true;
            }
        } else if (action == GLFW_RELEASE) {
            if(button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }

    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    public static void endFrame() {
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }

    public static float getX() {
        return (float) get().xPos;
    }

    public static float getY() {
        return (float) get().yPos;
    }

    public static float getDx() {
        return (float) (get().lastX - get().xPos);
    }

    public static float getDy() {
        return (float) (get().lastY - get().yPos);
    }

    public static float getScrollX() {
        return (float) getScrollX();
    }

    public static float getScrollY() {
        return (float) getScrollY();
    }

    public static boolean isDragging() {
        return get().isDragging;
    }

    public static boolean isMouseButtonDown(int button) {
        if(button<get().mouseButtonPressed.length) {
            return get().mouseButtonPressed[button];
        } else return false;
    }

}
