package repository;

import domain.Student;

import java.io.*;

public class StudentFileRepository extends AbstractFileRepository<String, Student> {
    public StudentFileRepository(String fileName) {
        super(fileName);
    }

    @Override
    public void saveToFile() {
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(this.fileName))) {
            for (Student student :
                    this.findAll()) {
                String line = "";
                line = student.getID() + "," +
                        student.getName() + "," +
                        student.getFirstName() + "," +
                        student.getGroup() + "," +
                        student.getEmail() + "," +
                        student.getTeacherTrainingLab();
                printWriter.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
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
                if (parameters.length != 6) {//the grade has: id, value, data, teacher
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
                    String group = parameters[3];
                    String email = parameters[4];
                    String teacherTrainigLab = parameters[5];
                    Student student = new Student (id, name, firstName, group, email, teacherTrainigLab);
                    this.map.put(student.getId(), student);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
