package contacts.xiaozuzu.github.io.contactsbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import java.util.List;

import androidbasicutilproject.xiaozuzu.github.io.xzzandroidsupport.utils.LogUtil;
import contacts.xiaozuzu.github.io.contactsbook.R;
import contacts.xiaozuzu.github.io.contactsbook.model.Contact;
import contacts.xiaozuzu.github.io.contactsbook.util.ColorUtil;

public class SortAdapter extends BaseAdapter implements SectionIndexer {
    private List<Contact> list = null;
    private Context mContext;

    public SortAdapter(Context mContext, List<Contact> list) {
        this.mContext = mContext;
        this.list = list;
        LogUtil.setTag("SortAdapter");
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup arg2) {
        ViewHolder viewHolder = null;
        final Contact mContent = list.get(position);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.contact_list_item, null);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.name);
            viewHolder.tvLetter = (TextView) view.findViewById(R.id.letter);
            viewHolder.tvHeader = (TextView)view.findViewById(R.id.header_icon);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        int section = getSectionForPosition(position);

        if (position == getPositionForSection(section)) {
            viewHolder.tvLetter.setVisibility(View.VISIBLE);
            viewHolder.tvLetter.setText(mContent.getSortLetters());
        } else {
            viewHolder.tvLetter.setVisibility(View.GONE);
        }

        viewHolder.tvTitle.setText(this.list.get(position).getName());
        viewHolder.tvHeader.setText(this.list.get(position).getName().subSequence(0, 1).toString());
        viewHolder.tvHeader.setBackgroundColor(this.list.get(position).getHeaderColor());
        return view;

    }


    final static class ViewHolder {
        TextView tvLetter;
        TextView tvTitle;
        TextView tvHeader;
    }


    public int getSectionForPosition(int position) {
        return list.get(position).getSortLetters().charAt(0);
    }


    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }


    @Override
    public Object[] getSections() {
        return null;
    }
}