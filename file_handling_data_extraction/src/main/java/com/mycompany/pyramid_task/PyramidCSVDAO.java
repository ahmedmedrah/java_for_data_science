package com.mycompany.pyramid_task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PyramidCSVDAO implements PyramidDao {
    List<Pyramid> pyramids;

    public PyramidCSVDAO() {
        pyramids = new ArrayList<>();
    }

    @Override
    public Pyramid createPyramid(String[] metaPyramid) {
        return new Pyramid(metaPyramid[0],
                metaPyramid[2],
                metaPyramid[4],
                Double.parseDouble(metaPyramid[7]
                ));
    }

    @Override
    public List<Pyramid> readPyramidFromCSV(String path) {
        File f = new File(path);
        String line;

        try {
            FileReader fileReader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine();
            while (line != null) {
                line = bufferedReader.readLine();
                if (line != null) {
                    String[] attrs = line.split(",");
                    if(!attrs[7].isEmpty())
                        pyramids.add(createPyramid(attrs));
                }

            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pyramids;
    }
}
