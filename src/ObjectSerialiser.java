import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;

public class ObjectSerialiser {
    public static void printObjectFieldsAndValuesToFile(Object obj, String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            Class<?> clazz = obj.getClass();
            printWriter.println("Fields and Values of " + clazz.getSimpleName() + ":");

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(obj);
                printWriter.println(fieldName + ": " + value);
            }

            printWriter.close();
            fileWriter.close();
            System.out.println("Fields and values printed to file successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}