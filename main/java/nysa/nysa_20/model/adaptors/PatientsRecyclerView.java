package nysa.nysa_20.model.adaptors;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import nysa.nysa_20.R;
import nysa.nysa_20.activity.PersonalFileActivity;
import nysa.nysa_20.model.Account;
import nysa.nysa_20.model.AccountHolder;


public class PatientsRecyclerView extends RecyclerView.Adapter<PatientsRecyclerView.ViewHolder> {


    public Context mContext;
    public List<Account> patients;

    public PatientsRecyclerView(Context mContext) {
        this.mContext = mContext;
        patients = AccountHolder.getAccount().getPatients();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_row_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Account positionData = patients.get(position);
        holder.namePatient.setText(positionData.getFirstName() + " " + positionData.getLastName());
        holder.mailPatient.setText(positionData.getEmail());

        holder.Container.setOnClickListener(ev -> itemClicked(position));
        holder.namePatient.setOnClickListener(ev -> itemClicked(position));
        holder.mailPatient.setOnClickListener(ev -> itemClicked(position));

    }

    private void itemClicked(int position) {
        Intent toPersonalFile = new Intent(mContext, PersonalFileActivity.class);
        toPersonalFile.putExtra("PatientPosition",position);
        mContext.startActivity(toPersonalFile);
    }


    @Override
    public int getItemCount() {
        return patients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namePatient;
        TextView mailPatient;
        ConstraintLayout Container;

        public ViewHolder(View itemView) {
            super(itemView);
            Container = itemView.findViewById(R.id.patientsContainer);
            namePatient = itemView.findViewById(R.id.namePatientTextViewItem);
            mailPatient = itemView.findViewById(R.id.emailPatientTextViewItem);
        }
    }
}
