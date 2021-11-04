import java.util.Random;

public class Dog extends  Pet{
    public Dog() {
        final Random random = new Random();
        this.name = "dog " + String.valueOf(random.nextInt(100) + 1);
    }
}