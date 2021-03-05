package repository;

import domain.Teacher;

import java.io.*;

public class TeacherFileRepository extends AbstractFileRepository<String, Teacher> {
    public TeacherFileRepository(String fileName) {
        super(fileName);
    }

    @Override
    public void saveToFile() {
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(this.fileName))){
            for (Teacher teacher :
                    this.findAll()) {
                String line = "";
                line = teacher.getId() + "," +
                        teacher.getName() + "," +
                        teacher.getFirstName() + "," +
                        teacher.getEmail();
                printWriter.println(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadFromFile() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileName))){
            String line;
            int lineCounter = 0;
            while ((line = bufferedReader.readLine()) != null){
                lineCounter++;
                String[] parameters = line.split("[,]"); //the , is the delimitator between the fields
                if (parameters.length != 4) {//the grade has: id, value, data, teacher
                    System.out.println(
                            "The line " +
                                    String.valueOf(lineCounter) +
                                    " from the file " +
                                    this.fileName+ "is invalid"
                    );
                    continue;
                }
                else{
                    String id = parameters[0];
                    String name = parameters[1];
                    String firstName = parameters[2];
                    String email = parameters[3];
                    Teacher teacher = new Teacher (id, name, firstName, email);
                    this.map.put(teacher.getId(), teacher);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
