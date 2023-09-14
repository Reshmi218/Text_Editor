import javax.imageio.IIOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //Declaring properties of text-editor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    // file menu item
    JMenuItem newFile, saveFile, openFile;
    // Edit menu item
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;

    TextEditor(){
        //Initialize a frame
        frame = new JFrame();
        // Initialize a menubar
        menuBar = new JMenuBar();
        // Initialize text area
        textArea = new JTextArea();
        //Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");
        // Initialize menuItem
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        // add new item to file menu
        file.add(newFile);
        file.add(saveFile);
        file.add(openFile);
        // add ActionListener
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        // Initialize Edit menu
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        // add action Listener for edit menu
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //Adding to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
        //Add file
        menuBar.add(file);
        menuBar.add(edit);
        // set menuBar to frame
        frame.setJMenuBar(menuBar);
        // Add textArea
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        panel.add(textArea, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        frame.add(panel);
        // set dimensions of frame
        frame.setBounds(100, 100, 400, 400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public  void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cut) {
            // perform cut operation
            textArea.cut();
        }
        if (actionEvent.getSource() == copy) {
            // perform copy operation
            textArea.copy();
        }
        if (actionEvent.getSource() == paste) {
            // perform paste operation
            textArea.paste();
        }
        if (actionEvent.getSource() == selectAll) {
            // perform selectAll operation
            textArea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            // perform close operation
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();
                try{
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    while ((intermediate= bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    textArea.setText(output);
                }
                catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                String filePath = file.getPath();
                try{
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate ="", output ="";
                    while ((intermediate=bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                }
                catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}