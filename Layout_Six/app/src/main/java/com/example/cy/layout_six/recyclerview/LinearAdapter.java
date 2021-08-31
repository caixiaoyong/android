package com.example.cy.layout_six.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cy.layout_six.R;

import java.util.List;

/**
 * @author cy
 * @date 21-1-12.
 */

public class LinearAdapter extends RecyclerView.Adapter <LinearAdapter.LinearViewHolder>{
    private Context mcontext;
    private  LayoutInflater mLayoutInflater;
    /**
     *     private List<String> list;,实际开发将会传入列别数据list
     */
    public LinearAdapter(Context context){//public LinearAdapter(Context context,List)
        this.mcontext=context;
        mLayoutInflater=LayoutInflater.from(mcontext);
    }
    @Override
    public LinearAdapter.LinearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LinearViewHolder(mLayoutInflater.inflate(R.layout.layout_linear_item,parent,false));
    }

    @Override
    public void onBindViewHolder(LinearAdapter.LinearViewHolder holder, int position) {

        holder.textView.setText("Hello World!");
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mcontext, "click...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 30;//return list;list长度
    }
    class LinearViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        public LinearViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv_title);
        }
    }
}
