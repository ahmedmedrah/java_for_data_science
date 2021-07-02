package com.mycompany.pyramid_task;

import java.util.List;

public interface PyramidDao {
    Pyramid createPyramid(String[] metaPyramid);

    List<Pyramid> readPyramidFromCSV(String path);

}
