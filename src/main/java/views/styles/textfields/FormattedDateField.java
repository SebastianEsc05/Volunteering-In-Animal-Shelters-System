package views.styles.textfields;

import views.styles.FontUtil;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormattedDateField extends JFormattedTextField {
    private static final String DATE_FORMAT = "dd/mm/yyyy";

    public FormattedDateField() {
        super(createFormatter());
        setColumns(8);
        setOpaque(false);
        setBorder(null);
        setHorizontalAlignment(JTextField.CENTER);
        setPreferredSize(new Dimension(80, 30));
        setFont(FontUtil.loadFont(18, "Inter_Regular"));
    }

    private static MaskFormatter createFormatter() {
        try {
            MaskFormatter formatter = new MaskFormatter("##/##/####");
            formatter.setPlaceholderCharacter(' ');
            return formatter;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean dateValidator(String text) {
        if (text.contains("_")) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        try {
            Date fecha = sdf.parse(text);
            return fecha != null;

        } catch (ParseException e) {
            return false;

        }
    }

    public Date getDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            sdf.setLenient(false);
            return sdf.parse(getText());

        } catch (ParseException e) {
            return null;

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(2));

        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2d.dispose();
        super.paintComponent(g);

    }


}
