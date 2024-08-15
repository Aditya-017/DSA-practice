class Result {

    /*
     * Complete the 'bioHazard' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY allergic
     *  3. INTEGER_ARRAY poisonous
     */

    public static long bioHazard(int n, List<Integer> allergic, List<Integer> poisonous) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i =0; i<allergic.size(); ++i){
            int key = Math.max(allergic.get(i),poisonous.get(i));
            int value = Math.min(allergic.get(i),poisonous.get(i));
            map.put(key,Math.max(map.getOrDefault(key,-1), value));
        }
        // System.out.println(map);

        int i=1,j=1;
        long ans=0;
        Set<Integer> set = new HashSet<>();
        while(j<=n){
            if(map.containsKey(j) && set.contains(map.get(j))){
                int len = j-i;
                int com = j- map.get(j)-1;
                len = (len * (len+1))/2;
                com =(com * (com+1))/2;
                ans += (len-com);
                while(i<=map.get(j)){
                    set.remove(i);
                    ++i;
                }
            }
            set.add(j);
            ++j;
        }
        int len = j-i;
        len = (len * (len+1))/2;
        return ans+len;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int allergicCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> allergic = IntStream.range(0, allergicCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int poisonousCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> poisonous = IntStream.range(0, poisonousCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        long result = Result.bioHazard(n, allergic, poisonous);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
