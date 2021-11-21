import java.util.*;

class Race extends Thread{
    public synchronized void run() {
        System.out.println(Thread.currentThread().getName()); //вывод имени потока
        try {//добавляем время ожидания
            Thread.sleep(1000);
        }
        catch (InterruptedException e){} //отслеживание исключений
    }
}

public class HorseRace {
    private static int n; //количество лошадей

    public static void main(String[] args) {
        System.out.print("количество стартующих лошадей: ");
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt(); //считывание количества лошадей
        System.out.println("результаты скачек в порядке прибытия лошадей к финишу:");
        for (int i = 1; i <= n; i++){ //создание потоков
            Thread t = new Thread(new Race());
            t.setName("Лошадь №" + i); //имя потока
            t.start(); //запуск потока
        }
    }
}

