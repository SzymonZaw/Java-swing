import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class VinputText extends JPanel {
    private final JTextComponent textField;
    private final List<Validator> validators;
    private final JButton confirmButton;
    private final ImageIcon validIcon;
    private final ImageIcon invalidIcon;
    private final JLabel iconLabel;

    public VinputText(JButton confirmButton) {

        setLayout(new BorderLayout());
        textField = new JTextArea();
        validators = new ArrayList<>();

        add(textField, BorderLayout.CENTER);

        this.confirmButton = confirmButton;

        // Ładowanie ikon dla poprawnego i niepoprawnego tekstu
        validIcon = new ImageIcon("C:\\Users\\szymo\\Desktop\\IS\\Semestr IV\\Zaawansowane Programowanie Obiektowe\\Laboratorium\\lab5\\lab5\\src\\images\\0.png");
        // Ścieżka do obrazka dla poprawnego tekstu
        invalidIcon = new ImageIcon("C:\\Users\\szymo\\Desktop\\IS\\Semestr IV\\Zaawansowane Programowanie Obiektowe\\Laboratorium\\lab5\\lab5\\src\\images\\1.png");
        // Ścieżka do obrazka dla niepoprawnego tekstu

        // Skalowanie ikon do wymiarów 20x20 pikseli
        Image validImage = validIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image invalidImage = invalidIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        validIcon.setImage(validImage);
        invalidIcon.setImage(invalidImage);

        iconLabel = new JLabel(invalidIcon); // Domyślnie ustawiamy ikonę dla poprawnego tekstu
        iconLabel.setPreferredSize(new Dimension(20, 20)); // Zmieniono wymiary ikony na 20x20 pikseli
        add(iconLabel, BorderLayout.WEST);

        // Dodawanie listenera do pola tekstowego
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateField();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateField();
            }
        });
    }

    public void registerValidator(Validator v) {
        validators.add(v);
    }

    private void validateField() {
        String value = textField.getText();
        for (Validator validator : validators) {
            validator.validate(value);
            if (!validator.isValid()) {
                confirmButton.setEnabled(false);
                iconLabel.setIcon(invalidIcon); // Ustawienie ikony dla niepoprawnego tekstu
                iconLabel.setToolTipText(validator.getMessage());
                return;
            } else {
                iconLabel.setToolTipText(null);
            }
        }
        confirmButton.setEnabled(true);
        iconLabel.setIcon(validIcon); // Ustawienie ikony dla poprawnego tekstu
    }

    public String getText() {
        return textField.getText();
    }

    public void setText(String text) {
        textField.setText(text);
    }
}