package slam.sio.llb.fr.suivivisitesgsb.metier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import slam.sio.llb.fr.suivivisitesgsb.R;
import slam.sio.llb.fr.suivivisitesgsb.metier.Visite;

/**
 * Created by Orsucciu on 31/03/2016.
 */
public class VisiteAdapter extends BaseAdapter{

    private List<Visite> listeVisites;
    private LayoutInflater layoutInflater;

    public VisiteAdapter(Context c, List<Visite> lstVisite) {
        layoutInflater = LayoutInflater.from(c);
        listeVisites = lstVisite;
    }

    @Override
    public int getCount() {


        return this.listeVisites.size();
    }

    @Override
    public Object getItem(int position) {

        return this.listeVisites.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    private class ViewHolder {
        TextView textViewNom;
        TextView textViewPrenom;
        TextView textViewAdresse;
        TextView textViewTel;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.ligne_visite, null);
            holder.textViewNom = (TextView) convertView.findViewById(R.id.vueNom);
            holder.textViewPrenom = (TextView) convertView.findViewById(R.id.vuePrenom);
            holder.textViewAdresse = (TextView) convertView.findViewById(R.id.vueAdresse);
            holder.textViewTel = (TextView) convertView.findViewById(R.id.vueTel);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Affichage des valeurs dans les emplacements récupérés précédemment
        holder.textViewNom.setText(listeVisites.get(position).getNom());
        holder.textViewPrenom.setText(listeVisites.get(position).getPrenom());
        holder.textViewAdresse.setText(listeVisites.get(position).getAdresse());
        holder.textViewTel.setText(listeVisites.get(position).getTel());

        // Colorie en bleu les lignes dont la visite a pu être effectuée
        // A compléter

        return convertView;
    }
}
