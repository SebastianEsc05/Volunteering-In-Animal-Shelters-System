package presentation.styles;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    private boolean hovered = false;
    private int cornerRadius;
    private int width;
    private int height;
    private Color colorText;
    private Color colorBtn;
    private Color colorHover;

    public Button(String text, int width, int height, int fontSize, int cornerRadius, Color colorText, Color colorBtn, Color colorHover) {
        super(text);
        this.width = width;
        this.height = height;
        this.cornerRadius = cornerRadius;
        this.colorText = colorText;
        this.colorBtn = colorBtn;
        this.colorHover = colorHover;

        setFont(FontUtil.loadFont(fontSize, "Inter_Medium"));
        setForeground(Color.black);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                hovered = false;
                repaint();
            }
        });

    }
}
