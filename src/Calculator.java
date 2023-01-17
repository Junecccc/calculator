
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * 基于AWT计算器的具体实现
 */
public class Calculator {

    private JFrame frame;

    private JTextField textField;

    private JButton[] button;

    private JPanel panel;

    /**
     * data：当前输入的数据
     */
    private String data = "";

    /**
     * isLeftAvailable：判断数据应该向哪一个操作数中存储
     */
    private boolean isLeftAvailable;

    /**
     * left, right：左右操作数
     */
    private double left, right;

    private String prevOperator = "";

    /**
     * 文件写入操作
     */
    // 创建File文件，若文件不存在则自动创建
    String filePath = "D:\\H.txt";
   // String filePath = "D:\\H.txt";
    // 创建文件输出流，追加模式
    FileOutputStream fileOutputStream = new FileOutputStream(filePath, true);
    // 创建临时存储数组
    ArrayList<String> strings = new ArrayList<>();
    // 创建工具类对象
    ExpressionUtils expressionUtils = new ExpressionUtils();

    public Calculator() throws IOException {

    }

    /**
     * init：初始化 frame
     */
    public void init() throws IOException {
        setMyFrame();
        setMyTextField();
        setMyButton();
        display();
    }

    /**
     * setMyFrame：
     *
     * @description: 设置窗体
     */
    private void setMyFrame() {
        frame = new JFrame();

        // 设置 frame 的坐标
        frame.setLocation(700, 150);
        // 设置 frame 的大小
        frame.setSize(450, 540);
        // 设置 frame 的标题
        frame.setTitle("计算器");
        // 禁用窗口大小调整
        frame.setResizable(false);
        // 设置布局，自定义
        frame.setLayout(null);
        // 关闭窗体
    }


    /**
     * setMyTextField：
     *
     * @description: 设置文本域
     */
    private void setMyTextField() {
        textField = new JTextField("0");

        // 设置文本狂大小位置
        textField.setBounds(20, 15, 400, 60);
        // 设置文本框字体
        textField.setFont(new Font("黑体", Font.BOLD, 35));
        // 设置背景颜色
        textField.setBackground(new Color(230, 230, 250));

        frame.add(textField);
    }

    /**
     * setMyButton：
     *
     * @description: 设置按键事件
     */
    private void setMyButton() {
        // 按钮文本
        String[] arr =
                {"del", "cls", "H", "/",
                        "7", "8", "9", "*",
                        "4", "5", "6", "+",
                        "1", "2", "3", "-",
                        "+/-", "0", ".", "=",};
        // 按钮
        button = new JButton[arr.length];

        // 创建面板
        panel = new JPanel();

        // 设置面板的布局方式
        panel.setBounds(20, 90, 400, 350);

        // 网格布局
        panel.setLayout(new GridLayout(5, 4, 8, 8));

        for (int i = 0; i < button.length; i++) {
            // 创建按钮
            button[i] = new JButton(arr[i]);

            // 设置按钮字体
            button[i].setFont(new Font("黑体", Font.CENTER_BASELINE, 20));
            // 设置按钮背景颜色
            button[i].setBackground(new Color(242, 240, 235));

            // 设置 = 号的特殊颜色
            if (button.length - 1 == i) {
                button[i].setBackground(new Color(211, 120, 129));
            }

            // 添加事件
            int idx = i;
            // 设置鼠标监听
            button[i].addMouseListener(new MouseAdapter() {
                // 点击事件
                @Override
                public void mouseClicked(MouseEvent e) {
                    // 获取按钮上的内容
                    try {
                        click(button[idx].getText());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                // 鼠标进入组件事件
                @Override
                public void mouseEntered(MouseEvent e) {
                    button[idx].setFont(new Font("黑体", Font.CENTER_BASELINE, 35));
                    button[idx].setBackground(new Color(240, 255, 255));
                    button[idx].setForeground(new Color(255, 99, 71));
                }

                // 鼠标离开组件事件
                @Override
                public void mouseExited(MouseEvent e) {
                    button[idx].setFont(new Font("黑体", Font.CENTER_BASELINE, 20));
                    button[idx].setBackground(new Color(242, 240, 235));
                    button[idx].setForeground(new Color(0, 0, 0));
                }
            });

            // 按钮添加到面板
            panel.add(button[i]);
        }

        frame.add(panel);
    }


    public void click(String content) throws IOException {
        String operator = "";
        if ("1".equals(content)) {
            strings.add("1");
            data += "1";
            textField.setText(data);
        } else if ("2".equals(content)) {
            strings.add("2");
            data += "2";
            textField.setText(data);
        } else if ("3".equals(content)) {
            strings.add("3");
            data += "3";
            textField.setText(data);
        } else if ("4".equals(content)) {
            strings.add("4");
            data += "4";
            textField.setText(data);
        } else if ("5".equals(content)) {
            strings.add("5");
            data += "5";
            textField.setText(data);
        } else if ("6".equals(content)) {
            strings.add("6");
            data += "6";
            textField.setText(data);
        } else if ("7".equals(content)) {
            strings.add("7");
            data += "7";
            textField.setText(data);
        } else if ("8".equals(content)) {
            strings.add("8");
            data += "8";
            textField.setText(data);
        } else if ("9".equals(content)) {
            strings.add("9");
            data += "9";
            textField.setText(data);
        } else if ("0".equals(content)) {
            strings.add("0");
            data += "0";
            textField.setText(data);
        } else if (".".equals(content)) {
            strings.add(".");
            data += ".";
            textField.setText(data);
        } else if ("+/-".equals(content)) {
            // 找不到负号，代表这是正数
            if (data.indexOf('-') < 0) {
                strings.add("-");
                data = "-" + data;
            }
            // 负数
            else {
                strings.add("-");
                data = data.substring(1);
            }
            textField.setText(data);
        } else if ("H".equals(content)) {
            new History();
        } else if ("+".equals(content)) {
            strings.add("+");
            operator = "+";
            cal(operator);
        } else if ("-".equals(content)) {
            strings.add("-");
            operator = "-";
            cal(operator);
        } else if ("*".equals(content)) {
            strings.add("*");
            operator = "*";
            cal(operator);
        } else if ("/".equals(content)) {
            strings.add("/");
            operator = "/";
            cal(operator);
        } else if ("=".equals(content)) {
            operator = "=";
            cal(operator);
            // 临时存放数据的数组
            String[] tempString = new String[strings.size()];
            // 判断等号之前是数据是否合法
            for (int i = 0; i < strings.size(); i++) {
                tempString[i] = strings.get(i);
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (String s : tempString) {
                stringBuffer.append(s);
            }
            if (expressionUtils.validateExpression(stringBuffer.toString())) {
                for (int i = 0; i < tempString.length; i++) {
                    // 若数据合法,打印等号前的数据
                    System.out.print(tempString[i]);
                }
                // 数据写入文本
                for (String s : tempString) {
                    fileOutputStream.write(s.getBytes());
                }
                // 清空strings
                strings.clear();
                for (int i = 0; i < tempString.length; i++) {
                    tempString[i] = "";
                }
                // 向strings数组中添加一位等号
                strings.add("=");
                // 向strings数组中添加结果
                strings.add(String.valueOf(left));
                strings.add("\n");
                for (int i = 0; i < strings.size(); i++) {
                    tempString[i] = strings.get(i);
                    // 打印结果
                    System.out.print(tempString[i]);
                }
                // 数据写入文本
                for (String s : tempString) {
                    fileOutputStream.write(s.getBytes());
                }
                // 清空strings
                strings.clear();
                for (int i = 0; i < tempString.length; i++) {
                    tempString[i] = "";
                }
            } else {
                // 清空strings
                strings.clear();
                for (int i = 0; i < tempString.length; i++) {
                    tempString[i] = "";
                }
                System.out.println("输入不合法，请重新输入！");
            }
        } else if ("del".equals(content)) {
            // 删除一位
            if (data.length() != 0) {
                data = data.substring(0, data.length() - 1);
                // 移除数组中最后一位
                strings.remove(strings.size() - 1);
            }
            textField.setText(data);
        } else if ("cls".equals(content)) {
            // 删除一行，移除数组中的数据
            strings.clear();
            data = "";
            isLeftAvailable = false;
            textField.setText(data);
        }
    }

    /**
     * @method: display
     * @description: 设置 frame
     * @note: 如果把 display 函数内的语句写在 setMyFrame 方法中，frame 中显示不出来 label
     * 且需要点击一下 文本域 后才能显示文本域
     */
    public void display() {
        // 设置关闭 frame 窗口时退出程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置 frame 可见，默认值为 false
        frame.setVisible(true);
    }

    private void cal(String operator) throws IOException {
        // 如果 data 为空，不执行任何操作
        if ("".equals(data)) {
            // 用户只要输入 = ，无论前面的输入是否合法，结束此轮计算
            if ("=".equals(operator)) {
                isLeftAvailable = false;
            }
            return;
        }

        /**
         * 第一次把 data 中的数据赋给 left
         * 此后，每次运算都将 right 与 left 运算的结果放入 left 中，并显示 left
         * 运算符 = 使用后可以重新向 left 中输入值
         * 每次获取到 data 中的值后清空 data
         *
         * 每次运算使用上一次传入的运算符，等号出现重新开始新的计算
         */

        if (isLeftAvailable) {
            right = Double.parseDouble(data);
            data = "";

            // 使用前一个运算符
            if ("+".equals(prevOperator)) {
                left += right;
            } else if ("-".equals(prevOperator)) {
                left -= right;
            } else if ("*".equals(prevOperator)) {
                left *= right;
            } else if ("/".equals(prevOperator)) {
                left /= right;
            }

            // 如果运算结果为整数，就用整数输出
            if (left == (int) left) {
                left = (int) left;
            }

        } else {
            left = Double.parseDouble(data);
            // 清空 data
            data = "";
            isLeftAvailable = true;
        }

        // 将运算结果转换为字符串
        String result = left + "";

        // "=" 操作符清理右操作数，重新开始计算
        if ("=".equals(operator)) {
            isLeftAvailable = false;
        }
        // 更新运算符
        prevOperator = operator;
        textField.setText(operator +" "+  textFormat(result)  );
    }

    private String textFormat(String s) {
        // 创建一个格式化数字的对象
        NumberFormat nf = NumberFormat.getInstance();
        // 格式化结果
        String formatResult = nf.format(Double.parseDouble(s));
        return formatResult;
    }
}
