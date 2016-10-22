package cn.edu.gdmec.s07150831.heighcalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class HeightCalculatorActivuty extends AppCompatActivity {

    //体重
    private EditText weightEditText;
    //男选框
    private CheckBox manCheckBox;
    //女选框
    private CheckBox womanCheckBox;
    //计算按钮
    private Button calculatorButton;
    //结果显示
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        //UI控件实例化
        weightEditText = (EditText) findViewById(R.id.weight);
        manCheckBox = (CheckBox) findViewById(R.id.man);
        womanCheckBox = (CheckBox) findViewById(R.id.woman);
        calculatorButton = (Button) findViewById(R.id.calculator);
        resultTextView = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //执行按钮点击事件
        registerEvent();
    }

    //注册按钮点击事件
    public void registerEvent(){
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否有输入体重
                if (!weightEditText.getText().toString().trim().equals("")){
                    //获取体重信息
                    Double weight = Double.parseDouble(weightEditText.getText().toString());
                    //输出信息
                    StringBuffer sb = new StringBuffer();
                    sb.append("------评估结果-------- \n");
                    //判断是否选择男女
                    if (manCheckBox.isChecked() || womanCheckBox.isChecked()){

                        if (manCheckBox.isChecked()){//选择男
                            sb.append("男性标准身高：");
                            double result = evaluateHeight(weight,"男");
                            sb.append((int)result + "厘米");
                        }else if (womanCheckBox.isChecked()){//选择女
                            sb.append("女性标准身高：");
                            double result = evaluateHeight(weight,"女");
                            sb.append((int)result + "厘米");
                        }
                        //显示结果
                        resultTextView.setText(sb.toString());
                    }else{
                        //提示选择男女
                        String message = "请选择男或女";
                        showMessage(message);
                    }
                }else{
                    //提示输入体重
                    String message = "请输入体重";
                    showMessage(message);
                }
            }
        });
    }

    //计算公式
    public double evaluateHeight( double weight,String sex){
        double height = 0;
        if (sex == "男"){
            height = 170 - (62 - weight)/0.6;
        }
        if (sex == "女") {
            height = 158 - (52 - weight)/0.5;
        }
        return height;
    }

    //提示框
    public void showMessage(String message){
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统提示");
        alert.setMessage(message);
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

    //创建选项菜单

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        menu.add(0,1,0,"退出");
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
