package src;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResultPanel extends JPanel {
    private List<String> words;
    private double times;
    private int nodes;
    private long memory;
    private String algorithm;
    public ResultPanel() {
    }

    public void setAlgorithm(String alg){
        this.algorithm = alg;
    }

    public void setWords(List<String> result){
        this.words = result;
    }

    public void setTimes(double times){
        this.times = times;
    }

    public void setNodes(int nodes){
        this.nodes = nodes;
    }

    public void setMemory(long memory){
        this.memory = memory;
    }
    public void ShowResult() {
        setLayout(new BorderLayout());
    
        // Header
        JLabel headerLabel = new JLabel("RESULT", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(headerLabel, BorderLayout.NORTH);
        JLabel algLabel = new JLabel(
            algorithm, SwingConstants.CENTER
        );
        algLabel.setFont(new Font("Arial", Font.BOLD, 18));
        // Details
        JPanel detailsPanel = new JPanel(new GridLayout(5, 1));
        JLabel timesLabel = new JLabel("Times: " + times + " Seconds");
        
        timesLabel.setFont(timesLabel.getFont().deriveFont(Font.BOLD, 16));
        JLabel nodesLabel = new JLabel("Nodes: " + nodes);
        nodesLabel.setFont(nodesLabel.getFont().deriveFont(Font.BOLD, 16));
        JLabel memoryLabel = new JLabel("Memory Usage: " + memory + " Byte");
        memoryLabel.setFont(memoryLabel.getFont().deriveFont(Font.BOLD, 16));
        
        JLabel PathLengthLabel = new JLabel();
        if(words != null){
            PathLengthLabel.setText("Path Length: " + words.size());
        }else{
            PathLengthLabel.setText("Path Length: " + 0);
        }
        PathLengthLabel.setFont(PathLengthLabel.getFont().deriveFont(Font.BOLD, 16));
        detailsPanel.add(algLabel);
        detailsPanel.add(timesLabel);
        detailsPanel.add(nodesLabel);
        detailsPanel.add(memoryLabel);
        detailsPanel.add(PathLengthLabel);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        // Words Panel
        JPanel wordsPanel = new JPanel();
        if(words != null){
            wordsPanel.setLayout(new BoxLayout(wordsPanel, BoxLayout.Y_AXIS));
            for (String word : words) {
                JPanel wordPanel = new JPanel(new GridLayout(1, word.length()));
                for (int i = 0; i < word.length(); i++) {
                    Square square = new Square(Character.toString(word.charAt(i)), i);
                    wordPanel.add(square);
                }
                wordsPanel.add(wordPanel);
            }
        
        }else{
            JLabel emptyResult = new JLabel(
                "Result Not Found", SwingConstants.CENTER
            );
            emptyResult.setFont(new Font("Arial", Font.BOLD, 18));
            wordsPanel.add(emptyResult);
        }
        // Scroll Panel
        JScrollPane scrollPanel = new JScrollPane(wordsPanel);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    
        // Container Panel to hold detailsPanel and scrollPanel
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(detailsPanel, BorderLayout.NORTH);
        containerPanel.add(scrollPanel, BorderLayout.CENTER);
    
        // Add containerPanel to the center of BorderLayout
        add(containerPanel, BorderLayout.CENTER);
        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);
        emptyPanel.setPreferredSize(new Dimension(0,40));
        add(emptyPanel, BorderLayout.SOUTH);

    }

    private class Square extends JPanel {
        private String letter;
        private int currentIdx;

        public Square(String letter, int idx) {
            this.letter = letter;
            this.currentIdx = idx;
            setPreferredSize(new Dimension(50, 50));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Font font = new Font("Arial", Font.BOLD, 20);
            g2d.setFont(font);
            String lastWord = words.get(words.size() - 1);

            if (lastWord.length() == words.get(0).length()) {
                if (lastWord.charAt(currentIdx) == letter.charAt(0)) {
                    setBackground(Color.BLACK);
                    g2d.setColor(Color.WHITE);
                } else {
                    setBackground(Color.WHITE);
                    g2d.setColor(Color.BLACK);
                }
            }
        
            int stringWidth = g2d.getFontMetrics().stringWidth(letter);
            int stringHeight = g2d.getFontMetrics().getAscent();
            int x = (getWidth() - stringWidth) / 2;
            int y = (getHeight() + stringHeight) / 2;
            g2d.drawString(letter.toUpperCase(), x, y);
        }
    }
}
