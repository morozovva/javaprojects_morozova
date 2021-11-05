import java.util.Scanner;
import java.util.Stack;

public class Stack_prac{
    static Stack<Integer> first_player_input = new Stack<>();//стек 1 игрока, куда изначально вводятся цифры
    static Stack<Integer> second_player_input = new Stack<>();//стек 2 игрока, куда изначально вводятся цифры
    static Stack<Integer> first_player = new Stack<>();//стек 1 игрока для использования в механизме игры
    static Stack<Integer> second_player = new Stack<>();//стек 2 игрока для использования в механизме игры
    static Scanner in = new Scanner(System.in);

    public static Stack add_two_cards(Stack player, int a, int b){//добавление 2 карт в колоду победившего игрока
        Stack<Integer> new_stack = new Stack<>();//инициализация нового стека
        new_stack.push(b);//добавляем карту второго игрока
        new_stack.push(a);//добавляем карту первого игрока
        new_stack.addAll(player);//добавляем исходный стек к новому
        //System.out.println(new_stack);
        return new_stack;//возвращаем итоговый стек
    }

    public static void main(String[] args) {
        for (int i = 0; i<5; i++)//заполнение 1 стека с клавиатуры
            first_player_input.push(in.nextInt());
        for (int i = 0; i<5; i++)//заполнение 2 стека с клавиатуры
            second_player_input.push(in.nextInt());
        //добавляем в новые стеки элементы старых в обратном порядке для работы с ними
        for (int i = 0; i<5; i++){
            first_player.push(first_player_input.pop());
            second_player.push(second_player_input.pop());
        }
        int counter =0;//счетчик количества ходов
        while (!first_player.isEmpty() && !second_player.isEmpty()){//пока оба стека не пустые
            if (counter >=106){//если счетчик доходит до 106, выводим "botva" и выходим из цикла
                System.out.println("botva");
                break;
            }
            if (first_player.peek()==0){//если у первого игрока 0 карта
                if (counter%2 ==0)//проверяем, кто ходил первый
                    first_player = add_two_cards(first_player, first_player.pop(), second_player.pop());
                else
                    first_player = add_two_cards(first_player, second_player.pop(), first_player.pop());
            }
            else if (second_player.peek()==0){//если у второго игрока 0 карта
                if (counter%2 ==1)
                    second_player = add_two_cards(second_player, second_player.pop(), first_player.pop());
                else
                    second_player = add_two_cards(second_player, first_player.pop(), second_player.pop());
            }
            else if (first_player.peek()>second_player.peek()){//если карта первого игрока больше, чем у второго
                if (counter%2 ==0)
                    first_player = add_two_cards(first_player, first_player.pop(), second_player.pop());
                else
                    first_player = add_two_cards(first_player, second_player.pop(), first_player.pop());
            }
            else if (second_player.peek()>first_player.peek()){//если карта второго игрока больше, чем первого
                if (counter%2 ==1)
                    second_player = add_two_cards(second_player, second_player.pop(), first_player.pop());
                else
                    second_player = add_two_cards(second_player, first_player.pop(), second_player.pop());
            }
        counter++;//в конце хода увеличиваем счетчик
        }
        if (first_player.isEmpty()){//в зависимости у кого стек пустой, выводим победителя и количество ходов
            System.out.println("second "+counter);
        }
        else if (second_player.isEmpty()){
            System.out.println("first "+counter);
        }
    }
}
