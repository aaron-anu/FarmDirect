import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        DBConnection.getConnection();
        // Puts GUI on Event Dispatch Thread, which makes GUI using swing thread safe and avoid race around condition
        SwingUtilities.invokeLater(() -> new FarmDirectGUI().setVisible(true));
        System.out.println("App Started");
    }
}
