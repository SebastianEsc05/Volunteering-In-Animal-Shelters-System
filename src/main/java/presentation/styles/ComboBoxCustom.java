package presentation.styles;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.util.List;

public class ComboBoxCustom extends JComboBox<String> {

    public ComboBoxCustom(String type) {
        super();
        initComponents();
        loadByCategory(type);
        setFocusable(false);
    }

    private void initComponents() {
        setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton("▼");
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setContentAreaFilled(false);
                button.setForeground(Color.DARK_GRAY);
                button.setFocusPainted(false);
                return button;
            }
        });

        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setFont(FontUtil.loadFont(16, "Inter_Light"));
        setFocusable(false);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

    }

    public void setItems(List<String> items) {
        this.removeAllItems();
        for (String item : items) {
            this.addItem(item);
        }
    }

    public void loadByCategory(String category) {
        switch (category) {
            case "state":
                setItems(List.of("Pendiente", "Cancelada", "Finalizada"));
                break;
            case "health":
                setItems(List.of("Sano", "Enfermo", "Recuperación"));
                break;
            case "stateSearch":
                setItems(List.of("Todos", "Pendiente", "Cancelada", "Finalizada"));
                break;
            case "healthSearch":
                setItems(List.of("Todos", "Sano", "Enfermo", "Recuperación"));
                break;
            default:
                setItems(List.of()); // vacío si no coincide
        }
    }

    public String getSelectedValue() {
        return (String) this.getSelectedItem();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.white);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);

    }
}
