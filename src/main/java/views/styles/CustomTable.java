package views.styles;

import views.enums.PanelCategory;
import views.frames.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;

public class CustomTable extends JTable {
    private MainFrame owner;
    private PanelCategory category;

    public CustomTable(TableModel model , MainFrame owner, PanelCategory category) {
        super(model);
        this.owner = owner;
        this.category = category;
        configStyle();

    }

    public void setModel(TableModel model) {
        super.setModel(model);
        alingColumn();
    }

    private void configStyle() {

        //Background, foreground, font, row height
        setOpaque(true);
        this.setForeground(Color.BLACK);
        this.setFont(FontUtil.loadFont(14, "Inter_Light"));
        this.setRowHeight(60);

        //Grid
        this.setShowHorizontalLines(false);
        this.setShowVerticalLines(false);
        this.setGridColor(new Color(255, 255, 255));
        setIntercellSpacing(new Dimension(10, 10));

        //Selection colors
        this.setSelectionBackground(Color.white);
        this.setFillsViewportHeight(true);

        //Header
        JTableHeader header = this.getTableHeader();
        header.setOpaque(false);
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

    @Override
    public boolean isCellEditable(int row, int column) {
        return getColumnName(column).equals("Ver");
    }


    public void addColumnButton() {
        try {
            getColumn("Ver").setCellRenderer(new ButtonRenderer());
            getColumn("Ver").setCellEditor(new ButtonEditor(new JCheckBox(), owner, category));
        } catch (IllegalArgumentException e) {
            System.out.println("La tabla no tiene columna 'Ver', se omite el botón.");
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

    }
}
