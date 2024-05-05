package src;
import javax.swing.*;
import java.awt.*;
import java.util.List;
public class GUI extends JFrame {
    private JTextField startWordField;
    private JTextField endWordField;
    private boolean isResultOk = false;
    private ResultPanel resultPanel;
    private JPanel mainPanel;
    

    private void createResultPanel(List<String> result, double time, int nodes) {
        // Create or update the result panel based on the algorithm results
        // For demonstration purposes, let's assume resultPanel is instantiated here
        if (resultPanel != null) {
            mainPanel.remove(resultPanel);
        }
        resultPanel = new ResultPanel();
        // Add the resultPanel to the mainPanel
        resultPanel.setWords(result);
        resultPanel.ShowResult();
        System.out.println("Result Panel Created");

        mainPanel.add(resultPanel, BorderLayout.CENTER);
        // Refresh the GUI to reflect the changes
        
        revalidate();
        repaint();
        pack();
    }

    private Algorithm AlgortihmDecision(int AlgorithmType, String startword, String endword){
        switch (AlgorithmType) {
            case 1: // UCS
                return new UCS(startword, endword);
                
            case 2: // GREEDY 
                return new GreedyBFS(startword, endword);
                

            case 3: // ASTAR
                return new Astar(startword, endword);
        }
        return null;
    }

    private void RunAlgorithm(int AlgorithmType){
        String startWord = startWordField.getText().trim();
        String endWord = endWordField.getText().trim();

        isResultOk  = InputManager.CheckUserInput(startWord, endWord);
        long startTime = System.nanoTime();
        if(isResultOk){
            Algorithm alg = AlgortihmDecision(AlgorithmType, startWord, endWord);
            List<String> result = alg.GetResult();
            int nodes = alg.GetNodeCount();
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;
            double elapsedTimeInSeconds = elapsedTime / 1_000_000_000.0; 
            createResultPanel(result, elapsedTimeInSeconds, nodes);
        }
    }

    public GUI() {
        super("Wordladder");
        WordChecker.load();
        
    }

    public void ShowGUI(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        isResultOk = false;
        this.resultPanel = new ResultPanel();

        this.mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Header
        
        gbc.gridy++;
        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);
        inputPanel.add(emptyPanel);

        gbc.gridx++;
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel header = new JLabel("WORD LADDER", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.PLAIN, 60));
        headerPanel.add(header, BorderLayout.CENTER);
        inputPanel.add(headerPanel);

        gbc.anchor = GridBagConstraints.WEST;
        // Start Word Text Area
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("Start Word:"), gbc);
        gbc.gridx++;
        startWordField = new JTextField(15);
        inputPanel.add(startWordField, gbc);

        // End Word Text Area
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(new JLabel("End Word:"), gbc);
        gbc.gridx++;
        endWordField = new JTextField(15);
        inputPanel.add(endWordField, gbc);

        // Algorithm Selection Header
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        inputPanel.add(new JLabel("Algorithm to use:"), gbc);

        // Algorithm Selection Buttons
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.insets = new Insets(10, 5, 5, 5);
        JButton ucsButton = new JButton("UCS");
        JButton aStarButton = new JButton("A*");
        JButton greedyBFSButton = new JButton("Greedy BFS");
        inputPanel.add(ucsButton, gbc);
        gbc.gridx++;
        inputPanel.add(aStarButton, gbc);
        gbc.gridx++;
        inputPanel.add(greedyBFSButton, gbc);

        // Result Text Area
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        

        // Action Listeners 
        ucsButton.addActionListener(e -> {
            RunAlgorithm(1);
            // Perform action for UCS button
        });

        aStarButton.addActionListener(e -> {
            RunAlgorithm(3);
            // Perform action for A* button
        });

        greedyBFSButton.addActionListener(e -> {
            RunAlgorithm(2);
            // Perform action for Greedy BFS button
        });

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        add(mainPanel);
        pack();
        setVisible(true);
    }
}
