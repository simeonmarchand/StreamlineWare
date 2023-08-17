import javax.swing.*;

public class AppFrame extends JFrame {
    private JTabbedPane tabbedPane;

    public AppFrame() {
        setTitle("StreamlineWare");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Add", new AddTab());
        //tabbedPane.addTab("Remove", new RemoveTab());
        //tabbedPane.addTab("Edit", new EditTab());
        //tabbedPane.addTab("Search", new SearchTab());
        //tabbedPane.addTab("Reports", new ReportsTab());
        // Add other tabs here

        add(tabbedPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AppFrame();
        });
    }
}
