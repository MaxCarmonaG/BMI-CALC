package co.edu.unab.BmiCalc.recycler;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import co.edu.unab.BmiCalc.R;

public class ViewHolderRecords extends RecyclerView.ViewHolder {
    private final TextView txtDate;
    private final TextView txtWeight;
    private final TextView txtHeight;
    private final TextView txtBmi;
    private final ImageButton detailsBtn;

    public ViewHolderRecords(@NonNull View itemView) {
        super(itemView);
        txtDate = (TextView) itemView.findViewById(R.id.recordDate);
        txtWeight = (TextView) itemView.findViewById(R.id.recordWeight);
        txtHeight = (TextView) itemView.findViewById(R.id.recordHeight);
        txtBmi = (TextView) itemView.findViewById(R.id.recordBmi);
        detailsBtn = (ImageButton) itemView.findViewById(R.id.detailsBtn);

    }

    public TextView getTxtDate() {
        return txtDate;
    }

    public TextView getTxtWeight() {
        return txtWeight;
    }

    public TextView getTxtHeight() {
        return txtHeight;
    }

    public TextView getTxtBmi() {
        return txtBmi;
    }

    public ImageButton getDetailsBtn() {
        return detailsBtn;
    }
}
