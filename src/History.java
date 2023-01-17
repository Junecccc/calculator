import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 历史记录
 */
public class History extends JFrame implements ActionListener {

    // 这里为保存的历史记录文本文件的全路径名
    String filename = "D:\\H.txt";
    JTextArea jta = new JTextArea(5, 3);

    JScrollPane jsp = new JScrollPane(jta);

    JButton jb = new JButton("点击查看");

    JPanel jp = new JPanel();

    History() {

        setTitle("历史记录");

        jb.addActionListener(this);

        jp.add(jb);

        add(jsp, BorderLayout.CENTER);

        add(jp, BorderLayout.SOUTH);

        setSize(300, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setVisible(true);

    }
    @Override

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jb) {

            readFile(filename);

        }

    }

    public void readFile(String filename2) {

        FileReader fr = null;

        BufferedReader br = null;

        try {

            fr = new FileReader(filename2);

            br = new BufferedReader(fr);

            String str;

            while ((str = br.readLine()) != null) {

                jta.append(str + "\n");

            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                br.close();

                fr.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

}