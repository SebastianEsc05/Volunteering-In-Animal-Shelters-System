package views.styles;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;

public class CustomTable extends JTable {

    public CustomTable(TableModel model) {
        super(model);
        configStyle();

    }

    public void setModel(TableModel model) {
        super.setModel(model);
    }

    private void configStyle() {

        //Background, foreground, font, row height
        this.setBackground(Color.white);
        this.setForeground(Color.black);
        this.setFont(FontUtil.loadFont(12, "Inter_18pt-ExtraLight"));
        this.setRowHeight(60);

        //Grid
        this.setShowHorizontalLines(false);
        this.setShowVerticalLines(false);
        this.setGridColor(new Color(0, 0, 0));
        setIntercellSpacing(new Dimension(10, 10));

        //Selection colors
        this.setSelectionBackground(Color.black);
        this.setSelectionForeground(Color.WHITE);

        //Header
        JTableHeader header = this.getTableHeader();
        alingColumn();

        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value.toString());
                label.setOpaque(true);
                label.setBackground(Color.white);
                label.setForeground(Color.black);
                label.setFont(FontUtil.loadFont(15, "Inter_18pt-ExtraLight"));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                return label;
            }
        });

        header.setReorderingAllowed(false);
    }

    public void alingColumn() {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    public void addColumnButton() {

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Style.COLOR_BACKGROUND_DARK);
        g2d.drawRoundRect(0, 0, this.getWidth(), this.getHeight(), 20, 20);
        g2d.dispose();

    }
}
