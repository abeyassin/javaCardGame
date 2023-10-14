import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PictureMemoryGame extends JFrame {
    private ArrayList<String> imagePaths;
    private ArrayList<String> cardImages;
    private JButton[] cardButtons;
    private int numberOfMatches;
    private int firstCardIndex = -1;
    private int secondCardIndex;

    public PictureMemoryGame() {
        setTitle("Ibrahim's Card Matching Game!");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imagePaths = new ArrayList<>();
        //These are the pictures used for the game.
        imagePaths.add("pic1.png");
        imagePaths.add("pic2.png");
        imagePaths.add("pic3.png");
        imagePaths.add("pic4.png");
        imagePaths.add("pic5.png");
        imagePaths.add("pic6.png");
        imagePaths.add("pic1.png");
        imagePaths.add("pic2.png");
        imagePaths.add("pic3.png");
        imagePaths.add("pic4.png");
        imagePaths.add("pic5.png");
        imagePaths.add("pic6.png");

        cardImages = new ArrayList<>();
        for (String imagePaths : imagePaths) {
            cardImages.add("");
        }

        Collections.shuffle(imagePaths);
        Collections.shuffle(cardImages);

        JPanel cardPanel = new JPanel(new GridLayout(4, 3));
        cardButtons = new JButton[12];

        for (int i = 0; i < cardButtons.length; i++) {
            final int index = i;
            cardButtons[i] = new JButton();
            cardButtons[i].setIcon(new ImageIcon("Illuminati.png"));
            cardButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (imagePaths.get(firstCardIndex).equals(imagePaths.get(secondCardIndex))) {
                        cardButtons[firstCardIndex].setIcon(null);
                        cardButtons[secondCardIndex].setIcon(null);
                        cardImages.set(firstCardIndex, null);
                        cardImages.set(secondCardIndex, null);
                        numberOfMatches++;

                    if (numberOfMatches ==imagePaths.size()/2){
                        JOptionPane.showMessageDialog(null, "Congratulations! You Win!");
                        System.exit(0);

                    }

                    }
                }

            });
            cardPanel.add(cardButtons[i]);
        }

        add(cardPanel);
    }


    private void handleCardClick (int index) {
        if (cardButtons[index].getIcon() == null) {
            return; //Already matched card, do nothing.
        }

        if (firstCardIndex == -1) {
            firstCardIndex = index;
            cardButtons[firstCardIndex].setIcon(new ImageIcon(imagePaths.get(index)));
        } else {
            secondCardIndex = index;
            cardButtons[secondCardIndex].setIcon(new ImageIcon(imagePaths.get(index)));

            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (imagePaths.get(firstCardIndex).equals(imagePaths.get(secondCardIndex))) {
                        cardButtons[firstCardIndex].setIcon(null);
                        cardButtons[secondCardIndex].setIcon(null);
                        cardImages.set(firstCardIndex, null);
                        cardImages.set(secondCardIndex, null);
                        numberOfMatches++;

                        if (numberOfMatches == imagePaths.size() / 2) {
                            JOptionPane.showMessageDialog(null, "Congratulations! You've won the game, now go do something productive with your life!");
                            System.exit(0);
                        }
                    } else {
                        cardButtons[firstCardIndex].setIcon(new ImageIcon("Illuminati.png"));
                        cardButtons[secondCardIndex].setIcon(new ImageIcon("Illuminati.png"));
                    }
                    firstCardIndex = -1;
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
    }
