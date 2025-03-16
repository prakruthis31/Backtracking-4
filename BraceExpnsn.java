class Solution {

    List<String> result;

    public String[] expand(String s) {
        int len = s.length();
        this.result = new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>(); // convert input to blocks of characters

        int i = 0;
        while (i < len) {
            List<Character> block = new ArrayList<>();
            char ch = s.charAt(i);
            if (ch == '{') {
                i++;
                while (s.charAt(i) != '}') {

                    if (s.charAt(i) != ',') {
                        block.add(s.charAt(i));

                    }
                    i++;
                }

            } else {
                block.add(ch);
            }
            Collections.sort(block);
            blocks.add(block);
            i++;

        }
        System.out.print(blocks);
        dfs(blocks, 0, new StringBuilder());
        String[] fresult = new String[result.size()];
        for(int k=0;k<result.size();k++){
            fresult[k] = result.get(k);
        }
        return fresult;
    }

    private void dfs(List<List<Character>> blocks, int idx, StringBuilder path) {
        if (idx == blocks.size()) {
            result.add(path.toString());
            return;
        }

        List<Character> ls = blocks.get(idx);
        for (int i = 0; i < ls.size(); i++) {

            char ch = ls.get(i);
            path.append(ch);
            dfs(blocks, idx + 1, path);
            path.deleteCharAt(path.length()-1);
        }

    }
}