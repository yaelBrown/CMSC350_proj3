
// Should be caught in main class JOptionPane
// Called when Make Tree button is clicked
public class InvalidTreeSyntax extends Exception {

        public InvalidTreeSyntax(String msg) {
            super(msg);
        }

        public InvalidTreeSyntax(String msg, Throwable t) {
            super(msg);
        }

}
