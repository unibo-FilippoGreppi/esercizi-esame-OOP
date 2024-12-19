package src.a01a.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;


public class SubsequenceCombinerFactoryImpl implements SubsequenceCombinerFactory {
    private final static int SIZE = 3;
    private <X, Y> SubsequenceCombiner<X, Y> generic(Predicate<List<X>> predicate, Function<List<X>, Y> function) {
        return new SubsequenceCombiner<X,Y>() {

            @Override
            public List<Y> combine(List<X> list) {
                final List<Y> output = new ArrayList<>();
                final List<X> tempList = new ArrayList<>();

                for (final var el : list) {
                    tempList.add(el);
                    if (predicate.test(tempList)) {
                        output.add(function.apply(tempList));
                        tempList.clear();
                    }
                }
                if (!tempList.isEmpty()) {
                    output.add(function.apply(tempList));
                }
                return output;
            }
        };
    }

    @Override
    public SubsequenceCombiner<Integer, Integer> tripletsToSum() {
        return generic(list -> list.size() == SIZE, list -> list.stream().reduce(0, Integer::sum));
    }

    @Override
    public <X> SubsequenceCombiner<X, List<X>> tripletsToList() {
       return generic(list -> list.size() == SIZE, list -> {
            final List<X> output = new ArrayList<>();
            output.addAll(list);
            return output;
       });
    }

    @Override
    public SubsequenceCombiner<Integer, Integer> countUntilZero() {
        return generic(
            list -> list.size() != 0 && list.get(list.size() - 1) == 0, 
            list -> list.get(list.size()- 1) == 0 ? list.size() - 1 : list.size()
        );
    }

    @Override
    public <X, Y> SubsequenceCombiner<X, Y> singleReplacer(Function<X, Y> function) {
        return generic(, function);
    }

    @Override
    public SubsequenceCombiner<Integer, List<Integer>> cumulateToList(int threshold) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cumulateToList'");
    }

}
