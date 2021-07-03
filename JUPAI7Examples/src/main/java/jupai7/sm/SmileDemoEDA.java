package jupai7.sm;

import smile.classification.RandomForest;
import smile.data.DataFrame;
import smile.data.formula.Formula;
import smile.data.measure.NominalScale;
import smile.data.vector.IntVector;
import smile.plot.swing.Histogram;
import smile.validation.metric.Accuracy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class SmileDemoEDA {
    String totalPath = "src/main/resources/data/titanic.csv";
    String trainPath = "src/main/resources/data/titanic_train.csv";
    String testPath = "src/main/resources/data/titanic_test.csv";

    public static void main(String[] args) throws IOException {
        SmileDemoEDA sd = new SmileDemoEDA ();
        PassengerProvider pProvider = new PassengerProvider ();
        DataFrame full = pProvider.readCSV(sd.totalPath);
        DataFrame trainData = full.slice(0,891);
        DataFrame testData = full.slice(892,1309);

        System.out.println (full.structure ());

        trainData = prepareData(trainData);
        testData = prepareData(testData);

        RandomForest model = RandomForest.fit(Formula.lhs("survived"), trainData);
        System.out.println("feature importance:");
        System.out.println(Arrays.toString(model.importance()));
        System.out.println(model.metrics ());
        //TODO load test data to validate model
       testModel(testData,model);
    }

    public static void testModel(DataFrame testData,RandomForest model){
        int[][] results=model.test (testData);
        RandomForest model1= model.prune (testData);
        System.out.println("feature importance:");
        System.out.println(Arrays.toString(model1.importance()));
        System.out.println(model1.metrics ());
        int[] pred = model.predict(testData);
        System.out.println("Accuracy= "+ Accuracy.of(testData.column("survived").toIntArray(), pred));
    }

    public static int[] encodeCategory(DataFrame df, String columnName) {
        String[] values = df.stringVector (columnName).distinct ().toArray (new String[]{});
        int[] new_val = df.stringVector (columnName).factorize (new NominalScale (values)).toIntArray ();
        return new_val;
    }

    public static DataFrame prepareData(DataFrame new_df){
//        new_df = new_df.merge (IntVector.of ("PClassValues",
//                encodeCategory (new_df, "pclass")));
//        System.out.println(new_df.summary());
//        System.out.println(new_df.structure());
//        System.out.println("-------------------------------------------------------------------------------");
        new_df = new_df.merge (IntVector.of ("Gender",
                encodeCategory (new_df, "sex")));

        new_df = new_df.omitNullRows ();
        new_df = new_df.drop ("name");
//        new_df=new_df.drop("pclass");
        new_df=new_df.drop("sex");






        return new_df;
    }

    private static void eda(DataFrame titanic) throws InterruptedException, InvocationTargetException {
        titanic.summary ();
        DataFrame titanicSurvived = DataFrame.of (titanic.stream ().filter (t -> t.get ("Survived").equals (1)));
        DataFrame titanicNotSurvived = DataFrame.of (titanic.stream ().filter (t -> t.get ("Survived").equals (0)));
        titanicNotSurvived.omitNullRows ().summary ();
        titanicSurvived = titanicSurvived.omitNullRows ();
        titanicSurvived.summary ();
        int size = titanicSurvived.size ();
        System.out.println (size);
        Double averageAge = titanicSurvived.stream ()
                .mapToDouble (t -> t.isNullAt ("Age") ? 0.0 : t.getDouble ("Age"))
                .average ()
                .orElse (0);
        System.out.println (averageAge.intValue ());
        Map map = titanicSurvived.stream ()
                .collect (Collectors.groupingBy (t -> Double.valueOf (t.getDouble ("Age")).intValue (), Collectors.counting ()));

        double[] breaks = ((Collection<Integer>) map.keySet ())
                .stream ()
                .mapToDouble (l -> Double.valueOf (l))
                .toArray ();

        int[] valuesInt = ((Collection<Long>) map.values ())
                .stream ().mapToInt (i -> i.intValue ())
                .toArray ();

        Histogram.of (titanicSurvived.doubleVector ("Age").toDoubleArray (), 15, false)
                .canvas ().setAxisLabels ("Age", "Count")
                .setTitle ("Age frequencies among surviving passengers")
                .window ();
        Histogram.of (titanicSurvived.intVector ("PClassValues").toIntArray (), 4, true)
                .canvas ().setAxisLabels ("Classes", "Count")
                .setTitle ("Pclass values frequencies among surviving passengers")
                .window ();
        //Histogram.of(values, map.size(), false).canvas().window();
        System.out.println (titanicSurvived.schema ());
        //////////////////////////////////////////////////////////////////////////

    }
}
