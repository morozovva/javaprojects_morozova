import java.util.Scanner;
import java.util.LinkedList;

public class DoubleList_prac{
    static LinkedList<Integer> first_player = new LinkedList<>();//колода 1 игрока
    static LinkedList<Integer> second_player = new LinkedList<>();//колода 2 игрока
    static Scanner in = new Scanner(System.in);

    public static LinkedList add_two_cards(LinkedList player, int a, int b){//добавление 2 карт в колоду победившего игрока
        player.add(a);//добавляем карту первого игрока
        player.add(b);//добавляем карту второго игрока
        //System.out.println(player);
        return player;//возвращаем итоговую очередь
    }

    public static void main(String[] args) {
        for (int i = 0; i<5; i++)//заполнение 1 колоды с клавиатуры
            first_player.add(in.nextInt());
        for (int i = 0; i<5; i++)//заполнение 2 колоды с клавиатуры
            second_player.add(in.nextInt());

        int counter =0;//счетчик количества ходов
        while (!first_player.isEmpty() && !second_player.isEmpty()){//пока обе колоды не пустые
            if (counter >=106){//если счетчик доходит до 106, выводим "botva" и выходим из цикла
                System.out.println("botva");
                break;
            }
            if (first_player.peek()==0){//если у первого игрока 0 карта
                if (counter%2 ==0) {//проверяем, кто ходил первый
                    first_player = add_two_cards(first_player, first_player.poll(), second_player.poll());
                }
                else{
                    first_player = add_two_cards(first_player, second_player.poll(), first_player.poll());
                }
            }
            else if (second_player.peek()==0){//если у второго игрока 0 карта
                if (counter%2 ==1)
                    second_player = add_two_cards(second_player, second_player.poll(), first_player.poll());
                else
                    second_player = add_two_cards(second_player, first_player.poll(), second_player.poll());
            }
            else if (first_player.peek()>second_player.peek()){//если карта первого игрока больше, чем у второго
                if (counter%2 ==0)
                    first_player = add_two_cards(first_player, first_player.poll(), second_player.poll());
                else
                    first_player = add_two_cards(first_player, second_player.poll(), first_player.poll());
            }
            else if (second_player.peek()>first_player.peek()){//если карта второго игрока больше, чем первого
                if (counter%2 ==1)
                    second_player = add_two_cards(second_player, second_player.poll(), first_player.poll());
                else
                    second_player = add_two_cards(second_player, first_player.poll(), second_player.poll());
            }
            counter++;//в конце хода увеличиваем счетчик
        }
        if (first_player.isEmpty()){//в зависимости у кого колода пустая, выводим победителя и количество ходов
            System.out.println("second "+counter);
        }
        else if (second_player.isEmpty()) {
            System.out.println("first " + counter);
        }
    }
}