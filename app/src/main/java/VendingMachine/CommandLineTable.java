package VendingMachine;

import java.util.*;

public class CommandLineTable {
    private static final String HORIZONTAL_SEP = "-";
    private String verticalSep = "|";
    private String joinSep = "+";
    public String[] headers;
    public List<String[]> rows = new ArrayList<>();
    private boolean rightAlign;

    public CommandLineTable() {
    }

    public void setRightAlign(boolean rightAlign) {
        this.rightAlign = rightAlign;
    }

    public void setHeaders(String... headers) {
        this.headers = headers;
    }

    public void addRow(String... cells) {
        rows.add(cells);
    }

    public void print() {
        int[] maxWidths = headers != null ?
                Arrays.stream(headers).mapToInt(String::length).toArray() : null;
        
        for (String[] cells : rows) {
            if (maxWidths == null) {
                maxWidths = new int[cells.length];
            }
            if (cells.length != maxWidths.length) {
                throw new IllegalArgumentException("Number of row-cells and headers should be consistent");
            }
            for (int i = 0; i < cells.length; i++) {
                maxWidths[i] = Math.max(maxWidths[i], cells[i].length());
            }
        }
        if (headers != null) {
            printLine(maxWidths);
            printRow(headers, maxWidths);
            printLine(maxWidths);
        }
        for (String[] cells : rows) {
            printRow(cells, maxWidths);
        }

        if (headers != null) {
            printLine(maxWidths);
        }
    }

    private void printLine(int[] columnWidths) {
        for (int i = 0; i < columnWidths.length; i++) {
            String line = String.join("", Collections.nCopies(columnWidths[i] +
                    verticalSep.length() + 1, HORIZONTAL_SEP));
            System.out.print(joinSep + line + (i == columnWidths.length - 1 ? joinSep : ""));
        }
        System.out.println();
    }
    private void printRow(String[] cells, int[] maxWidths) {
        for (int i = 0; i < cells.length; i++) {
            String s = cells[i];
            String verStrTemp = i == cells.length - 1 ? verticalSep : "";
            if (rightAlign) {
                System.out.printf("%s %" + maxWidths[i] + "s %s", verticalSep, s, verStrTemp);
            } else {
                System.out.printf("%s %-" + maxWidths[i] + "s %s", verticalSep, s, verStrTemp);
            }
        }
        System.out.println();
    }

    public boolean equals(CommandLineTable table){
        if(table.headers.length != this.headers.length){
            System.out.println("header Length not equals");
            return false;
        }
        if(table.rows.size() != this.rows.size()){
            System.out.println("row Length not equals");
            return false;
        }
        for(int i = 0; i < table.headers.length; i++){
            if(!table.headers[i].equals(this.headers[i])){
                System.out.println("at header expected " + this.headers[i] + "but was" + table.headers[i]);
                return false;
            }
        }
        for(int i = 0; i < table.rows.size(); i++){
            String[] row1 = table.rows.get(i);
            String[] row2 = this.rows.get(i);
            for(int j = 0; j < row1.length; j++){
                if(!row1[j].equals(row2[j])){
                    System.out.println("at row " + Integer.toString(i) + "expected " + row2[j] + " but was " + row1[j]);
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //test code
        CommandLineTable st = new CommandLineTable();
        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setHeaders("one", "two", "three");//optional - if not used then there will be no header and horizontal lines
        st.addRow("super", "broccoli", "flexible");
        st.addRow("assumption", "announcement", "reflection");
        st.addRow("logic", "pleasant", "wild");
        st.print();
    }
}
