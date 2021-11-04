import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Pet {
    public String name;
    public Pet() {}

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        //3): отображение из домашних животных, где в качестве ключа выступает строка, а в качестве значения класс Pet
        System.out.println("task 3");
        Map<String, Pet> hashmap = new HashMap<>(); //инициализация hashmap
        hashmap.put("Cat", new Cat());
        hashmap.put("Dog", new Dog());
        hashmap.put("Owl", new Owl());
        Set<Map.Entry<String, Pet>> set = hashmap.entrySet(); //сохраняем набор элементов
        System.out.println("HashMap was created: ");
        for (Map.Entry<String, Pet> me : set){//отображение набора
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue().getName());
        }
        //7): генерация набора случайных чисел и подсчет количество повторов.
        System.out.println("\ntask 7");
        final Random random = new Random(100);
        Map<Integer, Integer> hashMap = new HashMap<>(); //создаем новую hashmap
        for (int i = 0; i < 100; i++){
            int number = random.nextInt(10); // генерация рандомного числа от 0 до 10
            Integer frequency = hashMap.get(number); //считаем, сколько раз число было сгенерировано
            hashMap.put(number, frequency == null ? 1 : frequency + 1); //добавляем в хеш-таблицу количество повторений и само число
        }
        System.out.println(hashMap);
    }
}