package tk.roaimsapp.noxml;

import android.widget.LinearLayout;
import android.content.Context;
import android.widget.Button;
import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;
import android.widget.TextView;
import android.graphics.Color;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

public class MainLayout extends LinearLayout
{
    private LinearLayout layBt;
    public LinearLayout.LayoutParams lp;
    public Button btBt,btTv,btEt;
    public TextView mTv;
    private LinearLayout.LayoutParams btParams;
    
    public static final int ID = 9000000;
    private static final CharSequence WelcomeText="Welcome! This is an example app to show you how to create a view at runtime without using xml codes. For more detail please tap info icon at the top right corner.";
    
    public MainLayout(Context context){
        super(context);
        lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        super.setLayoutParams(lp);
        super.setOrientation(LinearLayout.VERTICAL);
        super.setGravity(Gravity.CENTER|Gravity.TOP);
        mTv = new TextView(context);
        mTv.setTextColor(Color.parseColor("#00aaff"));
        mTv.setTextSize(20);
        mTv.setText(WelcomeText);
        mTv.setHorizontallyScrolling(true);
        mTv.setSingleLine(true);
        mTv.setSelected(true);
        mTv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        mTv.setMovementMethod(new ScrollingMovementMethod());
        super.addView(mTv);
        layBt=new LinearLayout(context);
        layBt.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams wLp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layBt.setLayoutParams(wLp);
        layBt.setGravity(Gravity.CENTER);
        btParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,1);
        btBt=new Button(context);
        btBt.setLayoutParams(btParams);
        btBt.setTextSize(15);
        btBt.setText("Add Button");
        btBt.setId(ID);
        btTv = new Button(context);
        btTv.setLayoutParams(btParams);
        btTv.setTextSize(15);
        btTv.setText("Add TextView");
        btTv.setId(ID+1);
        btEt = new Button(context);
        btEt.setLayoutParams(btParams);
        btEt.setTextSize(15);
        btEt.setText("Add EditText");
        btEt.setId(ID+2);
        layBt.addView(btBt);
        layBt.addView(btTv);
        layBt.addView(btEt);
        super.addView(layBt);
    }
    
    public View lastView(){
       int pos = this.getChildCount()-1;
        if(pos>1){
            return this.getChildAt(pos);
        }
        return null;
    }
    
    public void clearLast(){
        int pos = this.getChildCount()-1;
        if(pos>1){
            this.removeViewAt(pos);
        }
    }
    
    public void clearAll(){
        super.removeAllViews();
        super.addView(mTv);
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
