package apps_dohomeapp.dohome.co.th.sqlserver.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import apps_dohomeapp.dohome.co.th.sqlserver.MODEL._Model_User;
import apps_dohomeapp.dohome.co.th.sqlserver.R;

public class Adapter_User extends RecyclerView.Adapter<Adapter_User.holder> {

    ArrayList<_Model_User> list_User;
    Context context;

    public Adapter_User(ArrayList<_Model_User> list_User, Context context) {
        this.list_User = list_User;
        this.context = context;
    }


    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user,viewGroup,false);
        holder holder = new holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int i) {

        _Model_User user = list_User.get(i);

        int position = i + 1;

        holder.tv_Position_Count.setText(String.valueOf(position));
        holder.tv_Employee_Code.setText("Code : " + user.EmployeeId + "   Name : " + user.Fullname + "   Nickname : " + user.Nickname);
        holder.tv_Employee_Tel.setText("Tel : " + user.Telephone);
        holder.tv_Employee_Email.setText("Email : " + user.Email);
        holder.tv_Employee_Jobkey.setText("Jobkey : " + user.JobKeyId + " - " + user.JOB_NAME);
        holder.tv_Employee_Position.setText("Position : " + user.PositionId  + " - " + user.POSOTION_NAME);
        holder.tv_Employee_Department.setText("Department : " + user.DepartmentId + " - " + user.DEPARTMENT_NAME);
        holder.tv_Employee_PersonnelArea.setText("PersonnelArea : " + user.PersonnelAreaId + " - " + user.BRANCH_NAME);
        holder.tv_Employee_Address.setText("Address Dohome : " + user.ADDRESS_DOHOME);
    }

    @Override
    public int getItemCount() {
        if(list_User != null)
            return list_User.size();
        return 0;
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView tv_Employee_Code,tv_Employee_Tel,tv_Employee_Email,tv_Employee_Jobkey
                ,tv_Employee_Position,tv_Employee_Department,tv_Employee_PersonnelArea,tv_Employee_Address,tv_Position_Count;

        public holder(@NonNull View itemView) {
            super(itemView);

            tv_Position_Count = itemView.findViewById(R.id.tv_Position_Count);
            tv_Employee_Code = itemView.findViewById(R.id.tv_Employee_Code);
            tv_Employee_Tel = itemView.findViewById(R.id.tv_Employee_Tel);
            tv_Employee_Email = itemView.findViewById(R.id.tv_Employee_Email);
            tv_Employee_Jobkey = itemView.findViewById(R.id.tv_Employee_Jobkey);
            tv_Employee_Position = itemView.findViewById(R.id.tv_Employee_Position);
            tv_Employee_Department = itemView.findViewById(R.id.tv_Employee_Department);
            tv_Employee_PersonnelArea = itemView.findViewById(R.id.tv_Employee_PersonnelArea);
            tv_Employee_Address = itemView.findViewById(R.id.tv_Employee_Address);
        }
    }
}
