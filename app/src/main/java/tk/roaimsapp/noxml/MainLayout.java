package tk.roaimsapp.noxml;

import android.widget.LinearLayout;
import android.content.Context;
import android.widget.Button;
import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;

public class MainLayout extends LinearLayout
{
    public LinearLayout.LayoutParams lp;
    public Button btBt,btTv,btEt;
    private LinearLayout.LayoutParams btParams;

    public static final int ID = 9000000;
    
    
    public MainLayout(Context context){
        super(context);
        lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        super.setLayoutParams(lp);
        super.setOrientation(LinearLayout.VERTICAL);
        super.setGravity(Gravity.CENTER|Gravity.TOP);
        LinearLayout layBt=new LinearLayout(context);
        layBt.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams wLp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layBt.setLayoutParams(wLp);
        layBt.setGravity(Gravity.CENTER);
        btParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,1);
        btBt=new Button(context);
        btBt.setLayoutParams(btParams);
        btBt.setText("Add Button");
        btBt.setId(ID);
        btTv = new Button(context);
        btTv.setLayoutParams(btParams);
        btTv.setText("Add TextView");
        btTv.setId(ID+1);
        btEt = new Button(context);
        btEt.setLayoutParams(btParams);
        btEt.setText("Add EditText");
        btEt.setId(ID+2);
        layBt.addView(btBt);
        layBt.addView(btTv);
        layBt.addView(btEt);
        super.addView(layBt);
    }
    
    public Button getBtBt(){
        return btBt;
    }
    public Button getBtTv(){
        return btTv;
    }
    public Button getBtEt(){
        return btEt;
    }
}
