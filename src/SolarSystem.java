import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Random;

public class SolarSystem {
    public static void main(String[] args) {
        String mercury = new String("Меркурий");
        String venus = new String("Венера");
        String earth = new String("Земля");
        String mars = new String("Марс");
        String jupiter = new String("Юпитер");

        ArrayList<String> solarSystem1 = new ArrayList<>(Arrays.asList(mercury, venus, earth, mars));//создаем 1 список с планетами
        ArrayList<String> solarSystem2 = new ArrayList<>(Arrays.asList(mars, jupiter, earth));//создаем 2 список с планетами
        Collections.unmodifiableList(solarSystem2);//делаем 2 список немодифицируемым

        solarSystem1.add("Нептун");//добавление элемента в список
        System.out.println(solarSystem1);

        solarSystem1.remove(venus);//удаление элемента из списка
        System.out.println(solarSystem1);

        solarSystem1.set(solarSystem1.indexOf(earth),jupiter );//изменение элемента списка
        System.out.println(solarSystem1);

        Collections.shuffle(solarSystem1);//перемешиваем элементы списка
        System.out.println(solarSystem1);

        Random random = new Random();
        int random_index = random.nextInt(solarSystem1.size());//выбор случайного индекса элемента списка
        System.out.println(solarSystem1.get(random_index));//вывод рандомного элемента списка по его индексу

        System.out.println("Индекс элемента 2 списка "+ earth+ " = " +solarSystem2.indexOf(earth));//поиск и вывод индекса элемента

        //проверка, есть ли элементы в списке
        System.out.println("Есть ли "+venus +" во 2 списке? " + solarSystem2.contains(venus));
        System.out.println("Есть ли "+mars +" во 2 списке? " +solarSystem2.contains(mars));

        //проверка, пуст ли список
        System.out.println("Пуст ли 1 список? "+ solarSystem1.isEmpty());

        //создание нового списка из существующего
        List<String> solarSystem3 = solarSystem1.subList(0, 2);
        System.out.println(solarSystem3);

        //опустошение списка
        solarSystem3.clear();
        System.out.println(solarSystem3);

    }
}
