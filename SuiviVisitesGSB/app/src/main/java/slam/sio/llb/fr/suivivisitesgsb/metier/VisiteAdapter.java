package slam.sio.llb.fr.suivivisitesgsb.metier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;
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
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
