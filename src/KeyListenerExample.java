import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class KeyListenerExample extends JFrame implements KeyListener {
    private int x = 0; // initial X position of the image
    private int y = 25; // initial Y position of the image
    private BufferedImage image;
    private int moveSpeed = 10; // normal movement speed

    public KeyListenerExample() {
        setTitle("Moving Image");
        setSize(600, 605);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        try {
            // Load the image from a PNG file
            image = ImageIO.read(new File("src\\heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addKeyListener(this); // Register KeyListener on the frame
        setFocusable(true); // Set focus on the frame
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, x, y, null); // draw the image at the specified position (x, y)
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KeyListenerExample imageMovement = new KeyListenerExample();
            imageMovement.setVisible(true);
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this example
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SHIFT) {
            moveSpeed = 20; // double the speed when Shift is pressed
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            moveImage(-moveSpeed, 0); // move the image to the left
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            moveImage(moveSpeed, 0); // move the image to the right
        } else if (keyCode == KeyEvent.VK_UP) {
            moveImage(0, -moveSpeed); // move the image up
        } else if (keyCode == KeyEvent.VK_DOWN) {
            moveImage(0, moveSpeed); // move the image down
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SHIFT) {
            moveSpeed = 10; // revert to normal speed when Shift is released
        }
    }

    public void moveImage(int dx, int dy) {
        x += dx;
        y += dy;
        // Wrap-around logic for horizontal movement
        if (dx < 0 && x < 0) {
            x = 550;
        }
        if (dx > 0 && x > 500) {
            x = 0;
        }
        // Wrap-around logic for vertical movement
        if (dy > 0 && y > 555) {
            y = 25;
        }
        if (dy < 0 && y < 25) {
            y = 555;
        }
        repaint(); // redraw the window
    }
}
