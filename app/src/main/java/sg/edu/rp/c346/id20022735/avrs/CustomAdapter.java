package sg.edu.rp.c346.id20022735.avrs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<License> verlist;

    public CustomAdapter(Context context, int resource, ArrayList<License> objects){
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        verlist = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvname = rowView.findViewById(R.id.name);
        TextView tvnumber = rowView.findViewById(R.id.contact);
        TextView tvmembership = rowView.findViewById(R.id.membership);
        TextView tvlicense = rowView.findViewById(R.id.license);
        TextView tvschool = rowView.findViewById(R.id.school);
        TextView tvdesignation = rowView.findViewById(R.id.designation);


        // Obtain the Android Version information based on the position
        License currentVersion = verlist.get(position);

        // Set values to the TextView to display the corresponding information
        tvname.setText(currentVersion.getName());
        tvnumber.setText(currentVersion.getNumber());
        tvmembership.setText(currentVersion.getMembership());
        tvlicense.setText(currentVersion.getLicense());
        tvschool.setText(currentVersion.getSchool());
        tvdesignation.setText(currentVersion.getDesignation());


        return rowView;
    }
}
