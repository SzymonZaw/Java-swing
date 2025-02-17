import javax.swing.*;
import java.awt.*;

class Window {
    public static void main(String[] args) {
        // Tworzenie głównego okna
        JFrame frame = new JFrame("Validation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Tworzenie obiektu Animal
        Animal animal = new Animal();

        // Tworzenie przycisku Confirm
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setEnabled(false);

        // Tworzenie pola tekstowego z walidacją
        VinputText nameInput = new VinputText(confirmButton);
        nameInput.setPreferredSize(new Dimension(200, 20));
        nameInput.registerValidator(new MyPatternValidator("[A-Za-z]+", "Liczby są niedozwolone!"));

        // Utwórz nowy panel dla pola tekstowego
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS)); // Ustawienie BoxLayout z układem poziomym

        // Dodaj pole tekstowe do panelu
        inputPanel.add(nameInput);

        // Dodaj panel z polem tekstowym i etykietą do ramki
        frame.add(inputPanel);

        // Dodaj ActionListener do przycisku Confirm
        confirmButton.addActionListener(e -> {
            String name = nameInput.getText();
            animal.setVoice(name);
            System.out.println(animal);
        });
        frame.add(confirmButton);

        // Konfiguracja i wyświetlanie głównego okna
        frame.pack();
        frame.setVisible(true);
    }
}