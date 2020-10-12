package CSV.GUI;

import CSV.TransactionAnalyzer.TransactionAnalyser;

import javax.swing.*;

public class Window extends JFrame implements Checking {
    private JLabel inputFile = new JLabel("Input *.csv");
    private JLabel statistic = new JLabel("Statistic:");
    private JLabel fromDate = new JLabel("From date:");
    private JLabel toDate = new JLabel("To date:");
    private JLabel merchant = new JLabel("Merchant");
    private JTextField input = new JTextField();
    private JTextField fromDateIn=new JTextField("DD/MM/YYYY hh:mm:ss");
    private JTextField toDateIn = new JTextField("DD/MM/YYYY hh:mm:ss");
    private JTextField merchantIn = new JTextField();
    private JButton select = new JButton("...");
    private JButton analizing = new JButton("Analyz");
    private JTextArea output = new JTextArea(20,10);
    private JFileChooser fileChooser = new JFileChooser();

    private final String[][] FILTERS = {{"csv", "Файлы csv (*.csv)"}};
    private TransactionAnalyser transactionAnalyser;

    public Window(){
        setTitle("The Transaction Analyser");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,600);
        setLocationRelativeTo(null);
        setLayout(null);

        inputFile.setBounds(54,73,103,26);
        input.setBounds(168,73,149,25);
        select.setBounds(328,73,24,25);
        fromDate.setBounds(54,124,103,26);
        fromDateIn.setBounds(168,125,149,25);
        toDate.setBounds(72,166,59,26);
        toDateIn.setBounds(168,167,149,25);
        merchant.setBounds(65,209,74,26);
        merchantIn.setBounds(168,209,149,25);
        analizing.setBounds(197,250,108,25);
        statistic.setBounds(209,287,68,26);
        output.setBounds(40,338,421,200);

        select.addActionListener(e -> {
            fileChooser.setDialogTitle("Select file");
            FileFilterExt eff = new FileFilterExt(FILTERS[0][0],FILTERS[0][1]);
            fileChooser.addChoosableFileFilter(eff);
            int result = fileChooser.showOpenDialog(Window.this);
            // Если директория выбрана, покажем ее в сообщении
            if (result == JFileChooser.APPROVE_OPTION ){
                input.setText(fileChooser.getSelectedFile().toString());
            }

        });
        analizing.addActionListener(e->{
            if (input.getText().equals("")){
                output.setText("Ошибка ввода файла! Выберите файл");
                return;
            }
            if (!input.getText().matches(".+\\.csv")){
                output.setText("Ошибка ввода файла! Выбран файл не типа *.csv");
                return;
            }
            if(!checkDate(fromDateIn.getText())) {
            output.setText("Ошибка ввода fromDate! Необходимо вводить строго по образцу: DD/MM/YYYY hh:mm:ss");
                return;
            }
            if(!checkDate(toDateIn.getText())) {
                output.setText("Ошибка ввода toDate! Необходимо вводить строго по образцу: DD/MM/YYYY hh:mm:ss");
                return;
            }

            if(merchantIn.getText().equals("")){
            output.setText("Ошибка ввода merchant! Введите merchant");
                return;
            }

            transactionAnalyser = new TransactionAnalyser(input.getText(), fromDateIn.getText(),
                    toDateIn.getText(), merchantIn.getText());
            output.setText(transactionAnalyser.statistic());

        });
        input.setEnabled(false);

        add(inputFile);
        add(input);
        add(select);
        add(fromDate);
        add(fromDateIn);
        add(toDate);
        add(toDateIn);
        add(merchant);
        add(merchantIn);
        add(analizing);
        add(statistic);
        add(output);

        setVisible(true);


    }

    @Override
    public boolean checkDate(String date) {
        return date.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d \\d\\d:\\d\\d:\\d\\d");
    }


}
