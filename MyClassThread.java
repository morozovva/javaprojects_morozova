import java.lang.*;

class Ping extends Thread {//класс для слова Пинг
    public void run() {
        for(int i=1;i<=5;i++) {
            System.out.println("Ping");//вывод слова
            try{//добавляем время ожидания перед выводом нового слова первого потока
                sleep(1000);
            }
            catch(Exception e){}
        }
    }
}

class Pong extends Thread {//класс для слова Понг
    public void run() {
        for (int i=1;i<=5;i++){
            System.out.println("Pong");//вывод слова
            try{//добавляем время ожидания перед выводом нового слова второго потока
                sleep(1000);
            }
            catch(Exception e){}
        }
    }
}

public class MyClassThread {
    public static void main(String[] args) throws Exception {
        Ping p1 = new Ping();
        Pong p2 = new Pong();
        p1.start();//запускаем 1 поток
        Thread.sleep(500);//запускаем второй поток немного позже для того, чтобы слова выводились поочередно
        p2.start();//запускаем второй поток
        p1.join();//первый поток ждет завершения выполнения второго
    }
}