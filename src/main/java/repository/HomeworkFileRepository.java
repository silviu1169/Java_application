package repository;

import domain.Homework;

import java.io.*;

public class HomeworkFileRepository extends AbstractFileRepository<String, Homework> {
    public HomeworkFileRepository(String fileName) {
        super(fileName);
    }

    @Override
    public void saveToFile() {
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(this.fileName))) {
            for (Homework homework:
                 this.findAll()) {
                String line = homework.getID() + "," +
                        homework.getDescription() + "," +
                        homework.getStartWeek() + "," +
                        homework.getDeadlineWeek();
                printWriter.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        } ;
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
                    String description = parameters[1];
                    int startWeek = Integer.parseInt(parameters[2]);
                    int deadlineWeek = Integer.parseInt(parameters[2]);
                    Homework homework = new Homework(id, description, startWeek, deadlineWeek);
                    this.map.put(homework.getId(), homework);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
