package views.panels.entitypanels;

import controllers.AnimalController;
import interfaces.controller.IAnimalController;
import views.enums.PanelCategory;
import views.frames.MainFrame;
import views.panels.SidebarPanel;
import views.panels.addentitypanels.AddAnimalPanel;
import views.styles.*;
import views.styles.Button;
import views.styles.textfields.TxtFieldPh;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AnimalsPanel extends EntityPanel{
    private IAnimalController animalController;
    private AddAnimalPanel addAnimalPanel;
    private JScrollPane scrollPane;
    private Button newAnimalBtn;
    private ComboBoxCustom healthStatusCombo;
    private TxtFieldPh searchField;
    private Button searchBtn;

    public AnimalsPanel(MainFrame owner) {
        super(owner);
        this.animalController = new AnimalController();
        this.addAnimalPanel = new AddAnimalPanel(owner);
        this.newAnimalBtn = new Button("Nuevo Animal", 185, 35, 15, 25, Color.WHITE, Style.COLOR_BTN, Style.COLOR_BTN_HOVER);
        this.healthStatusCombo = new ComboBoxCustom("healthSearch");
        searchField = new TxtFieldPh("Id", 185, 35, 15, 25);
        searchBtn = new Button("Buscar", 100, 35, 15, 25, Color.WHITE, Style.COLOR_BTN_BACK, Style.COLOR_BTN_BACK_HOVER);

        //Table model
        model = animalController.getAnimalsTable();
        table = new CustomTable(model, owner, PanelCategory.ANIMALS);
        addComponents();

        //ActionListeners
        newAnimalBtn.addActionListener(e -> {
            owner.showNewPanel(this.addAnimalPanel);

        });

        healthStatusCombo.addActionListener(e -> {
            updateFilter();
        });

        backBtn.addActionListener(e -> {
            this.owner.showNewPanel(this.owner.getMainMenuPanel());
            resetSearchField();
        });

        searchBtn.addActionListener(e -> {
            String searchText = searchField.getText().trim();
            if (!searchText.isEmpty()) {
                try {
                    int id = Integer.parseInt(searchText);
                    DefaultTableModel newModel = animalController.getAnimalsByIdTable(id);
                    table.setModel(newModel);
                    table.addColumnButton();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un Id válido.", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                DefaultTableModel newModel = animalController.getAnimalsTable();
                table.setModel(newModel);
                table.addColumnButton();
            }
        });
    }

    @Override
    public void addComponents(){
        //SideBarPanel
        this.sideBarPanel = new SidebarPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Style.COLOR_BACKGROUND);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.setColor(Color.black);

            }
        };
        this.sideBarPanel.add(newAnimalBtn);
        this.sideBarPanel.add(healthStatusCombo);
        this.sideBarPanel.add(searchField);
        this.sideBarPanel.add(searchBtn);

        //Table Panel
        table.setPreferredScrollableViewportSize(new Dimension(600, 340));
        scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(backBtn);
        tablePanel.add(scrollPane);

        //East Panel
        this.eastPanel.add(this.sideBarPanel);
        eastPanel.add(Box.createHorizontalGlue());

        this.mainPanel.add(this.eastPanel);
        this.mainPanel.add(this.tablePanel);
        add(this.mainPanel);
    }

    public void updateFilter(){
        String selectedHealthStatus = healthStatusCombo.getSelectedValue();
        DefaultTableModel newModel;
        try {
            switch (selectedHealthStatus){
                case "Todos":
                newModel = animalController.getAnimalsShelterTable();
                break;
                case "Saludable":
                    newModel = animalController.getAnimalsByStatusHealthyTable();
                    break;
                case "Enfermo":
                    newModel = animalController.getAnimalsByStatusSickTable();
                    break;
                case "Recuperación":
                    newModel = animalController.getAnimalsByStatusRecoveringTable();
                    break;
                default:
                    newModel = animalController.getAnimalsShelterTable();
            }
            table.setModel(newModel);
            table.addColumnButton();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void refreshTable(){
        table.setModel(animalController.getAnimalsTable());
        table.addColumnButton();
        revalidate();
        repaint();
    }

    public void resetSearchField(){
        searchField.setText("");
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //Ttitle position
        Font tittleFont = FontUtil.loadFont(25, "Inter_Light");
        String tittleText = "Animales";
        FontMetrics metricsTittleText = g2d.getFontMetrics(tittleFont);
        int xTittleText = (sideBarPanel.getWidth() - metricsTittleText.stringWidth(tittleText)) / 2;
        g2d.setFont(tittleFont);
        g2d.drawString(tittleText, xTittleText, sideBarPanel.getY()+57);

        //table border
        g2d.setColor(Style.COLOR_BACKGROUND);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(tablePanel.getX()+20, scrollPane.getY()-10, scrollPane.getWidth()+20, sideBarPanel.getHeight()+30, 20, 20);
        g2d.setColor(Style.COLOR_BACKGROUND_DARK);
        g2d.setStroke(new BasicStroke(1.5f));
        g2d.drawLine(tablePanel.getX()+40, scrollPane.getY()+30, tablePanel.getX()+(tablePanel.getWidth()-30), scrollPane.getY()+30);

    }
}
