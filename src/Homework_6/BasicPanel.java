package Homework_6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BasicPanel extends JPanel implements KeyListener, MouseMotionListener, MouseListener {
    int width;  //window width
    int height; //window height
    char keyPressed;
    int keyCode;
    int mouseX, mouseY;

    BasicPanel(int width, int height) {
        this.width = width;
        this.height = height;
        Dimension d = new Dimension(width, height);
        setPreferredSize(d);
        //add listeners for keyboard and mouse interaction
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);
    }

    BasicPanel() {
        this.width = 500;
        this.height = 500;
        Dimension d = new Dimension(width, height);
        setPreferredSize(d);
        //add listeners for keyboard and mouse interaction
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);
    }

    public static void main(String[] args) {
        BasicPanel panel = new BasicPanel(800, 800);
        MyFrame f = new MyFrame(panel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keyPressed = e.getKeyChar();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyCode = e.getKeyCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setSize(int width, int height) {
        this.width =width;
        this.height =height;
        Dimension d = new Dimension(width, height);
        setPreferredSize(d);
    }

    void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception exc) {
        }
    }

    public void animate(int framerate) {
        int delay = 1000 / framerate;
        while (true) {
            repaint();
            delay(delay);
        }
    }
}

class MyFrame {
    private JFrame j;
    private JPanel panel;

    MyFrame (JPanel p) {
        panel = p;
        j = new JFrame();
        j.setTitle(" ");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.add(panel);
        j.pack();
        j.setVisible(true);
    }

    public void setTitle(String title) {
        j.setTitle(title);
    }
}