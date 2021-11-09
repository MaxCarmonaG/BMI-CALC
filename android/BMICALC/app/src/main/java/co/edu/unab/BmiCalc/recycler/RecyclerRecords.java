package co.edu.unab.BmiCalc.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.BmiCalc.model.Record;

public class RecyclerRecords extends RecyclerView.Adapter<ViewHolderRecords> {
    final private Context context;
    final private List<Record> records;
    final private LayoutInflater inflater;
    final private int layout;
    final private IClickListener clickListener;

    public RecyclerRecords(Context context, ArrayList<Record> records, int layout, IClickListener clickListener) {
        this.context = context;
        this.records = records;
        this.inflater = LayoutInflater.from(context);
        this.layout = layout;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolderRecords onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recordView = inflater.inflate(layout, null);
        return new ViewHolderRecords(recordView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecords holder, int position) {
        Record record = records.get(position);
        holder.getTxtDate().setText(record.getDate());
        holder.getTxtWeight().setText(Integer.toString(record.getWeight()));
        holder.getTxtHeight().setText(Integer.toString(record.getHeight()));
        holder.getTxtBmi().setText(String.format("%, .2f", record.getBmi()));
        holder.getDetailsBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(holder.getAbsoluteAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

}
