package views.styles.textfields;

import views.styles.FontUtil;
import views.styles.RoundBorder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TextAreaCustom extends JTextArea {


    public TextAreaCustom(int filas, int columnas) {
        super(filas, columnas);
        setOpaque(false);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setFont(FontUtil.loadFont(15, "Inter_Regular"));
        setLineWrap(true);
        setWrapStyleWord(true);
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBorder(new RoundBorder(20, Color.white));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo blanco con esquinas redondeadas
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        g2.dispose();
        super.paintComponent(g);
    }
}



