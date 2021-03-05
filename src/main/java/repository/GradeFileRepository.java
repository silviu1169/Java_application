package repository;

import domain.Grade;

import java.io.*;
import java.time.LocalDateTime;

public class GradeFileRepository extends AbstractFileRepository<String, Grade> {

   //id,data,value,teacher

    public GradeFileRepository(String fileName) {
        super(fileName);
    }

    @Override
    public void saveToFile() {
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(this.fileName))){
            for (Grade grade :
                    this.findAll()) {
                String line = "";
                line = grade.getId() + "," +
                        grade.getValue() + "," +
                        grade.getLocalDateTime() + "," +
                        grade.getTeacherId();
                printWriter.println(line);
            }
        } catch (IOException e) {
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
                    String[] array = id.split(" ");
                    String idStudent = array[0];
                    String idHomework = array[1];
                    int value = Integer.parseInt(parameters[1]);
                    String date = parameters[2];
                    String teacher = parameters[3];
                    //2019-11-06T19:30:18.607
                   LocalDateTime dateTime = LocalDateTime.parse(date);
                   Grade grade = new Grade (idStudent, idHomework, value, dateTime, teacher);
                   this.map.put(grade.getId(), grade);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }


}
