import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.JLabel;

public class Game extends JFrame {
    static Queue<Integer> first_player = new LinkedList<>();//очередь 1 игрока
    static Queue<Integer> second_player = new LinkedList<>();//очередь 2 игрока
    static Scanner in = new Scanner(System.in);

    static JLabel label1 = new JLabel("Для начала игры, нажмите на кнопку");
    static Font fnt = new Font("Times new roman", Font.BOLD, 20);
    static JPanel[] panel = new JPanel[5];
    static JButton button1 = new JButton(" Сделать ход");
    static JButton button2 = new JButton("Начать игру!");
    static JButton button3 = new JButton("Колоды заданы");
    static JButton exit_button = new JButton("Выйти из игры");
    static JLabel label2 = new JLabel("Ваша карта: ");
    static JLabel label3 = new JLabel("Карта соперника: ");
    static JLabel label4 = new JLabel("Победитель: ");
    public static int counter = 0;


    public static Queue add_two_cards(Queue player, int a, int b) {//добавление 2 карт в колоду победившего игрока
        player.add(a);//добавляем карту первого игрока
        player.add(b);//добавляем карту второго игрока
        return player;//возвращаем итоговую очередь
    }

    public Game() {
        label1.setFont(fnt);
        setLayout(new GridLayout(5,1));
        setSize(500, 200);
        for (int i = 0; i < panel.length; i++) {
            panel[i] = new JPanel();
            panel[i].setBackground(new Color(255, 255, 255));
            add(panel[i]);
        }
        panel[0].add(label1);
        panel[0].setBackground(new Color(142, 209, 234));
        panel[3].add(button2);

        exit_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    dispose();
                } catch (Exception e) {
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    panel[3].remove(button2);
                    label1.setText("Задайте колоды карт игроков");
                    panel[3].add(button3);
                    panel[3].updateUI();
                    }
                catch (Exception e){}
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    SwingUtilities.invokeLater(() -> {
                        panel[3].remove(button3);
                        panel[3].updateUI();
                    });
                    panel[2].add(label2);
                    panel[3].add(label3);
                    panel[1].add(button1);
                    panel[4].add(label4);
                }
                catch (Exception e){}
            }
            });

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    label2.setText("Ваша карта: " + first_player.peek());
                    label3.setText("Карта соперника: " + second_player.peek());
                    if (first_player.peek() == 0) {//если у первого игрока 0 карта
                        label4.setText("Вы выиграли этот раунд!");
                        if (counter % 2 == 0) {//проверяем, кто ходил первый
                            first_player = add_two_cards(first_player, first_player.poll(), second_player.poll());
                        } else {
                            first_player = add_two_cards(first_player, second_player.poll(), first_player.poll());
                        }
                    } else if (second_player.peek() == 0) {//если у второго игрока 0 карта
                        label4.setText("Вы проиграли этот раунд!");
                        if (counter % 2 == 1)
                            second_player = add_two_cards(second_player, second_player.poll(), first_player.poll());
                        else
                            second_player = add_two_cards(second_player, first_player.poll(), second_player.poll());
                    } else if (first_player.peek() > second_player.peek()) {//если карта первого игрока больше, чем у второго
                        label4.setText("Вы выиграли этот раунд!");
                        if (counter % 2 == 0)
                            first_player = add_two_cards(first_player, first_player.poll(), second_player.poll());
                        else
                            first_player = add_two_cards(first_player, second_player.poll(), first_player.poll());
                    } else if (second_player.peek() > first_player.peek()) {//если карта второго игрока больше, чем первого
                        label4.setText("Вы проиграли этот раунд!");
                        if (counter % 2 == 1)
                            second_player = add_two_cards(second_player, second_player.poll(), first_player.poll());
                        else
                            second_player = add_two_cards(second_player, first_player.poll(), second_player.poll());
                    }
                    counter++;//в конце хода увеличиваем счетчик
                } catch (Exception e) {
                }
            }
        });

    }

    public static void main(String[] args) {
        new Game().setVisible(true);
        for (int i = 0; i < 5; i++)//заполнение 1 очереди с клавиатуры
            first_player.add(in.nextInt());
        for (int i = 0; i < 5; i++)//заполнение 2 очереди с клавиатуры
            second_player.add(in.nextInt());


        while ((!first_player.isEmpty()) && (!second_player.isEmpty())){
            if (counter >= 106) {//если счетчик доходит до 106, выводим "botva" и выходим из цикла
                System.out.println("botva");
                break;
            }button1.getActionCommand();
            if (counter%2 == 0)
                label1.setText("Вы делаете ход первыми");
            else
                label1.setText("Первым ходит соперник");

        }
        panel[2].remove(label2);
        panel[3].remove(label3);
        panel[1].remove(button1);
        panel[4].remove(label4);
        panel[1].updateUI();
        panel[2].updateUI();
        panel[3].updateUI();
        panel[4].updateUI();
        panel[3].add(label4);

        if ((first_player.isEmpty()) ||second_player.isEmpty()){
            label4.setFont(fnt);
            panel[2].add(exit_button);
            if (first_player.isEmpty()) {//в зависимости у кого очередь пустая, выводим победителя и количество ходов
                label4.setText("Вы проиграли:(");
                label1.setText("Ваш соперник выиграл за " + counter+ " ходов");

            } else if (second_player.isEmpty()) {
                label4.setText("Вы победили!");
                label1.setText("Вы победили за " + counter+ " ходов");
            }
        }

    }
}