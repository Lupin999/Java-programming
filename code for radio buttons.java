import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RadioButtonDemo extends JFrame implements ActionListener {

    private JRadioButton birdButton, catButton, dogButton, rabbitButton, pigButton;
    private ButtonGroup buttonGroup;
    private JLabel pictureLabel;

    public RadioButtonDemo() {
        super("RadioButtonDemo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create radio buttons
        birdButton = new JRadioButton("Bird");
        catButton = new JRadioButton("Cat");
        dogButton = new JRadioButton("Dog");
        rabbitButton = new JRadioButton("Rabbit");
        pigButton = new JRadioButton("Pig");

        // Group the radio buttons
        buttonGroup = new ButtonGroup();
        buttonGroup.add(birdButton);
        buttonGroup.add(catButton);
        buttonGroup.add(dogButton);
        buttonGroup.add(rabbitButton);
        buttonGroup.add(pigButton);

        // Add action listeners to the radio buttons
        birdButton.addActionListener(this);
        catButton.addActionListener(this);
        dogButton.addActionListener(this);
        rabbitButton.addActionListener(this);
        pigButton.addActionListener(this);

        // Create a label to display the image
        pictureLabel = new JLabel();

        // Add components to the frame
        add(birdButton);
        add(catButton);
        add(dogButton);
        add(rabbitButton);
        add(pigButton);
        add(pictureLabel);

        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedPet = "";
        ImageIcon image = null;

        if (e.getSource() == birdButton) {
            selectedPet = "Bird";
            image = new ImageIcon("bird.jpg"); // Replace with actual image path
        } else if (e.getSource() == catButton) {
            selectedPet = "Cat";
            image = new ImageIcon("cat.jpg"); // Replace with actual image path
        } else if (e.getSource() == dogButton) {
            selectedPet = "Dog";
            image = new ImageIcon("dog.jpg"); // Replace with actual image path
        } else if (e.getSource() == rabbitButton) {
            selectedPet = "Rabbit";
            image = new ImageIcon("rabbit.jpg"); // Replace with actual image path
        } else if (e.getSource() == pigButton) {
            selectedPet = "Pig";
            image = new ImageIcon("pig.jpg"); // Replace with actual image path
        }

        // Display the selected pet in a message box
        JOptionPane.showMessageDialog(this, "You selected a " + selectedPet);

        // Update the image label
        pictureLabel.setIcon(image);
        pictureLabel.revalidate(); // Refresh the label to display the new image
    }
    public static void main(String[] args) {
        new RadioButtonDemo();
    }
}