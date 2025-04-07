import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> students;
        students = loadStudentsList();
        int inferior = 0;
        int superior = students.size() - 1;
        int numRandom = (int) (Math.random() * (superior - inferior + 1)) + inferior;
        if (!students.isEmpty()) {
            System.out.println("La persona afortunada que realizará la presentación és:\n\t " +
                               students.get(numRandom)
            );
            students.remove(numRandom);
            writeNewList(students);
        }
    }

    public static ArrayList<String> loadStudentsList() {
        String fullPath = "src" + File.separator + "list.txt";
        ArrayList<String> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fullPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    students.add(line);
                }
            }
            checkEmptyList(students);
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error, " + e.getMessage());
        } catch (ListEmpty e) {
            System.out.println(e.getMessage());
        }
        return students;
    }

    private static void checkEmptyList(ArrayList<String> students) {
        if (students.isEmpty()) {
            throw new ListEmpty("Ya no quedan alumnos por hacer la presentación, la lista esta vacía ");
        }
    }

    public static void writeNewList(ArrayList<String> students) {
        String fullPath = "src" + File.separator + "list.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath))) {
            for (String student : students) {
                writer.write(student);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

