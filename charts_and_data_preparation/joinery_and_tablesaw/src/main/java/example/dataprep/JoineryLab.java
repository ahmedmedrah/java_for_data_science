package example.dataprep;

import joinery.DataFrame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JoineryLab {
    public static void main(String[] args) {
        try {

            DataFrame<Object> df1 = DataFrame.readCsv ("src/main/resources/data/titanic.csv");
//            System.out.println (df1.toString ());
            System.out.println ("=========================================================================================");
            List<Object> newValues = new ArrayList<>();
            List<Object> values = df1.col ("sex");
            for (Object obj : values) {
                if (obj instanceof String) {
                    String str = (String) obj;
                    if (str.equals ("female"))
                        newValues.add (1);
                    else
                        newValues.add (0);
                }

            }
            df1 = df1.add("gender",newValues);
            df1 = df1.retain("pclass","survived","name","age","gender","fare","embarked");
            System.out.println(df1.describe());
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
